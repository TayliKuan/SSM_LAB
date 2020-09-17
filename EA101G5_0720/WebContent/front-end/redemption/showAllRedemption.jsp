<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.redemption.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ include file="/front-end/header.jsp" %>

<%
	String Coano = (String)session.getAttribute("coano");
	RedemptionService redSvc = new RedemptionService();
	List<RedemptionVO> redlist = redSvc.showAllRed(Coano); 
	pageContext.setAttribute("redlist", redlist);
	
%>
<html>
<head>
<style>
	table th {
		text-align:center;
	}
	.footer {
		margin-top:100px;
	}
	body{
	background-color: #f8b300;
	background-position:-190px 0 ;
	background-repeat:no-repeat;
	}
	h2{
	margin-top:20px;
	}
</style>
<meta charset="utf-8">
<title>點數兌換紀錄</title>
</head>
<body>


		<div>
			<img src="<%=request.getContextPath()%>/images/deposit/ad02.png" style="width:1920px;height:350px">
		</div>
		<div id="main">
			<h2>我的兌換紀錄</h2>
			<a href="<%=request.getContextPath()%>/front-end/redemption/redemption_index.jsp" style="font-size:15px">返回教練錢包</a>
		</div><br>
		
		<div class="container">
			<table class="table table-hover" style="text-align:center">
				<thead class="thead-dark">
					<tr>
						<th scope="col">兌換日期</th>
						<th scope="col">兌換金額</th>
						<th scope="col">審核狀態</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="redVO" items="${redlist}">
				<tr>
					<td><fmt:formatDate value="${redVO.reddate}" type="both" pattern="YYYY-MM-dd "/></td>
					<td>${redVO.redprice}</td>
					<td>${redVO.redsta}</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
<div class="footer"><%@ include file="/front-end/footer.jsp" %></div>

</body>
</html>