<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.coach.model.*"%>

<%
	String coano = (String)request.getAttribute("coano");
	ActivityService actSvc = new ActivityService();
	CoaService coaSvc= new CoaService();
	List<ActivityVO> actlist = actSvc.getAllReservation(coano);
	CoaVO coaVO = coaSvc.getOneCoa(coano);
	pageContext.setAttribute("coaVO", coaVO);
	pageContext.setAttribute("actlist", actlist);
%>
<html>
<head>
<meta charset="utf-8">
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
<style>

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
		<div id="main">
			<h1>FitMate教練點數發放</h1>
			<h3>目前選擇教練 : ${coaVO.coano} ${coaVO.coaname}</h3>
		</div>
		<div>
			<a href="<%=request.getContextPath()%>/back-end/redemption/selectCoachForPoint.jsp">上一頁</a>
		</div>
		
		<div class="table-responsive-sm table-hover table-warning">
			<table class="table align-items-center">
				<tr>
					<th>活動編號</th>
					<th>活動點數</th>
					<th>活動狀態</th>
				</tr>
				
				<c:forEach var="actVO" items="${actlist}">
					<tr>
						<td class="align-middle">${actVO.actno}</td>
						<td class="align-middle">${actVO.actprice}</td>
						<td class="align-middle">${actVO.actsta}</td>
						<td class="align-middle">
						<form action="<%=request.getContextPath()%>/redemption/redemption.do" method="post" class="form1">
							<input type="button" value="發放點數" class="btn btn-outline-danger my-2 my-sm-0"
							<c:if test="${actVO.actsta !='上架已成團'}"> disabled="disabled"
                   			</c:if>>
							<input type="hidden" name="action" value="addpayact">
							<input type="hidden" name="actno" value="${actVO.actno}">
							<input type="hidden" name="coano" value="${coaVO.coano}">
						</form>
						
							
						</td>
					</tr>
				</c:forEach>
			</table>
			


			<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->
		</div>
	</div>
<script>

$(document).ready(function(){
	$('.form1').on('click','.btn',function(e){
		e.preventDefault();
		var father = $(this);
		swal({
			title:'注意',
			text:'你確定要發放點數給教練嗎?',
			icon:'warning',
			buttons:true,
			dangerMode:true
		}).then(function(isConfirm){
			if(isConfirm){
				setTimeout(function(){
					swal('成功','點數發放完畢','success');										
					},3000);
				setTimeout(function(){
					father.parent().submit();
				},4500);
				swal({
					title:'系統審核中',
					text:'請勿隨意關閉視窗,以免資料損毀',
					icon:'warning',
					buttons:false,
				});	
			} else {
				swal('取消','取消作業','error');
			}
		});
	});
});


</script>	
</body>
</html>