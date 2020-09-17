<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.information.model.*"%>

<%
	InformationService infSvc = new InformationService();
	List<InformationVO> inflist = infSvc.getAllInfByDate();
	pageContext.setAttribute("inflist", inflist);
	//推播
	pageContext.setAttribute("userName","abc");
%>

<html>
<head>
<meta charset="UTF-8">
<title>FitMate消息清單</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>FitMate管理後台</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Coming+Soon&family=Noto+Sans+TC:wght@700&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous">
	
</script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous">
	
</script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"
	integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V"
	crossorigin="anonymous">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/css/backend.css">
<style>
.pic {
	height: 140px;
	width: 120px;
}

body {
	background:
		url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
	background-position: center center;
}

#main {
	margin-left: 5px;
}

.side-menu label {
	padding-top:20px;
}

.date {
	width:200px;
}

.type {
	width:100px;
}

.title {
	width:270px;
}

.insert {
	margin-left:95.1%;
}
</style>
</head>
<body  onload="connect2();" onunload="disconnect2();">

	<%@ include file="/back-end/backinclude.jsp"%>

	<!-- 主要內文區開始 -->
	<div class="article side-open">
		<!-- logo區開始 -->
		<div id="logo">
			<img
				src="<%=request.getContextPath()%>/images/backend_public/logo.png"
				alt="">
		</div>
		<!-- logo區結束 -->

		<div id="main">
			<h1>FitMate消息管理</h1>
			<a href="<%=request.getContextPath()%>/back-end/information/showAllInformation.jsp">返回消息首頁</a>
		</div>
		<div class="insert">
			<input type="submit" value="新增消息" onclick="location.href='<%=request.getContextPath()%>/back-end/information/insertOneInformation.jsp'" class="btn btn-warning">
		</div>
	<%@ include file="page1.file"%>
		<div class="table-responsive-sm table-hover table-warning">
<table class="table align-items-center">
	<tr>
		<th>消息編號</th>
		<th>發布日期</th>
		<th>類別</th>
		<th>標題</th>
		<th>內容</th>
	</tr>
<c:forEach var="infVO" items="${inflist}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">	
	<tr>
		<td class="align-middle">${infVO.inno}</td>
		<td class="align-middle date">${infVO.indate}</td>
		<td class="align-middle type">${infVO.intype}</td>
		<td class="align-middle title">${infVO.intitle}</td>
		<td class="align-middle">${infVO.indesc}</td>
		<td class="align-middle">
			<form action="<%=request.getContextPath()%>/information/information.do" method="post" style="margin-bottom: 0px;">
			<input type="submit" value="更新" class="btn btn-outline-primary my-2 my-sm-0">
			<input type="hidden" name="inno" value="${infVO.inno}">
			<input type="hidden" name="action" value="alterOneInf">
			</form>
		</td>
		<td class="align-middle">
			<form action="<%=request.getContextPath()%>/information/information.do" method="post" style="margin-bottom: 0px;">
			<input type="button" value="刪除" class="btn btn-outline-danger my-2 my-sm-0">
			<input type="hidden" name="inno" value="${infVO.inno}">
			<input type="hidden" name="action" value="deleteOneInf">
			</form>
		</td>
		<td class="align-middle">
			
<!-- 			<input type="button" value="推播" class="btn btn-outline-success my-2 my-sm-0"> -->
			<button class="btn btn-outline-success my-2 my-sm-0 sendAD">推播</button>

		</td>
	</tr>
	
</c:forEach>
</table>
</div>
<%@ include file="page2.file"%>
</div>
<script>

$(document).ready(function(){
	$('input:button').on('click',function(e){
		e.preventDefault();
		swal({
			title:'注意',
			text:'你確定要刪除這則消息嗎?',
			icon:'warning',
			buttons:true,
			dangerMode:true
		}).then(function(isConfirm){
			if(isConfirm){
				swal('成功','已將此則消息刪除','success');
				setTimeout(function(){
					$('input:button').parent().submit();
				},1500);
			} else {
				swal('取消','你已經取消刪除作業','error');
			}
		});
	});
});


</script>
 			<script>
				var MyPoint2 = "/NotifyWS/${userName}";
				var host2 = window.location.host;
				var path2 = window.location.pathname;
				var webCtx2 = path2.substring(0, path2.indexOf('/', 1));
				var endPointURL2 = "ws://" + window.location.host + webCtx2 + MyPoint2;
				console.log("showall="+endPointURL2);
				var webSocket2;
			
				function connect2() {
					webSocket2 = new WebSocket(endPointURL2);
			
					webSocket2.onopen = function(event) {
// 						updateStatus("WebSocket Connected");
						console.log("back open");
					};
			
// 					webSocket.onclose = function(event) {
// // 						updateStatus("WebSocket Disconnected");
// 					};
				}
				
				$('.sendAD').on('click', function(){
					var intitle = $(this).parent().parent().children().eq(3).text();
					sendMessage2(intitle);
				});
			 	
				function sendMessage2(intitle) {
					webSocket2.send(intitle);
				}
			
				function disconnect2() {
					webSocket2.close();
				}
	</script>
</body>
</html>