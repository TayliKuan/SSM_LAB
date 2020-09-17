<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
<!DOCTYPE html>
<html>
<head>
<title>FitMate管理後台</title>
<meta charset="utf-8">

<style>
		body{ 
			background:url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
			 background-position: center center;
		}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Coming+Soon&family=Noto+Sans+TC:wght@700&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css/backend.css">
</head>

<body>

	<%@ include file="/back-end/backinclude.jsp"%>
	<div class="article side-open">
		<div id="logo">
			<img src="<%=request.getContextPath()%>/images/backend_public/logo.png" alt="fitmatelogo">
		</div>
<a href="<%=request.getContextPath() %>/back-end/product/productManage.jsp">回首頁</a><br>
<div class="table-responsive-sm table-hover table-success">
<table class="table align-items-center">
	<tr>
		<td>商品編號</td>
		<td><%=productVO.getProdno()%></td>
	<tr>
	<tr>
		<td>商品類別</td>
		<td>
		<jsp:useBean id="product_classSvc" scope="page" class="com.product_class.model.Product_classService" />
		<c:forEach var="product_classVO" items="${product_classSvc.all}">
			<c:if test="${productVO.pclass_id==product_classVO.pclass_id}">
				${product_classVO.pclass_name}
			</c:if>
		</c:forEach>
		</td>
	<tr>
	<tr>
		<td>商品名稱</td>
		<td><%=productVO.getProdname()%></td>
	<tr>
	<tr>
		<td>商品價格</td>
		<td><%=productVO.getProdprice()%></td>
	<tr>
	<tr>
		<td>商品數量</td>
		<td><%=productVO.getProdqty()%></td>
	<tr>
	<tr>
		<td>商品圖片</td>
		<td><img src="<%= request.getContextPath()%>/product/product.pic?prodno=${productVO.prodno}" width="200px" height="250px"></td>
	<tr>
	<tr>
		<td>商品描述</td>
		<td><%=productVO.getProddesc()%></td>
	<tr>
	<tr>
		<td>商品狀態</td>
		<td><%=productVO.getProdsta()%></td>
	<tr>
	<tr>
		<td colspan="2">
		<form method="post" action="<%=request.getContextPath()%>/product/product.do">
				<input type="submit" value="修改" class="btn btn-outline-success my-2 my-sm-0">
				<input type="hidden" name="prodno" value="${productVO.prodno}">
				<input type="hidden" name="action" value="getOne_For_Update">
		</form>
		</td>
	<tr>
</table>
</div>
</div>


</body>
</html>