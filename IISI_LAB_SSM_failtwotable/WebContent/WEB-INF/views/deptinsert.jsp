<%@page import="tw.gov.bli.dept.domain.Dept"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
  Dept dept = (Dept) request.getAttribute("dept");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增客戶資料</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
 <div class='wrapper'>
    <header class='header'>
        <div class="header-title">新增用戶資料</div>
    </header>
    <div class="container">
 <!-- ------------------------------------------------form:form 開始------------------------------------------------ -->
    <form:form action="deptinsert.action" method="post" modelAttribute="deptCase">
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
            </tr>
        </table>
        <button type="submit" name="action" value="建立單位與所屬客戶" class="btn btn-outline-dark btn-block">建立單位與所屬客戶</button>
        
    <br>
    out.print(${usersSize});

     <table class="table table-striped table-sm">
            <tr>
                <td class="text-center">客戶姓名：</td>
                <td><form:input path="users[${usersSize}].username" type="text" name="username" class="form-control" placeholder="請輸入姓名" /></td>
                 <td><form:errors path="users[${usersSize}].username" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center">身分證字號：</td>
                <td><form:input path="users[${usersSize}].userid" type="text" name="userid" class="form-control" placeholder="請輸入身分證字號" /></td>
           		<td><form:errors path="users[${usersSize}].userid" style="color:red"></form:errors></td>
            </tr>
           
            <tr>
                <td class="text-center">性別：</td>
                <td><form:radiobutton path="users[${usersSize}].sex" id="vehicle1" name="sex" value="男" />
                    <label for="vehicle1">男</label>
                    <form:radiobutton path="users[${usersSize}].sex" id="vehicle2" name="sex" value="女" />
                    <label for="vehicle2">女</label></td>
                    <td><form:errors path="users[${usersSize}].sex" style="color:red"></form:errors></td>
                    
            </tr>
            <tr>
                <td class="text-center">地址：</td>
                <td><form:input path="users[${usersSize}].address" type="text"  name="address" class="form-control" placeholder="請輸入地址" /></td>
                <td><form:errors path="users[${usersSize}].address" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center" >電話：</td>
                <td><form:input path="users[${usersSize}].phone" type="text" name="phone" class="form-control" placeholder="請輸入電話" /></td>
                 <td><form:errors path="users[${usersSize}].phone" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center" >生日：</td>
                <td><form:input path="users[${usersSize}].birthday"  type="date" name="birthday" class="form-control" placeholder="請輸入生日" /></td>
                <td><form:errors path="users[${usersSize}].birthday" style="color:red"></form:errors></td>
                
            </tr>
            <tr>
                <td class="text-center" >加入日期：</td>
                <td><form:input  path="users[${usersSize}].joindate"  type="date" name="joindate" class="form-control" placeholder="請輸入加入日期" /></td>
            	<td><form:errors path="users[${usersSize}].joindate" style="color:red"></form:errors></td>
            	            	
            </tr>
            <tr>
                <td colspan="2">
                <button type="submit" name="action" value="存一個客戶" class="btn btn-outline-dark btn-block">暫存一筆客戶</button>
                </td>
            </tr>
        </table>
    <input type="hidden" name="usersSize" value="${usersSize}">
   
    <form:errors path="*" element="div" ></form:errors>
<c:if test="${usersSize != 0}">
<h1>${dept.users}</h1>
	<c:forEach var="users" items="${dept.users}" end="${usersSize-1}" varStatus="status">
		<h2> 
		username:${user.username} <form:hidden  path="users[${status.index}].username" />------  
		userid:${user.userid}<form:hidden  path="users[${status.index}].userid" />------  
		sex:${user.sex}<form:hidden  path="users[${status.index}].sex"/>------
		address:${user.address}<form:hidden  path="users[${status.index}].address"/>------
		phone:${user.phone}<form:hidden  path="users[${status.index}].phone"/>------
		birthday:${user.birthday}<form:hidden  path="users[${status.index}].birthday"/>------
		joindate:${user.joindate}<form:hidden  path="users[${status.index}].joindate"/>------
		</h2>
	</c:forEach>
</c:if>
    
     </form:form> 
 <!-- ------------------------------------------------form:form 結束------------------------------------------------ -->
        <a href="dept_findAll.action">返回上一頁</a>
    </div>
</div>
<script>
	<c:if test="${not empty message}">
		alert("${message}");
	</c:if>
</script>
</body>
</html>