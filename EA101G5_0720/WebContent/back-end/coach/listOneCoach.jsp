<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	pageContext.setAttribute("context", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<title>FitMate管理後台</title>
<meta charset="utf-8">

<style>
body {
		background:url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
}
.frame {  
    height: 160px; /*can be anything*/
    width: 160px; /*can be anything*/
    position: relative;
    margin:0px auto;
}
img.expown {  
    max-height: 100%;  
    max-width: 100%; 
    width: auto;
    height: auto;
    position: absolute;  
    top: 0;  
    bottom: 0;  
    left: 0;  
    right: 0;  
    margin: auto;
}
h4{
    color: navy;
    width: 800px;
    
}
#back { 

    
	background-color: skyblue;
	color: #000;
	left: 93%;
	border: none;
	position: absolute;
	/* width: 75px; */
	margin-top: 5% !important;
	/* right: 3%; */
	right: 0;
	top: 0;
}

#back:hover { 
	background-color: steelblue;
	color: #000;
}
</style>
</head>

<body>
	<%@ include file="/back-end/backinclude.jsp"%>
	<div class="article side-open">
		<div id="logo">
			<img src="${context}/images/backend_public/logo.png" alt="">
		</div>
		<div id="main">
			<h2>專長詳情</h2><br>
			<h4>教練編號：${coaVO.coano}</h4><br>
			<h4>教練姓名：${coaVO.coaname}</h4><br>
			<h4>關於我：</h4><h4>${coaVO.coaintro}</h4><br>
		</div>
		<div class="card-group">
			<c:forEach var="expOwnVO" items="${expOwnVOs}">
				<div class="card">
					<div class="frame">
					<a href="#" class="pop">
						<img src="data:image/png;base64,${expOwnVO.expownStr}" class="card-img-top expown" style="width: 400px; height: 400px;" alt="證照或獎狀圖">
						</a>
						<div class="modal fade" id="imagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog" data-dismiss="modal">
						    <div class="modal-content"  >              
						      <div class="modal-body">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						        <img src="" class="imagepreview" style="width: 100%;" >
						      </div> 
						    </div>
						  </div>
						</div>
					</div>
					<div class="card-body">
						<h5 class="card-title">${expOwnVO.expno}</h5>
						<p class="card-text">
						<small class="text-muted">${expOwnVO.expdesc}</small>
						</p>
					</div>
				</div>
			</c:forEach>	
		</div>
	</div>
</body>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Coming+Soon&family=Noto+Sans+TC:wght@700&display=swap" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<!-- bootstrap table & editable dependency -->
<link href="https://cdn.jsdelivr.net/gh/Talv/x-editable@develop/dist/bootstrap4-editable/css/bootstrap-editable.css" rel="stylesheet">
<link href="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/Talv/x-editable@develop/dist/bootstrap4-editable/js/bootstrap-editable.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.15.4/dist/extensions/editable/bootstrap-table-editable.min.js"></script>
<!-- bootstrap table & editable dependency -->

<script>
	/* pass value to js */
	var context = "${context}";
	var coano = "${coano}";
</script>

<!-- custom js -->

<script src="${context}/js/custom-js/coach/listOneCoach.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"></meta>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css/backend.css">

	<div class="back">
		<button type="button" class="btn btn-primary" onclick="goBack()" id="back">回上頁</button> 
	</div>

</html>
