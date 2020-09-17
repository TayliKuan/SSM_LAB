<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
上傳至幽靈路徑
	<h3>傳統文件上傳 未成功</h3>
	<form action="user/fileupload" method="post"
		enctype="multipart/form-data">
		選擇文件:<input type="file" name="upload" /><br> 
		<input type="submit" value="上傳" />
	</form>
	
		<h3>Spring文件上傳</h3>
	<form action="user/fileupload2" method="post"
		enctype="multipart/form-data">
		選擇文件:<input type="file" name="upload" /><br> 
		<input type="submit" value="上傳" />
	</form>
	
	 <a href="user/testparams?name=123">RequestMapping註解</a>
	
</body>
</html>