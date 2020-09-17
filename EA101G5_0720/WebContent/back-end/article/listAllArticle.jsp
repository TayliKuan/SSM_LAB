<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page  import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<!DOCTYPE html>


<%
		ArticleService articleSvc = new ArticleService();
		List<ArticleVO> list = articleSvc.getAll();
		pageContext.setAttribute("list",list);
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

<body>


<div class="table-responsive-sm table-hover table-success">
<%@ include file="/back-end/backinclude.jsp"%>

	<!--中間内容-->
  <div  class="m-container-small m-padded-tb-big">
    <div class="ui container">
      <table class="ui celled table">
        <thead>
        <tr>
			<th>文章編號</th>
			<th>文章標題</th>
			<th>文章照片</th>
			<th>文章時間</th>
			<th>文章狀態</th>
			<th>學員編號</th>
			<th>教練編號</th>
			<th>修改文章</th>
		</tr>
        </thead>
    <%@ include file="page1.file" %> 
	<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        <tbody>
          <tr>
           	<td>${articleVO.artNo}</td>
			<td>${articleVO.artTitle}</td>
			<div class="five wide column">
			<td><img src="<%=request.getContextPath()%>/ProductReader?artno=${articleVO.artNo}" 
            width=80px height=45px class="ui rounded image"></td>
            </div>
			<td>${articleVO.artDate}</td>
			<td>${articleVO.artSta}</td>
			<td>${articleVO.stuNo}</td> 
			<td>${articleVO.coaNo}</td> 
            <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/article.do" >
			     <input class="ui mini red basic button" type="submit" value="修改">
			     <input type="hidden" name="artNo"  value="${articleVO.artNo}">
			     <input type="hidden" name="action"	value="getOne_For_BackendUpdate"></FORM>
			</td>
        </tbody>
  </c:forEach>
      </table>
  <%@ include file="page2.file" %>
    </div>
  </div>
  <br><br><br>

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

