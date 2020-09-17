<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ include file="/front-end/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	String stuno = (String) session.getAttribute("stuno");
	pageContext.setAttribute("stuno", stuno);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title  -->
<title>FitMate</title>

<!-- Favicon  -->
<link rel="icon"
	href="<%=request.getContextPath()%>/images/core-img/heart.svg">

<!-- Core Style CSS -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/css/core-style.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/custom-css/product/product.css">
<style>
.single_product_details_area {
	margin-left: 20%;
	height:70vh;
}

.footerposition{
	height: 50px;
    margin-top: 30px;
	width: 100vw;
    z-index: 999;
}
#fortype{
margin-top:30px !important;
font-size:18px;
text-align:center;
}

.fb-btn{
    margin: 0 auto;
    margin-top: 20px;
    border-radius: 30px;
}

 .submit{
    margin: 0 auto;
 
    border-radius: 30px;
}

h4>span{
    
   display: flex;
    justify-content: center;
}
input{
width: 260px;
    margin: 0 auto;

}
.modal-content {
 
    background-color: black;
}
label>span{
	margin-left: 305px;
}
label {
	display: contents;
}

.title {
	font-size: 16px;
}
#search{
    width: 300px;
    margin-top: 5px;
}
</style>


<script src="https://cdn.jsdelivr.net/npm/js-cookie@beta/dist/js.cookie.min.js"></script>

</head>

<body>
	
	<!-- ##### Single Product Details Area Start ##### -->
	<section class="single_product_details_area d-flex align-items-center">

		<!-- 單一商品圖片 -->

		<div class="single_product_thumb clearfix" id="product_pic">

			<img src="<%= request.getContextPath()%>/product/product.pic?prodno=${productVO.prodno}"
				alt="">

		</div>

		<!-- 單一商品資訊-->
		<div class="single_product_desc clearfix">

			
			<h2 style="display: flex;">${productVO.prodname}</h2>
			
			<p class="product-price">
			${productVO.prodprice}
			</p>
			<p class="product-desc">${productVO.proddesc}</p>

			<!-- form -->
			<form class="cart-form clearfix" method="post">

				<div class="cart-fav-box d-flex align-items-center">
					<!-- Cart -->
				<a href="#" class="btn essence-btn addtocart">Add to Cart</a> <input
					type="hidden" value="${productVO.prodno}">
				</div>
			</form>
		</div>
	</section>
	<!-- ##### Single Product Details Area End ##### -->


<div class="footerposition">
<%@ include file="/front-end/footer.jsp"%>
</div>

</body>
		
		
</html>