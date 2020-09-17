<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>form.jsp</title>
</head>
<body>
<br>
<ol><li>
            <a href='<%=request.getContextPath()%>/helloAction/sayHello1.do?ename=peter1吳永志'>forward-1</a>
<br>
<br><br>
</li><li>
            <form method="get" action="<%=request.getContextPath()%>/helloAction/sayHello2.do">
                                  請輸入您的名字:
               <input type="text" name="ename" value="peter2吳永志"><p>
               <input type="submit" value="forward-2">
            </form>
<br><br>
</li><li>

            <form method="post" action="<%=request.getContextPath()%>/helloAction/sayHello3_Redirect.do">
                                  請輸入您的名字:
               <input type="text" name="ename" value="peter3吳永志"><p>
               <input type="submit" value="redirect">
            </form>
</li></ol>
</body>
</html>