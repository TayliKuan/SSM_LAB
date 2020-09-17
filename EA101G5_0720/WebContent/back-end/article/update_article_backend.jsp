<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%
  ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); 

%>

<html>
<head>
<title>FitMate管理後台</title>
<meta charset="utf-8">

<style>
	body{ 
	background-color: #C3E6CB !important;
	}
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
<%--  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/article/images/me.css"> --%>
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
  <script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
<body>


<div class="table-responsive-sm table-hover table-success">
<%@ include file="/back-end/backinclude.jsp"%>
  
  
 
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
  		
  		<div class="required field">
          <div class="ui left labeled action input">
             <label class="ui compact teal basic label">文章狀態:</label>
             
           	 <select class="ui search dropdown" name="artSta">
           	 	<div class="default text">請選擇</div>
    			<option value="文章顯示" ${(articleVO.artSta=="文章顯示")?'checked':''}>文章顯示</option>
  				<option value="文章隱藏" ${(articleVO.artSta=="文章隱藏")?'checked':''}>文章隱藏</option>
			</select>
               </div>
            </div>
            <br><br><br>
            
            
  		
<div class="ui right aligned container">  
<button type="button" class="ui button" onclick="window.history.go(-1)" >返回</button>
<input type="hidden" name="action" value="backend_update">
<input type="hidden" name="artno"  value="${articleVO.artNo}">
<input type="hidden" name="stuNo"  value="${articleVO.stuNo}">
<input type="hidden" name="coaNo"  value="${articleVO.coaNo}">
<input class="ui teal button" type="submit" value="送出修改">
 </div>
 </form>
			 	</div>
			</div>
		</div>
</body>

  <br><br><br>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>

<script>
	CKEDITOR.replace( 'artCon' );
</script>


</html>