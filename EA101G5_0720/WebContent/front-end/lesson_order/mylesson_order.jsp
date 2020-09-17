<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.complaint.model.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lesson_order.model.*"%>
<%@ page import="com.lesson.model.*"%>

<%@ include file="/front-end/header.jsp" %>

<%
String stuno = (String)session.getAttribute("stuno");
pageContext.setAttribute("stuno",stuno);
	Lesson_orderService lordSvc = new Lesson_orderService();
	List<Lesson_orderVO> list = lordSvc.getfindByStuno(stuno);

	//Lesson_orderVO lesson_orderVO = (Lesson_orderVO) request.getAttribute("lesson_orderVO");

	//Lesson_orderVO lesson_orderVO = lordSvc.findByPrimaryKey("20200619-LO002");

	LessonService lessonSvc = new LessonService();
	//LessonVO lessonVO = (LessonVO) request.getAttribute("lessonVO");
	//LessonVO lessonVO = lessonSvc.getOneByPK(lesson_orderVO.getLessno());
	List<LessonVO> lessonlist = lessonSvc.getAllLesson();

	pageContext.setAttribute("list", list);
	pageContext.setAttribute("lessonlist", lessonlist);
%>

<html>

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
<link rel="icon" href="img/core-img/favicon.ico">


<!-- Core Style CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/css/core-style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/custom-css/regular-page.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap"
	rel="stylesheet">
<!-- 星星icon用 -->
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<script
	src="${pageContext.request.contextPath}/js/jquery/jquery-2.2.4.min.js"></script>



<style type="text/css">
.nice-select {
	display: none;
}

select {
	display: inline !important;
}

.fa-goyellow {
	color: yellow;
}
.regular-page-content-wrapper {
margin-bottom: 220px;
}

table{
font-size:18px;
}
</style>



</head>
<body>
<div class="single-blog-wrapper" style="background-color: black;">
        <div class="single-blog-post-thumb">
            <img src="<%=request.getContextPath()%>/images//bg-img/STU1920.png" alt="">
        </div>
</div>
	
	<!-- ##### Header Area End ##### -->


	<!-- ##### Blog Wrapper Area Start ##### -->
	<div class="single-blog-wrapper">

		<!-- Single Blog Post Thumb -->
		<div class="single-blog-post-thumb"></div>

		<div class="container col-12">
			<div class="row justify-content-center">
				<div class="col-12 col-md-8">
					<div class="regular-page-content-wrapper section-padding-80">
						<div class="regular-page-text">
							<h1>我的課程訂單</h1>

							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>


							<%
								for (LessonVO lessvo : lessonlist)
									System.out.println(lessvo.getLessname());

								for (Lesson_orderVO test : list)
									System.out.println(test.getLord_no());
							%>



							<table class="table col-12">
								<thead>
									<tr>
										<th scope="col">課程訂單編號</th>
<!-- 										<th scope="col">課程編號</th> -->
										<th scope="col">課程名稱</th>
<!-- 										<th scope="col">課程類型</th> -->
										<th scope="col">課程狀態</th>
										<th scope="col">開課時間</th>
										<th scope="col">課程點數</th>
										<th scope="col">訂單時間</th>
										<th>評價</th>
										<th>客訴</th>
									</tr>
								</thead>


								<%@ include file="/pages/page1.file"%>

								<c:forEach var="lesson_orderVO" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">



									<c:forEach var="lessonVO" items="${lessonlist}">
										<c:if test="${lesson_orderVO.lessno==lessonVO.lessno}">

											<tr>

												<td id="lord_no" scope="col">${lesson_orderVO.lord_no}</td>
<%-- 												<td scope="col">${lessonVO.lessno}</td> --%>
												<td scope="col">${lessonVO.lessname}</td>
