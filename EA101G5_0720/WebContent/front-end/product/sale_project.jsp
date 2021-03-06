<%@page import="com.sale_project.model.Sale_projectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_fav.model.*"%>
<%@ page import="com.sale_list.model.*"%>
<%@ page import="com.sale_project.model.*"%>
<%@ include file="/front-end/header.jsp" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%--JSTLI18N標籤庫--%>


<%
//從session來的
List<ProductVO> sapro_list = (List<ProductVO>)session.getAttribute("product_sale_VO_list");
pageContext.setAttribute("sapro_list", sapro_list);
//得到的商品們
Sale_projectService saleSvc = new Sale_projectService();
List<Sale_projectVO> sapro_list1 = saleSvc.getAllfilter();
pageContext.setAttribute("sapro_list1", sapro_list1);
//得到的學生編號
String stuno = (String) session.getAttribute("stuno");
pageContext.setAttribute("stuno", stuno);
//得到的學員追蹤陣列
Product_favService prod_favsvc = new Product_favService();
List<Product_favVO> listfav = prod_favsvc.getOnePf(stuno);
pageContext.setAttribute("listfav", listfav);

List<Sale_projectVO> Sale_projectVO_list = saleSvc.getAllfilter();
Map<String, String>  salemap = new HashMap<>();
Sale_listService sale_listSvc = new Sale_listService();
List <Sale_listVO> sale_list = null;
	
for(Sale_projectVO  Sale : Sale_projectVO_list){
	sale_list = sale_listSvc.getOneSl(Sale.getSapro_no());
		for(Sale_listVO  Salelist : sale_list){
			salemap.put(Salelist.getProdno(),String.valueOf(Salelist.getSapro_price()));
		}
	}
pageContext.setAttribute("salemap", salemap);
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom-css/product/product.css">


<script src="https://cdn.jsdelivr.net/npm/js-cookie@beta/dist/js.cookie.min.js"></script>

<style>
#titilea {
	font-size: 22px;
}

.single-product-wrapper .product-img .product-favourite button {
	position: absolute;
	height: 25px;
	width: 45px;
	font-size: 14px;
	color: #ccc;
	top: 20px;
	right: 20px;
	z-index: 10;
	line-height: 25px;
	background-color: #ffffff;
	box-shadow: 0 0 3px rgba(0, 0, 0, 0.15);
	text-align: center;
	opacity: 0;
	visibility: hidden;
	border: none;
	outline: none;
}

.single-product-wrapper .product-img .product-favourite button.active {
	opacity: 1;
	visibility: visible;
}

.single-product-wrapper:hover .product-img .product-favourite button {
	opacity: 1;
	visibility: visible;
}

