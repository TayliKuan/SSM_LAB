<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增客戶資料</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
  <div class='wrapper'>
    <header class='header'>
        <div class="header-title">新增用戶資料</div>
    </header>
    <div class="container"> 
  			<form:form method="post" action="insertemps.action" modelAttribute="deptDoMain">
  			<input type="submit" class="btn btn-dark mb-2" value="確定新增">
			<table class="table table-striped table-sm">
				<tr>
					<td class="text-center">部門名稱</td>
					<td><form:input cssClass="form-control" type="TEXT" path="dname"  name="dname" size="45"/></td>
					<td><form:errors path="dname" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td class="text-center">部門地址</td>
					<td><form:input cssClass="form-control" type="TEXT" path="loc"  name="loc" size="45"/></td>
					<td><form:errors path="loc" cssClass="error"></form:errors></td>
				</tr>
			</table>
			<br>
			<table class="table table-striped table-sm">
				<tr>
					<td class="text-center">姓名:</td>
					<td><form:input cssClass="form-control" type="TEXT" path="emp.ename" name="emp.ename" size="45"/></td>
					<td><form:errors path="emp.ename" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td class="text-center">職位</td>
					<td><form:input cssClass="form-control" type="TEXT" path="emp.job" name="emp.job" size="45"/></td>
					<td><form:errors path="emp.job" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td class="text-center">到職日</td>
					<td><form:input cssClass="form-control" type="DATE" path="emp.hiredate" name="emp.hiredate" size="45"/></td>
					<td><form:errors path="emp.hiredate" cssClass="error"></form:errors></td>
				</tr>
				
				<tr>
					<td class="text-center">薪水</td>
					<td ><form:input cssClass="form-control" type="TEXT" path="emp.sal" name="emp.sal" size="45"/></td>
					<td><form:errors path="emp.sal" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td class="text-center">獎金</td>
					<td><form:input cssClass="form-control" type="TEXT" name="emp.comm" path="emp.comm" size="45" /></td>
					<td><form:errors path="emp.comm" cssClass="error"></form:errors></td>
				</tr>
			</table>
			<input type="submit" name="action" value="新增員工" class="btn btn-info">
	</form:form>
	<table class="table table-striped table-sm">
		<tr>
			<td>員工姓名</td>
			<td>職位</td>
			<td>到職日</td>
			<td>薪水</td>
			<td>獎金</td>
			<td>修改</td>
			<td>刪除</td>
		</tr>
		<c:forEach var="emp" items="${dept.emps}" varStatus="loop">
			<tr>
				<td>${emp.ename}</td>
				<td>${emp.job}</td>
				<td>${emp.hiredate}</td>
				<td>${emp.sal}</td>
				<td>${emp.comm}</td>
				<td>             
					<form:form action="updateEmp.action" method="POST" modelAttribute="deptDoMain">
						<input type="hidden" name="index" value="${loop.index}">
						<input name="action" type="submit" value="修改" class="btn btn-info">
					</form:form>
				</td>
				<td>
					<form:form action="deleteEmp.action" method="POST">
						<input type="hidden" name="index" value="${loop.index}">
						<input name="action" type="submit" value="刪除" class="btn btn-danger">
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
    </div>
  </div>
</body>
</html>