<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_list.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.student.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/header.jsp" %>
<% 

Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
String stuno = (String)session.getAttribute("stuno");
pageContext.setAttribute("stuno", stuno);

StuService stusvc = new StuService();
  int Stupoint = stusvc.getOneStu(stuno).getStupoint();
  pageContext.setAttribute("Stupoint", Stupoint);
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
<link rel="icon" href="<%=request.getContextPath()%>/images/core-img/heart.svg">
<!-- Core Style CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css/core-style.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom-css/product/product.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
* {
	font-family: 微軟正黑體; !important
}

.single_product_details_area {
	margin-left: 20%;
}

table {
	font-size: 20px;
}

#adj {
	font-size: 20px;
}

.step1{
	margin-top:20px;
}		
.btnstyle{
	border:none;
	padding:10px;
	background-color:#BDBDBD;
	outline:none;
	box-shadow:2px 2px 5px #999;
	width: 79px;
    margin: 0 auto;
	}
	
.btnstyle:hover{
position:relative; top:1px; left:1px;
}
					
.submitbtn{
	background-color:#f8b300;
	border:none;
	outline:none;
	width:100%;
	height:40px;
	margin-bottom:30px;
	font-size:20px;
}

.submitbtn:hover{
position:relative; top:1px; left:1px;
}

#magic:hover{
position:relative; top:1px; left:1px;
}

.step1{
	margin-bottom:40px;
}

h1,h2,h3,h4,h5{
	letter-spacing:0.5px;
	font-family:微軟正黑體;
}
.footerposition{
height: 50px;
margin-top: 30px;
width: 100vw;
z-index: 999;
}
</style>
</head>

<body>

<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
</c:if>

	
	<!-- ##### Breadcumb Area Start ##### -->
	<div class="breadcumb_area bg-img"
		style="background-image: url(<%=request.getContextPath()%>/images/product/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="page-title text-center">
						<h2  style="font-family:微軟正黑體">確認結帳</h2>
					</div>
				</div>
			</div>
		</div>
	</div>

<%-- 錯誤表列 --%> 
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
	<!-- 結帳分上下開始 -->
	
	<div class="container">

	<div class="step1">
	<div class="row">
		<div class="col-9"><h3>第一步：確認商品明細：</h3></div>
		<div class="col-3 text-right align-self-end"><a href="<%=request.getContextPath()%>/front-end//product/product.jsp" style="font-size:15px"">←回FitMate商城繼續購買</a>
		</div>
	</div>
<!--商品明細開始 -->
		

		
		<table class="table table-striped align-items-center">
			<tr>
				<th class="text-center">商品編號</th>
				<th class="text-center">商品名稱</th>
				<th class="text-center">商品價格</th>
				<th class="text-center">數量</th>
				<th class="text-center">刪除</th>
			</tr>


			<form  method="post" action="<%=request.getContextPath()%>/front-end/product/product_order.do" id="myForm" >

		<c:forEach var="product_order_listVO" items="${list}">			
				<tr class = order_list>
				<td class="text-center align-middle">${product_order_listVO.pordno}</td>  
				<td class="text-center align-middle">${product_order_listVO.prodno}</td>
				<td class="prodprice text-center align-middle">${product_order_listVO.pord_listprice}</td>
				<td class="qty text-center align-middle">
				<select size="2" name="qty">
			<c:forEach var="count"  begin="1" end="10">
				<option name=""  value="${count}" ${(product_order_listVO.pord_listqty==count)? 'selected':'' } >${count}
			</c:forEach>
			</select></td>
				<input type="hidden"  name="prodname" value="${product_order_listVO.pordno} ">
				<input type="hidden"  name="prodno" value="${product_order_listVO.prodno}">
				<input type="hidden"  name="prodprice" value="${product_order_listVO.pord_listprice}">

				<td class="text-center">	<input type="button" class="remove_product btnstyle" value="刪除"></td>
			
			

			
			</tr>
			
			</c:forEach>		
			
			<tr>
				<td colspan="1" class="text-center">我的點數: <font id="point">${Stupoint}</font></td>  
				<td colspan="2">結帳後剩餘點數:<font id="newpoint_text"></font></td>
				<td>運費:80 </td>
				<td colspan="2" style="text-align: center; size="+2"><font id="all_price">購買金額：</font></td>
			</tr>
			<input type="hidden" id="newpoint" name ="newpoint" value="">
			<input type="hidden" id="amount" name="amount" value="">
		</table>
</div>
		<!-- 商品明細結束 -->

		<h3>第二步：確認寄件資訊：</h3>
			<div class="row">

				
				<div class="col-md-12 mb-3">
