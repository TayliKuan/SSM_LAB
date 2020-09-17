<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.expertise.model.*"%>
<%@ page import="com.expertise_own.model.*"%>

<!DOCTYPE html>


<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->


<title>註冊成為教練 - addCoach.jsp</title>

<!-- FIT.ico  -->
<link rel="icon" href="<%=request.getContextPath()%>/images/core-img/FIT.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css/core-style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom-css/coach/coach_form.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.15.3/dist/sweetalert2.all.min.js"></script>
<style>
.btn-info {
	cursor: pointer;
	margin-top: 5px;
	margin-right: 15px;
	height: 38px;
}

.swal2-select {
	display: none !important;
}
</style>


</head>


<body>
	<script>
		if ("${update_error_code}" !== "") {
			Swal.fire({
				icon : "${update_error_code}" === "0" ? 'success' : "warning",
				text : "${update_error_msg}",
			})
		}
		<%session.removeAttribute("update_error_code");%>
		<%session.removeAttribute("update_error_msg");%>
	</script>

	<%@ include file="/front-end/header.jsp"%>

	<div class="single-blog-wrapper">

		<div class="single-blog-post-thumb">
			<img src="<%=request.getContextPath()%>/images//bg-img/COA1920.png" alt="">
		</div>
		<FORM class="form01" METHOD="post" ACTION="<%=request.getContextPath()%>/coach/coach.do" name="form1" enctype="multipart/form-data">
			<div class="container">
				<div class="regular-page-content-wrapper section-padding-80">
					<h3>註冊成為教練:</h3>
					<%-- 錯誤表列 
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								 <li style="color: red">${message.key}</li> 
								<li style="color: red">${message.value}</li>
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
																<div class="d-flex justify-content-center align-items-center rounded" style="height: 140px; background-color: rgb(233, 236, 239);">
																	<span>
																		<img id="previewPic" src="<%=request.getContextPath()%>/images/NoData/nopic2.jpg" style="width: 140px; height: 140px; border-radius: .25rem;">
																	</span>
																</div>
															</div>
														</div>
														<div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
															<div class="text-center text-sm-left mb-2 mb-sm-0">
																<h4 id="title" class="pt-sm-2 pb-1 mb-0 text-nowrap"></h4>
																<p class="mb-0"></p>
																<div class="text-muted">
																	<small></small>
																</div>
																<div class="mt-2">
																	<i class="fa fa-fw fa-camera"></i>
																	<input type="FILE" id="pic" name="coapic" size="45" value="" placeholder="上傳照片" />
																</div>
															</div>
															<div class="text-center text-sm-right">
																<span class="badge badge-secondary">FITMATE專業教練申請</span>
																<div class="text-muted">
																	<small></small>
																</div>
															</div>
														</div>
													</div>
													<ul class="nav nav-tabs">
														<li class="nav-item">
															<a href="" class="active nav-link">設定</a>
														</li>
													</ul>
													<div class="tab-content pt-3">
														<div class="tab-pane active">
															<table id="coach-table">
																<div class="row">
																	<div class="col">
																		<div class="row">
																			<div class="col">
																				<div class="form-group">
																					<label>姓名</label>
																					<input class="form-control" type="TEXT" id="coaname" name="coaname" size="45" value="${param.coaname}" id="coaname" placeholder="請輸入姓名" />
																					<p style="color: red;">${errorMsgs.coaname}</p>
																				</div>
																			</div>
																			<div class="col">
																				<div class="form-group">
																					<label>電話</label>
																					<input class="form-control" type="TEXT" name="coatel" size="45" value="${param.coatel}" id="coatel" placeholder="請輸入電話" />
																					<p style="color: red;">${errorMsgs.coatel}</p>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col">
																				<div class="form-group">
																					<label>信箱</label>
																					<input class="form-control" type="TEXT" name="coamail" size="45" value="${param.coamail}" id="coamail" placeholder="請輸入信箱" />
																					<p style="color: red;">${errorMsgs.coamail}</p>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col mb-3">
																				<div class="form-group">
																					<label>自我介紹</label>
																					<textarea class="form-control" name="coaintro" rows="5" id="coaintro" placeholder="請輸入自我介紹">${param.coaintro}</textarea>
																					<p style="color: red;">${errorMsgs.coaintro}</p>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-12 col-sm-6 mb-3">
																		<div class="row">
																			<div class="col">
																				<div class="form-group">
																					<label>匯款帳戶</label>
																					<input class="form-control" type="TEXT" name="coaacc" size="45" value="${param.coaacc}" id="coaacc" placeholder="請輸入帳戶" />
																					<p style="color: red;">${errorMsgs.coaacc}</p>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col">
																				<div class="form-group"></div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col">
																				<div class="form-group">
																					<label>
																						<span class="d-none d-xl-inline"></span>
																					</label>
																					<button type="button" class="btn btn-success" id="add-exp">新增專長</button>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="col-12 col-sm-5 offset-sm-1 mb-3">
																		<label>性別</label>
																		<div class="custom-controls-stacked px-2">
																			<div class="row" style="margin-left: 140px; margin-top: 10px;">
																				<div>
																					<input type="radio" name="coasex" size="45" value="男" checked="true" />
																					男
																				</div>
																				<div style="margin-left: 70px;">
																					<input type="radio" name="coasex" size="45" value="女" />
																					女
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</table>
															<div class="row">
																<div class="col d-flex justify-content-end">
																	<button type="button" class="btn btn-info" id="magicBtn">神奇小按鈕</button>
																	<jsp:useBean id="expSvc" scope="page" class="com.expertise.model.ExpService" />
																	<input type="hidden" name="action" value="insert" />
																	<input class="btn btn-primary" type="submit" value="送出新增" id="addCoach" />
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

					</div>
				</div>
		</form>
	</div>

	<%@ include file="/front-end/footer.jsp"%>

