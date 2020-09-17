<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>


<%
	EmployeeService empSvc = new EmployeeService();
	List<EmployeeVO> emplist = empSvc.getAllEmp();
	pageContext.setAttribute("emplist", emplist);
%>

<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
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

.insert {
	margin-left:90.4%;
}

.pic:hover {
	transform:scale(3,3);
}

.side-menu label {
	padding-top:20px;
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
			<h1>FitMate員工資料</h1>
			<a href="<%=request.getContextPath()%>/back-end/employee/showAllEmployee.jsp">返回員工首頁</a>
		</div>
		<div class="insert">
			<input type="submit" value="新增員工" onclick="location.href='<%=request.getContextPath()%>/back-end/employee/insertOneEmp.jsp'" class="btn btn-warning">
		</div>
		<div class="container col-11">
		<%@ include file="page1.file"%>
		<div class="table-responsive-sm table-hover table-warning">
			<table class="table align-items-center">
				<tr>
					<th>員工編號</th>
					<th>員工姓名</th>
					<th>員工帳號</th>
					<th>員工信箱</th>
					<th>雇用日期</th>
					<th>員工圖片</th>
					<th>員工權限</th>
				</tr>
				<c:forEach var="empVO" items="${emplist}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
				
					<tr>
						<td class="align-middle">${empVO.empno}</td>
						<td class="align-middle">${empVO.ename}</td>
						<td class="align-middle">${empVO.eacc}</td>
						<td class="align-middle">${empVO.email}</td>
						<td class="align-middle">${empVO.edate}</td>
						<td><img
							src="<%=request.getContextPath()%>/employee/employeePic.do?empno=${empVO.empno}"
							class="pic"></td>
						<td class="align-middle">${empVO.esta}</td>
						<td class="align-middle">
							<form 
								action="<%=request.getContextPath()%>/employee/employee.do"
								method="post" style="margin-bottom: 0px;">
								<input type="submit" value="更新資料" class="btn btn-outline-primary my-2 my-sm-0">
								<input type="hidden" name="empno" value="${empVO.empno}">
<%-- 								<input type="hidden" name="requestURI" value="<%=request.getRequestURI()%>"> --%>
<%-- 								<input type="hidden" name="whichPage" value="${whichPage}">  --%>
								<input type="hidden" name="action" value="alterOneEmp">
									
							</form>
						</td>
						<td class="align-middle">
							<form action="<%=request.getContextPath()%>/employee/employee.do" method="post" style="margin-bottom: 0px;">
								<input type="button" value="清算員工" class="btn btn-outline-danger my-2 my-sm-0"> 
								<input type="hidden" name="empno" value="${empVO.empno}"> 
<%-- 								<input type="hidden" name="requestURI" value="<%=request.getRequestURI()%>"> --%>
<%-- 								<input type="hidden" name="whichPage" value="${whichPage}">  --%>
								<input type="hidden" name="action" value="deleteOneEmp">
							</form>
						</td>
						
					</tr>
				</c:forEach>
			</table>


			<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->
		</div>
			<%@ include file="page2.file"%>
		</div>
	</div>
	<script>
		
		$(document).ready(function(){
			$('input:button').on('click',function(e){
				e.preventDefault();
				swal({
					title:'注意',
					text:'你確定要清算這名員工嗎?',
					icon:'warning',
					buttons:true,
					dangerMode:true
				}).then(function(isConfirm){
					if(isConfirm){
						swal('成功','該名員工已變成消波塊','success');
						setTimeout(function(){
							$('input:button').parent().submit();
						},1500);
					} else {
						swal('取消','你已經取消清算作業','error');
					}
				});
			});
		});		
		
	</script>

</body>
</html>