<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO");
%>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<meta charset="utf-8">
<style>

	body { 
		background:url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
		background-position: center center;
	}
	
	
	#confirm , #result{
		margin-left: 46%;
	}
	
	
</style>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css/backend.css">
<style>
.container{
	margin-top:150px;
}
</style>
</head>

<body>

	<%@ include file="/back-end/backinclude.jsp"%>
<!-- 主要內文區開始 -->
	<div class="article side-open">
		<!-- logo區開始 -->
		<div id="logo">
			<img src="<%=request.getContextPath()%>/images/backend_public/logo.png" alt="">
		</div>
		<!-- logo區結束 -->

		<!-- ------------------------------------從這裡開始編輯喔各位！----------------------- -->
		<div id="main">
			<h2>忘記密碼</h2>
			<a href="<%=request.getContextPath()%>/back-end/backend_index.jsp">返回首頁</a>
		</div>
		
		<c:if test="${not empty errorMsgs}">
			<a>看看你的錯:</a>
			<br>
			<a><c:forEach var="message" items="${errorMsgs}">${message}</c:forEach></a>
		</c:if>
	<div class="container">
		<form action="<%=request.getContextPath()%>/employee/employee.do" method="post">
		
		<table class="table align-items-center">
			<tr>
				<td>請輸入員工帳號</td>
				<td><input type="text" name="eacc"></td>
			</tr>
			<tr>
				<td>請輸入員工信箱</td>
				<td><input type="email" name="email"></td>
			</tr>
		</table>
				<input type="hidden" name="action" value="forgetPsw">
				<input type="submit" value="送出查詢" class="btn btn-success my-2 my-sm-0" id="confirm">
		</form>
		<br><br>
	</div>	
		<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->
	</div>
	
<script>
	
	$("#confirm").click(function(){
		setTimeout(function(){
			swal('成功','信件已寄出,請耐心等待','success',{button:'確認'})
		},5000);		
	});
	
</script>

</body>
</html>