<!-- 					<label for="stuno"><h4>學員編號：</h4><span></span></label>  -->
					<input
						type="hidden" class="form-control" id="stuno" name="stuno" value="${stuno}">
				</div>
			
				<div class="col-md-12 mb-3" >
					<label for="recipient"><div style="display:flex"><h5>收件人：</h5></div><span></span></label> 
					<input type="text" class="form-control" id="recipient" name="recipient" value="<%=(product_orderVO == null) ? "" : product_orderVO.getRecipient()%>"><font style="color:red">${errorMsgs.recipient}</font>
				</div>

				<div class="col-12 mb-3">
					<label for="phonenumber"><div style="display:flex"><h5>收件人電話號碼:</h5></div><span></span></label>
					<input type="text" class="form-control" id="phonenumber" name="phonenumber" value="<%=(product_orderVO == null) ? "" : product_orderVO.getPhonenumber()%>"><font style="color:red">${errorMsgs.phonenumber}</font>
				</div>

				<div class="col-12 mb-3">
					<label for="pordadd"><div style="display:flex"><h5>收件地址:</h5></div><span></span></label> <input
						type="text" class="form-control mb-3" id="pordadd" name="pordadd" value="<%=(product_orderVO == null) ? "" : product_orderVO.getPordadd()%>"><font style="color:red">${errorMsgs.pordadd}</font>

				</div>
				<div class="col-12 mb-3">
				<input type=hidden value="insert" name="action">					
				<input type="button" value="送出訂單" class="buy submitbtn">
				</div>
				<div class="col-12 mb-3">
				<button id="magic" type="button" class="btn btn-primary btn-sm">神奇小按鈕</button>
				</div>
		</form>
		
<form action="<%=request.getContextPath()%>/deposit/deposit.do" method="post" id = "depform">
   				<input type="hidden" name="stuno" value="${stuVO.stuno}">  				
   				<input type="hidden" name="action" value="goInsert"> 
</form>		
		
		

</div>

	</div>


	<!-- 結帳分上下結束 -->

<div class="footerposition">
<%@ include file="/front-end/footer.jsp"%>
</div>

</body>


<script> 
//神奇小按鈕
 $(document).ready(function(){
   $("#magic").click(function(){
     $("#recipient").val("王柏融");
     $("#phonenumber").val("0915432234");
     $("#pordadd").val("新北市新店區中山路537巷9號");
   });

 });



   $(".buy").click(function(){

   	if($("#newpoint").val()<=0){
   		
   		var yes = confirm('點數不夠要儲值嗎?？');

   		if (yes) {
   	 		$("#depform").submit();
   		} else {
   		    alert('取消儲值');
   		}

   	}else{
   		var yes = confirm('確定要購買嗎?');

   		if (yes) {
   	 		$("#myForm").submit();

   		} else {
   		    alert('取消購買');
   		}
   		

   	
   	}
   	
   	
   });
						




//算總價	
	function addprice(){
		var allprice=0;
	$(".order_list").each(function(){
		
		var price = $(this).find(".prodprice").text().trim();
		var qty = $(this).find(".qty").find(".current").text().trim();
		var a = parseInt(price)*parseInt(qty);
		allprice +=a;
		
	});
	
	
	$("#all_price").text("購買金額："+allprice);
	$("#amount").val(allprice);

	var point = $("#point").text();
	$("#newpoint").val(parseInt(point)-allprice-80);//後端
	$("#newpoint_text").text(parseInt(point)-allprice-80);//顯示用的		
	console.log($(".buy"));
	if(allprice===0){
		
		$(".buy").attr("title","請購買商品");//button關掉
		$(".buy").prop("disabled",true);
	
	}

	else{
		$(".buy").removeAttr("title");
		$(".buy").prop("disabled",false);
	}
	
	};

	addprice();//第一次跑完加總

	
	 $(document).on('click.nice_select', '.nice-select .option:not(.disabled)', function() {
			addprice();//每次作用一次就在跑一次addprice 數量加減
	});
	 

	 $(".order_list").on('click','.remove_product', function() {
		console.log($(this).parent().parent().remove());
		addprice(); //每刪除一次在重算一次
	});

	 
	 
	 
// 	 $(".buy").on('click',function(e){
// 			swal({
// 				title:'注意',
// 				text:'您確定要購買嗎?',
// 				icon:'warning',
// 				buttons:true,
// 				dangerMode:true
// 			}).then(function(isConfirm){
// 				if(isConfirm){
// 						$("#myForm").submit();
						
// 				} else {
// 					swal('取消','已取消','error');
// 				}
// 			});
// 		});
	 
	 
	 
	 
	 
	
</script>





</html>