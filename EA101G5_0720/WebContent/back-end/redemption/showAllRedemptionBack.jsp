<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.redemption.model.*"%>

<%
	RedemptionService redSvc = new RedemptionService();
	List<RedemptionVO> redlist = redSvc.showAll();
	pageContext.setAttribute("redlist", redlist);
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
			<h1>FitMate教練點數兌換申請審核</h1>
		</div>
		<%@ include file="page1.file"%>
		<div class="table-responsive-sm table-hover table-warning" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
			<table class="table align-items-center">
				<tr>
					<th>申請兌換編號</th>
					<th>申請教練編號</th>
					<th>申請日期</th>
					<th>申請兌換金額</th>
					<th>審核狀態</th>
				</tr>
				
				<c:forEach var="redVO" items="${redlist}">
					<tr>
						<td class="align-middle">${redVO.redno}</td>
						<td class="align-middle">${redVO.coano}</td>
						<td class="align-middle"><fmt:formatDate value="${redVO.reddate}" type="both"/></td>
						<td class="align-middle">${redVO.redprice}</td>
						<td class="align-middle">${redVO.redsta}</td>
						<td class="align-middle">
						
						<form action="<%=request.getContextPath()%>/redemption/redemption.do" method="post" class="form1">
							<input type="button" value="審核" class="btn btn-outline-danger my-2 my-sm-0"
							<c:if test="${redVO.redsta=='已審核'}"> value="Disabled" disabled
                   			</c:if>>
							<input type="hidden" name="action" value="change">
							<input type="hidden" name="redno" value="${redVO.redno}">
						</form>
						
							
						</td>
					</tr>
				</c:forEach>
			</table>


			<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->
		</div>
			<%@ include file="page2.file"%>
	</div>
<script>

$(document).ready(function(){
	$('.form1').on('click','.btn',function(e){	
		e.preventDefault();
		var father = $(this);
		swal({
			title:'注意',
			text:'你確定要通過該筆兌換申請?',
			icon:'warning',
			buttons:true,
			dangerMode:true
		}).then(function(isConfirm){
			if(isConfirm){
				father.parent().submit();
				swal({
					title:'系統審核中',
					text:'請勿隨意關閉視窗,以免資料損毀',
					icon:'warning',
					buttons:false,
				});
				setTimeout(function(){
					swal('成功','審核完畢 正在將通知信件寄送給教練','success');										
					},4500);
			} else {
				swal('取消','取消審核','error');
			}
		});
	});
});


</script>	
</body>
</html>