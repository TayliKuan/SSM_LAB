<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.deposit.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<%
	String stuno = (String)session.getAttribute("stuno");
	DepositService depSvc = new DepositService();
	List<DepositVO> deplist = depSvc.showAllDep(stuno);
	pageContext.setAttribute("deplist", deplist);
%>

<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>儲值紀錄</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
 integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
 integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
 integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
 integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<style>
	table th {
		text-align:center;
	}
	
	#logout-btn{
	margin-top: 30;
	}
	#footer{
	margin-top:200px;
	}
	a>img{
	margin-top: 30;
	}
	#logo{
	margin-bottom:30;
	}
	tr>td{
	text-align:center;
	}
</style>
</head>

<%@ include file="/front-end/header.jsp" %>
<body>
		<div>
			<img src="<%=request.getContextPath()%>/images/deposit/ad02.png" style="width:1920px;height:350px">
		</div>
		<div id="main">
		<br>
			<h2>我的儲值紀錄</h2>
			<a href="<%=request.getContextPath()%>/front-end/deposit/deposit_index.jsp">返回我的錢包</a>
		</div><br><br>
		
		<div class="container">
			<table class="table table-hover col-md-10">
				<thead class="thead-dark">
					<tr>
						<th scope="col">儲值日期</th>
						<th scope="col">獲得點數</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="depVO" items="${deplist}">
				<tr>
					<td><fmt:formatDate value="${depVO.depdate}" type="date" dateStyle="full"/></td>
					<td>${depVO.depprice}</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="footer">
<%@ include file="/front-end/footer.jsp" %>
</div>
</body>
</html>