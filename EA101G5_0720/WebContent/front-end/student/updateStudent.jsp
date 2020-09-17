<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.student.model.*"%>
<%@ page import="sun.misc.BASE64Encoder"%>

<!DOCTYPE html>
<%
	String stuno = (String) session.getAttribute("stuno");

	System.out.println("updateStudent page stuno: " + stuno);

	StuService stuService = new StuService();
	StuVO stuVO = stuService.getOneStu(stuno);
	BASE64Encoder encoder = new BASE64Encoder();
	if(stuVO.getStupic()!=null){
		stuVO.setStupicStr(encoder.encode(stuVO.getStupic()));
	}
	pageContext.setAttribute("stuVO", stuVO);
%>

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>學員資料修改 - updateStudent.jsp</title>

<!-- FIT.ico  -->
<link rel="icon"
	href="<%=request.getContextPath()%>/images/core-img/FIT.ico">

<!-- Core Style CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/css/core-style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/custom-css/student/addStudent.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">

<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@9.15.3/dist/sweetalert2.all.min.js"></script>
<style>
.swal2-select {
	display: none !important;
}

p.msg {
	color: red !important;
}
</style>
</head>


<body>

	<script>
	var update_error_code = "${update_error_code}";
	var update_error_msg = "${update_error_msg}";

	if (update_error_code !== "") {
		
		Swal.fire({
			icon : update_error_code === "0"? 'success':"warning",
			text : update_error_msg,
		})
		
		<%request.removeAttribute("update_error_code");%>
		<%request.removeAttribute("update_error_msg");%>

	}
	 </script>


	<%@ include file="/front-end/header.jsp"%>

	<div class="single-blog-wrapper" style="background-color: black;">
		<div class="single-blog-post-thumb">
			<img src="<%=request.getContextPath()%>/images//bg-img/STU1920.png"
				alt="">
		</div>
		<FORM class="form1" METHOD="post"
			ACTION="<%=request.getContextPath()%>/student/student.do"
			name="form1" enctype="multipart/form-data">
			<input type="hidden" name="stuno" value="${stuVO.stuno}">
			<div class="container">
				<div class="regular-page-content-wrapper section-padding-80">
					<span class="oi oi-pencil" style="background-color: white;"></span>
					<h3 style="color: white;">學員個人資料:</h3>
					<%-- 錯誤表列
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if> --%>
					<div class="container">
						<div class="row flex-lg-nowrap">
							<div class="col">
								<div class="row">
									<div class="col mb-3">
										<div class="card">
											<div class="card-body">
												<div class="e-profile">
													<div class="row">
														<div class="col-12 col-sm-auto mb-3">
															<div class="mx-auto" style="width: 140px;">
																<div
																	class="d-flex justify-content-center align-items-center rounded"
																	style="height: 140px; background-color: rgb(233, 236, 239);">
																	<span> <img id="previewPic"
																		src="data:image/png;base64,${stuVO.stupicStr}"
																		style="width: 140px; height: 140px; border-radius: .25rem;">
																	</span>
																</div>
															</div>
														</div>
														<div
															class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
															<div class="text-center text-sm-left mb-2 mb-sm-0">
																<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap" name="stuname">${stuVO.stuname}</h4>
																<p class="mb-0"></p>
																<div class="text-muted">
																	<small></small>
																</div>
																<div class="mt-2">
																	<i class="fa fa-fw fa-camera"></i> <input type="FILE"
																		id="pic" name="stupic" size="45" 
																		 placeholder="上傳圖片"/>
																</div>
															</div>
															<div class="text-center text-sm-right">
																<span class="badge badge-secondary">FITMATE學員</span>
																<div class="text-muted">
																	<small></small>
																</div>
															</div>
														</div>
													</div>
													<ul class="nav nav-tabs">
														<li class="nav-item"><a href=""
															class="active nav-link">設定</a></li>
													</ul>
													<div class="tab-content pt-3">
														<div class="tab-pane active">
															<table id="student-table">
																<div class="row">
																	<div class="col">
																		<div class="row">
																			<div class="col">
																				<div class="form-group">
																					<label>姓名</label> <input class="form-control"
																						type="TEXT" name="stuname" size="45"
																						value="${stuVO.stuname}" placeholder="請輸入姓名" />
																					<p class="msg">${errorMsgs.stuname}</p>
																				</div>
																			</div>
																			<div class="col">
																				<div class="form-group">
																					<label>手機號碼</label> <input class="form-control"
																						type="TEXT" name="stutel" size="45"
																						value="${stuVO.stutel}" placeholder="請輸入手機號碼" />
																					<p class="msg">${errorMsgs.stutel}</p>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col">
																				<div class="form-group">
																					<label>信箱</label> <input class="form-control"
																						type="TEXT" name="stumail" size="45"
																						value="${stuVO.stumail}" placeholder="請輸入信箱"
																						readonly />
																					<p class="msg">${errorMsgs.stumail}</p>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col">
																				<div class="form-group">
																					<label>地址</label> <input name="stuadd" type="text"
																						class="address form-control"
																						value="${stuVO.stuadd}">
																					<p class="msg">${errorMsgs.stuadd}</p>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-12 col-sm-6 mb-3">
																		<div class="mb-2">
																			<b></b>
																		</div>
																		<div class="row">
																			<div class="col">
																				<div class="form-group">
																					<label>生日</label> <input class="form-control"
																						type="text" name=stubir id="f_date1"
																						readonly="readonly">
																					<p class="msg">${errorMsgs.stubir}</p>
																				</div>
																			</div>
																		</div>
																		<div class="row"></div>
																	</div>
																	<div class="col-12 col-sm-5 offset-sm-1 mb-3">
																		<div class="row">
																			<label>性別</label>
																		</div>
																		<div class="row"
																			style="margin-left: 140px; margin-top: 10px;">
																			<div>
																				<input type="radio" name="stusex" size="45"
																					value="男" <%if (stuVO.getStusex().equals("男")) {%>
																					checked <%}%> />男
																			</div>
																			<div style="margin-left: 90px;">
																				<input type="radio" name="stusex" size="45"
																					value="女" <%if (stuVO.getStusex().equals("女")) {%>
																					checked <%}%> />女
																			</div>
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col">
																		<br> <label>
																			<H6>更改密碼</H6>
																		</label>
																	</div>
																</div>
																<div class="row">
																	<div class="col">
																		<div class="form-group">
																			<input class="form-control" type="password"
																				name="stupswOld" size="45" placeholder="請輸入舊密碼" />
																			<p style="color: red;">${errorMsgs.stupswOld}</p>
																		</div>
																	</div>
																	<div class="col">
																		<div class="form-group">
																			<input class="form-control" type="password"
																				name="stupswNew" size="45" placeholder="請輸入新密碼" />
																			<p style="color: red;">${errorMsgs.stupswNew}</p>
																		</div>
																	</div>
																	<div class="col">
																		<div class="form-group">
																			<input class="form-control" type="password"
																				name="stupswConfirm" size="45"
																				placeholder="請再次輸入新密碼" />
																			<p style="color: red;">${errorMsgs.stupswConfirm}</p>
																		</div>
																	</div>
																</div>
																<br>
																<br>
																<div class="row">
																	<div class="col d-flex justify-content-end">
																		<input type="hidden" name="action" value="update">
																		<input type="hidden" name="stuno" value="${stuVO.stuno}" />
																		<input class="btn btn-primary" type="submit"
																			id="submit_update" value="確認修改">
																	</div>
																</div>
															</table>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</form>
	</div>
	<%@ include file="/front-end/footer.jsp"%>

</body>


<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : 'dark', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : new Date("${stuVO.stubir}"),
		maxDate : '+1970-01-01' // 去除今日(不含)之後
	});

	$("#pic").change(function() {
		readURL(this, $("#previewPic"));
	});
	function readURL(input, previewElement) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				previewElement.attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	$("#zipcode3").twzipcode({
		"zipcodeIntoDistrict" : true,
		"css" : [ "city form-control", "town form-control" ],
		"countyName" : "city", // 指定城市 select name
		"districtName" : "town" // 指定地區 select name
	});
</script>
</html>