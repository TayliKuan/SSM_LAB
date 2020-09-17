<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.student.model.*"%>
	<%@ include file="/front-end/header.jsp" %>


<%
	String stuno = (String)session.getAttribute("stuno");
	StuService stuSvc = new StuService();
	StuVO stuVO = stuSvc.getOneStu(stuno);
	pageContext.setAttribute("stuVO", stuVO);
%>

<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom-css/deposit/deposit_wallet.css">
<style>
#word{
	text-align:center;
	font-size:15px;
	color:#FFFFFF;
}

body{
	background-color: #f8b300;
	background-position:-190px 0 ;
	background-repeat:no-repeat;
}

#commit{
	width:122px;
}

.container{
	background-color: #f8b300;
	margin-top: -70px;
}

.test{
	margin-top:850px;
	margin-left:-1099px;
	width:1945px;
	height:170px;
	background-color:#000;
	
}

#text{
	margin-left:45.5%;
}

</style>
<title>我的錢包</title>

</head>
<body>
	
	<div class="container">
	
        <div class="walletb"></div>
        <div class="money"> 
            <h1>$</h1>
        </div>
        <div class="wallet">
            <div class="lock">
            	<div class="circle"></div>
            </div> 
            <div id="word">錢包剩餘點數:</div>
          	<div id="point">${stuVO.stupoint}</div>
        </div>    
    </div>

	<div id="text">
		<div>
			<form action="<%=request.getContextPath()%>/deposit/deposit.do" method="post"><br>
			<img src="<%=request.getContextPath()%>/images/backend_public/news.png">
				<input type="submit" value="查看儲值紀錄" class="btn btn-outline-dark" style="width:123px">
				<input type="hidden" name="stuno" value="${stuVO.stuno}">
				<input type="hidden" name="action" value="selectAll"> 
			</form>	
		</div>
		<br>
		<div>
			<form action="<%=request.getContextPath()%>/deposit/deposit.do" method="post">
				<img src="<%=request.getContextPath()%>/images/backend_public/money.png">
				<input type="submit" value="我要儲值" class="btn btn-outline-dark" id="commit">
				<input type="hidden" name="stuno" value="${stuVO.stuno}">
				<input type="hidden" name="action" value="goInsert"> 
			</form>
		</div>	
	</div>

	<div class="test">
		<img src="<%=request.getContextPath()%>/images/deposit/ad04.PNG" >		
	</div>
<%@ include file="/front-end/footer.jsp" %>
</body>

</html>

