<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.redemption.model.*"%>
<%@ page import="com.coach.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/header.jsp" %>

<%	
	String coano = (String)session.getAttribute("coano");
	CoaService coaSvc = new CoaService(); 
	CoaVO coaVO = coaSvc.getOneCoa(coano);
	pageContext.setAttribute("coaVO", coaVO);
%>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<title>申請點數兌換</title>
<style>
body{
	background-color: #f8b300;
	background-position:-190px 0 ;
	background-repeat:no-repeat;
	font-size:18px;
}
a{
font-size:16px;
}
.footer{
	margin-top:35px
}
.swal2-title {
    display: flex !important;
    justify-content: center !important;
}
</style>
</head>
<body>
	 	<div>
			<img src="<%=request.getContextPath()%>/images/deposit/ad02.png" style="width:1920px;height:350px">
		</div>
		<div class="main">
	 	<h1>申請點數兌換</h1>
	 	<a href="<%=request.getContextPath()%>/front-end/redemption/redemption_index.jsp">返回教練錢包</a>
	 	</div><br><br>
	 	<c:if test="${not empty errorMsgs}">
			<a>看看你的錯:</a>
			<br>
			<a><c:forEach var="message" items="${errorMsgs}">${message}</c:forEach></a>
		</c:if>
		<div class="container">
		<form action="<%=request.getContextPath()%>/redemption/redemption.do" method="post">
			<table>
				<tr>
					<td>請輸入欲兌換點數</td>
					<td><input type="number" name="redprice" id="price"></td>
				</tr>
				<tr>
					<td>目前擁有點數:<font color=red>${coaVO.coapoint}</font></td>
				</tr>
			</table><br>
			<input type="submit" value="送出申請" id="commit" class="btn btn-outline-dark">
			<input type="hidden" value="insert" name="action">
			<input type="hidden" name="coano" value="${coaVO.coano}">
		</form><br>
		</div>
		
		<div class="footer"><%@ include file="/front-end/footer.jsp" %></div>
		
<script>
	
		$('input:submit').on('click',function(e){
			e.preventDefault();
			swal.fire({
				title:'注意',
				text:'您確定要將這些點數兌換為現金嗎?',
				icon:'warning',
				showCancelButton: true,
				dangerMode:true,
				reverseButtons: true
			}).then((result)=>{
				if(result.value){
					if(${coaVO.coapoint} < $('#price').val()){
						
						swal.fire('錯誤','申請點數超過目前持有點數','error');
					} else {
						swal.fire('您已經完成兌換申請作業','FitMate審核通過後將以email通知您','success');
						setTimeout(function(){
							$('input:submit').parent('form').submit();
						},1500);
					} 
				} else {
					swal.fire('取消','已取消兌換申請作業','error');
				}
			});
		});
	
</script>
</body>

</html>