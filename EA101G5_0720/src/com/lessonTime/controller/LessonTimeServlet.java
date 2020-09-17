package com.lessonTime.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson.model.LessonService;
import com.lesson.model.LessonVO;
import com.lessonTime.model.LessonTimeService;
import com.lessonTime.model.LessonTimeVO;

@WebServlet("/LessonTimeServlet")
public class LessonTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LessonTimeServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String[] ltime_date = req.getParameterValues("ltime_date");
				String[] ltime_ss = req.getParameterValues("ltime_ss");
				String lessno = req.getParameter("lessno");
				String lessname =req.getParameter("lessname");
				
				Integer lesstimes = null;
				try {
					lesstimes = new Integer(req.getParameter("lesstimes"));
				} catch (NumberFormatException e) {
				}
				String lessend = req.getParameter("lessend");
				
				LessonTimeVO lessonTimeVO = null;
				String date1=null;
				java.sql.Date dates = null;
				String ss1 =null;
				List<LessonTimeVO> list= new ArrayList<LessonTimeVO>();
				
				//錯誤處理 第一版 (後來因為檢查時段 可以檢查是否為空)
				for (int i = 0; i < ltime_date.length; i++) {
					if("".equals(ltime_date[i])) {
						errorMsgs.add("第" +(i+1)+ "時間不可為空!");
					}
				}
				for (int i = 0; i < ltime_ss.length; i++) {
					if("".equals(ltime_ss[i])) {
						errorMsgs.add("第" +(i+1)+ "時段不可為空!");
					}
				}
				
				if(errorMsgs.isEmpty()) {
					//如果時段不為空 就新增時段
					for(int i =0;i<ltime_date.length;i++) {
						date1 = ltime_date[i];
						dates = java.sql.Date.valueOf(date1);
						ss1 = ltime_ss[i];
						lessonTimeVO = new LessonTimeVO();
						lessonTimeVO.setLtime_date(dates);
						lessonTimeVO.setLtime_ss(ss1);
						/*************************** 2.開始新增資料 ***************************************/
						//直接同時建DETAIL 時段明細
						LessonTimeService lessonTimeService = new LessonTimeService();
						lessonTimeService.addLessonTime(dates, ss1,lessno);
						
						list.add(lessonTimeVO);
					}
				}

				LessonService lessonSvc = new LessonService();
				LessonVO lessonVO = lessonSvc.getOneByPK(lessno);
				
				if (!errorMsgs.isEmpty()) {
					for (String str : errorMsgs) {
						System.out.println(str);
					}

					req.setAttribute("lessno_seq", lessno);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/lesson/addTime.jsp");
					failureView.forward(req, res);
					return;
				}
			
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String url = "/front-end/lesson/selectLesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				req.setAttribute("insert","新增課程成功");
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/lesson/addTime.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOneTime_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String lessno = new String(req.getParameter("lessno"));
				/***************************2.開始查詢資料****************************************/
				LessonTimeService lTSvc = new LessonTimeService();
				List<LessonTimeVO> Timelist = lTSvc.findTimeByPK(lessno);
				
				LessonService lSvc = new LessonService();
				LessonVO lessonVO = lSvc.getOneByPK(lessno);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("Timelist", Timelist);
				req.setAttribute("lessonVO", lessonVO);
				String url ="/front-end/lesson/updateTime.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理************************************/

			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
		if("update_time".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String[] ltime_date = req.getParameterValues("ltime_date");
				String[] ltime_ss = req.getParameterValues("ltime_ss");
				String lessno = req.getParameter("lessno");
				String lessname =req.getParameter("lessname");
				String[] ltime_no = req.getParameterValues("ltime_no");
				Integer lesstimes = null;
				try {
					lesstimes = new Integer(req.getParameter("lesstimes"));
				} catch (NumberFormatException e) {
				}
				String lessend = req.getParameter("lessend");
				
				LessonTimeVO lessonTimeVO = null;
				String date1=null;
				java.sql.Date dates = null;
				String ss1 =null;
				String tt = null;
				List<LessonTimeVO> Timelist= new ArrayList<LessonTimeVO>();
				
				
				if(errorMsgs.isEmpty()) {
					for(int i =0;i<ltime_date.length;i++) {
						date1 = ltime_date[i];
						dates = java.sql.Date.valueOf(date1);
						ss1 = ltime_ss[i];
						tt = ltime_no[i];
						lessonTimeVO = new LessonTimeVO();
						lessonTimeVO.setLtime_date(dates);
						lessonTimeVO.setLtime_ss(ss1);
						lessonTimeVO.setLtime_no(tt);
				/*************************** 2.修改資料 ***************************************/
						LessonTimeService lessonTimeService = new LessonTimeService();
						lessonTimeService.updateLessonTime(dates, ss1,tt);
						
						Timelist.add(lessonTimeVO);
					}
				}

				LessonService lessonSvc = new LessonService();
				LessonVO lessonVO = lessonSvc.getOneByPK(lessno);
				
				if (!errorMsgs.isEmpty()) {
					for (String str : errorMsgs) {
						System.out.println(str);
					}
					req.setAttribute("Timelist", Timelist);
					req.setAttribute("lessonVO", lessonVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/lesson/updateTime.jsp");
					failureView.forward(req, res);
					return;
				}
			
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("Timelist", Timelist);
				req.setAttribute("lessonVO", lessonVO);
				String url = "/front-end/lesson/selectLesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				req.setAttribute("updateTime","時段修改成功");
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/lesson/updateTime.jsp");
				failureView.forward(req, res);
			}
			
		}
	}
}
