<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%// request.setCharacterEncoding("UTF-8"); %>  <!-- Query String的中文處理程式碼可完全省去，參見Servlet講義p255 -->
<!DOCTYPE html>
<html>
<head>
<title>我是展示層 (view) front-view.jsp</title>
</head>
<body>
<b> 
              這是前端的: /welcome/front-view.jsp
</b>
<hr>
	   【sendRedirect from Spring4 MVC】 \${param.ename}: ${param.ename} (無值)
	
<br>
	   【sendRedirect from Spring4 MVC】 \${requestScope.greetingKey} = ${requestScope.greetingKey} (無值)
	 
<br>
	   【sendRedirect from Spring4 MVC】 \${param.greetingKey}: ${param.greetingKey}

</body>
</html>