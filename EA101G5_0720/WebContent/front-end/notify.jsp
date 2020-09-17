<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<% 
// String Stuno = (String)session.getAttribute("stuno");
// pageContext.setAttribute("userName",Stuno);

%>
<html lang="en">

<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>前台接收</title>
 
</head>

<body>
 
  <script>
	var MyPoint2 = "/NotifyWS/${userName}";
	var host2 = window.location.host;
	var path2 = window.location.pathname;
	var webCtx2 = path2.substring(0, path2.indexOf('/', 1));
	var endPointURL2 = "ws://" + window.location.host + webCtx2 + MyPoint2;
	console.log("notifyendPointURL="+endPointURL2);
	var webSocket2;

	$(function(){
		connect2();
	});

	function connect2() {
		// create a websocket
		webSocket2 = new WebSocket(endPointURL2);

		webSocket2.onopen = function(event) {
// 			document.getElementById('sendMessage').disabled = false;
// 			document.getElementById('connect').disabled = true;
// 			document.getElementById('disconnect').disabled = false;
			console.log("front - open");
		};

		webSocket2.onmessage = function(event) {
		Swal.fire({
			  icon: 'info',
			  title: '系統通知',
			  text:event.data,
			  footer: '<a href="${pageContext.request.contextPath}/front-end/information/showAllInformation.jsp">前往消息</a>'
			});
		};

		webSocket2.onclose = function(event) {
// 			updateStatus("WebSocket Disconnected");
		};
	}


	function disconnect2() {
		webSocket2.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

</script>
</body>

</html>