<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<h3>hello world</h3>
<!-- 
<a href="hello">哈摟</a>
 -->
<!--params= {"name"} 如果沒給 就會HTTP Status 400 – Bad Request 
user/testparams?name=123 這樣可以請求到
阿如果String testparams() {}這方法有規定name=456 這樣123就不行 要一樣是456才可以
-->
 <a href="user/testparams?name=123">RequestMapping註解</a>
</body>
</html>