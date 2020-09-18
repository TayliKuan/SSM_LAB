<%@page import="tw.gov.bli.dept.domain.Dept"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增客戶資料</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
 <div class='wrapper'>
    <header class='header'>
        <div class="header-title">修改單位資料</div>
    </header>
    <div class="container">
     
    <H3> 修改單位</H3>
    <br>
<!-- ------------------------------------------------form:form 開始------------------------------------------------ -->

    <form:form action="updateOneDept.action" method="post" modelAttribute="dept">
<!--------------------------------------------table dept 開始------------------------------------------------ -->
    
        <table class="table table-striped table-sm">
            <tr>
                <td class="text-center">單位名稱：</td>
                <td><form:input path="dname" type="text" name="dname" class="form-control" placeholder="請輸入單位名稱" /></td>
                 <td><form:errors path="dname" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center">地點：</td>
                <td><form:input path="loc" type="text" name="loc" class="form-control" placeholder="請輸入地點" /></td>
           		<td><form:errors path="loc" style="color:red"></form:errors></td>
           		 <td><input  type="hidden" name="deptno" value="${dept.deptno}" /></td>
            </tr>
        </table>
         <button type="submit" class="btn btn-outline-dark btn-block">修改單位</button>
        
  		</form:form> 
<!-- ------------------------------------------table dept end------------------------------------------------ -->
    
        
<!-- ------------------------------------------table 暫存的user end------------------------------------------------ -->
        <a href="dept_findAll.action">返回上一頁</a>
    </div>
</div>

<script>
	<c:if test="${not empty message}">
		swal("${message}");
	</c:if>
</script>

</body>
</html>