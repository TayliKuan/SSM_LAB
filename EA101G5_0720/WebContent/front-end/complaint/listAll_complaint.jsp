<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.complaint.model.*"%>
<%@ page import="com.student.model.*" %>
<%@ include file="/front-end/header.jsp" %>

<jsp:useBean id="complaintSvc" scope="page" class="com.complaint.model.ComplaintService" />

<%

    List<ComplaintVO> list = complaintSvc.getAll();
    pageContext.setAttribute("list",list);

//    StuService stusrv = new StuSrvice();
//    List<StuComplaintVO> stulist = complaintSvc.getAll();
//    pageContext.setAttribute("stulist",stulist);

//    CaoService caosrv = new CaoService();
//    List<CoaVO> coalist = coaSvc.getAll();
//    pageContext.setAttribute("coalist",coalist);

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
    <!-- ##### Header Area Start ##### -->
    <header class="header_area">
        <div class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
            <!-- Classy Menu -->
            <nav class="classy-navbar" id="essenceNav">
                <!-- Logo -->
                <a class="nav-brand" href="index.html"><img src="${pageContext.request.contextPath}/img/core-img/logo.png" alt=""></a>
                <!-- Navbar Toggler -->
                <div class="classy-navbar-toggler">
                    <span class="navbarToggler"><span></span><span></span><span></span></span>
                </div>
                <!-- Menu -->
                <div class="classy-menu">
                    <!-- close btn -->
                    <div class="classycloseIcon">
                        <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                    </div>
                    <!-- Nav Start -->
                    <div class="classynav">
                        <ul>
                            <li><a href="index.html">首頁</a></li>
                            <li><a href="blog.html">消息</a></li>
                            <li><a href="#">商城</a>
                                <div class="megamenu">
                                    <ul class="single-mega cn-col-4">
                                        <li class="title">服飾</li>
                                        <li><a href="shop.html">男士服飾</a></li>
                                        <li><a href="shop.html">女士服飾</a></li>
                                        <li><a href="shop.html">T-shirts</a></li>
                                        <li><a href="shop.html">Jackets</a></li>
                                        <li><a href="shop.html">Trench</a></li>
                                    </ul>

                                    <ul class="single-mega cn-col-4">
                                        <li class="title">健身相關</li>
                                        <li><a href="shop.html">健身食品</a></li>
                                        <li><a href="shop.html">健身配件</a></li>
                                        <li><a href="shop.html">T-shirts</a></li>
                                        <li><a href="shop.html">Jackets</a></li>
                                        <li><a href="shop.html">Trench</a></li>
                                    </ul>
                                   
                                    <ul class="single-mega cn-col-4">
                                        <li class="title">促銷專案</li>
                                        <li><a href="shop.html">母親節</a></li>
                                        <li><a href="shop.html">父親節</a></li>
                                        <li><a href="shop.html">T-shirts</a></li>
                                        <li><a href="shop.html">Jackets</a></li>
                                        <li><a href="shop.html">Trench</a></li>
                                    </ul>
               
                                </div>
                            </li>
                            <li><a href="#">課程</a>
                                <ul class="dropdown">
                                    <li><a href="index.html">課程總覽</a></li>
                                    <li><a href="shop.html">瑜珈</a></li>
                                    <li><a href="single-product-details.html">肌力訓練</a></li>
                                    <li><a href="checkout.html">溜冰/滑板</a></li>
                                    <li><a href="blog.html">有氧課程</a></li>
                                    <li><a href="single-blog.html">海上/海下運動</a></li>
                                    <li><a href="regular-page.html">登山健行</a></li>
                                    <li><a href="contact.html">重量訓練</a></li>
                                    <li><a href="contact.html">球類運動</a></li>
                                    <li><a href="contact.html">武術</a></li>
                                    <li><a href="contact.html">其他</a></li>
                                </ul>
                            </li>
                            <li><a href="contact.html">揪團</a></li>
                            <li><a href="#">學員</a>
                                <ul class="dropdown">
                                    <li><a href="index.html">個人資料</a></li>
                                    <li><a href=".html">查看課表</a></li>
                                     <li><a href=".html">購買清單</a></li>
                                    <li><a href=".html">錢包管理</a></li>
                                    <li><a href=".html">個人信箱</a></li>

                                </ul>
                            </li>
                            <li><a href="blog.html">討論區</a></li>
                            
                        </ul>
                    </div>
                    <!-- Nav End -->
                </div>
            </nav>

            <!-- Header Meta Data -->
            <div class="header-meta d-flex clearfix justify-content-end">
                <!-- Search Area -->
            
                <!-- User Login Info -->
                <div class="user-login-info">
                    <a href="#"><img src="${pageContext.request.contextPath}/img/core-img/user.svg" alt=""></a>
                </div>
                 <div class="user-login-info">
                    <a href="#"><img src="${pageContext.request.contextPath}/img/core-img/email.svg" alt=""></a>
                </div>
                

            </div>

        </div>
    </header>
    <!-- ##### Header Area End ##### -->

   
    <!-- ##### Blog Wrapper Area Start ##### -->
    <div class="single-blog-wrapper">

        <!-- Single Blog Post Thumb -->
        <div class="single-blog-post-thumb">
         
        </div>

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8">
                    <div class="regular-page-content-wrapper section-padding-80">
                        <div class="regular-page-text">
                            <h1>檢舉管理頁面</h1>
    
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
                                  <th scope="col">檢舉編號</th>
                                  <th scope="col">學員</th>
                                  <th scope="col">被檢舉教練</th>
                                  <th scope="col">檢舉原因</th>
                                   <th scope="col">檢舉狀態</th>
                                    <th scope="col">回復</th>
                                   <th scope="col">刪除</th>
                                </tr>
                              </thead>
                       
	<%@ include file="pages/page1.file" %> 
	
	
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
    
 
	
	
	<c:forEach var="complaintVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			  
                               
            <th scope="col">${complaintVO.comno}</th>
            <th scope="col">${complaintVO.stuno}
             <c:if test="${complaintVO.stuno== 'S001'}">
         			   蔡阿嘎
	          </c:if>
            </th>
            <th scope="col">${complaintVO.coano}
              <c:if test="${complaintVO.coano== 'C001'}">
         			    陳純甄     
	          </c:if>

            </th>
            
      
            
            <th scope="col">${complaintVO.comdesc}</th>
            <th scope="col"> <c:if test="${complaintVO.comsta==0}">
                   	未處理
                    </c:if><c:if test="${complaintVO.comsta==1}">
                   	已處理
                    </c:if>
            </th>   
             
            
                             
      
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/complaint/complaint.do" style="margin-bottom: 0px;">
			     <input type="submit" value="回復"<c:if test="${complaintVO.comsta==1}"> value="Disabled" disabled
                    </c:if>>
	
			     <input type="hidden" name="comno"  value="${complaintVO.comno}">
			     <input type="hidden" name="action"	value="updatecomsta"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/complaint/complaint.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="comno"  value="${complaintVO.comno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	 </tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
    
    
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
 
         
   <%@ include file="/front-end/footer.jsp" %>
    <!-- ##### Footer Area End ##### -->

    <!-- jQuery (Necessary for All JavaScript Plugins) -->
    <script src="${pageContext.request.contextPath}/css/js/popper.min.js"></script>
    <!-- Popper js -->
   	<script src="${pageContext.request.contextPath}/css/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Bootstrap js -->
    <script src="${pageContext.request.contextPath}/css/js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="${pageContext.request.contextPath}/css/js/plugins.js"></script>
    <!-- Classy Nav js -->
    <script src="${pageContext.request.contextPath}/css/js/classy-nav.min.js"></script>
    <!-- Active js -->
    <script src="${pageContext.request.contextPath}/css/js/active.js"></script>


</body>




</html>