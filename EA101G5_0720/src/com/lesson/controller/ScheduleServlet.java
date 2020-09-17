package com.lesson.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson.model.LessonService;
import com.lesson.model.LessonVO;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
	Timer timer = new Timer();

	public ScheduleServlet() {
		super();
	}

	public void init() {

		TimerTask task = new TimerTask() {
			// 把未成團課程自動下架
			public void run() {

//				System.out.println("工作排定的時間 = " + new Date(scheduledExecutionTime()));
				System.out.println("已建立排程!");   
				
				LessonService lessonSvc = new LessonService();
				List<LessonVO> list = lessonSvc.getAllLesson();
				
//				java.util.Date date = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String today = sdf.format(scheduledExecutionTime());
				
//				System.out.println("today="+today);
				for (int i = 0; i < list.size(); i++) {
					java.sql.Date lessend = list.get(i).getLessend();
//					System.out.println("第"+i+"lessend="+lessend);
					// 截止日與今日一樣
					if (lessend.toString().equals(today)) {
//						System.out.println("true 截止日與今日一樣");
						// 狀態又是未成團
						if (list.get(i).getLesssta().equals("未成團")) {
//							System.out.println("第"+i+"list.get(i).getLesssta()="+list.get(i).getLesssta());
//							System.out.println("狀態又是未成團");
							// 把它下架
							lessonSvc.update_off(list.get(i).getLessno());
//							System.out.println("下架成功");
						}
					}
				}

			}
		};
		// 從2020 6 1 開始每天0:0:0 執行 這個排程
		Calendar cal = new GregorianCalendar(2020, Calendar.JUNE, 01, 0, 0, 0);
		timer.scheduleAtFixedRate(task, cal.getTime(), 24 * 60 * 60 * 1000);
	}

	public void destroy() {
		timer.cancel();
		System.out.println("已移除排程!");
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}

}
