<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lesson_fav.model.*"%>
<%@ page import="com.lesson.model.*"%>

<%@ page import="com.student.model.*" %>


<%@ include file="/front-end/header.jsp" %>
<%
// 	List <Lesson_favVO> list =(List <Lesson_favVO>) request.getAttribute("list");
// 	pageContext.setAttribute("list",list);

	
String stuno = (String)session.getAttribute("stuno");
pageContext.setAttribute("stuno",stuno);
	Lesson_favService lesson_favScv = new Lesson_favService();
  List<Lesson_favVO> list = lesson_favScv.getfindByStuno(stuno);
    //List<Lesson_favVO> list = lesson_favSvc.getfindByLessno("L002");
	pageContext.setAttribute("list",list);
	

	LessonService lessonSvc = new LessonService();
    List<LessonVO> lessonlist = lessonSvc.getAllLesson();
    pageContext.setAttribute("lessonlist",lessonlist);
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
margin-bottom: 220px;
}

.footer_area {

    margin-top: 200px;
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
                            <h1>課程追蹤頁面</h1>
    
                     <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


                             <table class="table">
                              <thead>
                                <tr>
                                  <th scope="col">課程編號</th>
                                  <th scope="col">課程名稱</th>
                                  <th scope="col">教練</th>
                                  <th scope="col">課程類型</th>
                                   <th scope="col">課程點數</th>
                                   <th scope="col">刪除追蹤</th>
                                   <th scope="col">GO買去</th>
                                </tr>
                              </thead>
                       
	
	                 
	 
<%-- 	<c:forEach var="stuVO" items="${stuSvc.all}"> --%>  
<%--                     <c:if test="${complaintVO.stuno==stuVO.stuno}">--%> 
<%-- 	                    ${stuVO.name}--%> 
<%--                     </c:if>--%> 
<%--     </c:forEach>--%> 
    
    
<%--     <c:forEach var="coaVO" items="${coaSvc.all}">--%> 
<%--                     <c:if test="${complaintVO.coano==coaVO.coano}">--%> 
<%-- 	                    ${coaVO.name}--%> 
<%--                     </c:if>--%> 
<%--     </c:forEach>--%> 
    	
    	



	<%@ include file="/pages/page1.file" %>
	
	<c:forEach var="lesson_favVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
 	 <tr> 
		
		
			<td><c:forEach var="lessonVO" items="${lessonlist}">
                    <c:if test="${lessonVO.lessno==lesson_favVO.lessno}">
	                    ${lessonVO.lessno}
                    </c:if>
                </c:forEach>
			</td>
		
		
				<td><c:forEach var="lessonVO" items="${lessonlist}">
                    <c:if test="${lessonVO.lessno==lesson_favVO.lessno}">
	                    ${lessonVO.lessname}
                    </c:if>
                </c:forEach>
			</td>
			
							<td><c:forEach var="lessonVO" items="${lessonlist}">
                    <c:if test="${lessonVO.lessno==lesson_favVO.lessno}">
	                    ${lessonVO.lesstype}
                    </c:if>
                </c:forEach>
			</td>
			
			
							<td><c:forEach var="lessonVO" items="${lessonlist}">
                    <c:if test="${lessonVO.lessno==lesson_favVO.lessno}">
	                    ${lessonVO.lessname}
                    </c:if>
                </c:forEach>
			</td>
			
			
			
			
							<td><c:forEach var="lessonVO" items="${lessonlist}">
                    <c:if test="${lessonVO.lessno==lesson_favVO.lessno}">
	                    ${lessonVO.lessprice}
                    </c:if>
                </c:forEach>
			</td>
			
			
		
			 <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/lesson_fav/lesson_fav.do" style="margin-bottom: 0px;">
			     <input type="submit" class="btn btn-secondary"  value="刪除">
			     <input type="hidden" name="Stuno"  value="${lesson_favVO.stuno}">
			     <input type="hidden" name="Lessno" value="${lesson_favVO.lessno}">			     
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
	 		<td>

				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lesson/lesson.do" style="margin-bottom: 0px; width:133px">
			     <input type="submit" class="btn btn-secondary"  value="查看詳情">
			    
			     <input type="hidden" name="lessno" value="${lesson_favVO.lessno}">			     
			     <input type="hidden" name="action" value="show_lesson_detail"></FORM>
			</td>

	 		</tr>	 
	    </c:forEach>
</table>
<%@ include file="/pages/page2.file" %>


<script type="text/javascript">




</script>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
 
         

<%@ include file="/front-end/footer.jsp" %>

</body>


</html>