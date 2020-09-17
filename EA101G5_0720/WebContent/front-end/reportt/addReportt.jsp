<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.reportt.model.*"%>
<%@ page import="com.article.model.*"%>
 <%@ include file="/front-end/header.jsp" %>
<%
	ReporttVO reporttVO = (ReporttVO) request.getAttribute("reporttVO");
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>


<%-- <%=mrVO == null%>--${mrVO.memno}--//line 100 --%>

<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>首頁</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
<%--   <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/article/editormd.min.css"> --%>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/article/images/me.css">
  <!-- Favicon  -->
  <link rel="icon" href="<%=request.getContextPath()%>/images/core-img/favicon.ico">

  <!-- Core Style CSS -->
<%--   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/css/core-style.css"> --%>
<%--   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"> --%>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">
  
<%--   <script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script> --%>
  
</head>

<body bgcolor='white'>

   <!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>首頁</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/article/editormd.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/article/images/me.css">
  <!-- Favicon  -->
  <link rel="icon" href="<%=request.getContextPath()%>/images/core-img/favicon.ico">

  <!-- Core Style CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/css/core-style.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">
  
  <script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
  
  <style type="text/css">
  .table1 {
  	margin-top:200x;
  }
  
  .footer {
  	margin-top:285px;
  }
  
  .nice-select{
  width:720px;
  }
  </style>
</head>

<body bgcolor='white'>


  

   <!--中間内容-->
  <div  class="m-container-small m-padded-tb-big">
    <div class="ui container table1">
 <form action="<%=request.getContextPath()%>/reportt/reportt.do" method="post" class="ui form">
      
				<div class="required field">
					<div class="ui left labeled action input">
						<label class="ui compact teal basic label">文章標題:</label>
						 <jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
							<select size="1" name="artNo">
								<c:forEach var="articleVO" items="${articleSvc.all}">
									<option value="${articleVO.artNo}">${articleVO.artTitle}
<%-- 									<option value="${articleVO.artTitle}">${articleVO.artTitle} --%>
								</c:forEach>
							</select>
					</div>
				</div>




		<div class="required field">
          <div class="ui left labeled input">
            <div class="ui compact teal basic label">
              <div class="text">檢舉內容:</div>
              <div class="menu">
              </div>
            </div>
            <input type="TEXT" name="repdesc" placeholder="檢舉內容"
			 value="<%= (reporttVO==null)? "" : reporttVO.getRepDesc()%>" />
          </div>
        </div>
        
<!--         <div class="required field"> -->
<!--           <div class="ui left labeled input"> -->
<!--             <div class="ui compact teal basic label"> -->
<!--               <div class="text">檢舉時間:</div> -->
<!--               <div class="menu"> -->
<!--               </div> -->
<!--             </div> -->
<!--             <input type="TEXT" name="repdate" placeholder="檢舉時間" -->
<%-- 			 value="<%= (reporttVO==null)? "" : reporttVO.getRepDate()%>" /> --%>
<!--           </div> -->
<!--         </div> -->

        <div class="ui right aligned container">
		<input type="hidden" name="action" value="insertReport">
        <input type="hidden" name="artno" value="${reporttVO.artNo}">
<!-- 		<button type="button" class="ui button" onclick="window.history.go(-1)" >返回</button> -->
		<button type="submit" id="publish-btn" class="ui teal button">送出檢舉</button>
		</div>
     </div>
   </div>        
</form>
  </body>
  <div class="footer">
<%@ include file="/front-end/footer.jsp" %>
  </div>       
</html>