<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客戶資料管理</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	 <div class='wrapper'>
        <header class='header'>
            <div class="header-title">客戶資料管理</div>
        </header>
        <div class="container">
<form action="findUserByCondition" method="post">
            <table class="table table-striped tablestyle">
                <tr>
                    <td>姓名</td>
                    <td>性別</td>
                    <td>身分證字號</td>
                    <td>生日</td>
                    <td>搜尋</td>
                </tr>
                <tr>
                
                    <td><input type="text" name="username" class="form-control" placeholder="請輸入姓名"></td>
                    <td>
                        <input type="checkbox" id="vehicle1" name="sex" value="男">
                        <label for="vehicle1">男</label>
                        <input type="checkbox" id="vehicle2" name="sex" value="女">
                        <label for="vehicle2">女</label>
                    </td>
                    <td><input type="text" name="userid" class="form-control" placeholder="請輸入身分證字號"></td>
                    <td><input type="date" name="birth" class="form-control" placeholder="請輸入生日"></td>
                   
                    <td>
                    
                    <button type="submit" class="btn btn-outline-secondary">送出搜尋</button>
                   
                    </td>
                    
                </tr>
            </table>
 </form> 
 
 
            <div class="btn-toolbar mb-3 justify-content-end" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group mr-2" role="group" aria-label="First group">
                  <a href="<%=request.getContextPath()%>/user/insert_enter"  class="btn btn-secondary">新增客戶資料</a>
                  
<!--                    <form action="deleteMany" method="post"> -->
                  <button type="submit" class="btn btn-secondary" id="delall">批次刪除資料</button>
<!--                   <input type="hidden" name="deleteMany"  id="box"> -->
<!--                   </form>  -->
                   
                  <button type="button" class="btn btn-secondary">產出Pdf</button>
                  <button type="button" class="btn btn-secondary">產出Excel</button>
                </div>
            </div>
            <div class="row">
                <table class="table table-striped tablestyle" style="margin-top:20px">
                    <tr>
                        <td>
                            選擇
                        </td>
                        <td>編號</td>
                        <td>姓名</td>
                        <td>身分證字號</td>
                        <td>性別</td>
                        <td>地址</td>
                        <td>電話</td>
                        <td>生日</td>
                        <td>加入日期</td>
                        <td>修改</td>
                        <td>刪除</td>
                    </tr>
                    <c:forEach items="${list}" var="user">
                    <tr>
                   
                        <td><input type="checkbox" name="deleteMany" value="${user.uno}" id="deleteMany"></td>
                      
                        <td><c:out value="${user.uno}" /></td>
                        <td><c:out value="${user.username}" /></td>
                        <td><c:out value="${user.userid}" /></td>
                        <td><c:out value="${user.sex}" /></td>
                        <td><c:out value="${user.address}" /></td>
                        <td><c:out value="${user.phone}" /></td>
                        <td><fmt:formatDate value="${user.birthday}"
								pattern="yyyy/MM/dd" /></td>
                        <td><fmt:formatDate value="${user.joindate}"
								pattern="yyyy/MM/dd" /></td>
						 		
                        <td> 
                        <form action="findByPrimaryKey" method="post">
                        <button type="submit" class="btn btn-outline-danger" name="findByPrimaryKey">修改</button> 
                        <input type="hidden" name="uno" value="${user.uno}">
                        </form>
                        </td>
                        
                        <td>
                        <form action="delete" method="post">
                        <button type="submit" class="btn btn-outline-danger" name="delete">刪除</button>
                         <input type="hidden" name="uno" value="${user.uno}">
                        </form> 
                        </td>
                    </tr>
                    </c:forEach>
                </table>
             </div>
       
        </div>
    </div>
    <script>
//     $( document ).ready(function() {
//     $("#delall").click(function(){
//     	var deleteMany = new Array();
//     	$('input:checkbox:checked[name="deleteMany"]').each(function(i) { 
// 		deleteMany[i] = this.value; 
// 		console.log(deleteMany[0]);
// 			});
//     	});
//     });
    </script>
<a href="findAll">回全部</a>
</body>
</html>