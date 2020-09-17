<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.expertise.model.*"%>
<%@ include file="/front-end/header.jsp"%>

<%
	ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO");
	String stuno = (String)session.getAttribute("stuno");
	pageContext.setAttribute("stuno", stuno);
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
input{
width: 260px;
    margin: 0 auto;

}
.modal-content {
 
    background-color: black;
}
label>span{
	margin-left: 305px;
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
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" name="form1"
						enctype="multipart/form-data">
						
<table class="table table-sm table-borderless table-striped table-dark" style="margin-top:20px; font-size:18px; font-family:微軟正黑體">
	<tr>
		<td><input type="hidden" value="${stuno}" name="stuno"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">教練名稱</td>
		<td><jsp:useBean id="coaSvc" scope="page"
										class="com.coach.model.CoaService" />
										<select id="coano" name="coano" class="custom-select d-block">
<!-- 										<option value="" style="font-size:22px">請選擇</option> -->
										<c:forEach var="CoaVO" items="${coaSvc.all}">
											<option value="${CoaVO.coano}"
												${(CoaVO.coaname==CoaVO.coaname)? 'selected':''} style="font-size:22px">${CoaVO.coaname}</option>
										</c:forEach>
									</select></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動名稱</td>
		<td><input type="TEXT" class="form-control form-control-lg" name="actname" id="actname" size="45"
										placeholder="請輸入活動名稱"
										value="<%=(activityVO == null) ? "" : activityVO.getActname()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle" style="height:38px;border-radius:6px;font-size:14px;width:733.6px;padding-left:12px;margin-left:4px">活動類型</td>
		<td><jsp:useBean id="expSvc" scope="page"
										class="com.expertise.model.ExpService" />
									<select id="acttype" name="acttype" class="custom-select d-block">
										<option value="" style="font-size:22px">請選擇</option>
										<c:forEach var="ExpVO" items="${expSvc.all}">
											<option value="${ExpVO.expno}"
												${(ExpVO.expno==ExpVO.expno)? 'selected':''} style="font-size:22px">${ExpVO.expdesc}</option>
										</c:forEach>
									</select></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動地點</td>
		<td>
			<div id="zipcode3">
			<div class="f5" data-role="county" ></div>
								<div class="f6" data-role="district" style="width:145px;hight:100px"></div>
								<br><br>
								<input type="TEXT" class="form-control form-control-lg" style="margin-top:2px" 
								name="actloc"id="actloc" placeholder="請輸入活動地點"
										value="<%=(activityVO == null) ? "" : activityVO.getActloc()%>" />
</div>
									</td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動開始報名日期</td>
		<td><input type="TEXT" class="form-control form-control-lg" name="actstart" id="start_date" size="36.5"
										placeholder="請輸入開始報名日期"
										value="<%=(activityVO == null) ? "" : activityVO.getActstart()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動截止報名日期</td>
		<td><input type="TEXT" class="form-control form-control-lg" name="actend" id="end_date" size="36.5"
										placeholder="請輸入截止報名日期"
										value="<%=(activityVO == null) ? "" : activityVO.getActend()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動日期</td>
		<td><input type="TEXT" class="form-control form-control-lg" name="actdate" id="actdate" size="36.5"
										placeholder="請輸入活動日期"
										value="<%=(activityVO == null) ? "" : activityVO.getActdate()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動時段</td>
		<td><select id ="actss" name="actss" class="custom-select d-block">
										<option value="" style="font-size:14px">請選擇</option>
										<option value="早上" style="font-size:14px">早上</option>
										<option value="下午" style="font-size:14px">下午</option>
										<option value="晚上" style="font-size:14px">晚上</option>
									</select></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動最小成團人數</td>
		<td><input type="TEXT" class="form-control form-control-lg" name="actmin" id="actmin" size="36.5"
										placeholder="請輸入活動最小成團人數"
										value="<%=(activityVO == null) ? "" : activityVO.getActmin()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動最大成團人數</td>
		<td><input type="TEXT" class="form-control form-control-lg" name="actmax" id="actmax" size="36.5"
										placeholder="請輸入活動最大上限人數"
										value="<%=(activityVO == null) ? "" : activityVO.getActmax()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動點數</td>
		<td><input type="TEXT" class="form-control form-control-lg" name="actprice" id="actprice" size="36.5"
										placeholder="請輸入活動點數"
										value="<%=(activityVO == null) ? "" : activityVO.getActprice()%>"></td>
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">活動描述</td>
		<td><textarea name="actdesc" id="actdesc" cols="40" rows="4" style="width:100%;border-radius:6px">
									</textarea></td>
	</tr>
	<tr>
<!-- 		<td class="text-center align-self-center" style="vertical-align: middle">活動圖片</td> -->
<!-- 		<td> -->
		<input type="FILE" class="form-control form-control-lg" name="actpic" id="pic" style="display:none;"
										value="<%=(activityVO == null) ? "" : activityVO.getActpic()%>">
<!-- 										</td> -->
	</tr>
	<tr>
		<td class="text-center align-self-center" style="vertical-align: middle">圖片預覽</td>
		<td class="text-center"><label for="pic" style="width:auto">
		<img id="previewPic" class="justify-content-center" 
								src="<%=request.getContextPath()%>/images/activity/A000.jpg"
								style="width: 350px; height: 300px;"></label>
								<h6><span id="magic" class="badge badge-danger">magic</span></h6>
		</td>
	</tr>
	<tr>
	<td colspan="2">
	<input type="hidden" name="action" value="insert">
								<button id = "insert" class="btn btn-primary btn-lg btn-block" type="submit">送出新增</button>
	</td>
	</tr>
</table>
</FORM>
				</div>
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
	

</body>
<!-- 地址 -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>

	<script>
		$("#zipcode3").twzipcode({
			"zipcodeIntoDistrict" : true,
			"css" : [ "city form-control", "town form-control" ],
			"countyName" : "city", // 指定城市 select name
			"districtName" : "town" // 指定地區 select name
		});
	</script>
	<style>
	
.city, .town {
	width: 100%;
	margin-left:-5px;
}


.f1 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(5% - 10px)
}

.f2 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(10% - 10px)
}

.f3 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(15% - 10px)
}

.f4 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(20% - 10px)
}

.f5 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(25% - 10px)
}

.f6 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(30% - 10px)
}

.f7 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(35% - 10px)
}

.f8 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(40% - 10px)
}

.f9 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(45% - 10px)
}

.f10 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(50% - 10px)
}

.f11 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(55% - 10px)
}

.f12 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(60% - 10px)
}

.f13 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(65% - 10px)
}

.f14 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(70% - 10px)
}

.f15 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(75% - 10px)
}

.f16 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(80% - 10px)
}

.f17 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(85% - 10px)
}

.f18 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(90% - 10px)
}

.f19 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(95% - 10px)
}

.f20 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(100% - 10px)
}



</style>

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
// 		$('#stuno').val('S002');
		$('#coano').val('C001');
		$('#actname').val('浮潛');
		$('#actloc').val('山水里山水沙灘');
		$('#actmin').val('5');
		$('#actmax').val('50');
		$('#actprice').val('3000');
		$('#actdesc').val('澎湖浮潛體驗，有專業活潑的教練，不會游泳也可體驗近距離接觸大海的樂趣，人人都能輕易上手!!!');

		
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