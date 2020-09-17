 <%@page import="com.lesson_order.model.Lesson_orderService"%>
<%@page import="com.lesson_fav.model.Lesson_favService"%>
<%@page import="java.util.List"%>
<%@page import="com.lessonTime.model.LessonTimeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lesson.model.*"%>

<%@ include file="/front-end/header.jsp" %>
<%
	LessonVO lessonVO = (LessonVO) request.getAttribute("lessonVO");
	LessonTimeService ltsvc = new LessonTimeService();
	String lessno = lessonVO.getLessno();

	List<String> old = ltsvc.getOneTime(lessno);
	pageContext.setAttribute("role", session.getAttribute("role"));
	
	String stuno = (String)session.getAttribute("stuno");
	LessonService lessonSvc1 = new LessonService();
	
	String lesssta = lessonSvc1.getOneByPK(lessno).getLesssta();
	pageContext.setAttribute("lesssta", lesssta);
	
	Lesson_orderService lesson_orderSvc = new Lesson_orderService();
	boolean Y = lesson_orderSvc.getfindByStuno(stuno).stream().anyMatch( vo -> vo.getLessno().equals(lessno));
	pageContext.setAttribute("Y", Y);

	Lesson_favService lesson_favSvc = new Lesson_favService();
	boolean LY = lesson_favSvc.getfindByStuno(stuno).stream().anyMatch(o ->o.getLessno().equals(lessno));
	pageContext.setAttribute("LY", LY);
	
// 	System.out.println("已購買"+Y);
// 	System.out.println("已追蹤"+LY);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>FitMate</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css'>
<link rel="icon" href="${pageContext.request.contextPath}/images/core-img/FIT.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/css/core-style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom-css/regular-page.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom-css/lesson/lesson_detail.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">

