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
	
	//Lesson_orderVO lesson_orderVO = (Lesson_orderVO) request.getAttribute("lesson_orderVO");
	
	Lesson_orderVO lesson_orderVO = lordSvc.findByPrimaryKey("20200623-LO001");
	
	
	LessonService lessonSvc = new LessonService();
	//LessonVO lessonVO = (LessonVO) request.getAttribute("lessonVO");
	LessonVO lessonVO = lessonSvc.getOneByPK(lesson_orderVO.getLessno());
	

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


</style>



</head>
<body>
   
   
   
   
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/lesson_order/lesson_order.do">

        <div class="item_block">
          <label>學員編號</label>
          <input type="text" name="Stuno" value="${stuVO.stuno}"  placeholder="test">
        </div>

         <div class="item_block">
          <label>購買課程編號</label>
          <input type="text" name="Lessno" value="${lessonVO.name}" placeholder="test">
        </div>

   
   


      <input type="hidden" name="action" value="insert">
		<input type="submit" value="GO買">
	</FORM>
   
   
    <%@ include file="/front-end/footer.jsp" %>

</body>




</html>