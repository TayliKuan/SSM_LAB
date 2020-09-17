package com.websocketchat.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.*;

@ServerEndpoint("/NotifyWS/{userName}")
public class NotifyWS extends ServerEndpointConfig.Configurator {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	 
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);
	}

	 
	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
	}

//	@OnClose
//	public void onClose(Session userSession, CloseReason reason) {
//		connectedSessions.remove(userSession);
//		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
//				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
//	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
