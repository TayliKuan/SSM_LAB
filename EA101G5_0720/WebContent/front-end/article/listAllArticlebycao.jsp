<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page  import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
 <%@ include file="/front-end/header.jsp" %>
<!DOCTYPE html>

<%
		String coano = (String)session.getAttribute("coano");
		ArticleService articleSvc = new ArticleService();
		List<ArticleVO> list = articleSvc.getByCoano(coano);
		pageContext.setAttribute("list",list);
%>

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
  
  <style>
 
  .table-success, .table-success>td, .table-success>th {
   background-color:#f8b300 !important;
   }
</style>
</head>

<body>

<div class="single-blog-post-thumb">
			<img
				src="${pageContext.request.contextPath}/images/bg-img/COA1920.png"
				alt="">
		</div>
<div class="table-responsive-sm table-hover table-success">


	<!--中間内容-->
  <div  class="m-container-small m-padded-tb-big">
    <div class="ui container">
      <table class="ui celled table">
        <thead>
        <tr>
			<th>文章標題</th>
			<th>文章照片</th>
			<th>文章時間</th>
			<th>修改文章</th>
		</tr>
        </thead>
    <%@ include file="page1.file" %> 
	<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        <tbody>
          <tr>
			<td>${articleVO.artTitle}</td>
			<div class="five wide column">
			<td><img src="<%=request.getContextPath()%>/ProductReader?artno=${articleVO.artNo}" 
            width=80px height=45px class="ui rounded image"></td>
            </div>
			<td>${articleVO.artDate}</td>
            <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/article.do" >
			     <input class="ui mini red basic button" type="submit" value="修改">
			     <input type="hidden" name="artNo"  value="${articleVO.artNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
        </tbody>
  </c:forEach>
      </table>
    </div>
  </div>
  <br><br><br>
<%@ include file="/front-end/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>

  <script>
    $('.menu.toggle').click(function () {
      $('.m-item').toggleClass('m-mobile-hide');
    });

    $('.ui.dropdown').dropdown({
      on : 'hover'
    });

  </script>
</div>
</body>
</html>

