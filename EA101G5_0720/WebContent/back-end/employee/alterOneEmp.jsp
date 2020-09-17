<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.Date"%>

<%
	EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO");
	pageContext.setAttribute("empVO", empVO);
%>

<%
	Date edate = null;
	try {
		edate = empVO.getEdate();
	} catch (Exception e) {
		edate = new Date(System.currentTimeMillis());
	}
%>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/custom-css/employee/insert_alterOneEmp.css">
<style>
body {
	background:
		url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
	background-position: center center;
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
			<h1>修改員工資料</h1>
			<a href="<%=request.getContextPath()%>/back-end/employee/showAllEmployee.jsp">返回員工列表</a> 
		</div>
		<c:if test="${not empty errorMsgs}">
			<a>看看你的錯:</a>
			<br>
			<a><c:forEach var="message" items="${errorMsgs}">${errorMsgs}</c:forEach></a>
		</c:if>

		<form action="<%=request.getContextPath()%>/employee/employee.do" method="post" enctype="multipart/form-data">
			<div class="table-responsive-sm table-hover table-warning">
				<table class="table align-items-center">
					<tr>
						<td>員工編號:<font color=red><b>*</b></font></td>
						<td>${empVO.empno}</td>
					</tr>
					<tr>
						<td>員工姓名:</td>
						<td><input type="text" name="ename" value="${empVO.ename}"></td>
					</tr>
					<tr>
						<td>員工帳號:</td>
						<td><input type="hidden" name="eacc" value="${empVO.eacc}">${empVO.eacc}</td>
						
						<td><input type="hidden" name="epsw" value="${empVO.epsw}"></td>
					</tr>
					<tr>
						<td>員工信箱:</td>
						<td><input type="hidden" name="email" value="${empVO.email}">${empVO.email}</td>
					</tr>
					<tr>
						<td>雇用日期:<font color=red><b>*</b></font></td>
						<td><input type="date" name="edate" value="${empVO.edate}"></td>
					</tr>
					<tr>
						<td>員工圖片:</td>
						<td><input type="file" id="txt_pic" name="epic"><br></td>
						<td><img id="previewPic"
							src="<%=request.getContextPath()%>/employee/employeePic.do?empno=${empVO.empno}"><br></td>
					</tr>
					<tr>
						<td>員工權限:<font color=red><b>*</b></font></td>
						<td><select size="1" name="esta">
								<option value="系統管理員" ${empVO.esta=='系統管理員' ? "selected":""}>系統管理員
								<option value="一般管理員" ${empVO.esta=='一般管理員' ? "selected":""}>一般管理員
						</select></td>
					</tr>
				</table>
				<br>
			</div>
			<br> <input type="button" value="修改送出"
			 id="confirm" class="btn btn-primary my-2 my-sm-0"> <input
				type="hidden" value="alter" name="action"> <input
				type="hidden" name="empno" value="${empVO.empno}">
		</form>


		<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->
	</div>
	<script>
		$("#txt_pic").change(function() {
			readURL(this);
		});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#previewPic").attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}

		$(document).ready(function(){
			$('input:button').on('click',function(e){
				e.preventDefault();
				swal({
					title:'注意',
					text:'你確定要修改這名員工的資料嗎?',
					icon:'warning',
					buttons:true,
					dangerMode:true
				}).then(function(isConfirm){
					if(isConfirm){
						swal('成功','你已經成功修改','success');
						setTimeout(function(){
							$('input:button').parent('form').submit();
						},1500);
					} else {
						swal('取消','你已經取消修改作業','error');
					}
				});
			});
		});
	</script>
</body>
</html>