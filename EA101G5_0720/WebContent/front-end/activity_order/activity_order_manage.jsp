<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity_order.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ include file="/front-end/header.jsp"%>

<%


	Activity_orderVO activity_orderVO = (Activity_orderVO)request.getAttribute("activity_orderVO");
	pageContext.setAttribute("activity_orderVO", activity_orderVO);

	
	ActivityService activitySvc = new ActivityService();
	List<ActivityVO> activitylist = activitySvc.getAllActivity();
	pageContext.setAttribute("activitylist", activitylist);

%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->


<!-- Title  -->
<title>FitMate學員活動訂單 - listAllActivityOrderForStudent.jsp</title>
<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/css/core-style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/custom-css/regular-page.css">

<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap"
	rel="stylesheet">

<!-- 星星icon用 -->
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<style>
.card-img-top {
	height: 300px;
	width: 100%;
}
.gosubmit {
	position: absolute;
	bottom: 20px;
	right: 20px;
}
.card-deck {
	margin-top: 20px;
}
.row {
	margin-right: 70px;
	margin-left: 70px;
}
.img-region {
	height: 300px;
	width: 100%;
}
table {
	font-family: 微軟正黑體;
}
.nice-select {
	display: none;
}
select {
	display: inline-block !important;
}
.fa-goyellow{
	color:yellow;
}
.swal-footer{
	 display: flex !important;
    justify-content: center !important;
}


.tablestyle{
border:1px solid black;
width:100%;
margin:40px;

}

.tablestyle tr{
border:1px solid black;

}

.tablestyle td{
border:1px solid black;
height:48px;
font-size:20px;
font-weight:900;
}

.lefttd{
width:50%;
padding-left:2px;
}

.ridhttd{
width:50%;
padding-left:2px;
}

#backhome{
font-size: 16px;
border-radius: 20px;
font-family: 微軟正黑體;
padding: 6px;
border: solid 1px black;
background-color:black;
color:#fff;
}

#backhomediv{
margin-left:90%;
margin-top:20px;
}
#footerposition{
height:50px;
margin-top:400px;
width:100vw;
z-index:999;
}
</style>

</head>
<body>
	<!-- ##### Header Area Start ##### -->
	<!-- ##### Header Area End ##### -->
	 <div class="single-blog-wrapper" style="background-color: black;">
        <div class="single-blog-post-thumb">
            <img src="<%=request.getContextPath()%>/images//bg-img/STU1920.png" alt="">
        </div>
</div>
	<div class="container col-12">
		<div class="row justify-content-center">
			<div class="col-12 col-md-12">
				<div class="regular-page-content-wrapper section-padding-80">
					<div class="regular-page-text">
						<h3>FitMate活動報名紀錄</h3>

							<table border="1"
								style="background-color:#80FFFF" class="table table-striped">

								<tr class="text-left align-items-left">
									<td>活動訂單編號</td>
									<td class="align-middle">${activity_orderVO.aord_no}</td>
								</tr>
								<tr>
									<td>活動編號</td>
									<td class="align-middle">${activity_orderVO.actno}</td>
								</tr>
								<tr>
									<td>學員編號</td>
									<td class="align-middle">${activity_orderVO.stuno}</td>
								</tr>
								<tr>
									<td>活動教練評價</td>
									<td class="star">
										<!-- 下方JS 會判斷輸入的星數 -->
										<div class="star">
											<input class="star-f" type="hidden"
												value= "${activity_orderVO.aord_sc}"> <a href="#"
												class="star-count-1"> <i class="fa fa-lg fa-star-o"
												aria-hidden="true"></i>
											</a> <a href="#" class="star-count-2"> <i
												class="fa fa-lg fa-star-o " aria-hidden="true"></i>
											</a> <a href="#" class="star-count-3"> <i
												class="fa fa-lg fa-star-o  fa-ttt" aria-hidden="true"></i>
											</a> <a href="#" class="star-count-4"> <i
												class="fa fa-lg fa-star-o " aria-hidden="true"></i>
											</a> <a href="#" class="star-count-5"> <i
												class="fa  fa-lg fa-star-o" aria-hidden="true"></i>
											</a>
										</div>
									</td>
								</tr>
								<tr>
									<td>活動訂單時間</td>
									<td class="align-middle"><fmt:formatDate
											value="${activity_orderVO.aord_time}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</table>
				<div id="backhomediv">
				<a href="<%=request.getContextPath()%>/front-end/activity/activity_selectallforguest.jsp" id="backhome">按此返回活動總覽</a></div>			
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- ##### Blog Wrapper Area End ##### -->
	
	<!-- ##### Footer Area Start ##### -->
	<div id="footerposition">
	<%@ include file="/front-end/footer.jsp"%>
	<!-- ##### Footer Area End ##### -->
	</div>
	




	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<!-- Popper js -->
	<script
		src="${pageContext.request.contextPath}/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Bootstrap js -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<!-- Plugins js -->
	<script src="${pageContext.request.contextPath}/js/plugins.js"></script>
	<!-- Classy Nav js -->
	<script src="${pageContext.request.contextPath}/js/classy-nav.min.js"></script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/js/active.js"></script>




<script type="text/javascript">
//網頁載入時判斷是否評分過如果有評分過顯示STAR的CSS
$(document).ready(function() {
		
$(".star").find('input').each(function(){
	//console.log($(this));	 
	
	if($(this).val()!=='0'){
		var count = parseInt($(this).val());
		//console.log(count);
		
		for(var i = 0; i<count ; i++){
			$(this).parent().find("i").eq(i).removeClass("fa-star-o");
			$(this).parent().find("i").eq(i).addClass("fa-star fa-goyellow");
		}
	}
});
});	
	//判斷是否評價過
    jQuery("i").click(function(){   	
    	if($(this).parent().parent().children().eq(0).val()!=="0"){
    		
    		//console.log($(this).parent().parent().children().eq(0).val());
    		
    	}else{
    	
    		if (confirm("評價只能給一次  [確定] 或 [取消] "))
    	　　　 {　alert("[確定] 謝謝你給的評價" );  　
    	    		//console.log("0.0");
 
        	event.preventDefault();
        		
        	$(this).parent().prevAll().find("i").removeClass("fa-star-o");
        	$(this).parent().prevAll().find("i").addClass("fa-star fa-goyellow");
        	var pa =$(this).parent().prevAll();
        	$(this).removeClass("fa-star-o");
        	$(this).addClass("fa-star fa-goyellow");
        
       		var aord_sc = (pa.size());       	
       		console.log("pa.size()"+aord_sc);
        	var na = $(this).parent().nextAll().find("i").removeClass("fa-star fa-goyellow");    		
        				
        	$(this).parent().nextAll().find("i").addClass("fa-star-o");
        	
        	
        	$(this).parent().parent().children().eq(0).val();   	
     		
        	var starno=$(this).parent().parent().parent().parent().parent().children().eq(0).find(".align-middle").text();
    		
        	
        	$(this).parent().parent().children().eq(0).val(aord_sc);
 
        	
        	$.post("activity_order_sc.do",
        			
        			{
        				aord_no:starno, aord_sc:aord_sc
        			},function(data, status){
        				if(status == "success")
        					console.log(data);
        			}
        		        			
        			)
        		
    	 	}			
      	    else{　
        		alert("[取消]")} 			
        	}	
    	
    });
    		
</script>

</body>

</html>