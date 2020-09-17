package com.lesson_order.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lesson_order.model.*;
import com.lesson_order.controller.*;


import javax.servlet.*;


public class Lesson_orderServlet_ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Lesson_orderServlet_ajax() {
 
    }

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		
	
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String lord_no = req.getParameter("lord_no");
					String lord_sc = req.getParameter("lord_sc");
						
					
				
					if (lord_no == null || lord_no.trim().length() == 0) {
						errorMsgs.add("資料錯誤");
					}
				
					if (lord_sc == null) {
						errorMsgs.add("資料錯誤");
					}
					
					String no = lord_no.trim();
					int sc = Integer.parseInt(lord_sc);
					System.out.println(lord_no);

					System.out.println(sc);
					
			
					Lesson_orderVO  lesson_orderVO = new Lesson_orderVO();

					// Send the use back to the form, if there were errors
//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("lesson_orderVO", lesson_orderVO);
//						String url = "/front-end/lesson_order/mylesson_orders.jsp";
//						RequestDispatcher failureView = req.getRequestDispatcher(url);
//						failureView.forward(req, res);
//						return;
//						
//					}
//					
					/***************************2.開始新增資料***************************************/
					Lesson_orderService lesson_orderSvc = new Lesson_orderService();
					
					//System.out.println(stuno+"," + coano +"," + comdate +"," + comdesc +"," + comsta);
			
					//lesson_orderVO = lesson_orderSvc.up_lesson_order_lord_sc("20200621-LO040",9);
					
					lesson_orderSvc.up_lesson_order_lord_sc(no,sc);
					
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					
					
		            
			        PrintWriter out = res.getWriter();
			    
				    out.println(no);
				    out.println(sc);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("");
					failureView.forward(req, res);
				}
			}
		
	


}
