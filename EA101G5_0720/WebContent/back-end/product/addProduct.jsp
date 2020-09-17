<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
<jsp:useBean id="productSvc" scope="page"
	class="com.product.model.ProductService" />

<!DOCTYPE html>
<html>
<head>
<title>FitMate管理後台</title>
<meta charset="utf-8">

<style>
body {
	background:
		url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
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
			<img src="<%=request.getContextPath()%>/images/backend_public/logo.png" alt="FitMatelogo">
		</div>

		<h2>新增商品資料</h2>
		<a href="<%=request.getContextPath()%>/back-end/product/productManage.jsp">回商品資料管理首頁</a>
		<c:if test="${not empty errorMsgs}">
			請修正以下錯誤
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</c:if>

	<div class="table-responsive-sm table-hover table-success">
		<table class="table align-items-center">
			<form method="post" action="<%=request.getContextPath()%>/product/product.do" name="form1" enctype="multipart/form-data">
				<tr>
					<td>商品類別</td>
					<td><jsp:useBean id="product_classSvc" scope="page" class="com.product_class.model.Product_classService" /> 
							<select size="1" name="pclass_id" id="pclass_id">
							<c:forEach var="product_classVO" items="${product_classSvc.all}">
								<option value="${product_classVO.pclass_id}"
									${(productVO.pclass_id==product_classVO.pclass_id)? 'selected':'' }>
									${product_classVO.pclass_name}</option>
							</c:forEach>
							</select>
					</td>
				</tr>
				<tr>
					<td>商品名稱</td>
					<td>
					<input type="text" placeholder="請輸入商品名稱" id="prodname" name="prodname" size="40" value="<%=(productVO == null) ? "" : productVO.getProdname()%>" /></td>
				</tr>
				<tr>
					<td>商品價格</td>
					<td><input type="text" placeholder="請輸入商品價格" id="prodprice" name="prodprice" size="40" value="<%=(productVO == null) ? "" : productVO.getProdprice()%>" /></td>
				</tr>
				<tr>
					<td>商品數量</td>
					<td><input type="text" name="prodqty" placeholder="請輸入商品數量" id="prodqty" size="40" value="<%=(productVO == null) ? "" : productVO.getProdqty()%>" /></td>
				</tr>
				<tr>
					<td>商品狀態</td>
					<td><input type="radio" name="prodsta" value="上架中" checked />上架中
						<input type="radio" name="prodsta" value="未上架" />未上架</td>
				</tr>
				<tr>
					<td>商品圖片</td>
					<td><input type="file" id="pic" name="prodpic" placeholder="請選擇圖片" value="" />
					<br>
					<img id="previewPic" src="<%=request.getContextPath()%>/images/product/addprod.png" style="width: 300px; height: 300px;">
				</tr>
				<tr>
					<td>商品描述</td>
					<td><input type="text" placeholder="請輸入商品描述" id="proddesc" name="proddesc" size="40" value="<%=(productVO == null) ? "" : productVO.getProddesc()%>" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="hidden" name="action" value="insert"> 
					<input type="submit" value="送出新增" class="btn btn-outline-success my-2 my-sm-0">
					</form>
					<button id="magic" class="btn btn-outline-success my-2 my-sm-0">神奇小按鈕</button>
					</td>
				</tr>
		</table>
	</div>
</div>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	$("#pic").change(function() {
		readURL(this);
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#previewPic").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$(document).ready(function(){
		  $("#magic").click(function(){
			$("#pclass_id").val("PC002");
		    $("#prodname").val("透氣登山外套");
		    $("#prodprice").val("999");
		    $("#prodqty").val("50");
		    $("#proddesc").val("採用2層5,000 mm Schmerber防水等級塗層，適合降雨（2個小時12cm的降雨量時穿著，外套下擺有抽繩設計。可拆及可調式兜帽。");
		  });
		});
</script>

</body>
</html>