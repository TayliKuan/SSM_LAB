<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lesson.model.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ include file="/front-end/header.jsp" %>

<%
	String coano = (String)session.getAttribute("coano");
	pageContext.setAttribute("coano",coano);
	LessonService lessonService = new LessonService();
	List<LessonVO> ALL = lessonService.getCoachLesson(coano);
	pageContext.setAttribute("ALL", ALL);
	//非下架課程
	List<LessonVO> list = ALL.stream().filter(sta->!sta.getLesssta().equals("下架")).collect(Collectors.toList());
	pageContext.setAttribute("list", list);
	//歷史課程
	List<LessonVO> oldlesson = ALL.stream().filter(sta->sta.getLesssta().equals("下架")).collect(Collectors.toList());
	pageContext.setAttribute("oldlesson", oldlesson);
%>



<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
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
	href="${pageContext.request.contextPath}/images/core-img/FIT.ico">

<!-- Core Style CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/css/core-style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/custom-css/regular-page.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/custom-css/lesson/selectLesson.css">


<style>
table {
	background-color: #ffffff;
	font-size: 16px;
	width: 100%; *
	margin: auto;
	text-align: center;
}

h2{
    text-align: left;
}
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
	margin-right: 0px;
	margin-left: 0px;
}

.img-region {
	height: 300px;
	width: 100%;
}
.btn{
width:100px;
}

#okok {
    width: 50px;
    cursor: pointer;
}
.swal2-select{
display:none !important;
}
.swal2-title {
    display: flex !important;
    justify-content: center !important;
}
</style>

</head>

