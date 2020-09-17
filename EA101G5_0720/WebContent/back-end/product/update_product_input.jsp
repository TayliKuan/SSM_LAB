<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
	ProductVO productVO = (ProductVO)request.getAttribute("productVO");
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
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js">
	
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js">
	
</script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css/backend.css">
</head>

<body>

	<%@ include file="/back-end/backinclude.jsp"%>

	<!-- 主要內文區開始 -->
	<div class="article side-open">
		<!-- logo區開始 -->
		<div id="logo">
			<img src="<%=request.getContextPath()%>/images/backend_public/logo.png" alt="">
		</div>
		<!-- logo區結束 -->

		<!-- ------------------------------------從這裡開始編輯喔各位！----------------------- -->
<div id="main">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form METHOD="post" ACTION="<%=request.getContextPath() %>/product/product.do" name="form1" enctype="multipart/form-data">
<a href="<%=request.getContextPath() %>/back-end/product/productManage.jsp">回首頁</a>

<div class="table-responsive-sm table-hover table-success">
<table class="table align-items-center">
	<tr>
		<td>商品編號</td>
		<td><%=productVO.getProdno()%></td>
	</tr>
	<tr>
		<td>商品類別</td>
		<td>
	<jsp:useBean id="product_classSvc" scope="page" class="com.product_class.model.Product_classService" />
			<select size="1" name="pclass_id">
			<c:forEach var="product_classVO" items="${product_classSvc.all}">
				<option value="${product_classVO.pclass_id}" ${(productVO.pclass_id==product_classVO.pclass_id)? 'selected':'' }>
				${product_classVO.pclass_name}</option>
			</c:forEach>
	</select>
	</td>
	</tr>
	<tr>
		<td>商品名稱</td>
		<td><input type="TEXT" name="prodname" size="45" value="<%=productVO.getProdname()%>" /></td>
	</tr>
	<tr>
		<td>商品價格</td>
		<td><input type="TEXT" name="prodprice" size="45" value="<%=productVO.getProdprice()%>" /></td>
	</tr>
	<tr>
		<td>商品數量</td>
		<td>
		<input type="TEXT" name="prodqty" size="45" value="<%=productVO.getProdqty()%>" />
		</td>
	</tr>
	<tr>
		<td>商品圖片</td>
		<td>
		<img src="<%= request.getContextPath()%>/product/product.pic?prodno=${productVO.prodno}" width="200px" height="250px"><br><br>
		<input type="file"  name="prodpic" size="45" value="<%=productVO.getProdpic()%>" />
		</td>
	</tr>
	<tr>
		<td>商品描述</td>
		<td><input type="text" name="proddesc" size="45" value="<%=productVO.getProddesc()%>" /></td>
	</tr>
	<tr>
		<td>商品狀態</td>
		<td><input type = "radio" name="prodsta" value="上架中" checked/>上架中
			<input type = "radio" name="prodsta" value="未上架" />未上架</td>
	</tr>
	<tr>
		<td colspan="2">
<input type="hidden" name="action" value="update">
<input type="hidden" name="prodno" value="<%=productVO.getProdno()%>">
<input type="submit" value="送出修改" class="btn btn-outline-success my-2 my-sm-0"></form></td>
	</tr>

</table>
</div>


</div>
		<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->



</body>
</html>