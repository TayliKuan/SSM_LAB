<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href="user/findAll">查詢全部</a>
	
	<H3>新增測試</H3>
	<form action="user/insert" method="post">
		姓名:<input type="text" name="username" /><br>
		ID:<input type="text" name="userid" /><br>
		性別:<input type="text" name="sex" /><br>
		地址:<input type="text" name="address" /><br>
		電話:<input type="text" name="phone" /><br>
		生日:<input type="text" name="birthday" /><br>
		投保日:<input type="text" name="joindate" /><br>
	<input type="submit" value="新增" />
	</form>
	
	
</body>
</html>