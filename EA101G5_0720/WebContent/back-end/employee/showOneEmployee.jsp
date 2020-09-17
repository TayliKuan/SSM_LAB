<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO");
%>
<html>
<head>
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

.pic:hover {
	transform:scale(3,3);
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
			<h1>FitMate員工資料</h1>
			<a href="<%=request.getContextPath()%>/back-end/employee/showAllEmployee.jsp">返回員工列表</a> <a
				href="<%=request.getContextPath()%>/back-end/employee/employee_select_page.jsp">返回員工首頁</a>
		</div>
		<div class="table-responsive-sm table-hover table-warning">
			<table class="table align-items-center">
				<tr>
					<th>員工編號</th>
					<th>員工姓名</th>
					<th>員工帳號</th>
					<th>員工信箱</th>
					<th>雇用日期</th>
					<th>員工照片</th>
					<th>員工權限</th>
				</tr>
				<tr>
					<td class="align-middle">${empVO.empno}</td>
					<td class="align-middle">${empVO.ename}</td>
					<td class="align-middle">${empVO.eacc}</td>
					<td class="align-middle">${empVO.email}</td>
					<td class="align-middle">${empVO.edate}</td>
					<td><img src="<%=request.getContextPath()%>/employee/employeePic.do?empno=${empVO.empno}" class="pic"></td>
					<td class="align-middle">${empVO.esta}</td>
				</tr>
			</table>


			<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->
		</div>
	</div>

</body>
</html>