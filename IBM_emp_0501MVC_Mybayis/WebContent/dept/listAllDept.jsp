<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />

<html>
<head><title>┮Τ场 - listAllDept.jsp</title>

<style>
  table#table-1 {
	background-color: orange;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
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
<body>

<h4>絤策蹦ノ EL 糶猭:</h4>
<table id="table-1">
	<tr><td>
		 <h3>┮Τ场 - ListAllDept.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0"></a></h4>
	</td></tr>
</table>

<%-- 岿粇 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">叫タ岿粇:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>场絪腹</th>
		<th>场嘿</th>
		<th>场膀</th>
		<th>э</th>
		<th>埃<font color=red>(闽羛代刚籔ユ-み)</font></th>
		<th>琩高场</th>
	</tr>
	
	<c:forEach var="deptVO" items="${deptSvc.all}">
		<tr>
			<td>${deptVO.deptno}</td>
			<td>${deptVO.dname}</td>
			<td>${deptVO.loc}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dept/dept.do" style="margin-bottom: 0px;">
			    <input type="submit" value="э"   disabled="disabled"> 
			    <input type="hidden" name="deptno" value="${deptVO.deptno}">
			    <input type="hidden" name="action" value="getOne_For_Update_Dept"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dept/dept.do" style="margin-bottom: 0px;">
			    <input type="submit" value="埃">
			    <input type="hidden" name="deptno" value="${deptVO.deptno}">
			    <input type="hidden" name="action" value="delete_Dept"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dept/dept.do" style="margin-bottom: 0px;">
			    <input type="submit" value="癳琩高"> 
			    <input type="hidden" name="deptno" value="${deptVO.deptno}">
			    <input type="hidden" name="action" value="listEmps_ByDeptno_B"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%if (request.getAttribute("listEmps_ByDeptno")!=null){%>
       <jsp:include page="listEmps_ByDeptno.jsp" />
<%} %>

</body>
</html>