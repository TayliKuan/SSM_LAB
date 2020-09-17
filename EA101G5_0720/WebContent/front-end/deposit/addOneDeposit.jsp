<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.deposit.model.*"%>
<%@ page import="com.student.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/header.jsp" %>

<%
	String stuno = (String)session.getAttribute("stuno");
	StuService stuSvc = new StuService();
	StuVO stuVO = stuSvc.getOneStu(stuno);
	pageContext.setAttribute("stuVO", stuVO);
%>

<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<title>點數儲值頁面</title>
<style>
.swal2-title {
    display: flex !important;
    justify-content: center !important;
}
#footer{
	margin-top:100px;
}
</style>
</head>
<body>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
		<div>
			<img src="<%=request.getContextPath()%>/images/deposit/ad02.png" style="width:1920px;height:350px">
		</div>
		<div id="main">
			<h1>點數儲值</h1>
			<a href="<%=request.getContextPath()%>/front-end/deposit/deposit_index.jsp">返回我的錢包</a>
		</div><br><br>
		<div class="container">
		<form action="<%=request.getContextPath()%>/deposit/deposit.do" method="post" class="form-group">
			<table>
				<tr>
					<td>請選擇儲值金額: &nbsp;</td>
					<td><select size="1" name="depprice">
						<option value="1000">1000
						<option value="2000">2000
						<option value="3000">3000
						<option value="5000">5000
						<option value="10000">10000
						<option value="200000">100000
					</select></td>
				</tr>
				
				<tr>
					<td>目前持有點數:${stuVO.stupoint}</td>
				</tr>
			</table><br>
			
			<input type="button" value="進入線上付款頁面" id="confirm" class="btn btn-outline-warning">
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="stuno" value="${stuVO.stuno}">
		</form>	<br>
			<p style="color:red;font-size:12px">*FitMate溫馨小提醒:本網站點數與新台幣兌換比率為&nbsp;1&nbsp;:&nbsp;1</p>
		</div>
<script>
	$(document).ready(function(){
		$('input:button').on('click',function(e){
			e.preventDefault();
			swal.fire({
				title:'注意',
				text:'接下來將進入線上刷卡頁面,請再次確定金額是否正確?',
				icon:'warning',
				showCancelButton: true,
				dangerMode:true,
				reverseButtons: true
			}).then((result)=>{
				if(result.value){
				window.open('<%=request.getContextPath()%>/front-end/deposit/creditcard_index.html','線上刷卡',config='height=1080px,width=1940px');	
				setTimeout(function(){
					$('input:button').parent('form').submit();
				},1500); 
				} else {
					swal.fire('取消','已取消儲值作業','error');
				}
			});
		});
	});
	
	
	
	
</script>


</body>
<div id="footer">
<%@ include file="/front-end/footer.jsp" %>
</div>
</html>