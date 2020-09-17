<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�s�W�Ȥ���</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
  <div class='wrapper'>
    <header class='header'>
        <div class="header-title">�s�W�Τ���</div>
    </header>
    <div class="container"> 
  			<form:form method="post" action="updateEmps.action" modelAttribute="empDoMain">
			<table class="table table-striped table-sm">
				<tr>
					<td class="text-center">�m�W:</td>
					<td><form:input cssClass="form-control" type="TEXT" path="ename" name="ename" size="45"/></td>
					<td><form:errors path="ename" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td class="text-center">¾��</td>
					<td><form:input cssClass="form-control" type="TEXT" path="job" name="job" size="45"/></td>
					<td><form:errors path="job" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td class="text-center">��¾��</td>
					<td><form:input cssClass="form-control" type="DATE" path="hiredate" name="hiredate" size="45"/></td>
					<td><form:errors path="hiredate" cssClass="error"></form:errors></td>
				</tr>
				
				<tr>
					<td class="text-center">�~��</td>
					<td ><form:input cssClass="form-control" type="TEXT" path="sal" name="sal" size="45"/></td>
					<td><form:errors path="sal" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td class="text-center">����</td>
					<td><form:input cssClass="form-control" type="TEXT" name="comm" path="comm" size="45" /></td>
					<td><form:errors path="comm" cssClass="error"></form:errors></td>
				</tr>
			</table>
			<input type="hidden" name="index" value="${index}">
			<input type="submit"  value="�ק���u" class="btn btn-info">
	</form:form>
    </div>
  </div>
</body>
</html>