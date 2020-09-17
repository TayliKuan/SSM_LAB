<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product_order_list.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
Product_orderService prod_ordSvc = new Product_orderService();
List<Product_orderVO> list = prod_ordSvc.getAll();
pageContext.setAttribute("list", list);

StuService stuSvc = new StuService();
List<StuVO> stulist = stuSvc.getAll();
pageContext.setAttribute("stulist",stulist);

%>
<!DOCTYPE html>
<html>
<head>
<title>FitMate管理後台</title>
<meta charset="utf-8">

<style>
body {
	background:
		url("<%=request.getContextPath()%>/images/backend_public/bg1ori.jpg");
	background-position: center center;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#${product_orderVO.pordno}").click(function() {
			$(".${product_orderVO.pordno}").toggle();
		});
	});
</script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Coming+Soon&family=Noto+Sans+TC:wght@700&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css/backend.css">
</head>


<body>

	<%@ include file="/back-end/backinclude.jsp"%>
	<div class="article side-open">
		<div id="logo">
			<img src="<%=request.getContextPath()%>/images/backend_public/logo.png" alt="fitmate">
		</div>
		<h2>商品訂單管理</h2>
		<div id="main">
			
		</div>

		<%@ include file="page1.file"%>
		<div class="table-responsive-sm table-hover table-warning">
			<table class="table align-items-center">
				<tr class="table1">
					<th>訂單編號</th>
					<th>學員</th>
					<th>訂購日期</th>
					<th>總金額</th>
					<th>送貨地址</th>
					<th>訂單狀態</th>
					<th>運費</th>
					<th>修改</th>
				</tr>
				<c:forEach var="product_orderVO" items="${list}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>
						${product_orderVO.pordno}
						</td>
						
						
						<td>
						<c:forEach var="StuVO" items="${stulist}">
						<c:if test="${product_orderVO.stuno==StuVO.stuno}">${StuVO.stuname} </c:if>
						
						
						</c:forEach>
						</td>
						<td>${product_orderVO.porddate}</td>
						<td>${product_orderVO.pordtotal}</td>
						<td>${product_orderVO.pordadd}</td>
						<td>${product_orderVO.pordsta}</td>
						<td>${product_orderVO.fare}</td>
						<td>
							<form method="post"
								action="<%=request.getContextPath()%>/product/product_order.html">
								<input type="submit" value="修改"
									class="btn btn-outline-danger my-2 my-sm-0"> <input
									type="hidden" name="pordno" value="${product_orderVO.pordno}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			
			
		</div>
		<%@ include file="page2.file"%>
	</div>
	<!-- ------------------------------------從這裡結束編輯喔各位！----------------------- -->
</body>
</html>