package com.websocketchat.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import com.websocketchat.jedis.JedisHandleMessage;
import com.websocketchat.model.ChatMessage;
import com.websocketchat.model.State;

/*使用 javax.websocket 套件 實作*/

//拿到端點
@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {
	// ConcurrentHashMap 執行速度更快 不會鎖全部
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	//----------很像servlet----------------//
//建立連線
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		/*放入使用者與他的連線*/
		sessionsMap.put(userName, userSession);
		/*放入所有使用者 */
		Set<String> userNames = sessionsMap.keySet();
		/*包裝資料 */
		State stateMessage = new State("open", userName, userNames);
		/*把資料轉成JSON */
		String stateMessageJson = gson.toJson(stateMessage);
		/*蒐集所有連線 */
		Collection<Session> sessions = sessionsMap.values();
		/*跑回圈把連線拿出 比對如果是OPEN 又對到IP 就給他好友列表 */
		for (Session session : sessions) {
			//javax.websocket ->Interface Session 這的isOpen方法 Return true if and only if the underlying socket is open.
			if (session.isOpen()) {
				//以及Interface Session  這的getAsyncRemote()方法 可以非同步的發送訊息 The completion handlers for the asynchronous methods are always called with a different thread from that which initiated the send.
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}
		
		//測試
		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
//		System.out.println(text);
	}

	@OnMessage
	//參考官方example
	public void onMessage(Session userSession, String message) {
		//gson 用法 .class
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		//把歷史紀錄包成JSON 傳到前端
		if ("history".equals(chatMessage.getType())) {
			//到redis 查出來紀錄
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			//存成JSON 給前端
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			//使用者
			if (userSession != null && userSession.isOpen()) {
				//把對話資料轉JSON
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
//				System.out.println("history = " + gson.toJson(cmHistory));
				return;
			}
		}
		
		//接收者跟使用者都要存對話資料
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
		}
//		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		//更新好友列表 不在線就移除
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}
		//測試
		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
//		System.out.println(text);
	}
}