</body>
<script src="<%=request.getContextPath()%>/js/custom-js/coach/addCoach.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU=" crossorigin="anonymous"></script> -->
<script>
	$(document)
			.ready(
					function() {
						var expNumber = 0;

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

						$('#magicBtn').on('click', function() {
							$('#coaname').val('巨石強森');
							$('#coatel').val('0966885166');
							$('#coamail').val('coachfitmate@gmail.com');
							$('#coaintro').val('健身也是健心，鎖定明確的目標之後，你缺的只是投入更多的意志力與堅持到底的毅力！Rock教練不是體育科系出身，走上健身這條路也算是誤打誤撞，求學期間漸漸地愛上重訓，重訓後一般人視為痛苦、逃避的遲發性肌肉痠痛，但Rock教練視為淬鍊的過程，並樂在其中。');
							$('#coaacc').val('4513128652914');
						});

						$("#add-exp")
								.click(
										function() {
											expNumber += 1;
											$("#coach-table")
													.after(
															"<div class='card exp'>"
																	+ "<table style='margin:0 auto;'>"
																	+ "<td><a style='font-size:18px; color:navy;'>專長:</a> "
																	+ "<select size='1' name='expno"+expNumber+"'>"
																	+ "<c:forEach var='ExpVO' items='${expSvc.all}'>"
																	+ "<option value='${ExpVO.expno}' ${(ExpVO.expno==ExpVO.expno)? 'selected':''}>${ExpVO.expdesc}"
																	+ "</c:forEach>"
																	+ "</select></td>"
																	+ "<tr>"
																	+ "<td>"
																	+ "<h6 style='margin-top:10px;'>專業證照/比賽獎狀:</h6>"
																	+ "<input type='FILE' class='exp-preview' name='expown"+expNumber+"' size='45' placeholder='請上證照/獎狀' style='margin-bottom:10px;'/>"
																	+ "</td>"
																	+ "</tr>"
																	+ "<tr>"
																	+ "<td style='width:311.2px; height:100px;overflow:hidden;'>"
																	+ "<img id='previewPicExp' src='"+context+"/images/NoData/nopic.jpg' style='max-width:311.2px;_width:expression(this.width > 311.2 ? '311.2px' : this.width);'>"
																	+ "</td>"
																	+ "</tr>"
																	+ "<tr>"
																	+ "<td>"
																	+ "<input type='button' value='刪除專長' class='delete-exp' style='background-color:lightblue;border-width:1px;border-style:solid;margin-top:10px;'>"
																	+ "</td>" + "</tr>" + "</table>" + "<br>" + "</div>");

											// bind click event for new element
											$(".exp-preview").change(function() {
												readURL(this, $(this).parent().parent().parent().find("img"));
											});

											$(".delete-exp").click(function() {
												$(this).parent().parent().parent().parent().parent().remove();
											});

										});
						$("#coaname").keyup(function() {
							$("#title").html($(this).val());
						});

					});
</script>


</html>