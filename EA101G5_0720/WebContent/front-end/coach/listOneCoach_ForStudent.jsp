<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.expertise_own.model.*"%>
<%@ page import="java.util.List"%>


<%
	String role = (String) session.getAttribute("role");
	pageContext.setAttribute("role", role);
	pageContext.setAttribute("context", request.getContextPath());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>教練個人資料詳情 - listOneCoach_ForStudent.jsp</title>
<style>
.monyyyy {
	width: 750px;
	height: 30px;
	overflow: auto;
	position: relative;
}

.fa-goyellow {
	color: yellow;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</head>
<body style="background: #f8b302;">

	<%@ include file="/front-end/header.jsp"%>

	<div class="single-blog-wrapper" style="font-size: 18px; color: white;">

		<div class="single-blog-post-thumb" style="padding-top: 80px;">
			<img src="<%=request.getContextPath()%>/images/bg-img/coach1920.png" alt="">
		</div>

		<div class="row" style="margin: 100px;">

			<div class="col">
				<div class="card" style="height: auto; border-radius: 20px; background-color: black; line-height: 26px;">
					<div class="card-body">
						<div class="row">
							<div class="col">
								<h5 class="card-title" style="color: white; text-align: center; padding: 5px;">跟著專業FitMate,一起運動去!!!</h5>
								<div class="card-body" style="width: 200; height: 400px; position: relative;">
									<img src="data:image/png;base64,${coaVO.coapicStr}" class="card-img-top expown" style="width: 400px; height: 400px; max-width: 100%; max-height: 100%; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); border-radius: 200px; margin: 0 auto;" alt="教練照片">
								</div>
							</div>
							<div class="col">
								<h5 class="card-title" style="color: #f8b302;">關於我</h5>
								<p class="card-text">
								<div>
									<span>
										課程總評分：&nbsp&nbsp&nbsp&nbsp
										<span>評價人數：</span>${totalLessonCount}
										<div class="star lesson">
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
										</div>
									</span>

								</div>
								<div>
									<span>
										活動總評分：&nbsp&nbsp&nbsp&nbsp
										<span>評價人數：</span>${totalActivityCount}
										<div class="star activity">
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
											<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>
										</div>
									</span>

								</div>

								<div>
									<span>姓名：</span>${coaVO.coaname}</div>
								<div>
									<span>性別：</span>${coaVO.coasex}</div>
								<div>
									<span>專長：</span>
									<c:forEach var="expOwnVO" items="${expOwnVOs}">
										<p style="color: SteelBlue; font-weight: bolder; font-size: 18px;">${expOwnVO.expdesc}</p>
									</c:forEach>
								</div>
								<div>
									<span>自我介紹：</span>
									<br>${coaVO.coaintro}</div>
								<button type="button" class="btn btn-primary" style="margin-top: 10px" id="schedule-coach">預約教練</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row" style="margin: 100px;">
			<div class="col">
				<%@ include file="/front-end/lesson/timeTable.jsp"%>
			</div>
		</div>

	</div>

	<%@ include file="/front-end/footer.jsp"%>

	<script>
		$(document).ready(function() {

			// display stars
			var totalLessonAvg = parseInt("${totalLessonAvg}");
			for (var i = 0; i < totalLessonAvg; i++) {
				$(".star.lesson").find("i").eq(i).removeClass("fa-star-o");
				$(".star.lesson").find("i").eq(i).addClass("fa-star fa-goyellow");
			}

			var totalActivityAvg = parseInt("${totalActivityAvg}");
			for (var i = 0; i < totalActivityAvg; i++) {
				$(".star.activity").find("i").eq(i).removeClass("fa-star-o");
				$(".star.activity").find("i").eq(i).addClass("fa-star fa-goyellow");
			}

			$("#schedule-coach").click(function() {
				if ("${role}" == "student") {
					window.location.href = "${context}" + "/front-end/activity/activity_create.jsp";
				} else {
					Swal.fire({
						icon : 'warning',
						text : '請先以學員身份登入!',
					})
				}
			});

			//login 跑板
			$('.user-login-info').on('click', function() {
				$('#login-modal').find('label').css('margin', '0 auto');
				$('#login-modal').find('h2').css('font-size', '30px');
				$('#login-modal').find('h2').css('padding-bottom', '10px');
			});

		});
	</script>

</body>
</html>