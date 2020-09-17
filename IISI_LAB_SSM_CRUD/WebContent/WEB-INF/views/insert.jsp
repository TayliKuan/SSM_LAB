<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <form action="insert" method="post">
        <table class="table table-striped table-sm">
            <tr>
                <td class="text-center">客戶姓名：</td>
                <td><input type="text" name="username" class="form-control" placeholder="請輸入姓名" required></td>
            </tr>
            <tr>
                <td class="text-center">身分證字號：</td>
                <td><input type="text" name="userid" class="form-control" placeholder="請輸入身分證字號" required></td>
            </tr>
            <tr>
                <td class="text-center">性別：</td>
                <td><input type="radio" id="vehicle1" name="sex" value="男" required>
                    <label for="vehicle1">男</label>
                    <input type="radio" id="vehicle2" name="sex" value="女">
                    <label for="vehicle2">女</label></td>
            </tr>
            <tr>
                <td class="text-center">地址：</td>
                <td><input type="text"  name="address" class="form-control" placeholder="請輸入地址" required></td>
            </tr>
            <tr>
                <td class="text-center" >電話：</td>
                <td><input type="text" name="phone" class="form-control" placeholder="請輸入電話" required></td>
            </tr>
            <tr>
                <td class="text-center" >生日：</td>
                <td><input type="date" name="birthday" class="form-control" placeholder="請輸入姓名" required></td>
            </tr>
            <tr>
                <td class="text-center" >加入日期：</td>
                <td><input type="date" name="joindate" class="form-control" placeholder="請輸入姓名" required></td>
            </tr>
            <tr>
                <td colspan="2">
                <button type="submit" class="btn btn-outline-dark btn-block">送出新增</button>
                </td>
            </tr>
        </table>
        </form>
        <a href="findAll">返回上一頁</a>
    </div>
</div>

</body>
</html>