<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="java.text.*"%>
<%@ include file="/front-end/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->


<!-- Title  -->
<title>FitMate活動詳情</title>
<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/css/core-style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/custom-css/regular-page.css">

<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap"
	rel="stylesheet">

<style>
.card-img-top {
	height: 300px;
	width: 100%;
}

.gosubmit {
	position: absolute;
	bottom: 20px;
	right: 20px;

}

.card-deck {
	margin-top: 20px;
}

.row {
	margin-right: 70px;
	margin-left: 70px;
}

.img-region {
	height: 300px;
	width: 100%;
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
/* input{ */
/* width: 260px; */
/*     margin: 0 auto; */

/* } */

.modal-content {
 
    background-color: black;
}
label>span{
	margin-left: 305px;
}

</style>

</head>

<body>
	<!-- ##### Header Area Start ##### -->
	<!-- ##### Header Area End ##### -->

	<!-- ##### Blog Wrapper Area Start ##### -->
	
	<div class="single-blog-wrapper">

		<!-- Single Blog Post Thumb -->
		<div class="single-blog-post-thumb">
			<img
				src="${pageContext.request.contextPath}/images/bg-img/actDetail1920.png"
				alt="">
		</div>
<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService" />
<jsp:useBean id="expSvc" scope="page" class="com.expertise.model.ExpService" />
	
<div class="row justify-content-center" style="margin-top:10px">
<c:forEach var="expVO" items="${expSvc.all}">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do">
<input type="submit" value="${expVO.expdesc}" class="btn btn-dark btn-outline-dark" style="border-top:none;border-bottom:none;border-left:3px solid grey;border-right:3px solid grey;font-size: 16px;font-weight:bolder; padding: 30px;">&nbsp;&nbsp; 
<input type="hidden" name="acttype" value="${expVO.expno}"> 
<input type="hidden" name="action" value="getOneType">
</FORM>
</c:forEach>
</div>	
		<div class="container col-12">
			<div class="row justify-content-center">

				<%-- 錯誤訊息表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
		


<jsp:useBean id="now" scope="page" class="java.util.Date" />  

		<c:forEach var="activityVO" items="${actSvc.allActivity}">
					<div class="card-deck col-md-4">

						<div class="card">
							
							<div class="card-img-top img-region "
								style="background:url('<%=request.getContextPath()%>/activity/activitypic.do?actno=${activityVO.actno}');background-size:cover; background-position: center; z-index:888;">
								<div class="text-center my-5"><img id="previewPic" class="justify-content-center" 
								src="<%=request.getContextPath()%>/images/activity/A1000.png"
								style="width: 200px; height: 200px;z-index:999; opacity:0.9; display:${activityVO.actend < now ? 'display':'none'}"></div>
						
								</div>
							

							<div class="card-body">
								<h5 class="card-title">${activityVO.actname}</h5>
								<p class="card-text">${activityVO.actdesc}</p>
								<div class="card-text">
								<!-- 判斷如果人數大於成團最小人數(成團成功)就給予活動促銷價 -->
									<div class=price>
										${ (activityVO.actcur > activityVO.actmin) ? '<del>':''}
										定價 ： ${activityVO.actprice}
									<small class="text-muted"></small>
									<br>
									</div>
									<c:if test = "${(activityVO.actcur > activityVO.actmin)}">
										<div class=sale style="color:red;"><b>促銷價</b> ：<fmt:formatNumber  type="number" value ="${(activityVO.actprice) * 0.8}" pattern ="#,#00"/></div>
									
									<small class="text-muted"></small>
									</c:if>
								</div>
							</div>
							<div class="card-footer"style="padding: 4px">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/activity/activity.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="更多詳情..." class="btn btn-primary"
										class="btn btn-outline-dark" ${activityVO.actend < now ? 'disabled':''}> <input type="hidden"
										name="actno" value="${activityVO.actno}"> 
									<input
										type="hidden" name="action" value="getOne_For_Guest">
								</FORM>
							</div>
						</div>
					</div>
		</c:forEach>		



			</div>
		</div>
	</div>
	<!-- ##### Blog Wrapper Area End ##### -->
	
	<!-- ##### Footer Area Start ##### -->
	<div id="footer">
	<%@ include file="/front-end/footer.jsp"%>
	<!-- ##### Footer Area End ##### -->
	</div>

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<!-- Popper js -->
	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Bootstrap js -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<!-- Plugins js -->
	<script src="${pageContext.request.contextPath}/js/plugins.js"></script>
	<!-- Classy Nav js -->
	<script src="${pageContext.request.contextPath}/js/classy-nav.min.js"></script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/js/active.js"></script>
<script>
 $(document).ready(function(){
 	$(".card-body").each(function(){
 		$(this).children().eq(2).css("color","blue");
 		$(this).children().eq(3).css("color","red");
 	});
 });
 
//login 跑板
	$('.user-login-info').on('click', function(){
	$('#login-modal').find('input').css('width', '300px');
	$('#login-modal').find('input').css('margin-left', '175px');
	});
</script>

</body>

</html>
