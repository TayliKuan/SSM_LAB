<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.expertise.model.*"%>
<%@ include file="/front-end/header.jsp"%>
<%
	String stuno = (String)session.getAttribute("stuno");
	pageContext.setAttribute("stuno", stuno);

	ActivityService activitySvc = new ActivityService();
	List<ActivityVO> list = activitySvc.getAllForHost(stuno);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title  -->
<title>查詢主揪發起全部活動 - listAllActivityForHost.jsp</title>
<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css/core-style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom-css/regular-page.css">

<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">

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

.swal2-title {
    display: flex !important;
    justify-content: center !important;
}
#backhome{
font-size: 16px;
border-radius: 20px;
font-family: 微軟正黑體;
padding: 6px;
border: solid 1px black;
background-color:black;
color:#fff;
}

#backhomediv{
margin-left:90%;
margin-top:20px;
}
.footer_area {
   margin-top: 500px;
}
</style>

</head>
<body>
	<!-- ##### Header Area Start ##### -->
	<!-- ##### Header Area End ##### -->

		<!-- Single Blog Post Thumb -->
		<div class="single-blog-post-thumb">
			<img src="${pageContext.request.contextPath}/images/bg-img/STU1920.png" alt="">
		</div>
		<div class="container col-12">
			<div class="row justify-content-center">
				<div class="col-12 col-md-12">
					<div class="regular-page-content-wrapper section-padding-60">
						<div class="regular-page-text">
							<h3 style=“margin-top:50px”>FitMate我舉辦的活動清單</h3>
							<%@ include file="page1.file"%>
							<c:forEach var="activityVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<table border="1" class="table table-dark table align-items-center">
									<tr>
										<td rowspan="8" style="width: 480px; max-height: 100%">
											<img src="<%=request.getContextPath()%>/activity/activitypic.do?actno=${activityVO.actno}" alt="活動圖片" class="rounded float-right img-thumbnail img-fluid">
										</td>
										<td style="width: 130px">活動編號</td>
										<td style="width: 130px">${activityVO.actno}</td>
										<td style="width: 130px">活動名稱</td>
										<td style="width: 200px">${activityVO.actname}</td>
									</tr>
									<tr>
										<td>活動類型</td>
										<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService" />
										<jsp:useBean id="expSvc" scope="page" class="com.expertise.model.ExpService" />
										<td>
											<c:forEach var="expVO" items="${expSvc.all}">
												<c:if test="${activityVO.acttype==expVO.expno}">${expVO.expdesc}</c:if>
											</c:forEach>
										</td>
										<td>活動狀態</td>
										<td>${activityVO.actsta}</td>
									</tr>
									<tr>
										<td>活動日期</td>
										<td>
											<fmt:formatDate value="${activityVO.actdate}" pattern="yyyy-MM-dd" />
										</td>
										<td>活動時段</td>
										<td>${activityVO.actss}</td>
									</tr>
									<tr>
										<td>開始報名日期</td>
										<td>
											<fmt:formatDate value="${activityVO.actstart}" pattern="yyyy-MM-dd" />
										</td>

										<td>截止報名日期</td>
										<td>
											<fmt:formatDate value="${activityVO.actend}" pattern="yyyy-MM-dd" />
										</td>
									</tr>
									<tr>
										<td>最小成團人數</td>
										<td>${activityVO.actmin}</td>
										<td>最大上限人數</td>
										<td>${activityVO.actmax}</td>
									</tr>
									<tr>
										<td>目前報名人數</td>
										<td>${activityVO.actcur}</td>
										<td>活動點數</td>
										<td>${activityVO.actprice}</td>
									</tr>
									<tr>
										<td>學員編號</td>
										<td>${activityVO.stuno}</td>
										<td>教練編號</td>
										<td>${activityVO.coano}</td>
									</tr>

									<tr>
										<td>活動地點</td>
										<td colspan="3">${activityVO.actloc}</td>
									</tr>
									<tr>
										<td>
											<div class="row justify-content-center align-self-center">
												<div class="col-2" style="margin-right:25px">
												
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" style="margin-bottom: 0px;">
														<button style="width:80px;height:38px" type="submit" class="btn btn-warning" <c:if test="${activityVO.actsta=='下架'}">value="Disabled" disabled</c:if>>下架</button>
														<input type="hidden" name="actno" value="${activityVO.actno}">
														<input type="hidden" name="action" value="offactivity">
													</FORM>
													<!-- 											<input type="submit" class="btn btn-warning" value="下架"> -->

												</div>
												
												<div class="col-2" style="margin-right:25px">
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" style="margin-bottom: 0px;">
														<input style="width:80px" type="button" class="btn btn-primary" value="修改">
														<input type="hidden" name="actno" value="${activityVO.actno}">
														<input type="hidden" name="action" value="getOne_For_Update">
													</FORM>
												</div>
												
												<div class="col-2" style="margin-right:25px">
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" style="margin-bottom: 0px;">
														<input type="hidden" value="${activityVO.actno}" name="actno">
														<input type="hidden" value="${stuno}" name="stuno">
														<input type="submit" class="btn btn-danger" value="上架" style="width:80px;" 
														<c:if test="${activityVO.actsta=='待預約'}">value="Disabled" disabled</c:if>
														<c:if test="${activityVO.actsta=='上架待成團' ||activityVO.actsta=='上架已成團'}">value="Disabled" disabled</c:if>>
														<input type="hidden" name="action" value="listing">
														
													</FORM>
												</div>
											</div>
										</td>
										<td>活動描述</td>
										<td colspan="3">${activityVO.actdesc}</td>
									</tr>
								</table>

							</c:forEach>
							<div id="backhomediv">
								<a href="<%=request.getContextPath()%>/front-end/activity/activity_selectallforguest.jsp"  id="backhome">按此返回活動總覽</a>
							<%@ include file="page2.file"%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


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


</body>
<script>
	$(document).ready(function(){
		$('button:submit').on('click',function(e){
			e.preventDefault();
			Swal.fire({
				title:'注意',
				text:'您確定要下架嗎',
				icon:'warning',
				showCancelButton:true,
				showConfirmButton:true,
				dangerMode:true
			}).then((result)=>{
				if(result.value){
					setTimeout(function(){
						$('button:submit').parent().submit();
					},2000);
					Swal.fire('成功','您已成功下架','success');
				}else
					Swal.fire('取消','您已取消下架','error');
			});
		});
	});
	
	$(document).ready(function(){
		$('input:button').on('click',function(e){
			Swal.fire({
				title:'注意',
				text:'您確定要修改嗎',
				icon:'warning',
				showCancelButton:true,
				showConfirmButton:true,
				dangerMode:true
			}).then((result)=>{
				if(result.value){
					setTimeout(function(){
						$('input:button').parent().submit();
					},500);
					Swal.fire('成功','您已送出修改','success');
				}else
					Swal.fire('取消','您已取消修改','error');
			});
		});
	});
	
	$(document).ready(function(){
		$('input:submit').on('click',function(e){
			e.preventDefault();
			Swal.fire({
				title:'注意',
				text:'您準備要上架嗎',
				icon:'warning',
				showCancelButton:true,
				showConfirmButton:true,
				dangerMode:true
			}).then((result)=>{
				if(result.value){
					setTimeout(function(){
						$('input:submit').parent().submit();
					},1000);
					Swal.fire('成功','您已成功上架','success');
				}else
					Swal.fire('取消','您已取消下架','error');
			});
		});
	});
</script>
</html>
