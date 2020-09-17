<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.complaint.model.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/front-end/header.jsp" %>

<jsp:useBean id="complaintSvc" scope="page" class="com.complaint.model.ComplaintService" />

<%

ComplaintVO complaintVO =  (ComplaintVO) session.getAttribute("complaintVO");
pageContext.setAttribute("complaintVO",complaintVO);


StuService stusvc = new StuService();
StuVO stuVO= stusvc.getOneStu(complaintVO.getStuno());
pageContext.setAttribute("stuVO",stuVO);

List<ComplaintVO> list = complaintSvc.findByStuno(complaintVO.getStuno());
    pageContext.setAttribute("list",list);
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
    <link rel="icon" href="${pageContext.request.contextPath}/images/core-img/favicon.ico">
  

    <!-- Core Style CSS -->
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css/core-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom-css/regular-page.css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">

<style type="text/css">

.nice-select{
display:none;
}

select{
display:inline !important;
}

#footer{
margin-top:150px;
}
</style>



</head>
<body>
  

   

    <div class="single-blog-wrapper">

        <!-- Single Blog Post Thumb -->
        <div class="single-blog-post-thumb">
            <img src="img/bg-img/STU1920.png" alt="">
        </div>

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8">
                    <div class="regular-page-content-wrapper section-padding-80">
                        <div class="regular-page-text">
                            <h1>客訴列表</h1>

                            

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
                                 <th scope="col">客訴編號</th>                              
                                  <th scope="col">學員</th>
                                  <th scope="col">被客訴教練</th>
                                  <th scope="col">客訴原因</th>
                                   <th scope="col">客訴狀態</th>
                                </tr>
                              </thead>
                       
	<%@ include file="pages/page1.file"%> 
	
	<c:forEach var="complaintVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			  
                               
            <th scope="col">${complaintVO.comno}</th>

             <th scope="col">${complaintVO.stuno}${stuVO.stuname}
      
            </th>
           <jsp:useBean id="coaSvc" scope="page" class="com.coach.model.CoaService" />
           
		
			           
            <th scope="col">${complaintVO.coano}	<c:forEach var="coaVO" items="${coaSvc.all}"><c:if test="${complaintVO.coano == coaVO.coano}">
         			    ${coaVO.coaname}    
	          </c:if>     </c:forEach>
	                      </th>
       

          
            <th scope="col">${complaintVO.comdesc}</th>
            <th scope="col"> <c:if test="${complaintVO.comsta==0}">
                   	未處理
                    </c:if><c:if test="${complaintVO.comsta==1}">
                   	已處理
                    </c:if>
            </th>   
                         
                          
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>







                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
       
    <!-- ##### Blog Wrapper Area End ##### -->

     <!-- ##### Footer Area Start ##### -->
     <div id="footer">
   <%@ include file="/front-end/footer.jsp" %>
</div>

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

</body>




</html>