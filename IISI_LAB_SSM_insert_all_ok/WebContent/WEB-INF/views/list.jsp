<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
</head>
<body>
	<h3>查詢所有USER</h3>
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">選擇</th>
				<th scope="col">編號</th>
				<th scope="col">姓名</th>
				<th scope="col">ID</th>
				<th scope="col">性別</th>
				<th scope="col">地址</th>
				<th scope="col">電話</th>
				<th scope="col">生日</th>
				<th scope="col">投保日</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach items="${list}" var="user">
					<tr>
						<td><input type="checkbox" id="vehicle1" name="vehicle1"
							value="Bike"></td>
						<td><c:out value="${user.uno}" /></td>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.userid}" /></td>
						<td><c:out value="${user.sex}" /></td>
						<td><c:out value="${user.address}" /></td>
						<td><c:out value="${user.phone}" /></td>
						<td><fmt:formatDate value="${user.birthday}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${user.joindate}"
								pattern="yyyy-MM-dd" /></td>
						<td><button type="button" class="btn btn-success"
								name="update">修改</button></td>
						<td><button type="button" class="btn btn-danger"
								name="delete">刪除</button></td>
				</c:forEach>
			</tr>

		</tbody>
	</table>

</body>
</html>