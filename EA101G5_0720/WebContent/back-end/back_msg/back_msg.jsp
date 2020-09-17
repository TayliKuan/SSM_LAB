<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
pageContext.setAttribute("userName","System");

%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>FITMATE 線上客服</title>

<style>
body{
background-color:#f8B300 !important;
}
* {
	margin: 0;
	padding: 0;
	list-style: none;
	font-family: 微軟正黑體;
	box-sizing: border-box;
}

html, body {
	width: 100%;
	height: 100%;
}

 .menu img { 
 	width: 20px; 
 	height: 20px; 
 } 

#conversation .panel {
	padding: 10px;
	margin: 0;
	list-style: none;
	overflow-y: scroll;
	overflow-x: hidden;
	flex-grow: 1;
	border-radius: 4px;
	background: transparent;
}

.floating-chat .chat .panel::-webkit-scrollbar {
	width: 5px;
}

.floating-chat .chat .panel::-webkit-scrollbar-track {
	border-radius: 5px;
	background-color: rgba(25, 147, 147, 0.1);
}

.floating-chat .chat .panel::-webkit-scrollbar-thumb {
	border-radius: 5px;
	background-color: rgba(25, 147, 147, 0.2);
}

#conversation .panel li {
	position: relative;
	clear: both;
	display: inline-block;
	padding: 14px;
	margin: 0 0 20px 0;
	font: 12px/16px 'Noto Sans', sans-serif;
	border-radius: 10px;
	background-color: rgba(0, 0, 147, 0.2);
	word-wrap: break-word;
	max-width: 81%;
}

#conversation .panel li:before {
	position: absolute;
	top: 0;
	width: 25px;
	height: 25px;
	border-radius: 25px;
	content: '';
	background-size: cover;
}

#conversation .panel li:after {
	position: absolute;
	top: 10px;
	content: '';
	width: 0;
	height: 0;
	border-top: 10px solid rgba(0, 0, 147, 0.2);
}
#conversation .panel li.other {
	animation: show-chat-even 0.15s 1 ease-in;
	-moz-animation: show-chat-even 0.15s 1 ease-in;
	-webkit-animation: show-chat-even 0.15s 1 ease-in;
	float: left;
	margin-left: 45px;
	color: blue;
	font-size: 15px;
}

#conversation .panel li.other:before {
	left: -45px;
	background-image: url(<%=request.getContextPath()%>/images/backend_public/usermsg.jpg);
	/*放學員照片*/
}

#conversation .panel li.other:after {
	border-left: 10px solid transparent;
	left: -10px;
}

#conversation .panel li.self {
	animation: show-chat-odd 0.15s 1 ease-in;
	-moz-animation: show-chat-odd 0.15s 1 ease-in;
	-webkit-animation: show-chat-odd 0.15s 1 ease-in;
	float: right;
	margin-right: 45px;
	color: black;
	font-size: 15px;
}

#conversation .panel li.self:before {
	right: -45px;
	background-image: url(<%=request.getContextPath()%>/images/backend_public/backemp.jpg);
	/*放管理員照片*/
}

#conversation .panel li.self:after {
	border-right: 10px solid transparent;
	right: -10px;
}

#statusOutput{
	font-size: 25px;
    margin-top: 0px;
    margin-bottom: 0px; 
}
.column h2{
	font-size: 25px;
    margin-top: 0px;
    margin-bottom: 0px; 
}
#sendMessage{
	margin-top: 10px;
}
</style>

</head>
	<link href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Coming+Soon&family=Noto+Sans+TC:wght@700&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous">
	</script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
	</script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css/backmsg.css">
  <!-- Font Awesome File -->
   	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<body onload="connect();" onunload="disconnect();">
