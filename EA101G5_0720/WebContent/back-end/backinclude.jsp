<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
	pageContext.setAttribute("empVO", request.getAttribute("empVO"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>include</title>
<style>
@import
	url("https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css")
	; /*匯入左邊滑動>的圖型*/
</style>
<style>
* {
	margin: 0;
	padding: 0;
	list-style: none;
	font-family: 微軟正黑體;
	box-sizing: border-box;
}

html, body {
	width: 100%;
	height: 100%;
}

/*登入、logo*/
.login {
	position: relative;
	list-style: none;
	font-family: 微軟正黑體;
}

#loginbtn { /*登入按鈕*/
	background-color: #FFCC80;
	color: #000;
	left: 93%;
	border: none;
	position: absolute;
	/* width: 75px; */
	margin-top:2% !important;
	/* right: 3%; */
	right: 0;
	top: 0;
	z-index:999;
	
}

#logoutbtn{
	background-color: #FFCC80;
	color: #000;
	left: 93%;
	border: none;
	position: absolute;
	/* width: 75px; */
	margin-top:2% !important;
	/* right: 3%; */
	right: 0;
	top: 0;
	z-index:999;
	
}

#loginbtn:hover , #logoutbtn:hover{ /*滑鼠畫到登入按鈕時的狀態*/
	background-color: #F8b300;
	color: #000;
}

/*左邊選單設定*/
#titleh3 { /*左邊FitMate管理後台*/
	margin-bottom: 40px;
}

.side-menu { /*左邊滑出來的區塊*/
	position: fixed;
	float: left;
	height: 100%;
	padding-top: 50px;
	text-align: center;
	line-height: 50px;
	letter-spacing: 1px;
	box-sizing: border-box;
	background-color: #F8b300;
	display: flex;
	flex-direction: column;
	box-shadow: 5px 0px 10px hsla(240, 40%, 15%, .6);
	transform: translateX(-100%);
	transition: .3s;
	z-index: 1;
}

ul.menu {
	width: 200px;
	margin: auto;
}

#side-menu-switch:checked+.side-menu { /*按了按鈕之後側邊欄位會長出來*/
	transform: translateX(0);
}

#side-menu-switch { /*收合按鈕checkbox隱藏*/
	position: absolute;
	opacity: 0;
	z-index: -1;
}

.side-menu label { /*收合按鈕樣式*/
	position: absolute;
	width: 40px;
	height: 80px;
	background-color: #000;
	color: #fff;
	right: -40px;
	top: 0;
	bottom: 0;
	margin: auto;
	line-height: 80px;
	text-align: center;
	font-size: 30px;
	border-radius: 0 10px 10px 0;
}

.navbar .sub-menu { /*子連結*/
	display: none;
}

.navbar li:hover>.sub-menu { /*滑鼠移到父連結長出子連結*/
	display: block;
	background-color: #FFE0B2;
	width: 80%;
	height: auto;
	margin: auto;
}

.navbar .menu a { /*左邊menu連結樣式*/
	color: #000;
	display: block;
	font-size: 20px;
	font-family: 微軟正黑體;
	text-decoration: none;
	text-align: center;
}

.menu img { /*menu裡面的小圖*/
	width: 20px;
	height: 20px;
}

#login_view{
	width:500px;
	margin:5px;
}

.logintext{
	margin:5px;
	background-color:antiquewhite;
}

</style>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Coming+Soon&family=Noto+Sans+TC:wght@700&display=swap" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
	</script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
	</script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<body>
	<!-- checkbox用來收合的不要亂移動 開始 -->
	<input type="checkbox" name="" id="side-menu-switch">
	<!-- checkbox用來收合的不要亂移動 結束-->

	<!-- 左邊選單開始 -->

	<div class="side-menu">
		<h3 id="titleh3">FitMate管理後台</h3>
		<div class="navbar">
			<ul class="menu">
			<!-- 系統管理員才開放員工管理超連結 --> 
			<c:if test="${empVO.esta=='系統管理員'}">
				<li><a href="<%=request.getContextPath()%>/back-end/employee/showAllEmployee.jsp"><img src="<%=request.getContextPath()%>/images/backend_public/employee.png" alt="">
						平台員工管理</a>
<!-- 					<ul class="sub-menu"> -->
<!-- 						<li><a href="#">管理員權限設定</a></li> -->
<!-- 					</ul> -->
					</li>
			</c:if>
			<c:if test="${empVO.esta=='一般管理員'}">
				<li><a href="#" id="estafail"><img src="<%=request.getContextPath()%>/images/backend_public/employee.png" alt="">
						平台員工管理</a>
