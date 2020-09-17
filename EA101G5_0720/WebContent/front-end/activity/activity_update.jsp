<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.expertise.model.*"%>
<%@ include file="/front-end/header.jsp"%>

<%
	ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); //ActivityServlet.java(Concroller), 存入req的actVO物件
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
<title>FitMate活動詳情 - listAllActivity.jsp</title>
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
.nice-select{
	display:none!important;
}
select{
	display:inline-block!important;
}
</style>

</head>

<body>
	<!-- ##### Header Area Start ##### -->
	<!-- ##### Header Area End ##### -->

	<!-- ##### Blog Wrapper Area Start ##### -->
	<div class="single-blog-wrapper"></div>

	<!-- Single Blog Post Thumb -->
	<div class="single-blog-post-thumb">
		<img
			src="${pageContext.request.contextPath}/images/bg-img/actDetail1920.png"
			alt="">
	</div>
	<%-- 錯誤訊息表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-12 col-md-12">
				<div class="regular-page-content-wrapper section-padding-60">
					<FORM METHOD="post" ACTION="activity.do" name="form1"
						enctype="multipart/form-data">
						<!-- 表單開始 -->
						<h3 class="text-black">修改活動</h3>
						<table class="table table-borderless table-striped table-dark" style="margin-top:20px; font-size:18px; font-family:微軟正黑體">
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle"><div class="text-danger"><b>*</b>活動編號</div></td>
		<td>
         ${activityVO.actno}
			<input type="hidden" name="actno" id="actno" size="36.5"
										value="${activityVO.actno}"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle"><div class="text-danger"><b>*</b>學員編號</div></td>
		<td>${activityVO.stuno}
<input type="hidden" name="stuno" id="stuno" size="36.5"
										value="${activityVO.stuno}">
		</td>
	</tr>
<!-- 	<tr> -->

<!-- 		<td> -->
		<input type="hidden" class="form-control form-control-sm" name="coano" id="coano" size="36.5"
										value="<%=(activityVO == null) ? "" : activityVO.getCoano()%>">
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動名稱</td>
		<td><input type="TEXT" class="form-control form-control-sm" id="actname" name="actname" size="36.5"
										value="<%=(activityVO == null) ? "" : activityVO.getActname()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動類型</td>
		<td><jsp:useBean id="expSvc" scope="page"
										class="com.expertise.model.ExpService" />
									<select name="acttype" class="custom-select d-block">
										<option value="">請選擇</option>
										<c:forEach var="ExpVO" items="${expSvc.all}">
											<option value="${ExpVO.expno}"
												${(activityVO.acttype==ExpVO.expno)? 'selected':''}>${ExpVO.expdesc}</option>
										</c:forEach>
									</select></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動地點</td>
		<td><input type="TEXT" class="form-control form-control-sm" name="actloc" id="actloc" size="36.5"
										value="<%=(activityVO == null) ? "" : activityVO.getActloc()%>" /></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動日期</td>
		<td><input type="TEXT" class="form-control form-control-sm" name="actdate" id="actdate" size="36.5"
										value="<%=(activityVO == null) ? "" : activityVO.getActdate()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動時段</td>
		<td><select name="actss" class="custom-select d-block">
										<option value="">請選擇</option>
										<option value="早上" <c:if test="${activityVO.actss=='早上'}"> selected="selected"</c:if>>早上</option>
										<option value="下午" <c:if test="${activityVO.actss=='下午'}"> selected="selected"</c:if>>下午</option>
										<option value="晚上" <c:if test="${activityVO.actss=='晚上'}"> selected="selected"</c:if>>晚上</option>
										
									</select></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動開始報名日期</td>
		<td><input type="TEXT" class="form-control form-control-sm" name="actstart" id="start_date" size="36.5"
										value="<%=(activityVO == null) ? "" : activityVO.getActstart()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動截止報名日期</td>
		<td><input type="TEXT" class="form-control form-control-sm" name="actend" id="end_date" size="36.5"
										value="<%=(activityVO == null) ? "" : activityVO.getActend()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動最小成團人數</td>
		<td><input type="TEXT" class="form-control form-control-sm" name="actmin" id="actmin" size="36.5"
										value="<%=(activityVO == null) ? "" : activityVO.getActmin()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動最大上線人數</td>
		<td><input type="TEXT" class="form-control form-control-sm" name="actmax" id="actmax" size="36.5"
										value="<%=(activityVO == null) ? "" : activityVO.getActmax()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動點數</td>
		<td><input type="TEXT" class="form-control form-control-sm" name="actprice" id="actprice" size="36.5"
										value="<%=(activityVO == null) ? "" : activityVO.getActprice()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動狀態</td>
		<td>									<input type="TEXT" class="form-control form-control-sm" name="actsta" id="actsta" size="36.5"
										value="${activityVO.actsta}" readonly></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動描述</td>
		<td><textarea class="form-control form-control-sm" name="actdesc" id="actdesc" cols="40" rows="4">
									${activityVO.actdesc}</textarea></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td class="text-center align-self-center" style="vertical-align: middle">活動圖片</td> -->
