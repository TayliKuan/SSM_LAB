<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(function() {
		$("#btn").click(function() {
			// 		alert("hello btn");
			$.ajax({
				url : "user/testAjax",
				contentType : "application/json;charset:BIG5",
				data : '{"uname":"tayli","age":30,"date":"2020-01-01"}',
				dataType:"json",
				type:"post",
				success:function(data){
					//伺服器 拿到的資料 解析
					alert(data);
					alert(data.uname);
					alert(data.age);
					alert(data.date);
				}
			});
			

		});
	});
</script>
</head>
<body>
	<button id="btn">AJAX</button>
	
	<a href="user/testForwardOrRedirect">testForward</a>
</body>
</html>