<!-- 					<ul class="sub-menu"> -->
<!-- 						<li><a href="#">管理員權限設定</a></li> -->
<!-- 					</ul> -->
					</li>
			</c:if>
				<li><a href="#"><img src="<%=request.getContextPath()%>/images/backend_public/news.png" alt="">
						消息管理</a>
					<ul class="sub-menu">
						<li><a href="<%=request.getContextPath()%>/back-end/information/showAllInformation.jsp">消息管理</a></li>
					</ul></li>
					
				<li><a href="#"><img src="<%=request.getContextPath()%>/images/backend_public/complain.png" alt="">
						客服中心</a>
					<ul class="sub-menu">
						<li><a href="<%=request.getContextPath()%>/back-end/complaint/listAll_complaint.jsp">客訴管理</a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/back_msg/back_msg.jsp" target="_blank">線上客服</a></li>
					</ul></li>

				<li><a href="#"><img src="<%=request.getContextPath()%>/images/backend_public/shopping.png" alt="">
						商城管理</a>
					<ul class="sub-menu">
						<li><a href="<%=request.getContextPath()%>/back-end/product/productManage.jsp">商品資料管理</a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/product/product_orderManage.jsp">商品訂單管理</a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/product/sale_projectManage.jsp">促銷專案管理</a></li>
					</ul></li>

				<li><a href="#"><img src="<%=request.getContextPath()%>/images/backend_public/coach.png" alt="">
						教練管理</a>
					<ul class="sub-menu">
						<li><a href="<%=request.getContextPath()%>/back-end/coach/listAllCoach.jsp">教練權限管理</a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/redemption/showAllRedemptionBack.jsp">點數兌換審核</a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/redemption/selectCoachForPoint.jsp">點數發放</a></li>
					</ul></li>


				<li><a href="#"><img src="<%=request.getContextPath()%>/images/backend_public/talk.png" alt="">
						討論區管理</a>
					<ul class="sub-menu">
						<li><a href="<%=request.getContextPath()%>/back-end/article/listAllArticle.jsp">文章管理</a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/reportt/blog_reportt.jsp">檢舉管理</a></li>
					</ul></li>

			</ul>
		</div>
		<!-- label用來收合的不要亂移動 開始 -->
		<label for="side-menu-switch"> <i class="fa fa-angle-right"></i>
		</label>
		<!-- label用來收合的不要亂移動 結束 -->
	</div>
	<!-- 左邊選單結束 -->
	
	<!-- 登入按鈕開始 -->
	<div class="login">
	<!-- 用if判斷empVO是否為空值決定按鈕怎麼出現 -->
<%if (session.getAttribute("empVO")==null) {%>
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
			data-toggle="modal" data-target="#exampleModalCenter" id="loginbtn">登入</button>
<%}else{ %>
		<form action="<%=request.getContextPath()%>/employee/employee.do" method="post">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
			data-toggle="modal" data-target="#exampleModalCenter" id="logoutbtn">登出</button>
		<input type="hidden" name="action" value="logout">
		</form>
<%} %>
		&nbsp;
	<!--登入的彈出視窗(背景變暗層)-->
		<div class="modal fade" id="exampleModalCenter" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
			
			<form action="<%=request.getContextPath()%>/employee/employee.do" method="post" id="login_view">
			
				<div class="modal-content" id="modalcontent">	<!-- 彈出視窗show層 (form表單驗證層)-->
			
					<div class="modal-header">
						<img alt="" src="<%=request.getContextPath()%>/images/employee/logo1.png" width="50" height="50" border="0">
						<h5 class="modal-title" id="exampleModalCenterTitle">管理員登入</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
						</button>
					</div>
					
					<div class="modal-body">帳號:</div>
					<input type="text" name="eacc" class="logintext" id="account">
					
					<div class="modal-body">密碼:</div>
					<input type="password" name="epsw" class="logintext" id="password">
					
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal" id="cancel">取消</button>
						<button type="submit" class="btn btn-primary" name="action" value="logincheck" id="commit">登入</button>	
					</div>
				
					<ul>
						<li>忘記密碼<a href="<%=request.getContextPath()%>/back-end/forgetPsw.jsp">點此</a></li>
					</ul>
	
				</div>
				
			</form>	
			</div>
		
		</div>
	</div>
	<!-- 登入按鈕結束 -->
<script>

	$(document).ready(function(){	
		$('#logoutbtn').click(function(e){
			e.preventDefault();
			$('#modalcontent').css('display','none');
			swal('通知','您已經成功登出','success');
			setTimeout(function(){
				$('#logoutbtn').parent('form').submit();
			},1000);
		});
	});
	
	$(document).ready(function(){
		$('#estafail').click(function(){
			swal('警告','此為系統管理員專用功能,您目前權限為一般管理員','error');
		});
	});
</script>
</body>
</html>