<style>
	.btn-success {
		position: absolute;
		bottom: 20px;
		right: 130px;
	}
	.btn-danger {
		position: absolute;
		bottom: 20px;
		right: 20px;
	}
	#title {
		margin-left: 50px;
	}
	.btn {
		font-size: 15px;
	}
	.button {
		cursor: pointer;
	}
	h4 {
		font-size: 18px;
		color: black !important;
	    display: block !important;
	}
	.swal2-title{
		display:center !important;
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
	.swal2-title {
    display: flex !important;
    justify-content: center !important;
	}
	label {
    display: inline-block;
    margin-bottom: .5rem;
    margin-left: 200px;
}

</style>
</head>
<body>

	<div class="cart-bg-overlay"></div>
	<div class="single-blog-wrapper">
	<div class="single-blog-post-thumb">
		<img src="${pageContext.request.contextPath}/images/bg-img/LessonDetail.png" alt="">
	</div>
	
<%-- 追蹤成功 如果從c拿到followOK 就跳sweetalert--%>
	<c:if test="${not empty followOK}">
		<script>
			$(document).ready(function(){
				swal.fire("追蹤成功", "", "success");
			});
		</script>
	</c:if>

<%--是學員身分 就拿出對應學員點數資料--%>	
	<c:if test="${role == 'student'}">
		<% 
		StuService stusvc = new StuService();
		int Stupoint = stusvc.getOneStu(stuno).getStupoint();
		pageContext.setAttribute("Stupoint", Stupoint);
		%>
	</c:if>

<section>
	<h1 id="title">${lessonVO.lessname}</h1>
<div class="container">
	<div class="row justify-content-center">
		<div class="col-6">
			<div class="thumbnail">
				<img src="<%=request.getContextPath()%>/lesson/PicServletJDBC.do?lessno=${lessonVO.lessno}" class="img-responsive" alt="">
			<div class="caption">
			
<%--是學員身分 就長出這塊 可以追蹤或是購買--%>	
	<c:if test="${role == 'student'}">
		<div class="row buttons">
		
		<%--學員身 追蹤按鈕--%>	
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/lesson_fav/lesson_fav.do" class="col-6">
				<button type="submit" class="btn  col-sm-10 col-sm-offset-1 btn-lg" style="background-color: #2894FF; color: #fff; font-size: 1em;"
					<c:if test='${lesssta == "下架" || lesssta == "已結束" || LY}'> disabled="disabled" title="課程以下架或已追蹤" </c:if> >
					<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;加入追蹤
				</button>
				
				<input type="hidden" name="rurl" value="<%=request.getServletPath()%>"> 
				<input type="hidden" name="Lessno" value="${lessonVO.lessno}">
				<input type="hidden" name="Stuno" value="${stuno}">
				<input type="hidden" name="action" value="insert"> 
			</FORM>
			
			<%--學員 購買按鈕--%>	
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/lesson_order/lesson_order.do" class="col-6" id="goform">
				<button  class="btn col-sm-10 col-sm-offset-1 btn-lg gogogo" type="button" style="background-color: #fb641b; color: #fff; font-size: 1em; " <c:if test='${lesssta == "下架" || lesssta == "已結束" || Y}'> disabled="disabled" title="課程已下架或已購買" </c:if>   >
					<i class="fa fa-bolt" style="font-size: 1.2em;"></i>&nbsp;立即購買
				</button>
						
				<input type="hidden" name="Stuno" value="${stuno}"> 
		        <input type="hidden" name="Lessno" value="${lessonVO.lessno}">
				<input type="hidden" id="newpoint" name ="newpoint" value="${Stupoint-lessonVO.lessprice}">
				<input type="hidden" name="action" value="insert">										
			</FORM>
			
			<%--學員 點數不夠 至儲值點數--%>
			<form action="<%=request.getContextPath()%>/deposit/deposit.do" method="post" id = "depform">
				<input type="hidden" name="stuno" value="${stuVO.stuno}">
				<input type="hidden" name="action" value="goInsert"> 
			</form>			
			<%--學員 點數不夠 至儲值點數js alert--%>
				<script>
				$(".gogogo").click(function(){
					if($("#newpoint").val()<=0){
						var yes = confirm('點數不夠要儲值嗎?？');
						if (yes) {
					 		$("#depform").submit();
						} else {
						    alert('取消儲值');
						}
					}else{
						var yes = confirm('確定要購買嗎?');
						if (yes) {
					 		$("#goform").submit();
						} else {
						    alert('取消購買');
						}
					}
				});
				</script>																
		</div>
	</c:if>

<!--------------------------------------------------------------------------------------------------------------------------------->
<%--非學員身分 (訪客或教練)就長出這塊--%>									
	<c:if test="${role != 'student'}">
		<div class="row buttons">
		<%--非學員身分 (訪客或教練)課程下架無法點 沒下架跳出alert 登入--%>		
			<button type="submit" class="btn  col-sm-4 col-sm-offset-2 btn-lg" style="background-color: #2894FF; color: #fff; font-size: 1em;" id="follow" 
				<c:if test="${lessonVO.lesssta=='下架'}">value="Disabled" disabled</c:if>>
				<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;加入追蹤
			</button>

			<button class="btn col-sm-4 col-sm-offset-1 btn-lg" id="buy" style="background-color: #fb641b; color: #fff; font-size: 1em;"
				<c:if test="${lessonVO.lesssta=='下架'}">value="Disabled" disabled</c:if>>
				<i class="fa fa-bolt" style="font-size: 1.2em;"></i>&nbsp;立即購買
			</button>
		</div>
		<%--非學員身分 (訪客或教練)alert 登入--%>	
			<script>
			document.getElementById("follow").addEventListener("click", function() {
				swal.fire("提醒", "請先以學員身分登入", "warning");
			});
			document.getElementById("buy").addEventListener("click", function() {
				swal.fire("提醒", "請先以學員身分登入", "warning");
			});
			</script>
	</c:if>
		</div>
	</div>
</div>
<!--------------------------------------------------------------------------------------------------------------------------------->
<%--以下為課程詳情--%>						
	<div class="col-6">
		<div>
			<h4 class="button">
				<span class="glyphicon glyphicon-calendar" class="button"></span>
				查看課程時段 <i class="fa fa-chevron-right " class="button"></i>
			</h4>

			<div class="toggler">
				<div class="alert alert-primary">
					<h3 class="alert alert-primary">此堂課程時段如下</h3>
					<p><%@ include file="/front-end/lesson/showOneLessonTime.jsp" %></p>
				</div>
			</div>
			<br>
	<jsp:useBean id="lessonSvc" scope="page" class="com.lesson.model.LessonService" />
			<h4>
				<i class="glyphicon glyphicon-bullhorn"></i><strong>課程類型 : </strong>
				<c:forEach var="expertiseVO" items="${lessonSvc.allExpByExpno}">
					<c:if test="${lessonVO.lesstype==expertiseVO.expno}">${expertiseVO.expdesc}</c:if>
				</c:forEach>
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>課程簡介:</strong> ${lessonVO.lessdesc}
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>課程地點 : </strong>${lessonVO.lessloc}
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>課程堂數 : </strong>${lessonVO.lesstimes} 堂
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>販售點數 : </strong> ${lessonVO.lessprice} 點
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>目前報名人數 :</strong> ${lessonVO.lesscur} 人
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>最低成團人數 : </strong> ${lessonVO.lessmin} 人
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>最高人數限制 : </strong> ${lessonVO.lessmax} 人
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>開始報名日 : </strong> ${lessonVO.lessstart}
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>截止報名日 : </strong> ${lessonVO.lessend}
			</h4>
			<h4>
				<span class="glyphicon glyphicon-thumbs-up"></span><strong>課程狀態 : </strong> 
				<span class="badge badge-pill badge-danger" style="font-size:18px;">${lessonVO.lesssta}</span>
			</h4>
		</div>
			<br>
		<!--至教練頁面-->
				<form action="<%=request.getContextPath()%>/coach/coach.do" method="post">
					<input type="hidden" name="coano" value="${lessonVO.coano}">
					<input type="hidden" name="action" value="getOne">
					<button type="submit" class="btn btn-dark">查看教練介紹</button>
				</form>
		</div>
<!-- 學員身分 秀出對應的點數 表格-->
	<c:if test="${role == 'student'}">
		<div class="container" style="margin-bottom:40px; font-size:18px;">
			<div class="col-6 ">
				<table class="col-5 justify-content-start">
				  <thead>
				    <tr>
				      <th scope="col"  class="table-secondary">我的點數</th>
				      <th scope="col"  class="table-secondary">結帳後剩餘點數</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">${Stupoint}</th>
				      <td>${Stupoint-lessonVO.lessprice}</td>
				    </tr>
				  </tbody>
				</table>
			</div>		
		</div>		
	</c:if>		
			
<!-- 地圖 -->
	<div class="col-12">
		<h4>
			<span class="glyphicon glyphicon-thumbs-up"></span><strong>上課地點 : </strong>
		</h4>
		<iframe width="1100" height="600" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
			src=https://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=${lessonVO.lessloc}&z=16&output=embed&t=></iframe>
	</div>
	<!-- ifram 嵌入googleMap 備註
	width=寬度 height=高度 
	q=輸入查詢的完整地址
	z=地圖比例大小，可輸入 1-18 (建議最佳16)
	t=模式，沒輸入值時為預設地圖(建議)；h為衛星圖加路線；p為地形圖
	 -->
	<input type="button" value="返回活動總覽" class="btn btn-dark" onclick="location.href='<%=request.getContextPath()%>/front-end/lesson/listAll_lesson.jsp'">
	</div>
	</div>
</section>
</div>

<%@ include file="/front-end/footer.jsp" %>

	<script src="${pageContext.request.contextPath}/js/jquery/jquery-2.2.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins.js"></script>
	<script src="${pageContext.request.contextPath}/js/classy-nav.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/active.js"></script>

	<script>
		$(document).ready(function() {
			$(".toggler").hide();

			$(".button").click(function() {
				$(".toggler").toggle();
			});
		});
	</script>
</body>
</html>