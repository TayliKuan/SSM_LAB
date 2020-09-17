<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/header.jsp" %>

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
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@beta/dist/js.cookie.min.js"></script>
<style>

.footerposition{
height: 17vh;
width: 100vw;
z-index: 999;
top:81vh;
margin-top:30px;
}

.mainthank{
height:67vh;
}

*,
*::before,
*::after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;

  min-height: 90vh;
  padding: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}


/* other */
.info {
  margin-top:80px;
  text-align: center;
  font-size: 20px;
  margin-bottom:200px;
}

p {
/*   color: #2e2e2e; */
  margin-bottom: 20px;
}


/* block-$ */
.block-effect {
  font-size: calc(8px + 6vw);
  text-align: center;
  
}

.block-reveal {
  --t: calc(var(--td) + var(--d));

  color: transparent;
  padding: 4px;

  position: relative;
  overflow: hidden;

  animation: revealBlock 0s var(--t) forwards;
}

.block-reveal::after {
  content: '';

  width: 0%;
  height: 100%;
  padding-bottom: 4px;

  position: absolute;
  top: 0;
  left: 0;

  background: var(--bc);
  animation: revealingIn var(--td) var(--d) forwards, revealingOut var(--td) var(--t) forwards;
}


/* animations */
@keyframes revealBlock {
  100% {
    color: #0f0f0f;
  }
}

@keyframes revealingIn {

  0% {
    width: 0;
  }

  100% {
    width: 100%;
  }
}

@keyframes revealingOut {

  0% {
    transform: translateX(0);
  }

  100% {
    transform: translateX(100%);
  }

}

.abs-site-link {
  position: fixed;
  bottom: 20px;
  left: 20px;
  color: hsla(0, 0%, 0%, .6);
  font-size: 16px;
}


</style>

</head>
<body>

<script>

	Cookies.remove('cartItem');
	Cookies.remove('cartItem', { path: '' });
	Cookies.remove('name', { path: '/', domain: '.localhost' });

</script>
<div class="mainthank">
<h1 class="block-effect" style="--td: 1.2s">
  <div class="block-reveal" style="--bc: #4040bf; --d: .1s">FitMate</div>
  <div class="block-reveal" style="--bc: #bf4060; --d: .5s">感謝您的購買</div>
</h1>

<div class="info" style="font-size:20px;font-family:微軟正黑體">
 商品寄送約1-3天，請耐心等候<br>
 請至<a href="<%=request.getContextPath()%>/front-end/product/product_order.jsp" style="font-size:20px">我的訂單</a>，查詢商品配送狀況
</div>
</div>


<div class="footerposition" style="width: 100vw; position:fixed;top:81vh"">
<%@ include file="/front-end/footer.jsp"%>
</div>
		

</body>

</html>