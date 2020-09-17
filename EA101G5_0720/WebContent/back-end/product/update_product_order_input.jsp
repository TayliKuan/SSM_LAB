<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.student.model.*"%>

<%
	Product_orderVO product_orderVO = (Product_orderVO)request.getAttribute("product_orderVO");
	String stuno = (String)product_orderVO.getStuno();
	StuService stuSvc = new StuService();
	StuVO stuVO = stuSvc.getOneStu(stuno);
	pageContext.setAttribute("stuVO", stuVO);
%>
<!DOCTYPE html>
<html>
<head>
<title>FitMate管理後台</title>
<meta charset="utf-8">

<style>
		body{ 
			background:url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
			 background-position: center center;
		}
</style>
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
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js">
	
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js">
	
</script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css/backend.css">
</head>

<body>



	<!-- 主要內文區開始 -->
	<div class="article side-open">
		<!-- logo區開始 -->
		<div id="logo">
			<img src="<%=request.getContextPath()%>/images/backend_public/logo.png" alt="">
		</div>
		<!-- logo區結束 -->

		<!-- ------------------------------------從這裡開始編輯喔各位！----------------------- -->
		<div id="main">
			<h2>修改商品訂單</h2>
		</div>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<a href="<%=request.getContextPath() %>/back-end/product/product_orderManage.jsp">回訂單首頁</a>
	<div class="table-responsive-sm table-hover table-success">
		<table class="table align-items-center">
		<tr>
			<td>訂單編號</td>

			<td><%=product_orderVO.getPordno()%></td>
		</tr>
		<tr>
			<td>學員</td>
			<td>${stuVO.stuname}</td>
		</tr>
		<tr>
			<td>訂購日期</td>
			<td><%=product_orderVO.getPorddate()%></td>
		</tr>	
		<tr>	
			<td>總金額</td>
			<td><%=product_orderVO.getPordtotal()%></td>
		</tr>
		<tr>	
			<td>收件人</td>
			<td><%=product_orderVO.getRecipient()%></td>
		</tr>
		<tr>	
			<td>收件人電話</td>
			<td><%=product_orderVO.getPhonenumber()%></td>
		</tr>
		<tr>	
			<td>送貨地址</td>
			<td><%=product_orderVO.getPordadd()%></td>
		</tr>
		<tr>	
			<td>訂單狀態</td>
			<td>
<form METHOD="post" ACTION="<%=request.getContextPath() %>/product/product_order.html" name="form2">
			<label><input type = "radio" name="pordsta" value="待出貨" checked/>待出貨</label>
			<label><input type = "radio" name="pordsta" value="已出貨" />已出貨</label>
			<label><input type = "radio" name="pordsta" value="已送達" />已送達</label>
			</td>
		</tr>
		<tr>	
			<td>運費</td>
			<td><%=product_orderVO.getFare()%></td>
		</tr>				
		<tr>
			<td colspan="2">
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="pordno" value="<%=product_orderVO.getPordno()%>">
			<input type="hidden" name="stuno" value="<%=product_orderVO.getStuno()%>">
			<input type="hidden" name="porddate" value="<%=product_orderVO.getPorddate()%>">
			<input type="hidden" name="pordtotal" value="<%=product_orderVO.getPordtotal()%>">
			<input type="hidden" name="recipient" value="<%=product_orderVO.getRecipient()%>">
			<input type="hidden" name="phonenumber" value="<%=product_orderVO.getPhonenumber()%>">
			<input type="hidden" name="pordadd" value="<%=product_orderVO.getPordadd()%>">
			<input type="hidden" name="fare" value="<%=product_orderVO.getFare()%>">
			<input type="submit" value="送出修改" class="btn btn-outline-success my-2 my-sm-0"></form>	
			</td>
		</tr>		
		</table>
	</div>

	
		<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->



</body>
</html>