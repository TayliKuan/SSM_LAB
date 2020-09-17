<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.information.model.*"%>

<%
	InformationService infSvc = new InformationService();
	List<InformationVO> inflist = infSvc.getAllInfByDate();
	pageContext.setAttribute("inflist", inflist);
%>
<html>
<head>
<meta charset="UTF-8">
<title>FitMate公告版</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
 integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
 integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
 integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
 integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<style>
a {
    text-decoration:none;
}
.card-columns{
	display:block;
}
img.top {
	margin-top: 0px;
}

img{
	margin-top:32px;
}
#logo{
	margin-top: 0px;
}
.modal-content {

    margin-left: 200px !important;
}
</style>
</head>

<body>
<%@ include file="/front-end/header.jsp" %>

	<div>
		<img src="<%=request.getContextPath()%>/images/deposit/ad03.png" style="width:1920px;height:350px;margin-bottom:32px" class="top">
	</div><br><br>

<div class="container">
	<div class="card-columns">

<c:forEach var="infVO" items="${inflist}">
	
  		<div class="card bg-light mb-3 col-md-12">
  			<div class="card-header">${infVO.intype}</div>
    		<div class="card-body">
      		<h5 class="card-title"> ${infVO.intitle}</h5>
     		 <p class="card-text">${infVO.indesc}</p>
    		</div>
    		<div class="card-footer">
     		 <small class="text-muted">${infVO.indate}</small>
    		</div>
  		</div>
	
</c:forEach>

	</div>
</div>
</body>
<%@ include file="/front-end/footer.jsp" %>

</html>