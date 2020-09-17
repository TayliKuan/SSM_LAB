<%@page import="com.coach.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<%
	pageContext.setAttribute("context", request.getContextPath());
	pageContext.setAttribute("role", session.getAttribute("role"));

%>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>FitMate</title>

   <!-- Favicon  -->
<link rel="icon" href="${context}/images/core-img/FIT.ico">
<link rel="stylesheet" type="text/css" href="${context}/css/css/core-style.css">
<link rel="stylesheet" type="text/css" href="${context}/css/custom-css/index.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel="stylesheet" type="text/css" href="${context}/css/custom-css/login.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">
<!-- Shopping cookie -->
<script src="https://cdn.jsdelivr.net/npm/js-cookie@beta/dist/js.cookie.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom-css/product/product.css">
<style>
 #scrollUp{ 
 display:none !important;
 } 
 .swal2-title {
    display: flex !important;
    justify-content: center !important;
}
.swal2-select{
	display: none !important;
}
</style>
</head>
<body>
	<header class="header_area">
		<div class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
			<nav class="classy-navbar" id="essenceNav">
			
				<a class="nav-brand" href="${context}/front-end/index.jsp">
					<img src="${context}/images/core-img/logo.png" id="logo" alt="">
					
				</a>
				<div class="classy-navbar-toggler">
					<span class="navbarToggler">
						<span></span>
						<span></span>
						<span></span>
					</span>
				</div>
				<div class="classy-menu">
					<div class="classycloseIcon">
						<div class="cross-wrap">
							<span class="top"></span>
							<span class="bottom"></span>
						</div>
					</div>
					
					<div class="classynav">
						<ul>
							<li>
								<a href="${context}/front-end/index.jsp">首頁</a>
							</li>
							<li>
								<a href="${context}/front-end/information/showAllInformation.jsp">消息</a>
							</li>
							<li>
								<a href="#">商城</a>
								<ul class="dropdown">
									<li>
										<a href="${context}/front-end/product/product.jsp">商城總覽</a>
									</li>
									<li>
										<a href="${context}/product/product.do?action=pclass&pclass_id=PC001">男士服飾</a>
									</li>
									<li>
										<a href="${context}/product/product.do?action=pclass&pclass_id=PC002">女士服飾</a>
									</li>
									<li>
										<a href="${context}/product/product.do?action=pclass&pclass_id=PC003">健身食品</a>
									</li>
									<li>
										<a href="${context}/product/product.do?action=pclass&pclass_id=PC004">健身配件</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="#">課程</a>
								<ul class="dropdown">
									<li>
										<a href="${context}/front-end/lesson/listAll_lesson.jsp">課程總覽</a>
									</li>
									<li>
										<a href="${context}/front-end/coach/listAllCoach_ForStudent.jsp">教練總覽</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="#">揪團</a>
								<ul class="dropdown">
									<li>
										<a href="${context}/front-end/activity/activity_selectallforguest.jsp">活動總覽</a>
									</li>
									<c:if test="${role == 'student'}">
									<li>
										<a href="${context}/front-end/activity/activity_create.jsp">建立活動</a>
									</li>
									</c:if>
								</ul>
							</li>
							<li>
								<a href="${context}/front-end/article/blog.jsp">討論區</a>
							</li>
							
							
							<c:if test="${role == 'coach'}">
										<% 
 											String no = (String)session.getAttribute("coano");
											CoaService Svc = new CoaService();
											CoaVO coavo= Svc.getOneCoa(no);
											String coasta = coavo.getCoasta().trim();
											boolean aa = coasta.equals("授權");
											boolean gg = coasta.equals("停權");
											boolean none =coasta.equals("未授權");
											System.out.print(aa);
										%>
							<% if(aa==true ){ %>
								<li>
									<a href="#">教練專區</a>
									
									
									<ul class="dropdown">
										<li>
											<a href="${context}/front-end/coach/updateCoach.jsp">個人資料</a>
										</li>
										
										<li>
											<a href="${context}/front-end/lesson/coachTimeTable.jsp">查看課表</a>
										</li>
										
										<li>
											<a href="${context}/front-end/lesson/addLesson.jsp">建立課程</a>
										</li>
									
										<li>
											<a href="${context}/front-end/lesson/selectLesson.jsp">查詢與更新</a>
										</li>
										<li>
											<a href="${context}/front-end/activity/activity_selectallforcoach.jsp">查詢活動預約</a>
										</li>
										<li>
											<a href="${context}/front-end/redemption/redemption_index.jsp">點數管理</a>
										</li>
										<li>
											<a href="${context}/front-end/article/listAllArticlebycao.jsp">我的文章</a>
										</li>
									</ul>
									
									
								</li>
							<% } %>
							
							<% if(none==true){ %>
									<li>
									<a href="#">教練專區</a>
									
									
									<ul class="dropdown">
										<li>
											<a href="${context}/front-end/coach/updateCoach.jsp">個人資料</a>
										</li>
									</ul>
									</li>
							
							
							<% } %>
							
							<% if(gg==true ){ %>
								<li>
									<a href="#">教練專區</a>
									
									
									<ul class="dropdown">
										<li>
											<a href="${context}/front-end/coach/updateCoach.jsp">個人資料</a>
										</li>
										
										<li>
											<a href="${context}/front-end/lesson/coachTimeTable.jsp">查看課表</a>
										</li>
									
										<li>
											<a href="${context}/front-end/lesson/selectLesson.jsp">查詢與更新</a>
										</li>
										<li>
											<a href="${context}/front-end/activity/activity_selectallforcoach.jsp">查詢活動預約</a>
										</li>
										<li>
											<a href="${context}/front-end/redemption/redemption_index.jsp">點數管理</a>
										</li>
										<li>
											<a href="${context}/front-end/article/listAllArticlebycao.jsp">我的文章</a>
										</li>
									</ul>
									
									
								</li>
							<% } %>
							</c:if>
							
							
							<c:if test="${role == 'student'}">
								<li>
									<a href="#">學員專區</a>
									<ul class="dropdown">
										<li>
											<a href="${context}/front-end/student/updateStudent.jsp">個人資料</a>
										</li>
										<li>
											<a href="${context}/front-end/lesson_order/mylesson_order.jsp">課程報名紀錄</a>
										</li>
										<li>
											<a href="${context}/front-end/activity_order/activity_order_list.jsp">活動報名紀錄</a>
										</li>
										<li>
											<a href="${context}/front-end/product/product_order.jsp">購買商品清單</a>
										</li>
										<li>
											<a href="${context}/front-end/deposit/deposit_index.jsp">錢包管理</a>
										</li>
										<li>
											<a href="${context}/front-end/lesson_fav/lesson_fav.jsp">追蹤課程</a>
										</li>
										<li>
											<a href="${context}/front-end/activity_fav/activity_favorite_manage.jsp">追蹤活動</a>
										</li>
										<li>
											<a href="${context}/front-end/activity/activity_selectallforhost.jsp">我舉辦的活動</a>
										</li>
										<li>
											<a href="${context}/front-end/product/product_fav.jsp">追蹤商品</a>
										</li>
										<li>
											<a href="${context}/front-end/article/listAllArticlebystu.jsp">我的文章</a>
										</li>
										
									</ul>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</nav>
		
			<div class="header-meta d-flex clearfix justify-content-end">
				<c:if test="${role == 'coach'}">
					<div class="user-login-info">
						<div style="padding-top: 30px; width: 200px; font-size: 16px; font-family: Microsoft JhengHei; font-weight: bold;">
							<span style="color: blue;">${coaname}</span>
							&nbsp&nbsp歡迎登入！
						</div>
					</div>
					<!-- Cart Area -->
				<!--右邊購物車開始 -->
								<!-- Cart Area -->
				<div class="cart-area">
					<a href="#" id="essenceCartBtn"><img
						src="<%=request.getContextPath()%>/images/core-img/bag.svg" alt="">
						<span>0</span></a>
				</div>
								<div class="cart-bg-overlay"></div>
								<div class="right-side-cart-area">
									<!-- 購物車圖示-->
									<div class="cart-button">
										<a href="#" id="rightSideCart">
										<img src="<%=request.getContextPath()%>/images/core-img/bag.svg" alt="">
											<span>0</span></a>
									</div>
							
									<div class="cart-content d-flex">
										<!-- 購物車清單 -->
										<div class="cart-list"></div>
										<!-- 購物車總結 -->
										<div class="cart-amount-summary">
											<h2>我的購物車</h2>
											<ul class="summary-table">
												<li><span>商品總計:</span> <span>$ 0 元</span></li>
												<li><span>運費:</span> <span>$ 0 元</span></li>
												<li><span>總計:</span> <span>$ 0 元</span></li>
											</ul>
											<div class="checkout-btn mt-100">
											
													<input type="submit" value="結 帳" class="check btn btn-outline-success my-2 my-sm-0" id="checkoutbtn">
											<script>
							 				document.getElementById("checkoutbtn").addEventListener("click",function(){ 
							 					alert("請先以學員身分登入，才能結帳唷!") 
							 				}); 
							 				</script> 
												
											
											
											</div>
										</div>
									</div>
								</div>
								<!-- 右邊購物車結束 -->
				</c:if>
				
				
				
				
				
				<c:if test="${role == 'student'}">
					<div class="user-login-info">
						<div style="padding-top: 30px; width: 200px; font-size: 16px; font-family: Microsoft JhengHei; font-weight: bold;">
							<span style="color: blue;">${stuname}</span>
							&nbsp&nbsp歡迎登入！
						</div>
					</div>
					<!-- Cart Area -->
				<!--右邊購物車開始 -->
								<!-- Cart Area -->
				<div class="cart-area">
					<a href="#" id="essenceCartBtn"><img
						src="<%=request.getContextPath()%>/images/core-img/bag.svg" alt="">
						<span>0</span></a>
				</div>
									<div class="cart-bg-overlay"></div>
									<div class="right-side-cart-area">
										<!-- 購物車圖示-->
										<div class="cart-button">
											<a href="#" id="rightSideCart">
											<img src="<%=request.getContextPath()%>/images/core-img/bag.svg" alt="">
												<span>0</span></a>
										</div>
								
										<div class="cart-content d-flex">
											<!-- 購物車清單 -->
											<div class="cart-list"></div>
											<!-- 購物車總結 -->
											<div class="cart-amount-summary">
												<h2>我的購物車</h2>
												<ul class="summary-table">
													<li><span>商品總計:</span> <span>$ 0 元</span></li>
													<li><span>運費:</span> <span>$ 0 元</span></li>
													<li><span>總計:</span> <span>$ 0 元</span></li>
												</ul>
												<div class="checkout-btn mt-100">
											
												
												
													<form action="<%=request.getContextPath()%>/front-end/product/product_order.do" method="post" id="buy">
														<input type=hidden name="action" value="cart"> 
														<input type=hidden id="checkid" name="stuno" value="${stuno}">
														<input type=hidden name="cart_list" value=""> 
														<input type="submit" value="結 帳" class="check btn btn-outline-success my-2 my-sm-0" id="checkoutbtn">
														<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
													</form>
												
												</div>
											</div>
										</div>
									</div>
									<!-- 右邊購物車結束 -->
				</c:if>
				
				
				
				
				
				
				
				<c:if test="${role != 'coach' && role != 'student'}">
					<div class="user-login-info" data-toggle="modal" data-target="#login-modal">
						<a>
							<img src="${context}/images/core-img/user.svg" alt="">
						</a>
					</div>
					<!-- Cart Area -->
				<!--右邊購物車開始 -->
								<!-- Cart Area -->
				<div class="cart-area">
					<a href="#" id="essenceCartBtn"><img
						src="<%=request.getContextPath()%>/images/core-img/bag.svg" alt="">
						<span>0</span></a>
				</div>
										<div class="cart-bg-overlay"></div>
										<div class="right-side-cart-area">
											<!-- 購物車圖示-->
											<div class="cart-button">
												<a href="#" id="rightSideCart">
												<img src="<%=request.getContextPath()%>/images/core-img/bag.svg" alt="">
													<span>0</span></a>
											</div>
									
											<div class="cart-content d-flex">
												<!-- 購物車清單 -->
												<div class="cart-list"></div>
												<!-- 購物車總結 -->
												<div class="cart-amount-summary">
													<h2>我的購物車</h2>
													<ul class="summary-table">
														<li><span>商品總計:</span> <span>$ 0 元</span></li>
														<li><span>運費:</span> <span>$ 0 元</span></li>
														<li><span>總計:</span> <span>$ 0 元</span></li>
													</ul>
													<div class="checkout-btn mt-100">
													
															<input type="submit" value="結 帳" class="check btn btn-outline-success my-2 my-sm-0" id="checkoutbtn">
													<script>
									 				document.getElementById("checkoutbtn").addEventListener("click",function(){ 
									 					alert("請先以學員身分登入，才能結帳唷!") 
									 				}); 
									 				</script> 
														
													
													</div>
												</div>
											</div>
										</div>
										<!-- 右邊購物車結束 -->
				</c:if>

				<c:if test="${role == 'coach' || role == 'student'}">
					<div class="user-login-info">
						<a>
							<img src="${context}/images/core-img/logout.svg" alt="" id="logout-btn">
						</a>
					</div>
					
				</c:if>

			</div>
		</div>
	</header>
	

	<div class="box">
		<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content login-modal-content">
					<div class="cont">
						<div class="form sign-in">
							<form>
								<h2>學員專區</h2>
								<label>
									<span>信箱</span>
									<input type="email" name="stumail" />
								</label>
								<label>
									<span>密碼</span>
									<input type="password" name="stupsw" />
								</label>
								<div style='cursor:pointer'><p class="forgot-pass student">忘記密碼?</p></div>
								<button type="button" class="submit" id="student-login-btn">登入</button>
								<button type="button" class="fb-btn" id="sign-up-student">註冊學員</button>
							</form>
						</div>
						<div class="sub-cont">
							<div class="img">
								<div class="img__text m--up">
									<h2>我是教練</h2>
									<p>優質健身媒合平台讓你快速提升業務量和業界知名度!</p>
								</div>
								<div class="img__text m--in">
									<h2>我是學員</h2>
									<p>還不快手刀加入我們! 購物、揪團和專業課程，所有願望一次滿足！</p>
								</div>
								<div class="img__btn">
									<span class="m--up">教練登入</span>
									<span class="m--in">學員登入</span>
								</div>
							</div>
							<div class="form sign-up">
								<form>
									<h2>教練專區</h2>
									<label>
										<span>信箱</span>
										<input type="email" name="coamail" />
									</label>
									<label>
										<span>密碼</span>
										<input type="password" name="coapsw" />
									</label>
									<div style='cursor:pointer'><p class="forgot-pass coach">忘記密碼?</p></div>
									<button type="button" class="submit" id="coach-login-btn">登入</button>
									<button type="button" class="fb-btn" id="sign-up-coach">註冊教練</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		<c:if test="${role == 'student'}">
		
		<%@ include file="/front-end/front_msg.jsp"%>
		<%@ include file="/front-end/notify.jsp"%>
			
<!-- 			<P ALIGN="center"> -->
<%-- 			<iframe src="${context}/front-end/notify.jsp"></iframe> --%>
<!-- 			</P> -->
			
		</c:if>	
		
		<c:if test="${role == 'coach'}">
			
			<%@ include file="/front-end/front_msg_coach.jsp"%>
		</c:if>	
		
</body>

</html>