p{
text-decoration-color: red;
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
</head>

<body>

	<!-- ##### Breadcumb Area Start ##### -->
	<div class="breadcumb_area bg-img"
		style="background-image: url(<%=request.getContextPath()%>/images/product/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="page-title text-center">
						<h2>FitMate購物商城</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Breadcumb Area End ##### -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<!-- ##### Shop Grid Area Start ##### -->
<!-- 	<section class="shop_grid_area section-padding-80"> -->
		<div class="container">
			<div class="row">
				<div class="col-12 col-md-4 col-lg-3">
					<div class="shop_sidebar_area">

						<!-- ##### Single Widget ##### -->
						<div class="widget catagory mb-50">
							<!-- Widget Title -->
							<h6 class="widget-title mb-30 pclass-title">商品分類</h6>

							<!--  商品分類  -->
							<div class="catagories-menu">
								<ul id="menu-content2" class="menu-content">
									<!-- Single Item -->
									<li>
										<ul class="sub-menu collapse show" id="clothing">
											<li><a href="<%=request.getContextPath()%>/product/product.do?action=pclass&pclass_id=PC001">男士服飾</a></li>
											<li><a href="<%=request.getContextPath()%>/product/product.do?action=pclass&pclass_id=PC002">女士服飾</a></li>
										</ul>

									</li>
									<!-- Single Item -->
									<li><a href="#">健身相關</a>
										<ul class="sub-menu collapse show" id="shoes">
											<li><a href="<%=request.getContextPath()%>/product/product.do?action=pclass&pclass_id=PC003">健身食品</a></li>
											<li><a href="<%=request.getContextPath()%>/product/product.do?action=pclass&pclass_id=PC004">健身配件</a></li>
										</ul>
								</li>
									<!-- Single Item -->
									<li>
										<ul class="sub-menu collapse show" id="accessories">
											<c:forEach var="sale_projectVO" items="${sapro_list1}">
											<li><a href="<%=request.getContextPath()%>/product/product.do?action=sale_project&sale_id=${sale_projectVO.sapro_no}">${sale_projectVO.sapro_name}促銷專案</a></li>
											</c:forEach>
										</ul>
									<script>
										$("#sale").click(function(){
											$("#go_sale").submit();										
										});
									</script>									

								</ul>
							</div>
						</div>

						<!-- ##### Single Widget ##### -->
						<div class="widget brands mb-50">
							<!-- Widget Title 2 -->
							<!-- 左邊欄位預留加功能 -->
						</div>
					</div>
				</div>

				<div class="col-12 col-md-8 col-lg-9">
					<div class="shop_grid_product_area">
						<div class="row">
							<div class="col-12">
								<div
									class="product-topbar d-flex align-items-center justify-content-between">
									<%@ include file="page5.file"%>
									<div class="total-products">
										<p>
											
										</p>
									</div>
								</div>
							</div>
						</div>
			<jsp:useBean id="sale_listVO" scope="page" class="com.sale_list.model.Sale_listService"/>
			<div class="row col-12">
				<c:forEach var="productVO" items="${product_sale_VO_list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<!-- 送出開始 -->		
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" id ="go_sale">
				     <input type="hidden" name="action"     value="sale_project">
				     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
				     <input type="hidden" name="sale_id"    value="SA026">
				</FORM>

					<!-- 單一商品開始 -->
					<div class="col-12 col-sm-3 col-lg-4">
						<div class="single-product-wrapper">
					<!-- 商品圖片 -->
						<div class="product-img">
							<a href="<%=request.getContextPath()%>/Shopping.html?action=getOne_For_Display&prodno=${productVO.prodno}" id="titilea">
							<img src="<%= request.getContextPath()%>/product/product.pic?prodno=${productVO.prodno}" alt="${productVO.prodname}"></a>
					<!-- 限時優惠 -->
							<c:forEach var="saleitems" items="${salemap}" >
								<c:if test="${saleitems.key == productVO.prodno}">												  
									<div class="product-badge offer-badge">
			                             <span>限時優惠</span>
			                        </div>
								</c:if>
							</c:forEach>
					<!-- Favourite -->
					<c:if test="${role == 'student'}">
											<form
												action="<%=request.getContextPath()%>/product/product_fav.html"
												method="post">


												<div class="product-favourite">
													<button class="favme fa fa-heart" type="submit"  
													<c:forEach var="product_favVO" items="${listfav}">
													<c:if test="${product_favVO.prodno == productVO.prodno}">value="Disabled" disabled  title="此商品已加入追蹤" </c:if>
													</c:forEach>
													>
													</button>
													<input type="hidden" name="prodno" value="${productVO.prodno}"> 
													<input type="hidden" name="stuno" value="${stuno}"> 
													<input type="hidden" name="action" value="insert">
													<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
													<input type="hidden" name="whichPage" value="<%=whichPage%>">
												</div>
											</form>
											</c:if>
										</div>
					<!-- 商品名稱、價格、加入購物車-->
							<div class="product-description">
									<a href="<%=request.getContextPath()%>/Shopping.html?action=getOne_For_Display&prodno=${productVO.prodno}" id="titilea">${productVO.prodname}</a>
								<c:forEach var="saleitems" items="${salemap}" >
									 <c:if test="${saleitems.key == productVO.prodno}"> 
										<p class="product-price sale">${saleitems.value}</p>
									 </c:if>
								 </c:forEach>
										<p class="product-price">${productVO.prodprice}</p>
									<c:if test="${saleitems.key == productVO.prodno}">
										<p class="product-price" style="color:red">${saleitems.value}</p>
									</c:if>
								<!-- 加入購物車 -->
								<div class="hover-content">
									<div class="add-to-cart-btn">
									 <a href="#" class="btn essence-btn addtocart">Add to Cart</a> 
									 <input type="hidden" value="${productVO.prodno}">
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</c:forEach>
			</div>
<%@ include file="page2.file"%>
		</div>
	</div>
</div>
	<!-- ##### Shop Grid Area End ##### -->
</div>
<!-- ##### Blog Wrapper Area End ##### -->

<div class="footerposition"><%@ include file="/front-end/footer.jsp"%></div>


</html>