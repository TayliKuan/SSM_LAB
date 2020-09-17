<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>
     html, body{
    height: 100%;
    font-family:'微軟正黑體';
    }

    header{
        width:100vw;
        height:50px;
        background-color: #FBDA61;
background-image: linear-gradient(45deg, #FBDA61 0%, #FF5ACD 100%);



        text-align:center;
        margin-bottom:50px;
        box-shadow: -1px 7px 5px #999999;
        -webkit-box-shadow: -1px 7px 5px #999999;
        -moz-box-shadow: -1px 7px 5px #999999;
        
    }

    .header-title{
        font-weight: 900;
        font-size:25pt;
        text-shadow: 0.03em 0.03em #a8a8a8;
        color:#ffffff;
    }
    .tablestyle{
        text-align:center;
        vertical-align: middle;
    }

    .wrapper{
        min-height: 100%;
    }
    .searchtitle{
        background-color:#dd5555;
        height:50px;
        padding:5px;
        text-align:center;
        font-weight: 900;
    }
    .searchbody{
        background-color:#e6e6e6;
        padding:5px;
        height:50px;
    }
    .footer{
        position: absolute;
        bottom: 0;
        width:100vw;
    }
    fieldset {
            padding: .35em .625em .75em;
            margin: 0 2px;
            border: 1px solid silver;
        }

        legend {
            padding: .5em;
            border: 0;
            width: auto;
        }
    </style>
</head>
<body>
	<c:if test="${not empty deletesuccess}">
		<script type="text/javascript">
			swal("${deletesuccess}");
		</script>
	</c:if>
	  <div class='wrapper'>
        <header class='header'>
            <div class="header-title">員工資料管理</div>
        </header>
        <div class="container">
        	 <table class="table table-striped tablestyle">
                <tr>
                    <td>姓名</td>
                    <td>部門</td>
                    <td>到職日</td>
                    <td>搜尋</td>
                </tr>
                <tr>
                	<form:form action="findByCondition.action" method="POST" modelAttribute="empDoMain">
                     <td>
                     <form:input path="ename" name="ename" type="text"  cssClass="form-control" placeholder="請輸入姓名"></form:input>                     
                     </td>
                     <td>
						<form:select path="deptno" cssClass="form-control">
							<form:options items="${dept}" itemValue="deptno" itemLabel="dname" />
						</form:select>                      </td>
                     <td>
                     <form:input path="hiredate" name="hiredate" type="DATE" cssClass="form-control" placeholder="請選擇到職日"></form:input>
                     </td>
                    <td><input type="submit" class="btn btn-outline-secondary" value="送出搜尋"></td>
                    </form:form>
                </tr>
            </table>
            <div class="btn-toolbar mb-3 justify-content-end" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group mr-2" role="group" aria-label="First group">
                <h3 class="text-danger mr-5"><c:out value="${errormsg}"></c:out></h3>
                  <a class="btn btn-secondary" href="<%=request.getContextPath()%>/emp/add.action">新增客戶資料</a>
                	 <form:form action="deletebatch.action" method="POST" id="deleteform">
						<input id="deletebatch" class="btn btn-secondary" type="submit" value="批次刪除">
						<input class="btn btn-secondary" name="empno" type="hidden" value="0">
					</form:form>
                  <input type="button" class="btn btn-secondary" value="產出Pdf">
                  <input type="button" class="btn btn-secondary" value="產產出Excel">
                </div>
            <div class="row">
                <table id="example" class=" table table-striped tablestyle" style="margin-top:20px; width:100%;" >
                <thead>
                    <tr>
                        <th>選擇</th>
                        <th>姓名</th>
                        <th>職稱</th>
                        <th>到職日</th>
                        <th>薪水</th>
                        <th>獎金</th>
                        <th>部門編號</th>
                        <th>部門名稱</th>
                        <th>部門位置</th>
                        <th>修改</th>
                        <th>刪除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="emp" items="${emps}">
                    <tr>
                          <td><input class="check" type="checkbox" value="${emp.empno}"/></td>
                          <td><c:out value="${emp.ename}"></c:out></td>
					      <td><c:out value="${emp.job}"></c:out></td>
					      <td><c:out value="${emp.hiredate}"></c:out></td>
					      <td><c:out value="${emp.sal}"></c:out> </td>
					      <td><c:out value="${emp.comm}"></c:out></td>
					      <td><c:out value="${emp.dept.deptno}"></c:out> </td>
					      <td><c:out value="${emp.dept.dname}"></c:out> </td>
					      <td><c:out value="${emp.dept.loc}"></c:out> </td>
					      <th>
							<form:form action="getoneforupdate.action" method="GET">
							<input class="btn btn-info" type="submit" value="修改">
							<input type="hidden" name="empno" value='<c:out value="${emp.empno}"></c:out>'/>
							</form:form>
						</th>
						<th>
							<form:form action="delete.action" method="GET">
							<input class="btn btn-success" type="submit" value="刪除">
							<input type="hidden" name="empno" value='<c:out value="${emp.empno}"></c:out>'/>
							</form:form>
						</th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
             </div>
        </div>
    </div>   
     <script type="text/javascript">
		    $(function(){
		    	$(".check").click(function(){
		    		if($(this).prop("checked")){
			    		$(`<input type='hidden' class='deletecheck' id=`+$(this).val()+` name='empno' value=`+$(this).val()+`>`).insertAfter("#deletebatch");
		    		}
		    		else{
		    			var id = $(this).val();
		    			$(`#`+id).remove();
		    		}
		    	})
		    	$('#example').DataTable({
		    		  searching : false ,
		    	      lengthChange : false
		    	});
		    })
    </script>
</body>
</html>