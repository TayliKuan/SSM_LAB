<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.complaint.model.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lesson_order.model.*" %>
<%@ page import="com.lesson.model.*" %>

<%@ include file="/front-end/header.jsp" %>
<%
	Lesson_orderService lordSvc = new Lesson_orderService();
	//List<Lesson_orderVO> list = lordSvc.getfindByStuno("S001");
	
	Lesson_orderVO lesson_orderVO = (Lesson_orderVO) request.getAttribute("lesson_orderVO");
	
	//Lesson_orderVO lesson_orderVO = lordSvc.findByPrimaryKey("20200619-LO002");
	
	
	LessonService lessonSvc = new LessonService();
	//LessonVO lessonVO = (LessonVO) request.getAttribute("lessonVO");
	LessonVO lessonVO = lessonSvc.getOneByPK(lesson_orderVO.getLessno());
	
	//System.out.println(lesson_orderVO.getLord_no());
	//System.out.println(lessonVO.getLessname());

	
	pageContext.setAttribute("lesson_orderVO",lesson_orderVO);
	pageContext.setAttribute("lessonVO",lessonVO);

%>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>FitMate</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">
  

    <!-- Core Style CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css/core-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom-css/regular-page.css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">

<style type="text/css">

.nice-select{
display:none;
}

select{
display:inline !important;
}

.regular-page-content-wrapper {
margin-bottom: 250px;
}

</style>



</head>
<body>
    
<div class="single-blog-wrapper" style="background-color: black;">
        <div class="single-blog-post-thumb">
            <img src="<%=request.getContextPath()%>/images//bg-img/STU1920.png" alt="">
        </div>
    </div>
    <!-- ##### Blog Wrapper Area Start ##### -->
    <div class="single-blog-wrapper">

        <!-- Single Blog Post Thumb -->
        <div class="single-blog-post-thumb">
         
        </div>

        <div class="container col-12">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8">
                    <div class="regular-page-content-wrapper section-padding-80">
                        <div class="regular-page-text">
                            <h1>課程訂單成立</h1>
    
                     <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

                        <table class="table col-12">
                              <thead>
                                <tr>
                                  <th scope="col">課程訂單編號</th>
                                  <th scope="col">課程名稱</th>
                                  <th scope="col">課程類型</th>
                                  <th scope="col">課程狀態</th>
                                  <th scope="col">開課時間</th>
                                   <th scope="col">課程點數</th>
                                   <th scope="col">訂單時間</th>                             
                                </tr>
                              </thead>
              
                       <tr>
                          <th scope="col">${lesson_orderVO.lord_no}</th>
                                  <th scope="col">${lessonVO.lessname}</th>
                                  <th scope="col">${lessonVO.lesstype}</th>
                                  <th scope="col">${lessonVO.lesssta}</th>
                                  <th scope="col">${lessonVO.lessstart}</th>
                                   <th scope="col">${lessonVO.lessprice}</th>
                                   <th scope="col">${lesson_orderVO.lord_time}</th>  
	 				
	 				</tr>
	 		
</table>


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