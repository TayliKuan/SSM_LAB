<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lesson.model.*"%>
<%@ page import="com.lessonTime.model.*"%>

<%

LessonTimeService lTimeSvc = new LessonTimeService();
//報錯的原因可能因為沒有宣告變數
List<LessonTimeVO> list = lTimeSvc.findTimeByPK(lessno);
pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h3 {
    color: black;
    display: block;
    margin: 5px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 400px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
</style>
</head>

<body bgcolor='white'>


</table>
<table class="myTable">
	<tr id="table-1" class="myTable">
		<th>日期</th>
		<th>時段</th>
	</tr>
	
	<c:forEach var="lessonTimeVO" items="${list}">	
	<tr>
		<td>${lessonTimeVO.ltime_date}</td>
		<td>${lessonTimeVO.ltime_ss}</td>
	</tr>
 </c:forEach>
</table>


</body>

</html>