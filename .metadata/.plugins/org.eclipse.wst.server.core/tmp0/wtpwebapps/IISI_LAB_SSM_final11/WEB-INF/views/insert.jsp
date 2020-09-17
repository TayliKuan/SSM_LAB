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
</head>
<body>
 <div class='wrapper'>
    <header class='header'>
        <div class="header-title">新增用戶資料</div>
    </header>
    <div class="container">
 <!-- ------------------------------------------------form:form 開始------------------------------------------------ -->
    <form:form action="user_insert.action" method="post" modelAttribute="userCase">
        <table class="table table-striped table-sm">
            <tr>
                <td class="text-center">客戶姓名：</td>
                <td><form:input path="username" type="text" name="username" class="form-control" placeholder="請輸入姓名" /></td>
                 <td><form:errors path="username" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center">身分證字號：</td>
                <td><form:input path="userid" type="text" name="userid" class="form-control" placeholder="請輸入身分證字號" /></td>
           		<td><form:errors path="userid" style="color:red"></form:errors></td>
            </tr>
           
            <tr>
                <td class="text-center">性別：</td>
                <td><form:radiobutton path="sex" id="vehicle1" name="sex" value="男" />
                    <label for="vehicle1">男</label>
                    <form:radiobutton path="sex" id="vehicle2" name="sex" value="女" />
                    <label for="vehicle2">女</label></td>
                    <td><form:errors path="sex" style="color:red"></form:errors></td>
                    
            </tr>
            <tr>
                <td class="text-center">地址：</td>
                <td><form:input path="address" type="text"  name="address" class="form-control" placeholder="請輸入地址" /></td>
                <td><form:errors path="address" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center" >電話：</td>
                <td><form:input path="phone" type="text" name="phone" class="form-control" placeholder="請輸入電話" /></td>
                 <td><form:errors path="phone" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center" >生日：</td>
                <td><form:input path="birthday"  type="date" name="birthday" class="form-control" placeholder="請輸入生日" /></td>
                <td><form:errors path="birthday" style="color:red"></form:errors></td>
                
            </tr>
            <tr>
                <td class="text-center" >加入日期：</td>
                <td><form:input  path="joindate"  type="date" name="joindate" class="form-control" placeholder="請輸入加入日期" /></td>
            	<td><form:errors path="joindate" style="color:red"></form:errors></td>
            	
            </tr>
            <tr>
                <td colspan="2">
                <button type="submit" class="btn btn-outline-dark btn-block">送出新增</button>
                </td>
            </tr>
        </table>
        </form:form> 
 <!-- ------------------------------------------------form:form 結束------------------------------------------------ -->
        <a href="user_findAll.action">返回上一頁</a>
    </div>
</div>

</body>
</html>