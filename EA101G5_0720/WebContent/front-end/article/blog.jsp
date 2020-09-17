<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page  import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.student.model.*"%>
<!DOCTYPE html>

<%
		CoaService coachSvc = new CoaService();
		List<CoaVO> coalist = coachSvc.getAll();
		pageContext.setAttribute("coalist",coalist);

		StuService studentSvc = new StuService();
		List<StuVO> stulist = studentSvc.getAll();
		pageContext.setAttribute("stulist",stulist);
		
		ArticleService articleSvc = new ArticleService();
		List<ArticleVO> list = articleSvc.getAllDisplay();
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

 <%@ include file="/front-end/header.jsp" %>

  <!--中間內容-->
  <div  class="container padded-tb-big">
    <div class="ui container">
      <div class="ui stackable grid">
        <!--blog左邊列表-->
        <div class="eleven wide column">
          <!--header-->
          <div class="ui top attached segment">
            <div class="ui middle aligned two column grid">
              <div class="column">
                <h3 class="ui teal header">文章分享</h3>
              </div>
              <div class="right aligned column">
<%--                 共 <h2 class="ui orange header m-inline-block m-text-thin" th:text="${articleVO.totalartNo}">20</h2> 篇 --%>
              </div>
            </div>
          </div>

          <!--content-->
          <div class="ui attached  segment">
		<c:forEach var="articleVO" items="${list}" >
            <div class="ui padded vertical segment m-padded-tb-large">
              <div class="ui mobile reversed stackable grid">
                <div class="eleven wide column">
                   <h2 class="ui header" target="_blank">
                   <a href="<%=request.getContextPath()%>/front-end/article/article.do?action=getOne_For_Display&artno=${articleVO.artNo}">
                   ${articleVO.artTitle}</a></h2>
                   <p class="m-text"></p>
                  <div class="ui grid">
                    <div class="eleven wide column">
                      <div class="ui mini horizontal link list">
                        <div class="item">
<!--                           <img src="https://unsplash.it/100/100?image=1005" alt="" class="ui avatar image"> -->
                          <div class="content">
                          <a href="article.do?action=getOne_For_Display&artno=${articleVO.artNo}" class="header"></a>
                         <c:forEach var="StuVO" items="${stulist}" > <c:if test ="${StuVO.stuno == articleVO.stuNo}" >${StuVO.stuname}</c:if></c:forEach>  
                         <c:forEach var="CoaVO" items="${coalist}" > <c:if test ="${CoaVO.coano == articleVO.coaNo}" >${CoaVO.coaname}</c:if></c:forEach>  
                   		 </div>
                        </div>
                        <div class="item">
                          <i class="calendar icon"></i>${articleVO.artDate}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="five wide column">
                  <a href="<%=request.getContextPath()%>/front-end/article/article.do?action=getOne_For_Display&artno=${articleVO.artNo}">
                    <img src="<%=request.getContextPath()%>/ProductReader?artno=${articleVO.artNo}" width=800px height=450px class="ui rounded image">
<!--                <img src="https://unsplash.it/800/450?image=1005" alt="" class="ui rounded image"> -->
                  </a>
                </div>
             </div>
            </div>
<%--           </c:forEach>   --%>
<%--           </c:forEach>   --%>
          </c:forEach>  

          </div>

          <!--footer-->
          <div class="ui bottom attached segment">
            <div class="ui middle aligned two column grid">
              <div class="column">
                <a href="#" class="ui mini teal basic button">上一頁</a>
              </div>
              <div class="right aligned column">
                <a href="#" class="ui mini teal basic button">下一頁</a>
              </div>
            </div>
          </div>
        </div>
          <!--右邊的top-->
		  <div class="five wide column">
	<c:if test="${role == 'coach' || role == 'student'}">
          <!--新增-->
          <div class="ui segments">
            <div class="ui secondary segment">
              <div class="ui two column grid">
                <div class="column">
                  <i class="bookmark icon"></i>新增文章
                </div>
                <div class="right aligned column">
                </div>
              </div>
            </div>
            <div class="ui teal segment">
              <div class="ui fluid vertical menu" >
                <a href="<%=request.getContextPath()%>/front-end/article/addblog.jsp"  target="_blank" class="item">
                  <span  th:text="${type.name}">新增文章</span>
                </a>
              </div>
            </div>
          </div>
       </c:if>
          <!--檢舉文章-->
          <div class="ui segments">
            <div class="ui secondary segment">
              <div class="ui two column grid">
                <div class="column">
                  <i class="idea icon"></i>檢舉文章
                </div>
              </div>
            </div>
            <div class="ui teal segment">
              <div class="ui fluid vertical menu" >
                <a href="<%=request.getContextPath()%>/front-end/reportt/addReportt.jsp"  target="_blank" class="item">
                  <span th:text="${type.name}">檢舉文章內容</span>
                </a>
              </div>
            </div>
          </div>

      </div>
    </div>
	</div>
  </div>
<%@ include file="/front-end/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>

  <script>
    $('.menu.toggle').click(function () {
      $('.m-item').toggleClass('m-mobile-hide');
    });
  </script>
</body>
</html>