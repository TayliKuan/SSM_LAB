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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
 <div class='wrapper'>
    <header class='header'>
        <div class="header-title">新增用戶資料</div>
    </header>
    <div class="container">
     
    <H3> 新增單位</H3>
    <br>
<!-- ------------------------------------------------form:form 開始------------------------------------------------ -->

    <form:form action="deptinsert.action" method="post" modelAttribute="dept">
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
            </tr>
        </table>
        <button type="submit" name="action" value="建立單位與所屬客戶" class="btn btn-outline-dark btn-block">建立單位與所屬客戶</button>
<!-- ------------------------------------------table dept end------------------------------------------------ -->
        
    <br>
   <H3> 新增客戶</H3>
    <br>
<!-- ------------------------------------------table user 開始------------------------------------------------ -->
    
     <table class="table table-striped table-sm">
            <tr>
                <td class="text-center">客戶姓名：</td>
                <td><form:input path="user.username" type="text" name="username" class="form-control" placeholder="請輸入姓名" /></td>
                 <td><form:errors path="user.username" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center">身分證字號：</td>
                <td><form:input path="user.userid" type="text" name="userid" class="form-control" placeholder="請輸入身分證字號" /></td>
           		<td><form:errors path="user.userid" style="color:red"></form:errors></td>
            </tr>
           
            <tr>
                <td class="text-center">性別：</td>
                <td><form:radiobutton path="user.sex" id="vehicle1" name="sex" value="男" />
                    <label for="vehicle1">男</label>
                    <form:radiobutton path="user.sex" id="vehicle2" name="sex" value="女" />
                    <label for="vehicle2">女</label></td>
                    <td><form:errors path="user.sex" style="color:red"></form:errors></td>
                    
            </tr>
            <tr>
                <td class="text-center">地址：</td>
                <td><form:input path="user.address" type="text"  name="address" class="form-control" placeholder="請輸入地址" /></td>
                <td><form:errors path="user.address" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center" >電話：</td>
                <td><form:input path="user.phone" type="text" name="phone" class="form-control" placeholder="請輸入電話" /></td>
                 <td><form:errors path="user.phone" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center" >生日：</td>
                <td><form:input path="user.birthday"  type="date" name="birthday" class="form-control" placeholder="請輸入生日" /></td>
                <td><form:errors path="user.birthday" style="color:red"></form:errors></td>
                
            </tr>
            <tr>
                <td class="text-center" >加入日期：</td>
                <td><form:input  path="user.joindate"  type="date" name="joindate" class="form-control" placeholder="請輸入加入日期" /></td>
            	<td><form:errors path="user.joindate" style="color:red"></form:errors></td>
            	            	
            </tr>
            <tr>
                <td colspan="2">
                <button type="submit" name="action" value="暫存客戶" class="btn btn-outline-dark btn-block">暫存客戶</button>
                
                
                </td>
            </tr>
        </table>   
				<%--     <form:errors path="*" element="div" ></form:errors> --%>
<!-- ------------------------------------------table user end------------------------------------------------ -->

     </form:form> 
<!-- ------------------------------------------------form:form end------------------------------------------------ -->

    <br>
    <H3> 新增單位之客戶列表</H3>
<!-- ------------------------------------------table 暫存的user 開始------------------------------------------------ -->
     
     <table class="table table-striped table-sm">
		<tr>
			<td>客戶姓名</td>
			<td>身分證字號</td>
			<td>性別</td>
			<td>地址</td>
			<td>電話</td>
			<td>生日</td>
			<td>加入日期</td>
			<td>修改</td>
			<td>刪除</td>
		</tr>
		<c:forEach var="user" items="${users}" varStatus="loop">
			<tr>
				
				<td><c:out value="${user.username}" /></td>
                        <td><c:out value="${user.userid}" /></td>
                        <td><c:out value="${user.sex}" /></td>
                        <td><c:out value="${user.address}" /></td>
                        <td><c:out value="${user.phone}" /></td>
                        <td><fmt:formatDate value="${user.birthday}"
								pattern="yyyy/MM/dd" /></td>
                        <td><fmt:formatDate value="${user.joindate}"
								pattern="yyyy/MM/dd" /></td>
				     
<!-- ------------------------------------------修改 開始------------------------------------------------ -->
       			<td> 
					<form:form action="updateUser.action" method="POST" modelAttribute="dept">
						<input type="hidden" name="index" value="${loop.index}" id="updatebtn">
						<!--拿他的index回傳  方便後續操作-->
						<input name="action" type="submit" value="修改" class="btn btn-info">
					</form:form>
				</td>
<!-- ------------------------------------------刪除 開始------------------------------------------------ -->
				<td>
					<form:form action="deleteUser.action" method="POST">
						<input type="hidden" name="index" value="${loop.index}">
						<!--拿他的index回傳 方便後續操作 -->
						<input name="action" type="submit" value="刪除" class="btn btn-danger">
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
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