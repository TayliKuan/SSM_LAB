<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/article/images/me.css">
  <!-- Favicon  -->
  <link rel="icon" href="<%=request.getContextPath()%>/images/core-img/favicon.ico">

  <!-- Core Style CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/css/core-style.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">
  <script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
    <style>
 
  .table-success, .table-success>td, .table-success>th {
   background-color:#f8b300 !important;
   }
</style>
<body>
 
<div class="table-responsive-sm table-hover table-success">
<!--中間内容-->
  <div  class="m-container-small m-padded-tb-big">
    <div class="ui container">
      <form action="article.do" method="post" class="ui form"   enctype="multipart/form-data">
      
      <div class="required field">
          <div class="ui left labeled input">
          <label class="ui teal basic label">文章標題:</label>
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
		
		 <div class="field">
          <div class="ui left labeled input">
            <label class="ui teal basic label">建立時間:</label>
           <input type="hidden" name="artDate" placeholder="請填入建立時間"
					value="<%=(articleVO == null) ? "" : articleVO.getArtDate()%>" >
					<div class="ui fluid selection dropdown">${articleVO.artDate}
                <div class="menu">
                </div>
              </div>
          </div>
         </div>
       
         <div class="field">
          <div class="ui left labeled input">
            <label class="ui teal basic label">修改照片:</label>
            <input type="file" name="photo" size="50" />
            </div>
  		</div>
<div class="ui right aligned container">  
<button type="button" class="ui button" onclick="window.history.go(-1)" >返回</button>
<input type="hidden" name="action" value="update">
<input type="hidden" name="artno"  value="${articleVO.artNo}">
<input type="hidden" name="stuNo"  value="${articleVO.stuNo}">
<input type="hidden" name="coaNo"  value="${articleVO.coaNo}">
<input class="ui teal button" type="submit" value="送出修改">
 </form>
			 </div>
		</div>
   </div>
</body>
<%@ include file="/front-end/footer.jsp" %>    
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>

<script>
	CKEDITOR.replace( 'artCon' );
</script>


</html>