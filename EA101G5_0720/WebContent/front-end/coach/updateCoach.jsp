<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.expertise.model.*"%>
<%@ page import="com.expertise_own.model.*"%>
<%@ page import="sun.misc.BASE64Encoder"%>
<%@ page import="java.util.List"%>
<%@ include file="/front-end/header.jsp"%>

<!DOCTYPE html>

<%
	String coano = session.getAttribute("coano").toString();
	CoaService coaService = new CoaService();
	CoaVO coaVO = coaService.getOneCoa(coano);
	BASE64Encoder encoder = new BASE64Encoder();
	if (coaVO.getCoapic() != null) {
		coaVO.setCoapicStr(encoder.encode(coaVO.getCoapic()));
	}
	// get expertise data
	ExpOwnService expOwnService = new ExpOwnService();
	List<ExpOwnVO> expOwnVOs = expOwnService.getExpOwnsByCoano(coano);
	ExpService expService = new ExpService();
	for (ExpOwnVO expOwnVO : expOwnVOs) {
		ExpVO expVO = expService.getOneExp(expOwnVO.getExpno());
		expOwnVO.setExpdesc(expVO.getExpdesc());
		// encode bytes to base64 for display purpose
		expOwnVO.setExpownStr(encoder.encode(expOwnVO.getExpown()));
	}
	pageContext.setAttribute("coaVO", coaVO);
	pageContext.setAttribute("expOwnVOs", expOwnVOs);
	pageContext.setAttribute("context", request.getContextPath());
%>


<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->


<title>教練資料修改 - updateCoach.jsp</title>

<!-- Favicon  -->
<link rel="icon" href="<%=request.getContextPath()%>/images/core-img/FIT.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css/core-style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom-css/coach/coach_form.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.15.3/dist/sweetalert2.all.min.js"></script>


<style>

label{

	    display: contents;
}

p.msg {

    color: red !important;
}

