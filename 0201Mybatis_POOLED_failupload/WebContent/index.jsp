<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
�W�Ǧܫ��F���|
	<h3>�ǲΤ��W�� �����\</h3>
	<form action="user/fileupload" method="post"
		enctype="multipart/form-data">
		��ܤ��:<input type="file" name="upload" /><br> 
		<input type="submit" value="�W��" />
	</form>
	
		<h3>Spring���W��</h3>
	<form action="user/fileupload2" method="post"
		enctype="multipart/form-data">
		��ܤ��:<input type="file" name="upload" /><br> 
		<input type="submit" value="�W��" />
	</form>
	
	 <a href="user/testparams?name=123">RequestMapping����</a>
	
</body>
</html>