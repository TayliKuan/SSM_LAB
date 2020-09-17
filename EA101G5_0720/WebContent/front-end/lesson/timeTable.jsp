<%@page import="com.activity.model.ActivityService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 	pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lesson.model.*"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="com.coach.model.CoaVO"%>
<%

	String coano = (String)session.getAttribute("coano");
// 	String coano = ((CoaVO)request.getAttribute("coaVO")).getCoano();
	LessonService lessonSvc = new LessonService();
	JSONArray jsa = lessonSvc.getCoachAllLesson(coano);
	pageContext.setAttribute("jsonDates", jsa);
	List<LessonVO> list = lessonSvc.getAllLesson();
    pageContext.setAttribute("list",list);
    
    ActivityService actSvc = new ActivityService();
    JSONArray actjsa = actSvc.getAllToCoachTable(coano);
    pageContext.setAttribute("actDates", actjsa);
   	
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/custom-css/lesson/timeTable.css">
<!-- <title>timeTable</title> -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<style>
    .orange p {
    	font-size: 15px !important;
    } 
    .green p {
    	font-size: 15px !important;
    } 
    #nextM, #lastM {
    	cursor: pointer;
    }
    #nextM:hover, #lastM:hover {
    	color: blue;
    	font-weight: 700;
    }
    .monyyyy {
    width: 750px;
    height: 30px;
    overflow: auto;
    position: relative;
}
</style>

