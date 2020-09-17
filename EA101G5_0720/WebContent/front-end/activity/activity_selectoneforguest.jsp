<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.activity_fav.model.*"%>
<%@ page import="com.expertise.model.*"%>
<%@ page import="com.activity_order.model.*"%>
<%@ page import="com.deposit.model.*"%>
<%@ page import="com.student.model.*"%>
<%@ include file="/front-end/header.jsp"%>

<%  
	
	ActivityService activitySvc = new ActivityService();
	List<ActivityVO> list = activitySvc.getAllActivity();
	pageContext.setAttribute("list", list);
	
	String stuno = (String)session.getAttribute("stuno");
	pageContext.setAttribute("stuno", stuno);
	
	Activity_favService activity_favSvc = new Activity_favService();
	List<Activity_favVO> list1 = activity_favSvc.findActivityByPrimaryKey(stuno);
	pageContext.setAttribute("list1", list1);
	
	Activity_orderService activity_orderSvc = new Activity_orderService();
	List<Activity_orderVO> list2 = activity_orderSvc.findActivityBystuno(stuno);
	pageContext.setAttribute("list2", list2);
	
	Activity_favVO activity_favVO = (Activity_favVO)request.getAttribute("activity_favVO");
	pageContext.setAttribute("activity_favVO",activity_favVO);
	
	pageContext.setAttribute("role", session.getAttribute("role"));
	
// 	StuService stuSvc = new StuService(); 
// 	int Stupoint = stuSvc.getOneStu(stuno).getStupoint();
// 	pageContext.setAttribute("Stupoint", Stupoint);
	
%>

<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->


<!-- Title  -->
<title>查詢活動詳請 - listAllActivityForGuest.jsp</title>
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
table {
	font-family: 微軟正黑體;
}
.swal2-title {
    display: flex !important;
    justify-content: center !important;
}
}
.fa-goyellow{
	color:yellow;
}
#footerposition{
height:50px;
margin-top:400px;
width:100vw;
z-index:999;
}

/* table.table-striped{ */
/* background-color:ffcdd2!important; */

/* } */

.tablestyle{
border:1px solid black;
width:100%;
margin:40px;
}

.tablestyle tr{
border:1px solid black;
}

.tablestyle td{
border:1px solid black;
/* width:687px; */
height:46px;
font-size:18px;
}

.lefttd{
width:50%;
padding-left:2px;
text-align:center;
}

