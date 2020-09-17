<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<meta charset="utf-8">
<style>
body {
	background:
		url("<%=request.getContextPath()%>/images/bg-img/psw.jpg");
	background-position: center center;
}

</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Coming+Soon&family=Noto+Sans+TC:wght@700&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous">
	
</script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
	
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
	
</script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css/backend.css">

<style>

#logo {
    margin-left: -40px;
}


.input {
    padding-left: 124px;
    margin-top: 190px;
}


</style>




</head>

<%
	String title = request.getParameter("title");
	pageContext.setAttribute("title", title);
	pageContext.setAttribute("titleCn", "coach".equals(title) ? "教練" : "學員");
%>


<body>
	<div class="article side-open">
		<!-- logo區開始 -->
		<div id="logo" >
			<img src="<%=request.getContextPath()%>/images/backend_public/logo.png" alt="">
		</div>
		<!-- logo區結束 -->

		
		
       <div class="input">
		<h6 >請輸入${titleCn}信箱</h6>
		<c:if test="${title == 'coach'}">
			<input type="text" name="coamail">
		</c:if>
		<c:if test="${title == 'student'}">
			<input type="text" name="stumail">
		</c:if>
	
		<input type="submit" value="送出查詢" class="btn btn-outline-success my-2 my-sm-0" id="confirm">
		</div>
	</div>

	<script>
	$(document).ready(function() {
		$("#confirm").click(function() {
			$.ajax({
				type : "post",
				url : "<%=request.getContextPath()%>" + "/forgotPsw.do",
					data : "${title}" === "coach" ? "coamail=" + $('input[name=coamail]').val() : "stumail=" + $('input[name=stumail]').val(),
					success : function(data, status) {
						if (data.error_code == "0") {
							alert('信件已寄出,請耐心等待');
						}
						else{
							alert(data.error_msg);
						}
					},
					error : function() {
						alert('提交失敗');
					},
					complete : function() {
					}
				});
			});
		});
	</script>

</body>
</html>
