package com.lesson_order.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lesson_order.model.*;
import com.lesson_order.controller.*;


import javax.servlet.*;


public class Lesson_orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Lesson_orderServlet() {
 
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
					
				
					if (stuno == null || stuno.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					}
				
					if (lessno == null || lessno.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					}
				
				
			
					Lesson_orderVO  lesson_orderVO = new Lesson_orderVO();
					lesson_orderVO.setLessno(lessno);				
					lesson_orderVO.setStuno(stuno);
					
					java.sql.Timestamp lord_time = new java.sql.Timestamp(System.currentTimeMillis());
					int lord_sc=0;
					lesson_orderVO.setLord_time(lord_time);
					lesson_orderVO.setLord_sc(lord_sc);
					//lesson_orderVO.setLord_no(lord_no);
	
String stupoint = req.getParameter("newpoint");
					
int point = Integer.parseInt(stupoint);
					

	
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("lesson_orderVO", lesson_orderVO);
						String url = "/front-end/lesson_order/addl_ord.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
						return;
						
					}
					
					/***************************2.開始新增資料***************************************/
					Lesson_orderService lesson_orderSvc = new Lesson_orderService();
					
					//System.out.println(stuno+"," + coano +"," + comdate +"," + comdesc +"," + comsta);
					
 lesson_orderVO = lesson_orderSvc.addLesson_order(lesson_orderVO ,  point);
					
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
		
					String url = "/front-end/lesson_order/lesson_order.jsp";

//		            req.getSession().setAttribute("lesson_orderVO", lesson_orderVO);
//		            
//		            res.sendRedirect(url);

		            
		            
					req.setAttribute("lesson_orderVO", lesson_orderVO);
					RequestDispatcher successView = req.getRequestDispatcher(url); // complaint_sucess.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("");
					failureView.forward(req, res);
				}
			}
		
		
	
	
	
	if ("updatecomsta".equals(action)) { 

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數****************************************/
			String comno = req.getParameter("comno");
			
			/***************************2.開始查詢資料****************************************/
		//	ComplaintService complaintSvc = new ComplaintService();
			// ComplaintVO complaintVO = complaintSvc.updatecomstaComplaint(comno, "1");
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
		//	req.setAttribute("complaintVO", complaintVO);         // 資料庫取出的complaintVO物件,存入req
			String url = "/back-end/complaint/listAll_complaint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listAll_Compalint.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/complaint/listAll_complaint.jsp");
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
			String comno = req.getParameter("comno");
			
			/***************************2.開始刪除資料***************************************/
	//		ComplaintService empSvc = new ComplaintService();
		//	empSvc.deleteComplaint(comno);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/back-end/complaint/listAll_complaint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/complaint/listAll_complaint.jsp");
			failureView.forward(req, res);
		}
	}
	
	
	
	}
	

}