.ridhttd{
width:50%;
padding-left:2px;
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
</style>

</head>
<body>
	<!-- ##### Header Area Start ##### -->
	<!-- ##### Header Area End ##### -->

	<!-- ##### Blog Wrapper Area Start ##### -->
	<div class="single-blog-wrapper">

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

		<div class="container col-12">
			<div class="row justify-content-center">
				<div class="col-12 col-md-12">
						<div class="regular-page-content-wrapper section-padding-60">
						<div class="regular-page-text">
							<h3>
								${activityVO.actname} <span
									class="badge badge-pill badge-danger">${activityVO.actsta}</span>
							</h3>


							<table border="1"
								class="table table-dark align-items-center align-middle">
								<tr>
									<td rowspan="10" style="width: 480px;"><img
										src="<%=request.getContextPath()%>/activity/activitypic.do?actno=${activityVO.actno}"
										alt="活動圖片" class="rounded float-right img-thumbnail"></td>
									<td style="width: 130px">活動名稱</td>
									<td style="width: 130px">${activityVO.actname}</td>
								</tr>

								<tr>
									<td>活動類型</td>
									<jsp:useBean id="actSvc" scope="page"
										class="com.activity.model.ActivityService" />
									<jsp:useBean id="expSvc" scope="page"
										class="com.expertise.model.ExpService" />

									<td><c:forEach var="expVO" items="${expSvc.all}">
											<c:if test="${activityVO.acttype==expVO.expno}">${expVO.expdesc}</c:if>
										</c:forEach></td>
								</tr>
								<tr>
									<td>活動狀態</td>
									<td>${activityVO.actsta}</td>
								</tr>
								<tr>
									<td>活動地點</td>
									<td>${activityVO.actloc}</td>
									
								</tr>
								<tr>
									<td>最小成團人數</td>
									<td>${activityVO.actmin}</td>

								</tr>
								<tr>
									<td>最大上限人數</td>
									<td>${activityVO.actmax}</td>
								</tr>
								<tr>
									<td>目前報名人數</td>
									<td>${activityVO.actcur}</td>
								</tr>

								<tr>
									<td>活動報名日期</td>
									<td><fmt:formatDate value="${activityVO.actstart}"
											pattern="yyyy-MM-dd" />~ <fmt:formatDate
											value="${activityVO.actend}" pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<td>活動點數</td>
									<td id="getpoint">
									<c:if test = "${(activityVO.actcur > activityVO.actmin)}">
									 <fmt:formatNumber  type="number" value ="${(activityVO.actprice) * 0.8}" pattern ="#,#00"/> 
									 </c:if>
									 <c:if test = "${(activityVO.actcur <= activityVO.actmin)}">
									 ${(activityVO.actprice)}
									 </c:if>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="row align-items-center">
											<form action="<%=request.getContextPath()%>/coach/coach.do" method="post">
					<input type="hidden" name="coano" value="${activityVO.coano}">
					
					<input type="hidden" name="action" value="getOne">
					<button type="submit" class="btn btn-primary">查看教練介紹</button>
				</form>
<!-- 											<button type="button" class="btn btn-primary">查看教練介紹</button> -->
											&nbsp;
											<div>
											<c:if test="${role == 'student'}">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/activity_fav/activityfav.do"
													style="margin-bottom: 0px;">
													<input type="hidden" value="${activityVO.actno}" name="actno"> 
													<input type="hidden" value="${stuno}" name="stuno"> 
													<input type="hidden" name="action" value="insert">
													<input type="submit" id="follow" value="追蹤活動" class="btn btn-warning" id="follow"<c:forEach var="activity_favVO" items="${list1}">
													 
													<c:if test ="${(activity_favVO.actno == activityVO.actno)}"> value="Disabled" disabled  </c:if></c:forEach>>
												
													
												</FORM>
												 <script>
													 	$(document).ready(function(){
															$('#follow').on('click',function(e){
																e.preventDefault();
																Swal.fire({
																	title:'注意',
																	text:'您確定要追蹤此活動嗎',
																	icon:'warning',
																	showCancelButton:true,
																	showConfirmButton:true,
																	dangerMode:true
																}).then((result)=>{
																	if(result.value){
																		setTimeout(function(){
																			$('#follow').parent().submit();
																		},2000);
																		Swal.fire('成功','您已成功追蹤','success');
																	}else
																		Swal.fire('取消','還沒決定好嗎，歡迎再多看看我們的活動喔!!','error');
																});
															});
													 	});
													 </script>
											</c:if>
											<c:if test="${role != 'student'}">
												
													<input type="submit" id="follow" value="追蹤活動" class="btn btn-warning" id="follow"/>
												<script>
			document.getElementById("follow").addEventListener("click", function() {
				swal.fire("提醒", "請先以學員身分登入", "warning");
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
											</div>
											&nbsp;
											<div>
											<c:if test="${role == 'student'}">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/activity_order/activityorder.do"
													style="margin-bottom: 0px;" id="goform">
													
													<input type="hidden" value="${activityVO.actno}" name="actno"> 
													
													<input type="hidden" value="${stuno}" name="stuno">
													
													<c:if test = "${(activityVO.actcur > activityVO.actmin)}">
													<input type="hidden" value="${Stupoint-(activityVO.actprice) * 0.8}" name ="stupoint" id="stupoint">
													</c:if>
													 <c:if test = "${(activityVO.actcur <= activityVO.actmin)}">
									 				<input type="hidden" value="${Stupoint-(activityVO.actprice)}" name ="stupoint" id="stupoint">
													</c:if>
													<input type="hidden" name="action" value="insert">
													<input type="button" id="join" value="立即報名" class="btn btn-danger gogogo" id="joining" 
													<c:forEach var="activity_orderVO" items="${list2}">			
													<c:if test ="${(activity_orderVO.actno == activityVO.actno)}"> 
													value="Disabled" disabled </c:if> </c:forEach>  
													<c:if test ="${activityVO.actsta =='預約未上架'}">value="Disabled" disabled</c:if>
													<c:if test ="${activityVO.actsta =='待預約'}">value="Disabled" disabled</c:if>
													><br>
												</FORM>
												
				<%--學員 點數不夠 至儲值點數--%>
			<form action="<%=request.getContextPath()%>/deposit/deposit.do" method="post" id = "depform">
				<input type="hidden" name="stuno" value="${stuVO.stuno}">
				
				<input type="hidden" name="action" value="goInsert"> 
			</form>			
			<%--學員 點數不夠 至儲值點數js alert--%>
				<script>
				 $(document).ready(function(){ 
					 $(".gogogo").click(function(){ 
						 if($("#stupoint").val()<=0){ 
							 Swal.fire({ 
								 title:'注意', 
								 text:'點數不夠要儲值嗎?', 
								 icon:'warning', 
								 showCancelButton:true, 
								 showConfirmButton:true, 
								 dangerMode:true })
								 .then((result)=>{ 
									 if (result.value) { 
										 setTimeout(function(){ 
											 $("#depform").submit(); },
											 2000); 
										 Swal.fire('注意','您即將進入儲值頁面','warning'); 
										 } else { 
											 Swal.fire('取消','您尚未儲值喔','error'); 
											 } 
									 }); 
							 }else{ 
								 Swal.fire({ 
									 title:'注意', 
									 text:'您確定要此活動嗎', 
									 icon:'warning', 
									 showCancelButton:true, 
									 showConfirmButton:true, 
									 dangerMode:true })
									 .then((result)=>{ 
										 if(result.value){ 
											 setTimeout(function(){ 
												 $('.gogogo').parent().submit(); 
												 },2000); 
											 Swal.fire('成功','感謝您報名Fitmate的活動','success'); 
											 }else Swal.fire('取消','還沒決定好嗎，歡迎再多看看我們的活動喔!!','error');
										 }); 
								 } 
						 }); 
					 });
				</script>								
												</c:if>
												
												<c:if test="${role != 'student'}">
												
												<input type="submit" id="join" value="立即報名" class="btn btn-danger" >
												
												<script>
												document.getElementById("join").addEventListener("click", function() {
													swal.fire("提醒", "請先以學員身分登入", "warning");
												});
												</script>
												</c:if>
											</div>
										</div>
									</td>
								</tr>
							</table>
						<div id="backhomediv">
					<a href="<%=request.getContextPath()%>/front-end/activity/activity_selectallforguest.jsp"  id="backhome">按此返回活動總覽</a></div>

<!-- 學員身分 秀出對應的點數 表格-->
	<c:if test="${role == 'student'}">
		<div class="container" style="margin-bottom:40px; font-size:18px;">
			<div class="col-6 ">
				<table class="col-5 justify-content-start" style=“border: 1px solid #CCCCFF;”>
				  <thead>
				    <tr>
				      <th scope="col"  class="table-secondary" style=“background-color:#CCCCFF; border: 1px solid #CCCCFF;”>我的點數</th>
				      <th scope="col"  class="table-secondary" style=“background-color:#CCCCFF; border: 1px solid #CCCCFF;”>結帳後剩餘點數</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row" style=“background-color: ghostwhite;border: 1px solid #CCCCFF;”>${Stupoint}</th>
				      <td id="test" style=“background-color: ghostwhite;border: 1px solid #CCCCFF;”>

				      </td>
				    </tr>
				  </tbody>
				</table>
			</div>		
		</div>		
	</c:if>		
	<div class="row justify-content-center"><iframe width="1100" height="600"
							src=https://maps.google.com.tw/maps?f=q&hl=zh-TW&geocode=&q=${activityVO.actloc}&z=16&output=embed&t=></iframe></div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ifram 嵌入googleMap
				width=寬度
				height=高度
				q=輸入查詢的完整地址(.net 最好用Server.UrlEncode(string 地址)包起來)  或   經緯度，如果需要標明可在結尾加上()，於()中輸入表示名稱
				z=地圖比例大小，可輸入 1-18 (建議最佳16)
				t=模式，沒輸入值時為預設地圖(建議)；h為衛星圖加路線；p為地形圖
				 -->


<!-- ##### Blog Wrapper Area End ##### -->


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

console.log($("#test").html());
 $("#test").html($("#stupoint").val().trim());
				      
				      </script>
</html>