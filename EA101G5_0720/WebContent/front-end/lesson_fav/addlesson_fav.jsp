<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.student.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lesson_order.model.*" %>
<%@ page import="com.lesson_fav.model.*" %>
<%@ page import="com.lesson.model.*" %>


<%
	//Lesson_orderService lordSvc = new Lesson_orderService();
	//List<Lesson_orderVO> list = lordSvc.getfindByStuno("S001");
	
	//Lesson_orderVO lesson_orderVO = (Lesson_orderVO) request.getAttribute("lesson_orderVO");
	

	
	Lesson_favVO lesson_favVO = (Lesson_favVO) request.getAttribute("lesson_favVO");
	if(lesson_favVO!=null){
	
	LessonService lessonSvc = new LessonService();
	
	LessonVO lessonVO = lessonSvc.getOneByPK(lesson_favVO.getLessno());
	

	}

%>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>課程追蹤頁面</title>

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
   
   
   
   
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/lesson_fav/lesson_fav.do">

        <div class="item_block">
          <label>學員編號</label>
          <input type="text" name="Stuno" value="${stuVO.stuno}"  placeholder="test">
        </div>

         <div class="item_block">
          <label>追蹤課程編號</label>
          <input type="text" name="Lessno" value="${lessonVO.lessno}" placeholder="test">
        </div>

   
   


      <input type="hidden" name="action" value="insert">
         <input type="hidden" name="Stuno" value="${lesson_favVO.stuno}" >
		<input type="submit" value="加入追蹤">
		
	</FORM>
   

   
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/lesson_fav/lesson_fav.do">
   			           <input type="hidden" name="Stuno" value="${lesson_favVO.stuno}" >
   			           <input type="hidden" name="Lessno" value="${lesson_favVO.lessno}" >
   			  
   			   <input type="hidden" name="action" value="fav">
				<input type="submit" value="我的追蹤">
		</FORM>

</body>




</html>