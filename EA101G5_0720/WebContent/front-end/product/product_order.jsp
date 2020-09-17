<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product_order_list.model.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/front-end/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// String stuno="S001";
String stuno = (String) session.getAttribute("stuno");
pageContext.setAttribute("stuno", stuno);

// 	Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
	Product_orderService product_orderSvc = new Product_orderService();
	List<Product_orderVO> list = product_orderSvc.getAllByStuno(stuno);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title  -->
<title>FitMate</title>

<!-- Favicon  -->
<link rel="icon"
	href="<%=request.getContextPath()%>/images/core-img/heart.svg">

<!-- Core Style CSS -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/css/core-style.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/custom-css/product/product.css">
	<style>
  table{
    width: 100%;
    border-style: hidden;
    font-size: 20px;
    font-family: 微軟正黑體;
    
  }
  .onepord table th{
    background-color: #f8b300;
  }

  .onepord table td{
    background-color: #fff7e6;
       text-align: center;
  }
  
   .onelist table th{
    background-color: #feb0bd;
  }

  .onelist table td{
    background-color: #faeaed;
       text-align: center;
    padding:10px 10px 10px 10px;
  }

  .order_detail{
   text-align: right;
  }
  
   .onelist{
  	margin-bottom:20px;
  	margin-top:5px;
  }
  
  .btn{
  float:right;
  }
  
  .detail{
  	outline:none;
  	background-color:#fff7e6;
  	border:none;
  	color:blue;
  }
  
  
  .detail:focus{
    outline: 0;
}

.footerposition{
	position: fixed;
    bottom: 0;
    width:100vw;
}

.ordertable{
 margin-bottom:150px;
}
</style> 
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@beta/dist/js.cookie.min.js"></script>
</head>

<body>

	<div class="container ordertable">
		<div class="row">
			<h2>訂單查詢</h2>
		</div>
		<div class="row">
			<%@ include file="page1.file"%>
		</div>
<c:forEach var="product_orderVO" items="${list}">
<div class="text-center onepord">
<table>
  <tr>
    <th>訂單編號</th>
    <th>訂購日期</th>
    <th>總價</th>
    <th>訂單狀態</th>
    <th>運費</th>
    <th>收件人</th>
    <th>收件人電話</th>
    <th width="200px">收件地址</th>
    <th>商品詳情</th>
  </tr>
  <tr>
     <td>${product_orderVO.pordno}</td>
     <td>${product_orderVO.porddate}</td>
     <td>${product_orderVO.pordtotal}</td>
     <td>${product_orderVO.pordsta}</td>
     <td>${product_orderVO.fare}</td>
     <td>${product_orderVO.recipient}</td>
     <td>${product_orderVO.phonenumber}</td>
     <td width="200px">${product_orderVO.pordadd}</td>
     <td><button id="${product_orderVO.pordno}btn" class="detail">查看商品詳情</button></td>
   </tr>
  </table>
  </div>
<div class="text-center onelist">
<table style="display:none" id="${product_orderVO.pordno}">
  <tr>
    <th>商品名稱</th>
    <th>商品數量</th>
    <th>商品單價</th>
  </tr>
  <tr>
  <jsp:useBean id="product_order_listSvc" scope="page" class="com.product_order_list.model.Product_order_listService" /> 
  <c:forEach var="product_order_listVO" items="${product_order_listSvc.all}">
  	<c:if test="${product_orderVO.pordno==product_order_listVO.pordno}">
  	<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" /> 
  <c:forEach var="productVO" items="${productSvc.all}">
  	<c:if test="${product_order_listVO.prodno==productVO.prodno}">
	    <td>${productVO.prodname}</td>
	</c:if>
	</c:forEach>	
	    <td>${product_order_listVO.pord_listqty}</td>
	    <td>${product_order_listVO.pord_listprice}</td>
  </tr>
  </c:if>
  </c:forEach>
</table>
</div>

</c:forEach>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<c:forEach var="product_orderVO" items="${list}">
<script type="text/javascript">
  $(document).ready(function(){
    $("#${product_orderVO.pordno}btn").click(function(){
      $("#${product_orderVO.pordno}").slideToggle("fast");
    });
  });
</script>
</c:forEach>


	</div>
	<%@ include file="page2.file"%>
	
		<div class="footerposition"><%@ include file="/front-end/footer.jsp"%></div>


</body>

</html>