<div class="container app">
    <div class="row app-one">
      <div class="col-sm-4 side">
        <!--左邊好友列表 -->
        <div class="side-one"> 
          <!-- Heading -->
          <div class="row heading">
            <div class="col-sm-8 col-xs-3 heading-avatar">
              <div class="heading-avatar-icon">
              <!-- 放FITMATE-->
                <img src="<%=request.getContextPath()%>/images/core-img/logo.png">
              </div>
            </div>

            <div class="col-sm-2 col-xs-2 heading-compose  pull-right">
              <i class="fa fa-comments fa-2x  pull-right" aria-hidden="true"></i>
            </div>
          </div>

          <div class="row sideBar" id="rowbar"> </div>
        </div>
         <!-- 右邊訊息 歷史訊息區 -->
        <div class="side-two"> </div>
      </div>

      <!-- Conversation Start -->
      <div class="col-sm-8 conversation">
        <div class="row heading">
          <div class="col-sm-8 col-xs-7 heading-name">
            <!-- 選到的名字-->
            <div class="heading-name-meta"><h3 id="statusOutput" class="statusOutput"></h3></div>
          </div>
        </div>
        <!-- Message Box -->
        <div class="row message" id="conversation">
			<div id="messagesArea" class="panel message-area" ></div>
        </div>

        <div class="row reply">
          <div class="col-sm-11 col-xs-9 reply-main">
            <!-- 送出-->
            <textarea  rows="1" id="message" class="text-box" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();"></textarea>
          </div>
         	<button id="sendMessage" type="submit" onclick="sendMessage();">Send</button>
          	<input type="hidden" id="connect" class="button" value="Connect" onclick="connect();" /> 
			<input type="hidden" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" />
        </div>
        <!-- Reply Box End -->
      </div>
      <!-- Conversation End -->
    </div>
    <!-- App One End -->
  </div>

	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js'>
		
	</script>
	<script src="<%=request.getContextPath()%>/js/backend.js"></script>
	<!-- 客服聊天區-->
</body>

<script>
	var MyPoint = "/FriendWS/${userName}";
	console.log("MyPoint="+MyPoint);
	var host = window.location.host;
// 	console.log("host="+host);
	var path = window.location.pathname;
// 	console.log("path="+path);
	var webCtx = path.substring(0, path.indexOf('/', 1));
// 	console.log("webCtx="+webCtx);
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	console.log("sysendPointURL="+endPointURL);
	
	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${userName}';
// 	console.log("self="+self);
	var webSocket;
// 	console.log("webSocket="+webSocket);

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				//收到後端的好友列表 
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'self' : li.className += 'other';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				//可以滑動訊息
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'self' : li.className += 'other';
				li.innerHTML = jsonObj.message;
// 				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				//更新好友列表
				refreshFriendList(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("請輸入回覆訊息");
			inputMessage.focus();
		} else if (friend === "") {
			alert("請選擇對話學員");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var rowbar = document.getElementById("rowbar");
		
		rowbar.innerHTML = '';
		//自動生成 左邊好友列表的一格
		for (var i = 0; i < friends.length; i++) {
			if (friends[i] === self) { continue; }
			
			var add=`<div class="row sideBar-body">
			<div class="col-sm-3 col-xs-3 sideBar-avatar">
            <div class="avatar-icon">
              <img src="<%=request.getContextPath()%>/images/backend_public/usermsg.jpg">
            </div>
          </div>
          
          <div class="col-sm-9 col-xs-9 sideBar-main">
            <div class="row">
              <div class="col-sm-8 col-xs-8 sideBar-name">
              	<div id="row" class="name-meta" >
              	<div id=`;
              	add+=i;
              	add+=' class="column" name="friendName" value=';
              		
             add+=friends[i];
             add+="><h2>"+friends[i]+"</h2></div></div></div></div></div></div>";
			rowbar.innerHTML+=add;
		}
		
		addListener();
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("rowbar");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			//用history去後端查 給self 跟 friend 
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : friend,
					"message" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-2.2.4.min.js"></script>
</html>