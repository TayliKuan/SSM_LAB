<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
  	<meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>�s�W�Ȥ���</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/style.css">
</head>
<body>
	<div class='wrapper'>
	       <header class='header'>
        <div class="header-title">�ק���u���</div>
    </header>
	    <div class="container">
	    
<form:form method="post" action="update.action" modelAttribute="empDoMain">
	<table class="table table-striped table-sm">
		<tr>
			<td class="text-center">�s��:</td>
			<td><form:input cssClass=" form-control" readonly="true" type="TEXT" path="empno"  name="uno" size="45"/></td>
		</tr>
		<tr>
			<td class="text-center">�m�W:</td>
			<td><form:input cssClass="form-control" type="TEXT" path="ename" id="ename" name="ename" size="45"/></td>
			<td><form:errors path="ename" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="text-center">¾��</td>
			<td><form:input cssClass="form-control" type="TEXT" path="job" id="job" name="job" size="45"/></td>
			<td><form:errors path="job" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="text-center">��¾��</td>
			<td><form:input cssClass="form-control" type="DATE" path="hiredate" id="hiredate" name="hiredate" size="45"/></td>
			<td><form:errors path="hiredate" cssClass="error"></form:errors></td>
		</tr>
		
		<tr>
			<td class="text-center">�~��</td>
			<td ><form:input cssClass="form-control" type="TEXT" path="sal" name="sal" size="45"/></td>
			<td><form:errors path="sal" cssClass="error"></form:errors></td>
		</tr>
		
		<tr>
			<td class="text-center">����</td>
			<td><form:input cssClass="form-control" type="TEXT" name="comm" id="comm" path="comm" size="45" /></td>
			<td><form:errors path="comm" cssClass="error"></form:errors></td>
		</tr>		
		<tr>
			<td class="text-center">����</td>
			<td>
				<form:select path="deptno">
					<form:option value="10" label="�]�ȳ�"></form:option>
					<form:option value="20" label="��o��"></form:option>
					<form:option value="30" label="�~�ȳ�"></form:option>
					<form:option value="40" label="�ͺ޳�"></form:option>
				</form:select> 
			</td>
		</tr>
	</table>
<input type="submit" value="�e�X�ק�" class="btn btn-outline-dark btn-block">
</form:form>
	    </div>
	</div>
</body>
</html>