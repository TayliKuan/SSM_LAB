<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	href="<%=request.getContextPath()%>/css/custom-css/employee/employee_select_page.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/css/backend.css">

<style>
body {
	background:
		url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
	background-position: center center;
}

</style>

</head>

<body>

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

		<!-- ------------------------------------從這裡開始編輯喔各位！----------------------- -->


		<div id="main">
			<img alt="" src="<%=request.getContextPath()%>/images/employee/logo1.png" width="100" height="100"
				border="0">
			<h1>FitMate Employees</h1>
		</div>

		<h3>員工資料查詢</h3>

		<ul>
			<li><a href='showAllEmployee.jsp'>員工列表</a>點這看全部!! <br>
			<br></li>


			<jsp:useBean id="empSvc" scope="page"
				class="com.employee.model.EmployeeService" />

			<li>
				<form action="<%=request.getContextPath()%>/employee/employee.do" method="post">
					<b>依員工編號選擇</b> <select size="1" name="empno">
						<c:forEach var="empVO" items="${empSvc.allEmp}">
							<option value="${empVO.empno}">${empVO.empno}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_Display">
					<input type="submit" value="查詢" class="btn btn-outline-success my-2 my-sm-0">
				</form>
			</li>

			<li>
				<form action="<%=request.getContextPath()%>/employee/employee.do" method="post">
					<b>依員工姓名選擇</b> <select size="1" name="empno">
						<c:forEach var="empVO" items="${empSvc.allEmp}">
							<option value="${empVO.empno}">${empVO.ename}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_Display">
					<input type="submit" value="查詢" class="btn btn-outline-success my-2 my-sm-0">
				</form>
			</li>
		</ul>

		<h3>員工管理</h3>

		<ul>
			<li><a href='<%=request.getContextPath()%>/back-end/employee/insertOneEmp.jsp'>點此</a>新增員工</li>
		</ul>
		
		
		<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->
	</div>


</body>
</html>