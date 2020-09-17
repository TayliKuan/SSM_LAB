package com.lesson_fav.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.lesson_fav.controller.*;
import com.lesson_fav.model.*;
import com.lesson.controller.*;
import com.lesson.model.*;

public class Lesson_favServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public Lesson_favServlet() {
		
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		
		 if ("insert".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String stuno = req.getParameter("Stuno");
					String lessno = req.getParameter("Lessno");
					String rurl = req.getParameter("rurl");
//					System.out.println(stuno);
//					System.out.println(rurl);
					
					
					String stunoReg = "^[S]{1}\\d{3}$";
					if (stunoReg == null || stuno.trim().length() == 0) {
						errorMsgs.add("stuno: 請勿空白");
					} else if(!stuno.trim().matches(stunoReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("stuno: 格式錯誤");
		            }
					
				
					String lessnoReg = "^[L]{1}\\d{3}$";
					if (lessnoReg == null || lessno.trim().length() == 0) {
						errorMsgs.add("lessno: 請勿空白");
					} else if(!stuno.trim().matches(stunoReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("lessno: 格式錯誤");
		            }

	
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("errorMsgs", errorMsgs);
						String url ="/front-end/lesson_fav/addlesson_fav.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
						return;
						
					}
					
					/***************************2.開始新增資料***************************************/
					Lesson_favService lesson_favSvc = new Lesson_favService();
					LessonService lessonSvc = new LessonService();
					LessonVO lessonVO = lessonSvc.getOneByPK(lessno);
					
					req.setAttribute("lessonVO", lessonVO);
					//System.out.println(stuno+"," + coano +"," + comdate +"," + comdesc +"," + comsta);
					
					Lesson_favVO lesson_favVO = lesson_favSvc.addLesson_fav(stuno, lessno);
					
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
		
					
					//HttpSession session =req.getSession();
					//String location = (String)session.getAttribute("location");
//		            
				
					
//		            
//		            res.sendRedirect(url);
					req.setAttribute("lesson_favVO", lesson_favVO);
					req.setAttribute("followOK","追蹤成功");
					RequestDispatcher successView = req.getRequestDispatcher(rurl); 
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("");
					failureView.forward(req, res);
				}
			}
		
		
	

	

	if ("delete".equals(action)) { 

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			String stuno = req.getParameter("Stuno");
			String lessno = req.getParameter("Lessno");
			

			String stunoReg = "^[S]{1}\\d{3}$";
			if (stunoReg == null || stuno.trim().length() == 0) {
				errorMsgs.add("stuno: 請勿空白");
			} else if(!stuno.trim().matches(stunoReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("stuno: 格式錯誤");
            }
			
		
			String lessnoReg = "^[L]{1}\\d{3}$";
			if (lessnoReg == null || lessno.trim().length() == 0) {
				errorMsgs.add("lessno: 請勿空白");
			} else if(!stuno.trim().matches(stunoReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("lessno: 格式錯誤");
            }

			
			/***************************2.開始刪除資料***************************************/
			Lesson_favService lesson_favSvc = new Lesson_favService();
			lesson_favSvc.deleteLessno_fav(stuno, lessno);
			
			List <Lesson_favVO> list =  lesson_favSvc.getfindByStuno(stuno);
			
			 req.setAttribute("list", list);
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/front-end/lesson_fav/lesson_fav.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/lesson_fav/lesson_fav.jsp");
			failureView.forward(req, res);
		}
	}
	
	
	

	
	

	 if ("fav".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String stuno = req.getParameter("Stuno");
				String lessno = req.getParameter("Lessno");	
	
				String stunoReg = "^[S]{1}\\d{3}$";
				if (stunoReg == null || stuno.trim().length() == 0) {
					errorMsgs.add("stuno: 請勿空白");
				} else if(!stuno.trim().matches(stunoReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("stuno: 格式錯誤");
	            }
				
				String lessnoReg = "^[L]{1}\\d{3}$";
				if (lessnoReg == null || lessno.trim().length() == 0) {
					errorMsgs.add("lessno: 請勿空白");
				} else if(!stuno.trim().matches(stunoReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("lessno: 格式錯誤");
	            }
		

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs);
					String url ="/addlesson_fav.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
					
				}
				
				/***************************2.開始新增資料***************************************/
				Lesson_favService lesson_favSvc = new Lesson_favService();
				List<Lesson_favVO> list = lesson_favSvc.getfindByStuno(stuno);
				req.setAttribute("list", list);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
	
				String url = "/front-end/lesson_fav/lesson_fav.jsp";
				//HttpSession session =req.getSession();
				//String location = (String)session.getAttribute("location");
//	            
//	            
//	            res.sendRedirect(url);

	          
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/lesson_fav/lesson_fav.jsp");
				failureView.forward(req, res);
			}
		}

	
	
	}
	
	
}