<%-- 												<td scope="col">${lessonVO.lesstype}</td> --%>
												<td scope="col">${lessonVO.lesssta}</td>
												<td scope="col">${lessonVO.lessstart}</td>
												<td scope="col">${lessonVO.lessprice}</td>
												<td scope="col"><fmt:formatDate
														value="${lesson_orderVO.lord_time}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td class="star" width="150px">
													<!-- 下方 JS 會判斷輸入的星數 -->
													<div class="star">
														<input class="star-f" type="hidden"
															value=${lesson_orderVO.lord_sc} > <a href="#"
															class="star-count-1"> <i class="fa fa-lg fa-star-o"
															aria-hidden="true"></i>
														</a> <a href="#" class="star-count-2"> <i
															class="fa fa-lg fa-star-o " aria-hidden="true"></i>
														</a> <a href="#" class="star-count-3"> <i
															class="fa fa-lg fa-star-o  fa-ttt" aria-hidden="true"></i>
														</a> <a href="#" class="star-count-4"> <i
															class="fa fa-lg fa-star-o " aria-hidden="true"></i>
														</a> <a href="#" class="star-count-5"> <i
															class="fa  fa-lg fa-star-o" aria-hidden="true"></i>
														</a>

													</div>
												</td>
<td scope="col">
      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/complaint/complaint.do">
          <input type="hidden" name="Stuno" value="${stuno}" >
          <input type="hidden" name="Coano" value="${lessonVO.coano}">
       	 <input type="hidden" name="action" value="add" >
       	 
		<button type="submit" value="客訴">客訴</button>
</FORM>		
</td>

												
				
													
												
										
												
												
											</tr>



										</c:if>
									</c:forEach>
								</c:forEach>
							</table>


							<%@ include file="/pages/page2.file"%>

							<script type="text/javascript">

//網頁載入時判斷是否評分過如果有評分過顯示STAR的CSS
$(document).ready(function() {
		
$(".star").find('input').each(function(){
	//console.log($(this));	 
	
	if($(this).val()!=='0'){
		var count = parseInt($(this).val());
		//console.log(count);
		
		for(var i = 0; i<count ; i++){
			$(this).parent().find("i").eq(i).removeClass("fa-star-o");
			$(this).parent().find("i").eq(i).addClass("fa-star fa-goyellow");
		}

	}
});
});	
	//判斷是否評價過
    jQuery("i").click(function(){   	
    	if($(this).parent().parent().children().eq(0).val()!=="0"){
    		
    		//console.log($(this).parent().parent().children().eq(0).val());
    		
    	}else{
    	
    		if (confirm("評價只能給一次  [確定] 或 [取消] "))
    	　　　 {　alert("[確定] 謝謝你給的評價" );  　
    	    		//console.log("0.0");
 
        	event.preventDefault();
        		
        	$(this).parent().prevAll().find("i").removeClass("fa-star-o");
        	$(this).parent().prevAll().find("i").addClass("fa-star fa-goyellow");
        	var pa =$(this).parent().prevAll()
        	$(this).removeClass("fa-star-o");
        	$(this).addClass("fa-star fa-goyellow");
        
       		var lord_sc = (pa.size());       	
       		console.log(pa.size());
        	var na = $(this).parent().nextAll().find("i").removeClass("fa-star fa-goyellow");    		
        				
        	$(this).parent().nextAll().find("i").addClass("fa-star-o");
        	
        	
        	$(this).parent().parent().children().eq(0).val(lord_sc);   	
     		
        	var lessno=$(this).parent().parent().parent().parent().children().eq(0).text();
    	
   	
        	$.post("lesson_order_sc.do",
        			
        			{
        				lord_no:lessno, lord_sc:lord_sc
        			},function(data, status){
        				if(status == "success")
        					console.log(data);
        			}
        		        			
        			)
        		
    	 	}			
      	    else{　
        		alert("[取消]")} 			
        	}	
    	
    });
    		


</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




	<!-- ##### Blog Wrapper Area End ##### -->




<%@ include file="/front-end/footer.jsp" %>
</body>


</html>