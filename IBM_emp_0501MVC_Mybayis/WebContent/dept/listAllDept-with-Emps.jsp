<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />

<html>
<head>
<title>所有部門 - listAllDept-with-Emps.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有部門 - listAllDept-with-Emps.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<c:forEach var="deptVO" items="${deptSvc.all}">
		<tr>
			<th>部門編號</th>
			<th>部門名稱</th>
			<th>部門基地</th>
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
					<th>員工編號</th>
					<th>員工姓名</th>
					<th>職位</th>
					<th>雇用日期</th>
					<th>薪水</th>
					<th>獎金</th>
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
