<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部門資料管理</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<style>
.dataTables_wrapper {
	width: 100%
}

#testtable_paginate {
	width: 100%;
	text-align: center;
}

.dataTables_wrapper .dataTables_paginate .paginate_button:hover {
	background: linear-gradient(to bottom, #de1e09 0%, #e81010 100%);
}
</style>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>
<body>
	
	<!-- -----------------------------------------------header 開始------------------------------------------------ -->
	<div class='wrapper'>
		<header class='header'>
			<div class="header-title">部門資料管理</div>
		</header>
		<!-- -----------------------------------------------header 結束------------------------------------------------ -->
<!-- ---------------------------------------------新增 批次刪除 開始------------------------------------------------ -->

            <div class="btn-toolbar mb-3 justify-content-end" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group mr-2" role="group" aria-label="First group">
                  <a href="<%=request.getContextPath()%>/dept/dept_insert_enter.action"  class="btn btn-secondary">建立單位與新增客戶</a>
                  
                </div>
            </div>

 <!-- ---------------------------------------------新增 批次刪除 結束------------------------------------------------ -->
		<div class="row">
			<table class="table table-striped tablestyle"
				style="margin-top: 20px" id="myTable">
				<c:forEach items="${list}" var="dept">
					<thead>
						<tr>
							<td>單位編號</td>
							<td>單位</td>
							<td>地點</td>
						</tr>
					</thead>
					<tbody>

						<tr>
							<td><c:out value="${dept.deptno}" /></td>
							<td><c:out value="${dept.dname}" /></td>
							<td><c:out value="${dept.loc}" /></td>

							<%--<td><c:out value="${dept.users}" /></td> --%>
						<tr>
							<td>客戶編號</td>
							<td>姓名</td>
							<td>身分證字號</td>
							<td>性別</td>
							<td>地址</td>
							<td>電話</td>
							<td>生日</td>
							<td>加入日期</td>

						</tr>
						<c:forEach var="user" items="${dept.users}">

							<tr>
								<td>${user.uno}</td>
								<td>${user.username}</td>
								<td>${user.userid}</td>
								<td>${user.sex}</td>
								<td>${user.address}</td>
								<td>${user.phone}</td>
								<td><fmt:formatDate value="${user.birthday}"
										pattern="yyyy/MM/dd" /></td>
								<td><fmt:formatDate value="${user.joindate}"
										pattern="yyyy/MM/dd" /></td>

							</tr>
						</c:forEach>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<a href="dept_findAll.action"><button type="button"
				class="btn btn-info">返回部門資料管理</button></a>
		<a href="<%=request.getContextPath()%>/index.jsp"><button type="button"
				class="btn btn-danger">首頁</button></a>
	</div>
	<script>
	<c:if test="${not empty message}">
		alert("${message}");
	</c:if>
</script>
</body>
</html>