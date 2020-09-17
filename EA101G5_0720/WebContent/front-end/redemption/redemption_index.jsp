<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%@ include file="/front-end/header.jsp" %>

<%
	String coano = (String)session.getAttribute("coano");
	CoaService coaSvc = new CoaService(); 
	CoaVO coaVO = coaSvc.getOneCoa(coano);
	pageContext.setAttribute("coaVO", coaVO);
%>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>教練錢包</title>
<style>
body{
	background-color: #f8b300;
	background-position:-190px 0 ;
	background-repeat:no-repeat;
}
form {
	margin-bottom:20px;
	display: flex;
    justify-content: center;
    margin-top:50px;
}
#footer {
	margin-top:90px;
}
#point{
	font-size:18px;
}
</style>
</head>
<body>
	<div>
		<img src="<%=request.getContextPath()%>/images/deposit/ad02.png" style="width:1920px;height:350px">
	</div>
	<div class="container">
		<br>
			<table>
				<tr>
					<td id="point">目前擁有點數:<font color=red>${coaVO.coapoint}</font></td>
				</tr>
			</table>
			<form action="<%=request.getContextPath()%>/redemption/redemption.do" method="post">
			<img src="<%=request.getContextPath()%>/images/backend_public/news.png">
				<input type="submit" value="查看兌換紀錄" class="btn btn-outline-dark" style="width:122px">
				<input type="hidden" name="coano" value="${coaVO.coano}">
				<input type="hidden" name="action" value="showAll"> 
			</form>	<br>
		
			<form action="<%=request.getContextPath()%>/redemption/redemption.do" method="post">
			<img src="<%=request.getContextPath()%>/images/backend_public/money.png">
				<input type="submit" value="兌換申請" class="btn btn-outline-dark" style="width:122px">
				<input type="hidden" name="coano" value="${coaVO.coano}">
				<input type="hidden" name="action" value="goInsert"> 
			</form> <br>
	</div>
<div id="footer"><%@ include file="/front-end/footer.jsp" %></div>

</body>
</html>