<!-- 		<td> -->
		<input type="FILE" class="form-control form-control-sm" name="actpic" id="pic" style="display:none;"
										value="<%=(activityVO == null) ? "" : activityVO.getActpic()%>">
<!-- 										</td> -->
<!-- 	</tr> -->
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">圖片預覽</td>
		<td class="text-center">
		<label for="pic"><img id="previewPic" src="<%=request.getContextPath()%>/activity/activitypic.do?actno=${activityVO.actno}"
										style="width: 400px; height: 300px;"></label>
										<h6><span id="magic" class="badge badge-danger">magic</span></h6>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="hidden" name="actno" value="${activityVO.actno}">
								<input type="hidden" name="action" value="update">
								<button class="btn btn-primary btn-lg btn-block" type="submit">送出修改</button>
		</td>
	</tr>
</table>
					</FORM>
					<!-- 表單結束 -->

				</div>
			</div>
		</div>
	</div>
	<!-- ##### Blog Wrapper Area End ##### -->

<div id="footer">
	<%@ include file="/front-end/footer.jsp"%>
	<!-- ##### Footer Area End ##### -->
	</div>

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script
		src="${pageContext.request.contextPath}/css/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script src="${pageContext.request.contextPath}/css/js/popper.min.js"></script>
	<!-- Bootstrap js -->
	<script
		src="${pageContext.request.contextPath}/css/js/bootstrap.min.js"></script>
	<!-- Plugins js -->
	<script src="${pageContext.request.contextPath}/css/js/plugins.js"></script>
	<!-- Classy Nav js -->
	<script
		src="${pageContext.request.contextPath}/css/js/classy-nav.min.js"></script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/css/js/addLesson.js"></script>
	<script src="${pageContext.request.contextPath}/css/js/active.js"></script>
	<script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>

</body>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	$("#pic").change(function() {
		readURL(this);
	});

	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#previewPic").attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<script>
	$('#magic').on('click',function(){
		$('#actname').val('忘憂島之旅');
		$('#actloc').val('南海遊客中心');
		$('#actmin').val('15');
		$('#actmax').val('50');
		$('#actprice').val('3500');
		$('#actdesc').val('藍色珊瑚礁天堂--南海深度珊瑚礁之旅--忘憂島浮潛一日遊!!!');

		
	});
</script>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
	$.datetimepicker.setLocale('zh');
	$(function() {
		$('#actdate').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function() {
						this.setOptions({
							minDate : $('#end_date').val() ? $('#end_date')
									.val() : true
						})
					},
					timepicker : false
					
					
				});

		$('#start_date').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function() {
						this.setOptions({
							minDate : $('#end_date').val() ? $('#end_date')
									.val() : true
						})
					},
					timepicker : false
					
				});

		$('#end_date').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function() {
						this.setOptions({
							minDate : $('#start_date').val() ? $('#start_date')
									.val() : true
						})
					},
					timepicker : false
					
				});
	});
</script>

</html>