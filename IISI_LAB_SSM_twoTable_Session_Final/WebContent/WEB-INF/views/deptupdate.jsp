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
        <div class="header-title">修改用戶資料</div>
    </header>
    <div class="container">
     
    <H3> 新增單位</H3>
    <br>
<!-- ------------------------------------------------form:form 開始------------------------------------------------ -->

    <form:form action="updateUser.action" method="post" modelAttribute="dept">
<!--------------------------------------------table dept 開始------------------------------------------------ -->
    
        <table class="table table-striped table-sm">
            <tr>
                <td class="text-center">單位名稱：</td>
                <td><form:input path="dname" type="text" name="dname" class="form-control" placeholder="請輸入單位名稱" disabled="disabled" /></td>
                 <td><form:errors path="dname" style="color:red"></form:errors></td>
            </tr>
            <tr>
                <td class="text-center">地點：</td>
                <td><form:input path="loc" type="text" name="loc" class="form-control" placeholder="請輸入地點" disabled="disabled" /></td>
           		<td><form:errors path="loc" style="color:red"></form:errors></td>
            </tr>
        </table>
        <button type="submit" name="action" value="建立單位與所屬客戶" class="btn btn-outline-dark btn-block" disabled="disabled">建立單位與所屬客戶</button>
<!-- ------------------------------------------table dept end------------------------------------------------ -->
        
    <br>
   <H3> 修改客戶</H3>
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
                <button type="submit" name="action" value="confirm" class="btn btn-outline-dark btn-block">確定修改</button>
                <button type="submit" name="action" value="quit" class="btn btn-outline-dark btn-block">放棄修改</button>
                <input type="hidden" name="index" value="${value}" id="updatebtn">
                </td>
            </tr>
        </table>   
<!-- ------------------------------------------table user end------------------------------------------------ -->

     </form:form> 
<!-- ------------------------------------------------form:form end------------------------------------------------ -->


<script>
	<c:if test="${not empty message}">
		swal("${message}");
	</c:if>
</script>

</body>
</html>