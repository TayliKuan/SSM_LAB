<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String updateErrorCode = (String)session.getAttribute("update_error_code");
	String updateErrorMsg = (String)session.getAttribute("update_error_msg");
	pageContext.setAttribute("update_error_code", updateErrorCode);
	pageContext.setAttribute("update_error_msg", updateErrorMsg);
%>

<!DOCTYPE html>

<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<style>
.marquee {
	background-color: #E6E6F2;
}

.swal2-title {
    
    display: flex !important;
    justify-content: center !important;
}

h6.new {
    margin-left: 10px;
    font-size: 28px !important;
}

h2.new {
    text-align: left !important;
}
.welcome_area {
    margin-top: -5px;
}
</style>

</head>
<body>
	<marquee class="marquee" onMouseOver="this.stop()" onMouseOut="this.start()" scrollamount="15">
		<b>FITMATE有限公司&nbsp;&nbsp;&nbsp;顧問:吳永志 吳冠宏 郭惠民&nbsp;&nbsp;&nbsp;董事長 aka&nbsp;JQuery達人:方品貴&nbsp;&nbsp;&nbsp;執行長:官庭昱&nbsp;&nbsp;&nbsp;藝術總監:黃聖珊 黃羽均&nbsp;&nbsp;&nbsp;系統架構師 aka心靈輔導師:魏韶毅&nbsp;&nbsp;&nbsp;行銷經理:謝志琳&nbsp;&nbsp;&nbsp;業務經理:官民健 </b>
	</marquee>
	<%@ include file="/front-end/header.jsp"%>

	<section class="welcome_area bg-img background-overlay" style="background-image: url(${context}/images/bg-img/yoga.png);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="hero-content">
						<h6 class="new">夏季最新商品</h6>
						<h2 class="new">New Arrivals</h2>
						<a href="${context}/front-end/product/product.jsp" class="btn essence-btn">GO SHOP</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="top_catagory_area section-padding-80 clearfix">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-12 col-sm-6 col-md-4">
					<div class="single_catagory_area d-flex align-items-center justify-content-center bg-img" style="background-image: url(${context}/images/bg-img/bg-2.jpg);">
						<div class="catagory-content">
							<a href="${context}/front-end/activity/activity_selectallforguest.jsp">揪團運動</a>
						</div>
					</div>
				</div>
				<div class="col-12 col-sm-6 col-md-4">
					<div class="single_catagory_area d-flex align-items-center justify-content-center bg-img" style="background-image: url(${context}/images/bg-img/bg-3.jpg);">
						<div class="catagory-content">
							<a href="${context}/front-end/product/product.jsp">購物商城</a>
						</div>
					</div>
				</div>
				<div class="col-12 col-sm-6 col-md-4">
					<div class="single_catagory_area d-flex align-items-center justify-content-center bg-img" style="background-image: url(${context}/images/bg-img/bg-4.jpg);">
						<div class="catagory-content">
							<a href="${context}/front-end/lesson/listAll_lesson.jsp">專業教練課</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="cta-area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="cta-content bg-img background-overlay" style="background-image: url(${context}/images/bg-img/bg-5.jpg);">
						<div class="h-100 d-flex align-items-center justify-content-end">
							<div class="cta--text">
								<h2>FIND YOUR FITMATE</h2>
								<a href="${context}/front-end/coach/listAllCoach_ForStudent.jsp" class="btn essence-btn">查看所有教練</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<section class="new_arrivals_area section-padding-80 clearfix">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="section-heading text-center">
						<h2>熱門商品</h2>

					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="popular-products-slides owl-carousel">
						<div class="single-product-wrapper">
							<div class="product-img">
								<img src="${context}/images/bg-img/P008.PNG" alt="">
								<img class="hover-img" src="${context}/images/bg-img/P008.PNG" alt="">
								<div class="product-badge offer-badge">
									<span>HOT</span>
								</div>
							</div>
							<div class="product-description">
								<span>健身食品</span>
								<a href="">
									<h6>分離乳清蛋白粉-巧克力奶昔</h6>
								</a>
								<div class="hover-content">
								<div class="add-to-cart-btn">
								<a href="<%=request.getContextPath()%>/Shopping.html?action=getOne_For_Display&prodno=P008" class="btn essence-btn">查看詳情</a>
								</div>	
								</div>
							</div>
						</div>
						<div class="single-product-wrapper">
							<div class="product-img">
								<img src="${context}/images/bg-img/P010.PNG" alt="">
								<img class="hover-img" src="${context}/images/bg-img/P010.PNG" alt="">
							</div>
							<div class="product-description">
								<span>健身食品</span>
								<a href="single-product-details.html">
									<h6>有機水果覆盆莓麥片</h6>
								</a>
								<div class="hover-content">
									<div class="add-to-cart-btn">
										<a href="<%=request.getContextPath()%>/Shopping.html?action=getOne_For_Display&prodno=P010" class="btn essence-btn">查看詳情</a>
									</div>
								</div>
							</div>
						</div>
						<div class="single-product-wrapper">
							<div class="product-img">
								<img src="${context}/images/bg-img/P018.PNG" alt="">
								<img class="hover-img" src="${context}/images/bg-img/P018.PNG" alt="">
								<div class="product-badge offer-badge">
									<span>HOT</span>
								</div>
							</div>
							<div class="product-description">
								<span>健身配件</span>
								<a href="<%=request.getContextPath()%>/Shopping.html?action=getOne_For_Display&prodno=P018">
									<h6>成人運動防護護膝</h6>
								</a>
								<div class="hover-content">
									<div class="add-to-cart-btn">
										<a href="<%=request.getContextPath()%>/Shopping.html?action=getOne_For_Display&prodno=P018" class="btn essence-btn">查看詳情</a>
									</div>
								</div>
							</div>
						</div>
						<div class="single-product-wrapper">
							<div class="product-img">
								<img src="${context}/images/bg-img/P026.PNG" alt="">
								<img class="hover-img" src="${context}/images/bg-img/P026.PNG" alt="">
								<div class="product-badge new-badge">
									<span>New</span>
								</div>
							</div>
							<div class="product-description">
								<span>健身配件</span>
								<a href="single-product-details.html">
									<h6>30L水上運動防水背包</h6>
								</a>
								<div class="hover-content">
									<div class="add-to-cart-btn">
										<a href="<%=request.getContextPath()%>/Shopping.html?action=getOne_For_Display&prodno=P026" class="btn essence-btn">查看詳情</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="/front-end/footer.jsp"%>
	
	
		<script>
		if ("${update_error_code}" !== "") {
			Swal.fire({
				icon : "${update_error_code}" === "0" ? 'success' : "warning",
				title : "${update_error_msg}",
				text : '請至註冊信箱收信!'
			});
		}
		<%session.removeAttribute("update_error_code");%>
		<%session.removeAttribute("update_error_msg");%>
	</script>
	

	

</body>

</html>