<body>

	<!-- ##### Blog Wrapper Area Start ##### -->
	<div class="single-blog-wrapper">

		<!-- Single Blog Post Thumb -->
		<div class="single-blog-post-thumb">
			<img
				src="${pageContext.request.contextPath}/images/bg-img/COA1920.png"
				alt="">
		</div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<%-- 新增成功 --%>
		<c:if test="${not empty insert}">
			<script>
				Swal.fire('新增課程' ,"成功", "success");
				
			</script>
		</c:if>

		<%-- 時段修改成功 --%>
		<c:if test="${not empty updateTime}">
			<script>
				Swal.fire("時段 修改", "成功", "success");
			</script>
		</c:if>


		<div class="container col-12">
			<div class="row justify-content-center">
				<div class="col-12 col-md-12">
					<div class="regular-page-content-wrapper section-padding-80">
						<div class="regular-page-text">
							<h2>查詢課程</h2>
							 <input type="button" value="歷史課程" class="btn btn-dark" style="float:right" onclick="location.href='<%=request.getContextPath()%>/front-end/lesson/selectLessonStory.jsp'">
							 <br><br>
							<%@ include file="pages/page1.file"%>
							<c:forEach var="lessonVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">


								<table border="1"
									class="table table-striped table-dark  align-items-center align-middle"
									style="table-layout: fixed; word- wrap: break-word;">
									<tr>
										<td rowspan="10" style="width: 400px; height: 250px; vertical-align:middle; ">
										<img src="<%=request.getContextPath()%>/lesson/PicServletJDBC.do?lessno=${lessonVO.lessno}"
											class="rounded float-right img-thumbnail"></td>
									<tr>
										<td style="width: 130px">課程編號</td>
										<td style="width: 130px">${lessonVO.lessno}</td>
										<td style="width: 130px">課程名稱</td>
										<td style="width: 200px">${lessonVO.lessname}</td>
									</tr>
									<tr>
										<td>課程類型</td>
										<jsp:useBean id="lessonSvc" scope="page"
											class="com.lesson.model.LessonService" />
										<td><c:forEach var="expertiseVO"
												items="${lessonSvc.allExpByExpno}">
												<c:if test="${lessonVO.lesstype==expertiseVO.expno}">${expertiseVO.expdesc}</c:if>
											</c:forEach></td>
										<td>課程狀態</td>
										<td>${lessonVO.lesssta}</td>

									</tr>
									<tr>
										<td>課程堂數</td>
										<td>${lessonVO.lesstimes}</td>
										<td>課程地點</td>
										<td>${lessonVO.lessloc}</td>
									</tr>
									<td>課程報名起始日</td>
									<td>${lessonVO.lessstart}</td>

									<td>課程報名截止日</td>
									<td>${lessonVO.lessend}</td>
									<tr></tr>
									<td>最小成團人數</td>
									<td>${lessonVO.lessmin}</td>
									<td>最多上限人數</td>
									<td>${lessonVO.lessmax}</td>
									<tr></tr>
									<td>目前報名人數</td>
									<td>${lessonVO.lesscur}</td>
									<td>課程點數價格</td>
									<td>${lessonVO.lessprice}</td>
									<tr>
									<tr>
										<td rowspan="1" id="innertable" class="table-secondary">
											<div class="container">
												<div class="row">
													<div class="col-4">
														<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lesson/lesson.do" style="margin-bottom: 0px;">
															<button type="submit" class="btn btn-warning"
																<c:if test="${lessonVO.lesssta=='下架'}">value="Disabled" disabled</c:if>
																>修改課程</button>
															<input type="hidden" name="lessno" value="${lessonVO.lessno}"> 
															<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
															<input type="hidden" name="action" value="getOne_For_Update">
														</FORM>
													</div>
													<div class="col-4">
														<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lesson/lessonTime.do" style="margin-bottom: 0px;">
															<button type="submit" class="btn btn-info"
																<c:if test="${lessonVO.lesssta=='下架'}">value="Disabled" disabled</c:if>
																>修改時段</button>
															<input type="hidden" name="lessno" value="${lessonVO.lessno}"> 
															<input type="hidden" name="action" value="getOneTime_For_Update">
														</FORM>
													</div>
													<div class="col-4">
														<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lesson/lesson.do" style="margin-bottom: 0px;">
															<input type="submit" class="btn btn-secondary" value="下架課程"
																<c:if test="${lessonVO.lesssta=='下架'}">value="Disabled" disabled</c:if>
																>
															<input type="hidden" name="lessno" value="${lessonVO.lessno}"> 
															<input type="hidden" name="action" value="off_lesson">
															
														</FORM>
													</div>
												</div>
											</div>
										</td>
										<td>課程說明</td>
										<td colspan="3">${lessonVO.lessdesc}</td>
									</tr>
									<tr>
									</tr>
								</table>

							</c:forEach>
							<%@ include file="pages/page2.file"%>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Blog Wrapper Area End ##### -->



	<!-- ##### Footer Area Start ##### -->
	<%@ include file="/front-end/footer.jsp" %>
	<!-- ##### Footer Area End ##### -->

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<!-- Bootstrap js -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<!-- Plugins js -->
	<script src="${pageContext.request.contextPath}/js/plugins.js"></script>
	<!-- Classy Nav js -->
	<script src="${pageContext.request.contextPath}/js/classy-nav.min.js"></script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/js/active.js"></script>
	<!-- 打勾修改完成 -->
	<script
		src="${pageContext.request.contextPath}/js/custom-js/lesson/selectLesson.js"></script>
<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
	<script>
	$(document).ready(function(){
		const swalWithBootstrapButtons = Swal.mixin({
			  customClass: {
			    confirmButton: 'btn btn-success',
			    cancelButton: 'btn btn-danger'
			  },
			  buttonsStyling: false
			});													
		$('input:submit').on('click',function(e){							
			e.preventDefault();
			swalWithBootstrapButtons.fire({
				title : '確定下架？',
				text : '按下下架後 課程會永久刪除 無法回復',
				icon : 'warning',
				showCancelButton: true,
				confirmButtonText: '確定',
				  cancelButtonText: '取消!',
				  reverseButtons: true
			}).then((result) => {
				 if (result.value) {											
				$(this).parent().submit();
				}
			});
		});
	});
</script> 
</body>

</html>