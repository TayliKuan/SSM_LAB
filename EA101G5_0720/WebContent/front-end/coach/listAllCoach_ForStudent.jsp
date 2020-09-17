<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.expertise.model.*"%>
<%@ page import="com.expertise_own.model.*"%>

<%
	CoaService coaSvc = new CoaService();
	List<CoaVO> list = coaSvc.getAll();
	pageContext.setAttribute("list", list);

%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>FitMate</title>

<link rel="icon" href="<%=request.getContextPath()%>/images/core-img/FIT.ico">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css/core-style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom-css/coach/listAllCoach_ForStudent.css">
<!--<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">-->
<style>
.bootstrap-table .fixed-table-pagination>.pagination-detail .pagination-info {
    color:white;
}

.bootstrap-table .fixed-table-pagination>.pagination-detail .page-list {
    color:white;
}

.bootstrap-table .fixed-table-container .table th, .bootstrap-table .fixed-table-container .table td {
   font-size:18px;
}

.fa-goyellow {
	color: gold;
	font-weight:bold black;
	
}


</style>
</head>

<body style="background: black;">

	<%@ include file="/front-end/header.jsp"%>

	<div class="single-blog-wrapper">

		<div class="single-blog-post-thumb">
			<img src="<%=request.getContextPath()%>/images/bg-img/coach1920.png" alt="" style="margin-top: 0px;">
		</div>

		<table id="table" class="display" style="background: GhostWhite;"></table>

		<%@ include file="/front-end/footer.jsp"%>

		
		<link href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Coming+Soon&family=Noto+Sans+TC:wght@700&display=swap" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->

		<!-- bootstrap table dependency -->
		<link href="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.css" rel="stylesheet">
		<script src="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.js"></script>
		<!-- bootstrap table dependency -->


		<script>
			var context = "<%=request.getContextPath()%>";
		</script>


		<script src="<%=request.getContextPath()%>/js/custom-js/coach/listAllCoach_ForStudent.js"></script>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"></meta>
		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
		
</body>

</html>

