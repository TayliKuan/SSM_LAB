<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity_fav.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ include file="/front-end/header.jsp"%>

<%
	String stuno = (String)session.getAttribute("stuno");
	pageContext.setAttribute("stuno", stuno);
	
	Activity_favService activity_favSvc = new Activity_favService();
	List<Activity_favVO> list = activity_favSvc.findActivityByPrimaryKey(stuno);
	pageContext.setAttribute("list", list);

	ActivityService activitySvc = new ActivityService();
	List<ActivityVO> activitylist = activitySvc.getAllActivity();
	pageContext.setAttribute("activitylist", activitylist);
%>


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
<title>FitMate活動詳情 - listAllActivityForGuest.jsp</title>
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

table {
	font-family: 微軟正黑體;
}
.nice-select{
display:none!important;
}
select{
display:inline-block!important;
height:34px;
}
#footerposition{
height:50px;
margin-top:400px;
width:100vw;
z-index:999;
}
</style>

</head>

<body>
 <div class="single-blog-wrapper" style="background-color: black;">
        <div class="single-blog-post-thumb">
            <img src="<%=request.getContextPath()%>/images//bg-img/STU1920.png" alt="">
        </div>
        </div>
		<!-- Single Blog Post Thumb -->
		<div class="single-blog-post-thumb"></div>

		<div class="container col-12">
			<div class="row justify-content-center">
				<div class="col-12 col-md-12">
						<div class="regular-page-content-wrapper section-padding-60">
						<div class="regular-page-text">
							<h3 id="title" style="margin-top:100px">FitMate活動追蹤清單</h3>

							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>


							<table class="table table-dark table-hover">
								<tr class="text-center">
									<th scope="col">活動編號</th>
									<th scope="col">活動名稱</th>
									<th scope="col">教練編號</th>
									<th scope="col">活動類型</th>
									<th scope="col">活動點數</th>
									<th scope="col">刪除追蹤</th>
								</tr>


								<%@ include file="page1.file"%>
								<c:forEach var="activity_favVO" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr class="text-center">


										<td class="align-middle"><c:forEach var="activityVO"
												items="${activitylist}">
												<c:if test="${activityVO.actno==activity_favVO.actno}">
	                    ${activityVO.actno}
                    </c:if>
											</c:forEach></td>


										<td class="align-middle"><c:forEach var="activityVO"
												items="${activitylist}">
												<c:if test="${activityVO.actno==activity_favVO.actno}">
		                    			${activityVO.actname}
	                   					 </c:if>
											</c:forEach></td>

										<td class="align-middle"><c:forEach var="activityVO"
												items="${activitylist}">
												<c:if test="${activityVO.actno==activity_favVO.actno}">
		                    				
		                    				${activityVO.coano}
	                   					 </c:if>
											</c:forEach></td>


										<jsp:useBean id="expSvc" scope="page"
											class="com.expertise.model.ExpService" />

										<td class="align-middle"><c:forEach var="activityVO"
												items="${activitylist}">
												<c:if test="${activityVO.actno==activity_favVO.actno}">
													<c:forEach var="expVO" items="${expSvc.all}">
														<c:if test="${activityVO.acttype==expVO.expno}">${expVO.expdesc}</c:if>
													</c:forEach>
												</c:if>
											</c:forEach></td>

										<td class="align-middle"><c:forEach var="activityVO"
												items="${activitylist}">
												<c:if test="${activityVO.actno==activity_favVO.actno}">
	                    					${activityVO.actprice}
                    					</c:if>
											</c:forEach></td>

										<td class="align-middle">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/activity_fav/activityfav.do"
												style="margin-bottom: 0px;">
												<input type="submit" class="btn btn-primary" value="刪除">
												<input type="hidden" name="stuno" value="${stuno}"> 
												<input type="hidden" name="actno" value="${activity_favVO.actno}">
												<input type="hidden" name="action" value="delete">
											</FORM>
										</td>


									</tr>
								</c:forEach>
							</table>
						<a href="<%=request.getContextPath()%>/front-end/activity/activity_selectallforguest.jsp" style="margin-left:89%;font-size: 16px;border-radius: 20px;font-family: 微軟正黑體;padding: 6px;border: solid 1px black;
    padding: 6px;background-color:black;color:#fff" id="backhome">按此返回活動總覽</a></div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="page2.file"%>
	<!-- ##### Blog Wrapper Area End ##### -->

<c:if test="${not empty insert}">
		<script>
			swal("追蹤成功","", "success");
		</script>
</c:if>

	<!-- ##### Footer Area Start ##### -->
	<div id="footerposition">
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


</body>

</html>

