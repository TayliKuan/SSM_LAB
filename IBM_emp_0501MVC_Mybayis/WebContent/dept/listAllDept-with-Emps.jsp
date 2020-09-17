<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />

<html>
<head>
<title>�Ҧ����� - listAllDept-with-Emps.jsp</title>

<style>
  table#table-1 {
  width: 818px;
	background-color: orange;
    border: 1px solid #CCCCFF;
    text-align: center;
  }
 
  h4 {
    color: red;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
 
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����� - listAllDept-with-Emps.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<c:forEach var="deptVO" items="${deptSvc.all}">
		<tr>
			<th>�����s��</th>
			<th>�����W��</th>
			<th>������a</th>
		</tr>

		<tr>
			<td><h4>${deptVO.deptno}</h4></td>
			<td>${deptVO.dname}</td>
			<td>${deptVO.loc}</td>
		</tr>

		<tr>
			<td colspan="3">
			<table>
				<tr>
					<th>���u�s��</th>
					<th>���u�m�W</th>
					<th>¾��</th>
					<th>���Τ��</th>
					<th>�~��</th>
					<th>����</th>
				</tr>

				<c:forEach var="empVO" items="${deptVO.emps}">
					<tr>
						<td>${empVO.empno}</td>
						<td>${empVO.ename}</td>
						<td>${empVO.job}</td>
						<td>${empVO.hiredate}</td>
						<td>${empVO.sal}</td>
						<td>${empVO.comm}</td>
					</tr>
				</c:forEach>
			</table>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
