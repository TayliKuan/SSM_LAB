<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_fav.model.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/front-end/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// 	Product_favVO product_favVO = (Product_favVO) request.getAttribute("product_favVO");

String stuno = (String)session.getAttribute("stuno");
pageContext.setAttribute("stuno", stuno);
	
	Product_favService product_favSvc = new Product_favService();
	ProductService productSvc = new ProductService();
	
	List<Product_favVO> list = product_favSvc.getOnePf(stuno);
	pageContext.setAttribute("list", list);
	
	
	for(Product_favVO  vo : list){
		System.out.println(vo.getProdno());
	}
	
	
	List<ProductVO> prodlist = productSvc.getAll();
	pageContext.setAttribute("prodlist", prodlist);
	System.out.println(prodlist.size());	
	
	for(ProductVO  vo : prodlist){
		System.out.println(vo.getProdno());
	}

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


<!-- cookie用    -->
<script
	src="https://cdn.jsdelivr.net/npm/js-cookie@beta/dist/js.cookie.min.js"></script>


<style>
.footerposition{
/* 	position: fixed; */
    bottom: 0;
    width:100vw;
}

.followlist{
	margin-top:100px;
	margin-bottom:20px;
}
</style>
</head>

<body>
	
	<div class="container followlist">
		<div class="row">
			<h2>追蹤清單</h2>
		</div>
		<div class="row">
			<%@ include file="page1.file"%>
		</div>
		<c:forEach var="product_favVO" items="${list}">
			<div class="row alert alert-primary">
<%-- 				<jsp:useBean id="productSvc" scope="page" --%>
<%-- 					class="com.product.model.ProductService" /> --%>
				<c:forEach var="productVO" items="${prodlist}">
					<c:if test="${product_favVO.prodno==productVO.prodno}">
						<div class="col-2 pic">
							<img
								src="<%= request.getContextPath()%>/product/product.pic?prodno=${product_favVO.prodno}">
						</div>
					</c:if>
				</c:forEach>
				<div class="col-10">
					<div class="row" style="float: right">
						<form method="post"
							action="<%=request.getContextPath()%>/product/product_fav.html">
							<input type="submit" value="X"
								style="border: none; background-color: #cce5ff; outline: none">
							<input type="hidden" name="action" value="delete"> <input
								type="hidden" name="prodno" value="${product_favVO.prodno}">
							<input type="hidden" name="stuno" value="${product_favVO.stuno}">
						</form>
					</div>
					<div class="row" style="height: 60px">
						<h4>
							<c:forEach var="productVO" items="${prodlist}">
								<c:if test="${product_favVO.prodno==productVO.prodno}">${productVO.prodname}</c:if>
							</c:forEach>
						</h4>
					</div>
					<div class="row price">
						商品價格:
						<c:forEach var="productVO" items="${prodlist}">
							<c:if test="${product_favVO.prodno==productVO.prodno}">${productVO.prodprice}</c:if>
						</c:forEach>
					</div>
					<div class="row prodno">
						<c:forEach var="productVO" items="${prodlist}">
							<c:if test="${product_favVO.prodno==productVO.prodno}">${productVO.proddesc}</c:if>
						</c:forEach>
					</div>
					<div class="row"></div>
					<div class="row">
						<div class="col-9"></div>
						<div class="col-3">
							<!-- 					<a href="#" class="essence-btn">加入購物車</a> -->

							<div class="add-to-cart-btn">
								<a href="#" class="btn essence-btn addtocart1">Add to Cart</a> <input
									type="hidden" value="${productVO.prodno}">
							</div>

						</div>

					</div>
				</div>
			</div>
		</c:forEach>
	</div>
<%-- 	<%@ include file="page2.file"%> --%>


	<!-- ##### Footer Area Start ##### -->

		
<div class="footerposition">
<%@ include file="/front-end/footer.jsp"%>
</div>


</body>


<script>


