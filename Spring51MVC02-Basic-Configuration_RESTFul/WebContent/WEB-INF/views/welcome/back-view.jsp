<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%// request.setCharacterEncoding("UTF-8"); %>  <!-- Query String的中文處理程式碼可完全省去，參見Servlet講義p255 -->
<!DOCTYPE html>
<html>
<head>
<title>我是展示層 (view) back-view.jsp</title>
</head>
<body>
<b>        
              這是 Internal Resource View (內部資源視圖): /WEB-INF/views/welcome/back-view.jsp
</b>
<hr>
	   【forward from Spring4 MVC】 \${param.ename} = ${param.ename}

<br> 
	   【forward from Spring4 MVC】 \${requestScope.greetingKey} = ${requestScope.greetingKey}

</body>
</html>