<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.reportt.model.*"%>
<%@ page import="com.article.model.*"%>

<%
	ReporttVO reporttVO = (ReporttVO) request.getAttribute("reporttVO");
%>
<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>
<%-- <%=reporttVO == null%>--${reporttVO.stuNo}--//line 100 --%>
<!DOCTYPE html>
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

<div  class="m-container m-padded-tb-big">
    <div class="ui container">
        <FORM METHOD="post" ACTION="reportt.do" class="ui form" >

        <div class="required field">
          <div id="md-content" style="z-index: 1 !important;">
            <textarea placeholder="博客内容" name="content" style="display: none" th:text="*{content}"></textarea>
          </div>
        </div>

        <div class="two fields">
          <div class="required field">
            <div class="ui left labeled action input">
              <label class="ui compact teal basic label">文章編號:</label>
              <div class="ui fluid selection dropdown">${articleVO.artNo}
                <div class="menu">
                </div>
              </div>
            </div>
          </div>
          <div class=" field">
            <div class="ui left labeled action input">
              <label class="ui compact teal basic label">文章時間:</label>
              <div class="ui fluid selection dropdown">${articleVO.artDate}
                <div class="menu">
                </div>
              </div>
            </div>
          </div>
        </div>
        

        <div class="two fields">
          <div class="required field">
            <div class="ui left labeled action input">
              <label class="ui compact teal basic label">檢舉編號:</label>
              <div class="ui fluid selection dropdown">${reporttVO.repNo}
                <div class="menu">
                </div>
              </div>
            </div>
          </div>
          <div class=" field">
            <div class="ui left labeled action input">
              <label class="ui compact teal basic label">檢舉內容:</label>
              <div class="ui fluid selection dropdown">${reporttVO.repDesc}
                <div class="menu">
                </div>
              </div>
            </div>
          </div>
        </div>
        
         <div class="two fields">
          <div class="required field">
            <div class="ui left labeled action input">
              <label class="ui compact teal basic label">(發文者)學員編號:</label>
              <div class="ui fluid selection dropdown">${articleVO.stuNo}
                <div class="menu">
                </div>
              </div>
            </div>
          </div>
          <div class=" field">
            <div class="ui left labeled action input">
              <label class="ui compact teal basic label">(發文者)教練編號:</label>
              <div class="ui fluid selection dropdown">${articleVO.coaNo}
                <div class="menu">
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <div class="required field">
          <div class="ui left labeled input">
            <div class="ui compact teal basic label">
              <div class="text">文章標題:</div>
              <div class="menu">
              </div>
            </div>
            <input type="text" name="title" value="${articleVO.artTitle}">
          </div>
        </div>
        
        <div class="required field">
          <div class="ui left labeled input">
            <div class="ui compact teal basic label">
              <div class="text">檢舉時間:</div>
              <div class="menu">
              </div>
            </div>
            <input type="text" name="title" value="${reporttVO.repDate}">
          </div>
        </div>
        
        <div class="required field">
          <div class="ui left labeled action input">
              <label class="ui compact teal basic label">檢舉處理狀態:</label>
              
              <div class="ui fluid selection dropdown">
                <input type="hidden" name="repsta" value="${reporttVO.repSta}">
                <i class="dropdown icon"></i>
                <div class="text">${reporttVO.repSta}</div>
                <div class="menu">
                <div class="item" value="未處理" ${(reporttVO.repSta=="未處理")?'checked':''}>未處理</div>
                <div class="item" value="檢舉通過" ${(reporttVO.repSta=="檢舉通過")?'checked':''}>檢舉通過</div>
                <div class="item" value="檢舉未通過" ${(reporttVO.repSta=="檢舉未通過")?'checked':''}>檢舉未通過</div>
                </div>
              </div>
            </div>
            </div>

     	<div class="ui right aligned container">
     	<input type="hidden" name="action" value="update">
		<input type="hidden" name="repNo" value="<%=reporttVO.getRepNo()%>"> 
		<input type="hidden" name="artno" value="<%=reporttVO.getArtNo()%>"> 
		<input type="hidden" name="repdesc" value="<%=reporttVO.getRepDesc()%>"> 
		<input type="hidden" name="repdate" value="<%=reporttVO.getRepDate()%>"> 
		<input type="hidden" name="stuno" value="<%=reporttVO.getStuNo()%>"> 
		<input type="hidden" name="coano" value="<%=reporttVO.getCoaNo()%>"> 
		<input type="hidden" name="repsta" value="<%=reporttVO.getRepSta()%>"> 

        <button type="button" class="ui button" onclick="window.history.go(-1)" >返回</button>
<!--           <button type="button" id="save-btn" class="ui secondary button">保存</button> -->
          <button type="submit" id="publish-btn" class="ui teal button">送出修改</button>
        </div>
      </form>
    </div>
  </div>
  
  <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
  

 <!--/*/<th:block th:replace="admin/_fragments :: script">/*/-->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
  <!--/*/</th:block>/*/-->


  <script>

    //初始化Markdown编辑器
    var contentEditor;
    $(function() {
      contentEditor = editormd("md-content", {
        width   : "100%",
        height  : 640,
        syncScrolling : "single",
//        path    : "../static/lib/editormd/lib/"
        path    : "/lib/editormd/lib/"
      });
    });
    $('.menu.toggle').click(function () {
      $('.m-item').toggleClass('m-mobile-hide');
    });

    $('.ui.dropdown').dropdown({
      on : 'hover'
    });

//     $('#save-btn').click(function () {
//       $('[name="published"]').val(false);
//       $('#blog-form').submit();
//     });


    $('#publish-btn').click(function () {
      $('[name="published"]').val(true);
      $('#blog-form').submit();
    });




  </script>
</div>
</body>
</html>