</head>
<body>

	<div id="calendar">

		<br>
		<div class='monyyyy'>
			<div>
				<div id="lastM"><<</div>
			</div>
			<div id="MD"></div>
			<div>
				<div id="nextM">>></div>
			</div>
		</div>
		<div id="calcontainer">
			<div id="calheader"></div>
			<div id="daysweek">
				<div class="dayweek">
					<p>Sunday</p>
				</div>
				<div class="dayweek">
					<p>Monday</p>
				</div>
				<div class="dayweek">
					<p>Tuesday</p>
				</div>
				<div class="dayweek">
					<p>Wednesday</p>
				</div>
				<div class="dayweek">
					<p>Thursday</p>
				</div>
				<div class="dayweek">
					<p>Friday</p>
				</div>
				<div class="dayweek  brn">
					<p>Saturday</p>
				</div>

			</div>
			<div id="daysmonth">
				<!---------------------------------------- week 1 ---------------------------------------->
				<div class="week">
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul0" class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
								
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul0"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									
								</ul>
								
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul1"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul1"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul2"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul2"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul3"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul3"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul4"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul4"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul5"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul5"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul6"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul6"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
				</div>
				<!---------------------------------------- week 2 ---------------------------------------->
				<div class="week">
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul7"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul7"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul8"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul8"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul9"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul9"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul10"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul10"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul11"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul11"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul12"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul12"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul13"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul13"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
				</div>
				<!---------------------------------------- week 3 ---------------------------------------->
				<div class="week">
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul14"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul14"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul15"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul15"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul16"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul16"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul17"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul17"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul18"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul18"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul19"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul19"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul20"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul20"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
				</div>
				<!---------------------------------------- week 4 ---------------------------------------->
				<div class="week">
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul21"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul21"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul22"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul22"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul23"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul23"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul24"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul24"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul25"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul25"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul26"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul26"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul27"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul27"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
				</div>
				<!---------------------------------------- week 5 ---------------------------------------->
				<div class="week">
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul28"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul28"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul29"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul29"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul30"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul30"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul31"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul31"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul32"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul32"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul33"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul33"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul34"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul34"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
				</div>
				<!------------------------------------- week 5 end ---------------------------------------->
				<!---------------------------------------- week 6 ---------------------------------------->
				<div class="week">
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul35"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul35"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul36"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul36"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul37"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul37"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul38"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul38"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul39"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul39"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul40"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul40"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
					<div class="day">
						<div class="daybar"></div>
						<div class="dots">
							<div id="dotsul41"  class="clean">
								<ul>
									<li class="none"></li>
									<li class="none"></li>
									<li class="none"></li>
								</ul>
							</div>
						</div>
						<!-- slide open -->
						<div class="open">
							<div id="openul41"  class="clean">
								<ul>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
									<li class="none l4 a1"><p></p></li>
								</ul>
							</div>
						</div>
						<!-- slide closed -->
					</div>
				</div>
				<!---------------------------------------- week 6 end ---------------------------------------->
			</div>
		</div>
		<div id="calcat">

			<div class="caldot none"></div>
			<p>可預約</p>
			<div class="caldot green"></div>
			<p>揪團中</p>
			<br>
		</div>
	</div>

	<script>
		function initMenu() {

			var block = $(".day");
			block.addClass("clickable");
			block.hover(function() {
				window.status = $(this)
			}, function() {
				window.status = ""
			});

			$('.open').hide();
			block.click(function() {
				$(this).parents('div:eq(0)').find('.open').slideToggle('fast');
				
			}

			);
		}

		$(document).ready(function() {
			initMenu();
		});

		var monthName = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" ];//取值剛好4傳3 索引值3就是四月

		//抓DOM
		var lastM = document.getElementById("lastM");//要改變的按鈕 上個月
		var MD = document.getElementById("MD");//填 現在的年月
		var nextM = document.getElementById("nextM");//要改變的按鈕 下個月
		var daybar = document.getElementsByClassName("daybar");//要插入日期的地方

		function clean(){
		var open = document.getElementsByClassName("open");
		var dots = document.getElementsByClassName("dots");
		
		
		for (var i = 0; i < 84; i++) {
			var AllCleanClass = document.getElementsByClassName("clean");
			jQuery.each(AllCleanClass,function(i){
	 			$(this).find("li").eq(0).attr("class","none");
	 			$(this).find("li").eq(0).attr("class","none l4 a1");
	 			$(this).find("li").eq(1).attr("class","none");
	 			$(this).find("li").eq(1).attr("class","none l4 a1");
	 			$(this).find("li").eq(2).attr("class","none");
	 			$(this).find("li").eq(2).attr("class","none l4 a1");
	 			$(this).find("li").eq(0).removeAttr("title");
				$(this).find("p").eq(0).html("");
	 			$(this).find("li").eq(1).removeAttr("title");
	 			$(this).find("p").eq(1).html("");
	 			$(this).find("li").eq(2).removeAttr("title");
	 			$(this).find("p").eq(2).html("");
				
			});
		}
		}

		clean();

		function calender(daynow) {
			var year = Y;//現在的年
			var month = M;//現在的月
			var monthFirstday = new Date(year, month, 1);
			console.log(monthFirstday);
			var week = monthFirstday.getDay();//今天的星期
			console.log(week);
			var day = 1;//從一開始的日期
			var allday = isLeap(Y, M);//拿到當月日期總天數
			resetAll();
			console.log(week);
			for (day = 1; day <= allday; day++) {
				//從星期幾開始 加上put位置從0開始 day要減一
				daybar[week + day - 1].innerText = day;
			}
			MD.innerText = monthName[M] + " " + Y;
		}

		//現在時間
		function daynow() {
			today = new Date();
			D = today.getDay();//week 0是週日 1是一
			M = today.getMonth();//4月是傳3
			Y = today.getFullYear();
			var endDate = isLeap(Y);
			return today;
		}
		//閏年判斷
		function isLeap(Y, M) {
			var isLeapYear = false;
			var feb_end = 28;
			if (Y % 4 === 0) {
				isLeapYear = true;
				feb_end = 29;
			}
			if (Y % 100 === 0) {
				isLeapYear = false;
			}
			if (Y % 400 === 0) {
				isLeapYear = true;
				feb_end = 29;
			}
			var lastd = [ 31, feb_end, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
			return lastd[M];
		}
		//清除當前的日期
		function resetAll() {
			for (var i = 0; i <= 41; i++) {
				daybar[i].innerHTML = "-";
			}

		}
		//改變下個月
		function changeN(daynow) {
			var countM = 1;
			var countY = 0;
			newday = new Date(Y + countY, M + countM, 1);

			if ((M + countM) == 12) {
				countY++;
			}

			D = newday.getDay();//week 0是週日 1是一
			M = newday.getMonth();//4月是傳3
			Y = newday.getFullYear();
			var endDate = isLeap(Y);
			return newday;
		}

		//改變上個月
		function changeL(daynow) {
			var countM = 1;
			var countY = 0;
			newday = new Date(Y - countY, M - countM, 1);

			if ((M - countM) == 1) {
				countY--;
			}

			D = newday.getDay();//week 0是週日 1是一
			M = newday.getMonth();//4月是傳3
			Y = newday.getFullYear();
			var endDate = isLeap(Y);
			return newday;
		}

		//註冊事件 往上個月
		lastM.addEventListener("click", function(e) {
			changeL();
			calender();
			clean();
			add();
		});

		//註冊事件 往下個月
		nextM.addEventListener("click", function(e) {
			changeN();
			calender();
			clean();
			add();
		});

		calender(daynow());

		//課表加入--------------------------------------------------------------
		function add(daynow) {
			var year = Y;//現在的年
			var month = M;//現在的月 五月是4 要加回來

			var monthFirstday = new Date(year, month, 1);
			console.log(monthFirstday);
			var week = monthFirstday.getDay();//今天的星期
			
/*教練課程*/
		var oobar =
	<%=pageContext.getAttribute("jsonDates")%>
		console.log("oobar=" + oobar);
			var oobj = JSON.stringify(oobar);
			console.log("oobj=" + oobj);
			var oobdates = JSON.parse(oobj);
			console.log(oobdates);
			var oobjarr = parseInt(oobdates[0].ltime_date);
			console.log(oobjarr);
			
			var objlength = oobdates.length;//array length
			console.log("objlength=" + objlength);
<%--oobj=[{"coano":"C002","lesssta":"未成團","lesscur":"0","ltime_date":"2020-06-08",
"lessno":"L002","ltime_ss":"下午","lessname":"TRX懸吊訓練","lesstimes":3,"lessprice":3000},
--%>
			
			for (var i = 0; i < objlength; i++) {
				var yy = parseInt(oobdates[i].ltime_date.substring(0, 4));
				console.log("yy=" + yy);
				var mm = parseInt(oobdates[i].ltime_date.substring(5, 7));
				console.log("mm=" + mm);
				var dd = parseInt(oobdates[i].ltime_date.substring(8, 10));
				console.log("dd=" + dd);
				var tt = oobdates[i].ltime_ss;
				console.log("tt=" + tt);
				var lessname = oobdates[i].lessname;
				console.log(lessname);
				var lesstimes = oobdates[i].lesstimes;
				var lessprice = oobdates[i].lessprice;
				var lessno = oobdates[i].lessno;
				console.log(lessno);
				var coano = oobdates[i].coano;
				console.log(coano);
				if (yy === year && (mm - 1) === month) {

					var dayinner = dd + week - 1;// dayinner   (week+day-1);
					console.log("dayinner=" + dayinner);
					var iddotsul = "dotsul" + (dayinner);
					console.log("iddotsul=" + iddotsul);
					var idopenul = "openul" + (dayinner);
					console.log("idopenul=" + idopenul);
					var head = "<%=request.getContextPath()%>";
					var first = "/lesson/LessonDetailServlet.do?lessno="
					var middle = "&coano=";
					var end="&action=show_lesson_detail";
					var link = head+first+lessno+middle+coano+end;
					console.log(link);
				
					switch (tt) {
					case "早上":
						$("#"+iddotsul).find("li").eq(0).attr("class","orange");
						$("#"+idopenul).find("li").eq(0).attr("class","orange l4 a1");
						$("#"+idopenul).find("li").eq(0).attr("title","查看詳情");
						$("#"+idopenul).find("p").eq(0).html("<a href="+link+">"+lessname+'<br/><br/>'+"共"+lesstimes+"堂"+'<br/><br/>'+"點數:"+lessprice+"</a>");
						break;
						
					case "下午":
						$("#"+iddotsul).find("li").eq(1).attr("class","orange");
						$("#"+idopenul).find("li").eq(1).attr("class","orange l4 a1");
						$("#"+idopenul).find("li").eq(1).attr("title","查看詳情");
						$("#"+idopenul).find("p").eq(1).html("<a href="+link+">"+lessname+'<br/><br/>'+"共"+lesstimes+"堂"+'<br/><br/>'+"點數:"+lessprice+"</a>");
						break;
						
					case "晚上":
						$("#"+iddotsul).find("li").eq(2).attr("class","orange");
						$("#"+idopenul).find("li").eq(2).attr("class","orange l4 a1");
						$("#"+idopenul).find("li").eq(2).attr("title","查看詳情");
						$("#"+idopenul).find("p").eq(2).html("<a href="+link+">"+lessname+'<br/><br/>'+"共"+lesstimes+"堂"+'<br/><br/>'+"點數:"+lessprice+"</a>");
						break;
					}
				}
			}
			
			/*教練揪團*/
			var aoobar =
	<%=pageContext.getAttribute("actDates")%>
		console.log("aoobar=" + aoobar);
			var aoobj = JSON.stringify(aoobar);
			console.log("aoobj=" + aoobj);
			var aoobdates = JSON.parse(aoobj);
			console.log(aoobdates);
			var aoobjarr = parseInt(aoobdates[0].actdate);
			console.log(aoobjarr);
			
			var aobjlength = aoobdates.length;//array length
			console.log("aobjlength=" + aobjlength);
<%--oobj={"coano":"C002","actloc":"大安森林公園",
"actcur":"0","actsta":"未上架","actno":"A004",
"actss":"上午","acttype":"EXP002","actprice":"250",
"actdate":"2020-09-06","stuno":"S004","actname":"TABATA"
--%>
			
			for (var i = 0; i < aobjlength; i++) {
				var ayy = parseInt(aoobdates[i].actdate.substring(0, 4));
				console.log("ayy=" + ayy);
				var amm = parseInt(aoobdates[i].actdate.substring(5, 7));
				console.log("amm=" + amm);
				var add = parseInt(aoobdates[i].actdate.substring(8, 10));
				console.log("add=" + add);
				var att = aoobdates[i].actss;
				console.log("att=" + att);
				var actname = aoobdates[i].actname;
				console.log(actname);
				var actcur = aoobdates[i].actcur;
				var actprice = aoobdates[i].actprice;
				var actno = aoobdates[i].actno;
				console.log(actno);
				var coano = aoobdates[i].coano;
				console.log(coano);
				if (ayy === year && (amm - 1) === month) {

					var dayinner = add + week - 1;// dayinner   (week+day-1);
					console.log("dayinner=" + dayinner);
					var iddotsul = "dotsul" + (dayinner);
					console.log("iddotsul=" + iddotsul);
					var idopenul = "openul" + (dayinner);
					console.log("idopenul=" + idopenul);
					var head = "<%=request.getContextPath()%>";
					var first = "/ActivityTimetableServlet?actno="
					var middle = "&coano=";
					var end="&action=show_lesson_detail";
					var link = head+first+actno+middle+coano+end;
					console.log(link);
				
					switch (tt) {
					case "早上":
						$("#"+iddotsul).find("li").eq(0).attr("class","green");
						$("#"+idopenul).find("li").eq(0).attr("class","green l4 a1");
						$("#"+idopenul).find("li").eq(0).attr("title","查看揪團");
						$("#"+idopenul).find("p").eq(0).html("<a href="+link+">"+actname+'<br/>'+"<br/><br/>"+"點數:"+actprice+"</a>");
						break;
						
					case "下午":
						$("#"+iddotsul).find("li").eq(1).attr("class","green");
						$("#"+idopenul).find("li").eq(1).attr("class","green l4 a1");
						$("#"+idopenul).find("li").eq(1).attr("title","查看揪團");
						$("#"+idopenul).find("p").eq(1).html("<a href="+link+">"+actname+'<br/>'+"<br/><br/>"+"點數:"+actprice+"</a>");
						break;
						
					case "晚上":
						$("#"+iddotsul).find("li").eq(2).attr("class","green");
						$("#"+idopenul).find("li").eq(2).attr("class","green l4 a1");
						$("#"+idopenul).find("li").eq(2).attr("title","查看揪團");
						$("#"+idopenul).find("p").eq(2).html("<a href="+link+">"+actname+'<br/>'+"<br/><br/>"+"點數:"+actprice+"</a>");
						break;
					}
				}
			}
			
		}

		add();
		
        
	</script>
	
</body>
</html>
