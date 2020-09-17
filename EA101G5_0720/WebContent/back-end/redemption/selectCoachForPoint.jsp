<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>


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

.container {
	text-align:center;
	margin-top:10%
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
		<div class="container">
		<div>
			<h2>FitMate教練點數發放</h2>
		</div><br><br>
		
		<jsp:useBean id="coaSvc" scope="page"
				class="com.coach.model.CoaService" />
		<form action="<%=request.getContextPath()%>/redemption/redemption.do" method="post">
					<h3>課程</h3>
					<b>請選擇教練編號</b> <select size="1" name="coano">
						<c:forEach var="coaVO" items="${coaSvc.all}">
							<option value="${coaVO.coano}">${coaVO.coano}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_Display">
					<input type="submit" value="查詢" class="btn btn-outline-warning my-2 my-sm-0">
		</form>
		<br><br>
		<form action="<%=request.getContextPath()%>/redemption/redemption.do" method="post">
					<h3>活動</h3>
					<b>請選擇教練編號</b> <select size="1" name="coano">
						<c:forEach var="coaVO" items="${coaSvc.all}">
							<option value="${coaVO.coano}">${coaVO.coano}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_DisplayAct">
					<input type="submit" value="查詢" class="btn btn-outline-warning my-2 my-sm-0">
		</form>

		</div>
			<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->
	</div>
	
<script>

</script>	
</body>
</html>