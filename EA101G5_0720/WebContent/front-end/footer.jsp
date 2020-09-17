<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>

<html>

<body>

	<footer class="footer_area clearfix">
		<div class="container">
			<div class="row ">
				<div class="col-md-12 text-center">
					<p>
						Copyright &copy; 2020 by EA101G5
						<i class="fa fa-heart-o" aria-hidden="true"></i>
						by FitMate
						</a>
					</p>
				</div>
			</div>
		</div>
	</footer>
	
</body>
<script src="${context}/js/jquery/jquery-2.2.4.min.js"></script>
		<script src="${context}/js/popper.min.js"></script>
		<script src="${context}/js/bootstrap.min.js"></script>
		<script src="${context}/js/plugins.js"></script>
		<script src="${context}/js/classy-nav.min.js"></script>
		<script src="${context}/js/active.js"></script>
		<script src="${context}/js/index.js"></script>

 		<script> 
 			var context = "${context}";
 		</script> 
 		
 <!-- cart -->	

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
	
	console.log(list_cart);
	
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
	
	



	
			

$(".addtocart")
.click(
		function() {
			

			let pro_pic_src = $(this).parent().parent().parent()
					.parent().children().find("img").attr("src");
//  			console.log("pro_pic_src"+pro_pic_src);//商品同圖片路徑
			
			let pro_price = $(this).parent().parent().parent().find("p").eq(0).text().trim();
// 			console.log("pro_price"+pro_price);//商品單價
			
			let name = $(this).parent().parent().siblings().eq(0).text();
//  			console.log("name"+name);//商品名稱
			let pordno = $(this).next().val();
// 			console.log("pordno"+pordno);//商品編號
			var pord_listqty = 1;//預設購買數量
										
			if(productMap.size===0){
				
				product = new Product(pordno, name, pro_pic_src, pord_listqty, pro_price);
	
				productMap.set(pordno,product);
// 				console.log("成功新增第一筆:"+typeof(pordno));
			

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
				
				console.log(item.pordno);	
				console.log(item.name);
				console.log(item.pord_listqty);			
				console.log( parseInt(item.pro_price));
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
		
		
		
		$( document ).ready(function(){
			$(".sale").next().attr("style","text-decoration:line-through")
		});
		
		

		</script>

</html>