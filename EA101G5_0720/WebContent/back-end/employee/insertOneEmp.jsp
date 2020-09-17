<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.Date"%>

<%
	EmployeeVO empVO = (EmployeeVO)request.getAttribute("empVO");
%>

<%
	Date edate = null ;
	try{
		edate = empVO.getEdate();
	} catch (Exception e){
		edate = new Date(System.currentTimeMillis());
	}
%>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>FitMate管理後台</title>
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
			<h1>新增員工資料</h1>
			<a href="<%=request.getContextPath()%>/back-end/employee/showAllEmployee.jsp">返回員工首頁</a>
		</div>
		<c:if test="${not empty errorMsgs}">
			<a>看看你的錯:</a>
			<br>
			<a><c:forEach var="message" items="${errorMsgs}">${message}</c:forEach></a>
		</c:if>

		<form action="<%=request.getContextPath()%>/employee/employee.do" method="post" enctype="multipart/form-data">
			<div class="table-responsive-sm table-hover table-warning">
				<table class="table align-items-center">
					<tr>
						<td>員工姓名:</td>
						<td><input type="text" name="ename"
							value="<%= (empVO==null)? "" : empVO.getEname()%>" id="ename"></td>
					</tr>
					<tr>
						<td>員工帳號:</td>
						<td><input type="text" name="eacc"
							value="<%= (empVO==null)? "" : empVO.getEacc()%>" id="eacc"></td>
					</tr>
					<tr>
						<td>員工信箱:<br><font color=red size=3px><b>*員工密碼信件將寄至此信箱</b></font></td>
						<td><input type="email" name="email" value="<%= (empVO==null)? "" : empVO.getEmail()%>" id="email"></td>
					</tr>
					<tr>
						<td>雇用日期:</td>
						<td><input type="date" name="edate"
							value="<%= (empVO==null)? "" : empVO.getEdate()%>" id="edate"></td>
					</tr>
					<tr>
						<td>員工照片:</td>
						<td><input type="file" id="txt_pic" name="epic"></td>	
					</tr>
					<tr>
						<td>照片預覽:</td>
						<td><img id="previewPic" src="<%=request.getContextPath()%>/images/employee/nopic.jpg"></td>
					</tr>
					<tr>
						<td>員工權限:</td>
						<td><select size="1" name="esta" id="esta">
								<option value="系統管理員">系統管理員
								<option value="一般管理員">一般管理員
						</select></td>
					</tr>
				</table>
				<br>
			</div>
			<br> 
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="新增送出" id="confirm"
				 class="btn btn-primary my-2 my-sm-0">
		</form>
		
			<button id="magic"><img src="<%=request.getContextPath()%>/images/employee/naneto.png" style="width:130px;height:130px"></button>
			

		<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->

	</div>
<script>
	
$("#txt_pic").change(function(){
	readURL(this);
});

function readURL(input){
	if(input.files&&input.files[0]){
		var reader = new FileReader();
		reader.onload = function(e){
			$("#previewPic").attr('src',e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

$(document).ready(function(){
	$('input:submit').on('click',function(e){ //input的type不能用submit,擋不下送出
		e.preventDefault();	//避免form表單的預設送出
		swal({
			title:'注意',	//swal標準結構1
			text:'你確定要新增這名員工嗎?', //swal標準結構2
			icon:'warning', //swal標準結構3
			buttons:true, //顯示取消button
			dangerMode:true //確認button上紅色
		}).then(function(isConfirm){ //.then()表示按了第一個swal後要接著做的事情,用匿名函式傳isConfirm=swal的確認值
			if(isConfirm){ 
				$('input:submit').parent('form').submit();	//找到那個要傳出去的form表單標籤(不建議用id/class),用submit()執行送出
				swal({
					title:'資料新增中',
					text:'請勿隨意關閉視窗,以免資料損毀',
					icon:'warning',
					buttons:false,
				});
				setTimeout(function(){	//設定swal彈出的時間&順序
					swal('成功','你已經新增一名員工','success');
				},5000);
			} else {
				swal('取消','你已經取消新增作業','error');
			}
		});
	});
});

$(document).ready(function(){
	$('#magic').click(function(){
		$('#ename').val('水樹奈奈');
		$('#eacc').val('MizukiNana');
		$('#email').val('cycu10014106@gmail.com');
		$('#esta').val('一般管理員');
		$('#edate').val('2020-07-23');
	});
});
</script>

</body>
</html>