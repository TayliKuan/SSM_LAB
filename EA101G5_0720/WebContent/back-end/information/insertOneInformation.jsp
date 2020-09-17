<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.information.model.*"%>

<%
	InformationVO infVO = (InformationVO)request.getAttribute("infVO");
%>

<%
	Date indate = null ;
	try{
		indate = infVO.getIndate();
	} catch (Exception e){
		indate = new Date(System.currentTimeMillis());
	}
%>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>FitMate管理後台</title>
<script src="<%=request.getContextPath()%>/back-end/ckeditor/ckeditor.js"></script>
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
.dec {
	height:350px;
}


</style>

</head>
<%@ include file="/back-end/backinclude.jsp"%>
<body>

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
			<h1>新增消息/公告</h1>
			<a href="<%=request.getContextPath()%>/back-end/information/showAllInformation.jsp">返回消息首頁</a>
		</div>
	<c:if test="${not empty errorMsgs}">
		<a>看看你的錯:</a><br>
		<a><c:forEach var="message" items="${errorMsgs}">${errorMsgs}</c:forEach></a>
	</c:if>
	
<form action="<%=request.getContextPath()%>/information/information.do" method="post">
<div class="container col-10">
<div class="table-responsive-sm table-hover table-warning">

<table class="table align-items-center">
	<tr>
		<td>發布日期:</td>
		<td><input type="date" name="indate" id="date"></td>
	</tr>
	<tr>
		<td>類別:<font color=red size="3px"><b>*送出後無法更改</b></font></td>
		<td><select size="1" name="intype" id="type">
			<option value="[公告]">[公告]
			<option value="[促銷]">[促銷]
			<option value="[活動]">[活動]
		</select></td>
	</tr>
	<tr>
		<td>標題:</td>
		<td><input type="text" name="intitle" id="title"></td>
	</tr>
	<tr>
		<td>內容:</td>
		<td class="dec"><textarea style="width:500px;height:350px;" name="indesc" id="cke"></textarea></td>
	</tr>
</table>

</div>
</div><br><br>
<input type="button" value="新增送出" class="btn btn-primary my-2 my-sm-0" id="confirm">
<input type="hidden" name="action" value="insert">
</form>	
<button id="magic" style="margin-left:9.3%"><img src="<%=request.getContextPath()%>/images/employee/naneto.png" style="width:130px;height:130px"></button>
</div>
<script>

$(document).ready(function(){
	$('input:button').on('click',function(e){ //input的type不能用submit,擋不下送出
		e.preventDefault();	//避免form表單的預設送出
		swal({
			title:'注意',	//swal標準結構1
			text:'你確定要新增這則消息嗎?', //swal標準結構2
			icon:'warning', //swal標準結構3
			buttons:true, //顯示取消button
			dangerMode:true //確認button上紅色
		}).then(function(isConfirm){ //.then()表示按了第一個swal後要接著做的事情,用匿名函式傳isConfirm=swal的確認值
			if(isConfirm){ 
				swal('成功','你已經新增一則消息','success');//找到那個要傳出去的form表單標籤(不建議用id/class),用submit()執行送出
				setTimeout(function(){	//設定swal彈出的時間&順序	
				$('input:button').parent('form').submit();
				},2000);
			} else {
				swal('取消','你已經取消新增作業','error');
			}
		});
	});
});

CKEDITOR.replace('cke');

$('#magic').click(function(){
	$('#date').val('2020-07-23');
	$('#title').val('儲10萬再送你20萬振興活動!!');
	$('type').val('[促銷]');
});
</script>
</body>
</html>