//商品object     產品編號     名稱        產品圖片路徑           選購個數                  產品價格        
function Product(pordno, name, pro_pic_src, pord_listqty , pro_price){
			this.pordno = pordno;
			this.name = name;
			this.pro_pic_src = pro_pic_src;
			this.pord_listqty = pord_listqty;
			this.pro_price = pro_price;
			
		}
		
var productMap = new Map();



	var list_cart = Cookies.get("cartItem");
	
// 	console.log(list_cart);
	
	if (list_cart !== undefined){
	
	var list_cart_object =JSON.parse(list_cart);
	

	for(var key in list_cart_object){
		
	var	pordno=list_cart_object[key].pordno;
	var	name=list_cart_object[key].name;
	var	pro_pic_src=list_cart_object[key].pro_pic_src;
	var	pord_listqty=list_cart_object[key].pord_listqty;
	var	pro_price=list_cart_object[key].pro_price;
	
	product = new Product(pordno, name, pro_pic_src, pord_listqty, pro_price);

		productMap.set(product.pordno,product);
		
		var addcart = "<div class='single-cart-item ";
		addcart += pordno+"'>";
		addcart += "<a href='#' class='product-image'>";
		addcart += "<img src='" + pro_pic_src + "' class='cart-thumb' id='cartimg' alt=''>";
		addcart += "<div class='cart-item-desc'>";
		addcart += "<span class='product-remove ";
	    addcart += pordno+"'>";
		addcart += "<i class='fa fa-close' aria-hidden='true'></i>";
		addcart += "</span>";
		addcart += "<span class='badge'>";
		addcart += pordno+"</span>";
		addcart += "<h6>"+name+"</h6>";
		addcart += "<p class='size'>選購數量: "
		addcart += pord_listqty+"</p>";
		addcart += "<p class='price'>價格:"+pro_price+"</p>";
		addcart += "<p class='color'>數量價格:"+pro_price*pord_listqty+"</p></div></a></div>";
		
		$(".cart-list").append(addcart);
	
		
		var all_pord_listqty = 0;
			var all_price = 0;

			productMap.forEach(function(item, index, array){
					
			all_pord_listqty += item.pord_listqty;//商品總數
			all_price +=( parseInt(item.pord_listqty) * parseInt(item.pro_price) ); //商品總數價格

				});

			
		$("#essenceCartBtn").find("span").text(all_pord_listqty);
		$("#rightSideCart").find("span").text(all_pord_listqty);
		
	

//			console.log("pprice: "+pprice);//增加商品後購物車商品總額
		$(".cart-amount-summary").children().eq(1).find("span").eq(1).text("$ "+all_price+"  元");
		
		var freight = $(".cart-amount-summary").children().eq(1).find("span").eq(3).text();
	
		if(productMap.size>0)
		{
			var freight = 80;
		}else{
			var freight = 0;
		}
		
		$(".cart-amount-summary").children().eq(1).find("span").eq(3).text("$ "+freight+"  元");
		var total = all_price+freight;
		$(".cart-amount-summary").children().eq(1).find("span").eq(5).text("$ "+total+"  元");
		
		
		
	
	}
	
	}	
	
	



	
		

