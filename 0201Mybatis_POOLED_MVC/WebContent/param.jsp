<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<!-- 請求參數綁定 -->
<!-- 
 <a href="param/testParam">param請求參數綁定</a>
 <a href="param/testParam2?name=Tayli">param請求參數綁定2</a>
 <a href="param/testParam3?name=Tayli&password=123456">param請求參數綁定3</a>

<form action="param/saveAccount" method="post">
姓名:<input type="text" name="username"/><br>
密碼:<input type="text" name="password"/><br>
金額:<input type="text" name="money"/><br>
USER姓名:<input type="text" name="user.uname"/><br>
AGE:<input type="text" name="user.age"/><br>
	<input type="submit" value="提交"/>
</form>
 -->
 
 
 <!-- 把資料封裝到Account中 存在list和map集合 -->
 <!-- 
 <form action="param/saveAccount" method="post">
姓名:<input type="text" name="username"/><br>
密碼:<input type="text" name="password"/><br>
金額:<input type="text" name="money"/><br>
USER姓名:<input type="text" name="list[0].uname"/><br>
AGE:<input type="text" name="list[0].age"/><br>

USER姓名:<input type="text" name="map['one'].uname"/><br>
AGE:<input type="text" name="map['one'].age"/><br>
	<input type="submit" value="提交"/>
</form>
 -->
 
  <!-- 自訂義 -->
 <!--
 <form action="param/saveUser" method="post">
USER姓名:<input type="text" name="uname"/><br>
AGE:<input type="text" name="age"/><br>
birthday:<input type="text" name="date"/><br>
	<input type="submit" value="提交"/>
</form>
 -->
 
 <a href="param/testServlet">Servlet原生的API</a>
</body>
</html>