<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
 <%@ include file="/front-end/header.jsp" %>

<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
	
%>


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
  
</head>



<body bgcolor='white'>

  <!--中間內容-->
  <div  class="m-container-small m-padded-tb-big">
    <div class="ui container">
      <form action="<%=request.getContextPath()%>/front-end/article/article.do" METHOD="post" enctype="multipart/form-data" class="ui form">
        <div class="required field">
          <div class="ui left labeled input">
          <label class="ui teal basic label">文章標題</label>
            <input type="text" name="artTitle" placeholder="請填入文章標題"
					value="<%=(articleVO == null) ? "" : articleVO.getArtTitle()%>" >
          </div>
        </div>

         <div class="field">
          <div id="md-content" style="z-index: 1 !important;">
            <textarea placeholder="文章内容" name="artCon" id="artCon" style="display: none">
            <%=(articleVO == null) ? "":articleVO.getArtCon() %>
            </textarea>
          </div>
        </div>
		
<!-- 		 <div class="field"> -->
<!--           <div class="ui left labeled input"> -->
<!--             <label class="ui teal basic label">建立時間</label> -->
<!--            <input type="text" name="artDate" placeholder="請填入建立時間" -->
<%-- 					value="<%=(articleVO == null) ? "" : articleVO.getArtDate()%>" > --%>
<!-- 		 <td><input name="artDate" id="f_date1" type="text"/></td> -->
<!--           </div> -->
<!--          </div> -->
        

        <div class="field">
          <div class="ui left labeled input">
            <label class="ui teal basic label">圖片</label>
            <input type="file" name="photo" size="50" />
          </div>
        </div>

<c:if test="${role == 'coach'}">
	<% 
	String coano = (String)session.getAttribute("coano");
	pageContext.setAttribute("coano",coano);
	
	%>
</c:if>


<c:if test="${role == 'student'}">
	<% 
	String stuno = (String)session.getAttribute("stuno");
	pageContext.setAttribute("stuno",stuno);
	
	%>
</c:if>
        <div class="ui error message"></div>
        <div class="ui right aligned container">
<!--         <button type="button" class="ui button" onclick="window.history.go(-1)" >返回</button> -->
<%--         <input type="hidden" name="artNo" value="${articleVO.artNo}"> --%>
        <input type="hidden" name="stuNo" value="${stuno}">
        <input type="hidden" name="coaNo" value="${coaNo}">
		<input type="hidden" name="action" value="addArticle">
		   <input class="ui teal button" type="submit" value="送出">
		
        </div>

      </form>
    </div>
  </div>
  <br><br>
<%@ include file="/front-end/footer.jsp" %>



<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/article/editormd/editormd.min.js"></script>

  <script>
  
  CKEDITOR.replace( 'artCon' );

    //初始化Markdown编辑器
//     var contentEditor;
//     $(function() {
//       contentEditor = editormd("md-content", {
//         width   : "100%",
//         height  : 640,
//       });
//     });
    
    $('.menu.toggle').click(function () {
      $('.m-item').toggleClass('m-mobile-hide');
    });

    $('.ui.dropdown').dropdown({
      on : 'hover'
    });

    $('.ui.form').form({
      fields : {
        title : {
          identifier: 'title',
          rules: [{
            type : 'empty',
            prompt: '標題：請輸入文章標題'
          }]
        }
      }
    });
    

  </script>
</body>
</html>