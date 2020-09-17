<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.complaint.model.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/front-end/header.jsp" %>


<%
//    StuService stuSvc = new StuService();
//    List<StuVO> list = stuSvc.getAll();
//    pageContext.setAttribute("list",list);
    
//    CoachService coaSvc = new CoachService();
//    List<coaVO> caolist = coaSvc.getAll();
//    pageContext.setAttribute("caolist",caolist);
%>


<%
  ComplaintVO complaintVO = (ComplaintVO) request.getAttribute("complaintVO");
  
String stuno = (String)request.getAttribute("stuno");

	StuService stusvc = new StuService();
	StuVO stuVO= stusvc.getOneStu(stuno);
	pageContext.setAttribute("stuVO",stuVO);

	
String coano = (String)request.getAttribute("coano");
	
	CoaService coasvc = new CoaService();
	CoaVO coaVO = coasvc.getOneCoa(coano);
pageContext.setAttribute("coaVO",coaVO);


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
    <link rel="icon" href="${pageContext.request.contextPath}/img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css/core-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom-css/regular-page.css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">

<style type="text/css">


label{
    font-size: 20px;
        display: block;
}
#magic{
width:150px;
height:100%;   
float: right;
}

</style>

</head>

<body>

    <!-- ##### Blog Wrapper Area Start ##### -->
    <div class="single-blog-wrapper">

        <!-- Single Blog Post Thumb -->
         <div class="single-blog-post-thumb">
            <img src="<%=request.getContextPath()%>/images//bg-img/STU1920.png" alt="">
        </div>

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8">
                    <div class="regular-page-content-wrapper section-padding-80">
                        <div class="regular-page-text">
                            <h1>客訴</h1>
    
		 <%-- 錯誤表列 --%><div>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>
		<img src="${pageContext.request.contextPath}/images/lesson/complaint.jpg" id="magic">
      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/complaint/complaint.do">

        <div class="item_block">
          <label>客訴人:${stuVO.stuname}</label>
          <input type="hidden" name="Stuno" value="${stuno}" >
        </div>

         <div class="item_block">
          <label>被客訴教練:${coaVO.coaname}</label>
          <input type="hidden" name="Coano" value="${coaVO.coano}" >
        </div>

   

		  <div class="form-group">
    <label for="exampleFormControlTextarea1">原因：</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" name="com_drsc" rows="3"></textarea>
  		</div>

      <input type="hidden" name="action" value="insert">
		<button type="submit" value="">送出客訴</button>
	</FORM>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Blog Wrapper Area End ##### -->

<%@ include file="/front-end/footer.jsp" %>
    <!-- ##### Footer Area End ##### -->

    <!-- jQuery (Necessary for All JavaScript Plugins) -->
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <!-- Popper js -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Bootstrap js -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="${pageContext.request.contextPath}/js/plugins.js"></script>
    <!-- Classy Nav js -->
    <script src="${pageContext.request.contextPath}/js/classy-nav.min.js"></script>
    <!-- Active js -->
    <script src="${pageContext.request.contextPath}/js/active.js"></script>
<script>

$(document).ready(function(){
	$('#magic').on('click',function(){
		$('#exampleFormControlTextarea1').val('教學不認真 都在哈囉');
	
	});
});
</script>
</body>




</html>