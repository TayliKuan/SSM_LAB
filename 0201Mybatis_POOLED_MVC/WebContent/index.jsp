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
<a href="hello">���O</a>
 -->
<!--params= {"name"} �p�G�S�� �N�|HTTP Status 400 �V Bad Request 
user/testparams?name=123 �o�˥i�H�ШD��
���p�GString testparams() {}�o��k���W�wname=456 �o��123�N���� �n�@�ˬO456�~�i�H
-->
 <a href="user/testparams?name=123">RequestMapping����</a>
</body>
</html>