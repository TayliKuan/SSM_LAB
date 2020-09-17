<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lesson.model.*"%>
<%@ page import="com.expertise.model.*"%>
<%@ page import="com.coach.model.*"%>
<%@ include file="/front-end/header.jsp" %>

<%
	LessonVO lessonVO = (LessonVO) request.getAttribute("lessonVO");
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
	href="${pageContext.request.contextPath}/css/custom-css/lesson/updateLesson.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
</head>
<style>
.err{
margin-bottom:20px;
}
.title{
font-size:16px;
}
label {
display:contents;
}
h2{
    text-align: left;
}
.form-control {
    text-align: left;
    }
    
.btn-group-lg>.btn, .btn-lg {
    padding: .0rem 0rem;
    font-size: 1.25rem; 
}
#magic{
	width:80px;
	margin-left:1000px;
}
</style>
<body>
		

	<!-- ##### Blog Wrapper Area Start ##### -->
	<div class="single-blog-wrapper">

		<!-- Single Blog Post Thumb -->
		<div class="single-blog-post-thumb">
			<img src="${pageContext.request.contextPath}/images/bg-img/COA1920.png"
				alt="">
		</div>
		
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-12 col-md-12">
					<div class="regular-page-content-wrapper section-padding-80">
						<form method="post"	action="lesson.do" name="form1" enctype="multipart/form-data">
							<div class="regular-page-text">
								<h2>課程資料修改</h2>
								<!--表單開始-->
							<img src="${pageContext.request.contextPath}/images/bg-img/sup.png" id="magic">
							<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
								<div class="err">
									<font style="color: red" >請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red" >${message}</li>
										</c:forEach>
									</ul>
								</div>	
								</c:if>


								<div class="row">
									<div class="col-md-12 mb-3">
										<label for="firstName" class="title">課程名稱</label> 
										<input type="text"
											class="form-control" name="lessname" id="lessname"
											value="<%=lessonVO.getLessname()%>">

									</div>
									<div class="col-md-12 mb-3">
										<label for="country" class="title">課程類型</label>
										<jsp:useBean id="lessonSvc" scope="page"
											class="com.lesson.model.LessonService" />

										<select class="custom-select d-block " name="lesstype" id="lesstype">
											<c:forEach var="expertiseVO" items="${lessonSvc.allExpByExpno}">
												<option value="${expertiseVO.expno}" ${(lessonVO.lesstype==expertiseVO.expno)?'selected':'' }>${expertiseVO.expdesc}
											</c:forEach>
										</select>
											

									</div>


									<div class="col-md-12 mb-3">
										<label for="lastName" class="title">人數上限(最多幾位學生)</label> <input type="text"
											class="form-control" name="lessmax"
											value="<%=lessonVO.getLessmax()%>">

									</div>

									<div class="col-md-12 mb-3">
										<label for="lastName" class="title">人數下限(下限人數到即開團)</label> <input type="text"
											class="form-control" name="lessmin"
											value="<%=lessonVO.getLessmin()%>">

									</div>
								</div>



								<div class="mb-3">
									<label for="address" class="title">課程欲售點數</label> 
<!-- 									<input type="text" -->
<!-- 										class="form-control" name="lessprice" -->
<%-- 										value="<%=lessonVO.getLessprice()%>"> --%>
										
									<input type="text"
										class="form-control" disabled value="<%=lessonVO.getLessprice()%>"><li style="color: red" font-size="16px">課程欲售點數不可修改</li></input>
									<input type="hidden"
										class="form-control" name="lessprice"
										value="<%=lessonVO.getLessprice()%>" >
								</div>

								<div class="mb-3">
									<label for="address" class="title">課程地點</label> <input type="text"
										class="form-control" name="lessloc" id="lessloc"
										value="<%=lessonVO.getLessloc()%>">

								</div>

								<div class="mb-3">
									<label for="address" class="title">課程報名開始</label> <input type="text"
										class="form-control" id="from" name="lessstart"
										value="<%=lessonVO.getLessstart()%>">

								</div>
								<div class=" mb-3">
									<label for="address" class="title">課程報名截止</label> <input type="text"
										class="form-control" id="to" name="lessend"
										value="<%=lessonVO.getLessend()%>">

								</div>

								<div class="mb-3">
									<label for="address" class="title">課程堂數</label>
									 <input type="text"
										class="form-control" disabled value="<%=lessonVO.getLesstimes()%>"><li style="color: red" font-size="16px">堂數不可修改</li></input>
									<input type="hidden"
										class="form-control" name="lesstimes"
										value="<%=lessonVO.getLesstimes()%>" >



								</div>

								<div>
									<div class="col-md-12 mb-3" >
										<label for="address" class="title">課程說明</label>
										<textarea name="lessdesc"  class="form-control" id="exampleFormControlTextarea1" rows="3"><%=lessonVO.getLessdesc()%></textarea>
									</div>
									<div>
									<label class="title">原始封面圖片: </label> <br> 
										<img src="<%=request.getContextPath()%>/lesson/PicServletJDBC.do?lessno=${lessonVO.lessno}" class="innerpic">
										
										<div>
											<div>
												<label class="title">上傳新課程封面圖片: </label> <br> 
												<input type="file" id="myFile" name="lesspic" >
											</div>
										</div>
										<div>
											<label class="title">圖片預覽: </label>
											<div id="preview"></div>
											
										</div>
										<br>
										<div>
											<button type="button" id="deletebtn">刪除</button>

										</div>
									</div>


								</div>
							</div>

							<hr class="mb-4">
							<input type="hidden" name="action" value="update">
							<input type="hidden" name="lessno" value="${lessonVO.lessno}">
							
							<button class="btn btn-primary btn-lg btn-block" type="submit">送出修改</button>
						</form>
						<!--表單結束-->
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- ##### Blog Wrapper Area End ##### -->
<script>
$(document).ready(function(){
		$('#magic').on('click',function(){
			$('#lessname').val('SUP 水上瑜珈');
			$('#lesstype').val('EXP001');
			$('#lessloc').val('中大路300號(中央大學室內游泳池)');
			$('#exampleFormControlTextarea1').val('SUP水上瑜珈的學員在瑜珈動作訓練同時必須在SUP板上保持平衡，需要更大的專注力與身體對話，除了伸展每個動作之外還要保持平衡，達到核心肌群全身肌力的訓練，使身體每一個部位肌肉都發揮作用，藉由站在SUP板的特性將身體柔軟延伸更深度的展現，帶給身體更強的張力與力度，也由於展現動作時同時需要配合核心肌力的平衡，在練習時會消耗更大的熱量，比起陸上瑜珈達到減重與雕塑身型的效果更加具體明顯。');
		});
});
	</script>

	</footer>
		<%@ include file="/front-end/footer.jsp" %>
	<!-- ##### Footer Area End ##### -->
	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<!-- Bootstrap js -->
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<!-- Plugins js -->
	<script src="${pageContext.request.contextPath}/js/plugins.js"></script>
	<!-- Classy Nav js -->
	<script
		src="${pageContext.request.contextPath}/js/classy-nav.min.js"></script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/js/custom-js/lesson/addLesson.js"></script>
	<script src="${pageContext.request.contextPath}/js/active.js"></script>
	<script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>

</body>

</html>