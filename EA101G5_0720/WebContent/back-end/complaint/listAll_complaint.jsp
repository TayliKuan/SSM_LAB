<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ page import="java.util.*"%>
<%@ page import="com.complaint.model.*"%>
<%@ page import="com.student.model.*" %>


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
<!DOCTYPE html>
<html>
<head>
<title>FitMate管理後台</title>
<meta charset="utf-8">

<style>
		body{ 
			background:url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
			 background-position: center center;
		}
		
		
		select{
   background: transparent;
   width: 120px;
   padding: 0px;
   font-size: 16px;
 
   height: 34px;		
   }
		
		
</style>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Coming+Soon&family=Noto+Sans+TC:wght@700&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous">
	
</script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous">
	
</script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"
	integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V"
	crossorigin="anonymous">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css/backend.css">
</head>

<body>

	<%@ include file="/back-end/backinclude.jsp"%>

	<!-- 主要內文區開始 -->
	<div class="article side-open">
		<!-- logo區開始 -->
		<div id="logo">
			<img src="images/public/logo透明.png" alt="">
		</div>
		<!-- logo區結束 -->

		<!-- ------------------------------------從這裡開始編輯喔各位！----------------------- -->
		<div id="main">
					<h1>客訴管理頁面</h1>
		</div>

		


    
                     <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


	<%@ include file="/pages/page1.file" %> 
<div class="table-responsive-sm table-hover table-success">

                             <table class="table align-items-center ">
                              <thead>
                                <tr>
                                  <th scope="col">客訴編號</th>
                                  <th scope="col">學員</th>
                                  <th scope="col">被客訴教練</th>
                                  <th scope="col">客訴原因</th>
                                 
                                   <th scope="col">客訴狀態</th>
                                     <th scope="col">客訴時間</th>
                                    <th scope="col">回覆</th>
                                   <th scope="col">刪除</th>
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
    
 
	
	
	<c:forEach var="complaintVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			  
           <jsp:useBean id="stuSvc" scope="page" class="com.student.model.StuService" />
                               
            <th scope="col">${complaintVO.comno}</th>
            <th scope="col">${complaintVO.stuno}
               <c:forEach var="stuVO" items="${stuSvc.all}"><c:if test="${complaintVO.stuno== stuVO.stuno}">
         			    ${stuVO.stuname}    </c:if></c:forEach>
          
            
         
            </th>
            <th scope="col">${complaintVO.coano}
            
                 <jsp:useBean id="coaSvc" scope="page" class="com.coach.model.CoaService" />

         			   <c:forEach var="coaVO" items="${coaSvc.all}"><c:if test="${complaintVO.coano == coaVO.coano}">
         			    ${coaVO.coaname}    
	          
	          </c:if>
	           </c:forEach> 

            </th>
            
      
            
            <th scope="col">${complaintVO.comdesc}</th>
            <th scope="col"> <c:if test="${complaintVO.comsta==0}">
                   	未處理
                    </c:if><c:if test="${complaintVO.comsta==1}">
                   	已處理
                    </c:if>
            </th>   
             

             
            <th scope="col"><fmt:formatDate value="${complaintVO.comdate}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
                                   
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/complaint/listAll_complaint.jsp" style="margin-bottom: 0px;">
			     <input type="submit" value="回覆" class="btn btn-secondary"<c:if test="${complaintVO.comsta==1}"> value="Disabled" disabled
                    </c:if>>
	
			     <input type="hidden" name="comno"  value="${complaintVO.comno}">
			     <input type="hidden" name="action"	value="updatecomsta"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/complaint/listAll_complaint.jsp" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-secondary">
			     <input type="hidden" name="comno"  value="${complaintVO.comno}">
			     <input type="hidden" name="action" value="delete" ></FORM>
			</td>
	 </tr>
	</c:forEach>
</table>

</div>



<%@ include file="/pages/page2.file" %>
		<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->

	</div>	

</body>
</html>