$(".addtocart1")
.click(
		function() {							
			let pro_pic_src = $(this).parent().parent().parent().parent().prev().find("img").attr("src");
				
  			console.log(pro_pic_src);//商品同圖片路徑

			let price = $(this).parentsUntil("col-10").find("div.price").text().trim();
			
  			let pro_price = price.substring(price.length-3);
//  			console.log("pro_price"+pro_price);//商品單價
 		
			let name = $(this).parent().parent().parent().parent().children().eq(1).children().eq(0).text().trim();
//   			console.log("name"+name);//商品名稱
			let pordno = pro_pic_src.substring(pro_pic_src.length-4);
//  			console.log("pordno"+pordno);//商品編號
			var pord_listqty = 1;//預設購買數量
										
			if(productMap.size===0){
				
				product = new Product(pordno, name, pro_pic_src, pord_listqty, pro_price);
	
				productMap.set(pordno,product);
//  				console.log("成功新增第一筆:"+typeof(pordno));
			

					var addcart = "<div class='single-cart-item ";
					addcart += pordno+"'>";
					addcart += "<a href='#' class='product-image'>";
					addcart += "<img src='" + pro_pic_src + "' class='cart-thumb' id='cartimg' alt=''>";
					addcart += "<div class='cart-item-desc'>";
					addcart += "<span class='product-remove ";
				    addcart += pordno+"'>";
					addcart += "<i class='fa fa-close' aria-hidden='true'></i>";
					addcart += "</span>";
					addcart += "<span class='badge'>";
					addcart += pordno+"</span>";
					addcart += "<h6>"+name+"</h6>";
					addcart += "<p class='size'>選購數量: "
					addcart += pord_listqty+"</p>";
					addcart += "<p class='price'>價格:"+pro_price+"</p>";
					addcart += "<p class='color'>數量價格:"+pro_price*pord_listqty+"</p></div></a></div>";
					
					$(".cart-list").append(addcart);
					
					
					 var cart_list = JSON.stringify(mapToObj(productMap));	
					 addToCar(cart_list);
					

 			}else{
 				
 				if(productMap.get(pordno)===undefined){
 					product = new Product(pordno, name, pro_pic_src, pord_listqty, pro_price);

 					productMap.set(product.pordno,product);
//  					console.log("成功新增:"+productMap.get(pordno).pordno);
 				
 					var addcart = "<div class='single-cart-item ";
 					addcart += pordno+"'>";
 					addcart += "<a href='#' class='product-image'>";
 					addcart += "<img src='" + pro_pic_src + "' class='cart-thumb' id='cartimg' alt=''>";
 					addcart += "<div class='cart-item-desc'>";
 					addcart += "<span class='product-remove ";
 				    addcart += pordno+"'>";
 					addcart += "<i class='fa fa-close' aria-hidden='true'></i>";
 					addcart += "</span>";
 					addcart += "<span class='badge'>";
 					addcart += pordno+"</span>";
 					addcart += "<h6>"+name+"</h6>";
 					addcart += "<p class='size'>選購數量: "
 					addcart += pord_listqty+"</p>";
 					addcart += "<p class='price'>價格:"+pro_price+"</p>";
 					addcart += "<p class='color'>數量價格:"+pro_price*pord_listqty+"</p></div></a></div>";
 					
 					$(".cart-list").append(addcart);
					
//  					console.log("productMap.size:  "+productMap.size);
 					
 					 var cart_list = JSON.stringify(mapToObj(productMap));	
 					 addToCar(cart_list);
 					
 				
 				}else if(productMap.get(pordno).pordno===pordno){
 				
 					product=productMap.get(pordno);

 					pord_listqty = (product.pord_listqty)+1;
 					product.pord_listqty = pord_listqty;
										
					
// 					console.log("增加商品數量pord_listqty:  "+product.pord_listqty);
					

					var cart_product =$(".cart-list").children("div."+pordno);//新增過商品的區塊
			
					var size = "<p class='size'>選購數量: "
						size += pord_listqty+"</p>";
					cart_product.find(".size").html(size);
					
					var ppa ="數量價格:";
						ppa += pro_price*pord_listqty;
					
					cart_product.find(".color").html(ppa);
					
// 					console.log("productMap.size:  "+productMap.size);
				
					
					 var cart_list = JSON.stringify(mapToObj(productMap));	
					 addToCar(cart_list);
 				}


				
 				
 			}
			
			
//  			console.log("productMap.size:  "+productMap.size);
 			
 			var all_pord_listqty = 0;
 			var all_price = 0;

 			productMap.forEach(function(item, index, array){
 					
				all_pord_listqty += item.pord_listqty;//商品總數
				all_price +=( parseInt(item.pord_listqty) * parseInt(item.pro_price) ); //商品總數價格
	
 				});

 			
			$("#essenceCartBtn").find("span").text(all_pord_listqty);
			$("#rightSideCart").find("span").text(all_pord_listqty);
			
		

// 			console.log("pprice: "+pprice);//增加商品後購物車商品總額
			$(".cart-amount-summary").children().eq(1).find("span").eq(1).text("$ "+all_price+"  元");
			
			var freight = $(".cart-amount-summary").children().eq(1).find("span").eq(3).text();
		
			if(productMap.size>0)
			{
				var freight = 80;
			}else{
				var freight = 0;
			}
			
			$(".cart-amount-summary").children().eq(1).find("span").eq(3).text("$ "+freight+"  元");
			var total = all_price+freight;
			$(".cart-amount-summary").children().eq(1).find("span").eq(5).text("$ "+total+"  元");
			
//			console.log("total : "+total);//購物車商品價錢+運費總額
//			console.log($("i.fa-close"));//購物車商品價錢+運費總額
						
			
			
			
	productMap.forEach(function(item, index, array){
				
// 				console.log(item.pordno);	
// 				console.log(item.name);
// 				console.log(item.pord_listqty);			
// 				console.log( parseInt(item.pro_price));
			});
		});
		
		
		
	
	
		$(".cart-list").on('click', ".fa-close", function(){
			
			var p_n = $(this).parent().next().text();
			productMap.delete(p_n);
			$(this).parent().parent().parent().parent().remove();			
			
 //			console.log(productMap.size);
 			
 			var all_pord_listqty = 0;
 			var all_price = 0;
//  			console.log(productMap);
 			productMap.forEach(function(item, index, array){
 				
 				console.log("商品總數:");
				console.log(item.pord_listqty);
				all_pord_listqty += item.pord_listqty;	//移出商品後總數
				all_price +=( parseInt(item.pord_listqty) * parseInt(item.pro_price) ); //移除商品後總數價格

 				});

 			
			
			$("#essenceCartBtn").find("span").text(all_pord_listqty);
			$("#rightSideCart").find("span").text(all_pord_listqty);
			


// 			console.log("pprice: "+pprice);//移除商品後購物車商品總額
			$(".cart-amount-summary").children().eq(1).find("span").eq(1).text("$ "+all_price+"  元");
			
			
			
			if(productMap.size>0)
			{
				var freight = 80;
			}else{
				var freight = 0;
			}
			
			$(".cart-amount-summary").children().eq(1).find("span").eq(3).text("$ "+freight+"  元");
			var total = all_price+freight;
			$(".cart-amount-summary").children().eq(1).find("span").eq(5).text("$ "+total+"  元");
			
			console.log("total : "+total);//購物車商品價錢+運費總額
			console.log($("i.fa-close"));//購物車商品價錢+運費總額
			

			 var cart_list = JSON.stringify(mapToObj(productMap));	
			 addToCar(cart_list);
			

		})


		$(".check").click(function(){

//*****************	
// 			if($(this).val() != null){
			
			 var cart_list = JSON.stringify(mapToObj(productMap));	
			 addToCar(cart_list);
              
			  $(this).prev().val(cart_list)
   			  console.log($(this).prev());
			 
			  $("#buy").submit();
			
// 			}else{
// 			    alert("請登入");
// 			    $("#user-login-info")click(); 
				
// 			} 
		});
		
		
		
		//map 轉 JSON字串
		function mapToObj(inputMap) {
		    let obj = {};
		    inputMap.forEach(function(value, key){
		        obj[key] = value;
		    });
		    return obj;
		}

		
		
		
		function addToCar(cart_list){

	        Cookies.set("cartItem", cart_list , { expires: 1 })
	
	}
		
	</script>


</html>