.swal2-select {
	display: none !important;
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

		
	
	<div class="single-blog-wrapper">

		<div class="single-blog-post-thumb">
			<img src="<%=request.getContextPath()%>/images//bg-img/COA1920.png" alt="" style="margin-top:60px;">
		</div>
		<FORM class="form-01" METHOD="post" ACTION="<%=request.getContextPath()%>/coach/coach.do" name="form1" enctype="multipart/form-data">
			<div class="container">
				<div class="regular-page-content-wrapper section-padding-80">
					<h3>教練個人資料:</h3>
					<%-- 錯誤表列 
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<%-- <li style="color: red">${message.key}</li> 
								<li style="color: red">${message.value}</li>
							</c:forEach>
						</ul>
					</c:if>--%>
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
																		<img id="previewPic" src="data:image/png;base64,${coaVO.coapicStr}" style="width: 140px; height: 140px; border-radius: .25rem;">
																	</span>
																</div>
															</div>
														</div>
														<div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
															<div class="text-center text-sm-left mb-2 mb-sm-0">
																<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap" name="coaname">${coaVO.coaname}</h4>
																<p class="mb-0"></p>
																<div class="text-muted">
																	<small></small>
																</div>
																<div class="mt-2">
																	<i class="fa fa-fw fa-camera"></i>
																	<input type="FILE" id="pic" name="coapic" size="45" placeholder="上傳照片" />
																</div>
															</div>
															<div class="text-center text-sm-right">
																<span class="badge badge-secondary">FITMATE專業教練</span>
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
															<div class="row">
																<div class="col">
																	<div class="form-group">
																		<label>姓名</label>
																		<input class="form-control" type="TEXT" name="coaname" size="45" value="${coaVO.coaname}"  placeholder="請輸入姓名" />
																		<p class="msg">${errorMsgs.coaname}</p>
																	</div>
																</div>
																<div class="col">
																	<div class="form-group">
																		<label>電話</label>
																		<input class="form-control" type="TEXT" name="coatel" size="45" value="${coaVO.coatel}"  placeholder="請輸入電話" />
																		<p class="msg">${errorMsgs.coatel}</p>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col">
																	<div class="form-group">
																		<label>信箱</label>
																		<input class="form-control" type="TEXT" name="coamail" size="45" value="${coaVO.coamail}" placeholder="請輸入信箱" readonly/>
																		<p class="msg">${errorMsgs.coamail}</p>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col mb-3">
																	<div class="form-group">
																		<label>自我介紹</label>
																		<textarea class="form-control" name="coaintro" rows="5"  placeholder="請輸入自我介紹">${coaVO.coaintro}</textarea>
																		<p class="msg">${errorMsgs.coaintro}</p>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col">
																	<div class="form-group">
																		<label>匯款帳戶</label>
																		<input class="form-control" type="TEXT" name="coaacc" size="45" value="${coaVO.coaacc}"  placeholder="請輸入帳戶" />
																		<p class="msg">${errorMsgs.coaacc}</p>
																	</div>
																</div>
																<div class="col">
																	<div class="row" style="margin-left:15px;">
																		<label>性別</label>
																		<div style="margin-top:30px; margin-left:50px">
																		<input type="radio" name="coasex" size="45" value="男" <%if (coaVO.getCoasex().equals("男")) {%> checked <%}%> />
																		男</div>
																        <div style="margin-top:30px; margin-left:100px">
																		<input type="radio" name="coasex" size="45" value="女" <%if (coaVO.getCoasex().equals("女")) {%> checked <%}%> />
																		女</div>
																    </div>
															    </div>
															 </div>
														<div class="row">
															<div class="col">
																<br>
																<label>
																	<H6>更改密碼</H6>
																</label>
															</div>
														</div>
														<div class="row">
															<div class="col">
																<div class="form-group">
																	<input class="form-control" type="password" name="coapswOld" size="45" placeholder="請輸入舊密碼" />
																	<p class="msg">${errorMsgs.coapswOld}</p>
																</div>
															</div>
															<div class="col">
																<div class="form-group">
																	<input class="form-control" type="password" name="coapswNew" size="45" placeholder="請輸入新密碼" />
																	<p class="msg">${errorMsgs.coapswNew}</p>
																</div>
															</div>
															<div class="col">
																<div class="form-group">
																	<input class="form-control" type="password" name="coapswConfirm" size="45" placeholder="請再次輸入新密碼" />
																	<p class="msg">${errorMsgs.coapswConfirm}</p>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col">
																<div class="form-group">
																	<label>
																		<br>
																		<label>
																			<H6>
																				更改專長
																				<span style="color: red; font-size:16px">(若有變動則需重新審核教練資格)</span>
																			</H6>
																		</label>
																	</label>
																	<br>
																	<div style="display: flex;justify-content: center;">
																	<button type="button" class="btn btn-success" id="add-exp" style="width: 200px">新增專長</button>
																	</div>	
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col">
																<div class="card-columns" style="column-count: 3 !important;">
																	<c:forEach var="expOwnVO" items="${expOwnVOs}">
																		<div class="card exp">
																			<div class="frame" style="width:250px; height:350px;overflow:hidden;">
																				<a href="#" class="pop">
																					<img src="data:image/png;base64,${expOwnVO.expownStr}" class="card-img-top expown" style="max-width:250px;_width:expression(this.width > 250px ? '250px' : this.width);" alt="證照或獎狀圖">
																				</a>
																			</div>
																			<div class="card-body">
																				<h5 class="card-title">${expOwnVO.expdesc}</h5>
																			</div>
																			<button data-expno="${expOwnVO.expno}" type="button" class="btn btn-secondary delete-current-exp" style="background-color:lightblue; color:black;">刪除證照</button>
																		</div>
																			
																	</c:forEach>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col d-flex justify-content-end">
																<jsp:useBean id="expSvc" scope="page" class="com.expertise.model.ExpService" />
																<input type="hidden" name="action" value="update" />
																<input type="hidden" name="coano" value="${coaVO.coano}" />
																<input type="hidden" name="deleteExpnos" id="deleteExpnos"/>
																<input class="btn btn-primary" type="submit" value="確認修改" style="margin-top: 20px;" />
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
	</div>
	</form>
	</div>

	<%@ include file="/front-end/footer.jsp"%>

</body>
<script src="<%=request.getContextPath()%>/js/custom-js/coach/updateCoach.js"></script>
<script>
	
	function renameExpown(){
		var expNumber = 1;
		$(".card.exp").each(function(){
			$(this).find("input").attr("name", "expown" + expNumber);
			$(this).find("select").attr("name", "expno" + expNumber);
			expNumber++;
		});
	}

	function readURL(input, previewElement) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				previewElement.attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	
	
	$("#pic").change(function() {
		readURL(this, $("#previewPic"));
	});

	var expnoDeleteList = [];
	$(".delete-current-exp").click(function() {
		// add expno for deleting
		expnoDeleteList.push($(this).data("expno"));
		
		// set delete expnos to hidden input 
		$("#deleteExpnos").val(expnoDeleteList.join(","));
		console.log($("#deleteExpnos").val());
		
		// delete expno card
		$(this).closest(".card").remove();
		// reorder input names
		renameExpown();
	});
	
	$("#add-exp").click(
			function() {
				$(this).parent().parent().parent().parent().after(
						"<div class='row'>" + "<div class='col'>" + "<div class='card exp'>" + "<div class='frame' style='display:block; margin:auto; width:311.2px; height:auto;overflow:hidden;'>"
								+ "<img src='"+ context + "/images/NoData/nopic.jpg'  style=' width:auto;max-width:311.2px;_width:expression(this.width > 311.2 ? '311.2px' : this.width);'>" + "<div class='card-body'>"
								+ "<input type='FILE' class='exp-preview' name='expown' size='45' placeholder='請上傳證照/獎狀' style='margin-bottom:10px;'/>"
								+ "<span>專長:&nbsp&nbsp</span><select size='1' name='expno'>" + "<c:forEach var='expVO' items='${expSvc.all}'>"
								+ "<option value='${expVO.expno}'}>${expVO.expdesc}" + "</c:forEach>" + "</select>" + "<input type='button' value='刪除專長' class='delete-exp' style='background-color:lightblue; border-width:1px;border-style:solid;margin-top:10px;'>"
								+ "</div>" + "</div>" + "</div>" + "</div>" + "</div>");

				// bind click event for new element
				$(".exp-preview").change(function() {
					readURL(this, $(this).parent().parent().find("img"));
				});

				$(".delete-exp").click(function() {
					$(this).closest(".row").remove();
					renameExpown();
				});

				renameExpown();
			}
		);
</script>
</html>