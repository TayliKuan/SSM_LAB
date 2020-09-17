<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客戶資料管理</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
  <style>
	.dataTables_wrapper{
	      width:100%
	}
      #testtable_paginate{ 
        width:100%; 
        text-align:center; 
	} 
	   .dataTables_wrapper .dataTables_paginate .paginate_button:hover{
       background: linear-gradient(to bottom, #de1e09 0%, #e81010 100%);
    }
  </style>
</head>
<body>
<!-- -----------------------------------------------header 開始------------------------------------------------ -->
	 <div class='wrapper'>
        <header class='header'>
            <div class="header-title">客戶資料管理</div>
        </header>
<!-- -----------------------------------------------header 結束------------------------------------------------ -->
<!-- ------------------------------------------------複合查詢 開始------------------------------------------------ -->
        <div class="container">
	<form:form action="user_findUserByCondition.action" method="post" modelAttribute="userCase">
            <table class="table table-striped tablestyle" >
                <tr>
                    <td>姓名</td>
                    <td>性別</td>
                    <td>身分證字號</td>
                    <td>生日</td>
                    <td>搜尋</td>
                </tr>
                
                <tr>
                    <td><form:input path="username" type="text" name="username" class="form-control" placeholder="請輸入姓名" /></td>
                    <td>
                        <form:checkbox path="sex"  id="vehicle1" name="sex" value="男" />
                        <label for="vehicle1">男</label>
                        <form:checkbox path="sex"  id="vehicle2" name="sex" value="女" />
                        <label for="vehicle2">女</label>
                         <form:errors cssStyle="display:none" id="errorMsg" path="*" element="div" />
                    </td>
                    <td><form:input path="userid" type="text" name="userid" class="form-control" placeholder="請輸入身分證字號" /></td>
                    <td><form:input path="birthday" type="date" name="birth" class="form-control" placeholder="請輸入生日" /></td>
                   
                    <td>
                    	<form:button type="submit" class="btn btn-outline-secondary" >送出搜尋</form:button>
                    </td>
                </tr>
            </table>
           
 	</form:form> 
 <!-- ------------------------------------------------複合查詢 結束------------------------------------------------ -->
  <!-- ---------------------------------------------新增 批次刪除 開始------------------------------------------------ -->
 
            <div class="btn-toolbar mb-3 justify-content-end" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group mr-2" role="group" aria-label="First group">
                  <a href="<%=request.getContextPath()%>/user/user_insert_enter.action"  class="btn btn-secondary">新增客戶資料</a>
                  
                  <form:form action="user_deleteMany.action" method="post" id="formdel">
	                  <input type="hidden" name="deleteMany" value="0" >
	                  <input type="submit" class="btn btn-secondary" id="delall" value="批次刪除資料">
                  </form:form> 
                   
                  <button type="button" class="btn btn-secondary">產出Pdf</button>
                  <button type="button" class="btn btn-secondary">產出Excel</button>
                </div>
            </div>
 <!-- ---------------------------------------------新增 批次刪除 結束------------------------------------------------ -->
  <!-- ---------------------------------------------迴圈全部 開始------------------------------------------------ -->
            
            <div class="row" >
                <table class="table table-striped tablestyle" style="margin-top:20px" id="myTable" >
                 <thead>
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
                       </thead>
    <tbody>
                    <c:forEach items="${list}" var="user">
                    <tr>
                   
                        <td><input type="checkbox" name="deleteMany" value="${user.uno}" class="deleteMany"></td>
                      
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
                        <form action="user_findByPrimaryKey.action" method="post">
                        <button type="submit" class="btn btn-outline-danger" name="findByPrimaryKey">修改</button> 
                        <input type="hidden" name="uno" value="${user.uno}">
                        </form>
                        </td>
                        
                        <td>
                        <form action="user_delete.action" method="post">
                        <button type="submit" class="btn btn-outline-danger" name="delete">刪除</button>
                         <input type="hidden" name="uno" value="${user.uno}">
                        </form> 
                        </td>
                    </tr>
                    </c:forEach>
                     </tbody>
                </table>
             </div>
        <a href="user_findAll.action"><button type="button" class="btn btn-info" >返回客戶資料管理</button></a>
        </div>
    </div>
<!-- ---------------------------------------------迴圈全部 結束------------------------------------------------ -->
<!-- ------------------------------------------動態生成 批次刪除 開始------------------------------------------------ -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    
    <script>
    
    //動態生成 input hidden 給批次刪除用
    //移除的要刪掉
    $("#delall").click(function(){
		 var deleteMany = new Array();
		 $("input[name='deleteMany']:checked").each(function(i){
			 deleteMany.push($(this).val());
			 var newInput = document.createElement("input");
				newInput.type="hidden";
				newInput.name="deleteMany";
				newInput.value=deleteMany[i];
				document.getElementById('formdel').appendChild(newInput);
				console.log(newInput);
	     });
	
// 	 console.log("deleteMany.toString()"+deleteMany.toString());
})
    </script>
   
    <script>
    //分頁
    //https://datatables.net/
$(document).ready( function () {
    $( "#myTable" ).DataTable({
     searching : false ,
     lengthChange : false,
     language:{
             info:'',
             paginate:{'next':'下一頁','previous':'上一頁','first':'第一頁','last':'最後一頁'}
          }
    });
         } );
</script>
<!-- ------------------------------------------動態生成 批次刪除 結束------------------------------------------------ -->
</body>
</html>