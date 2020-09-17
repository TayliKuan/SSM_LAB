package com.complaint.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.complaint.model.ComplaintService;
import com.complaint.model.ComplaintVO;

import javax.servlet.*;


public class ComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ComplaintServlet() {
 
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
					String coano = req.getParameter("Coano");
					String comdesc = req.getParameter("com_drsc").trim();
					if (comdesc == null || comdesc.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					}
					
				
				
					java.sql.Timestamp comdate = new java.sql.Timestamp(System.currentTimeMillis());
			
					ComplaintVO complaintVO = new ComplaintVO();
					String comsta = "0";
					
					complaintVO.setStuno(stuno);
					complaintVO.setCoano(coano);
					complaintVO.setComdesc(comdesc);
					complaintVO.setComsta(comsta);

	
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("complaintVO", complaintVO);
						String url = "/front-end/complaint/addComplaint.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
						return;
						
					}
					
					/***************************2.開始新增資料***************************************/
					ComplaintService complaintSvc = new ComplaintService();
					
					//System.out.println(stuno+"," + coano +"," + comdate +"," + comdesc +"," + comsta);
					
					complaintVO = complaintSvc.addComplaint(stuno, coano, comdate, comdesc, comsta);
					
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
		
					String url = "complaint_sucess.jsp";

		            req.getSession().setAttribute("complaintVO", complaintVO);
		            
		            res.sendRedirect(url);

		            
		            
//					req.setAttribute("complaintVO", complaintVO);
//					RequestDispatcher successView = req.getRequestDispatcher(url); // complaint_sucess.jsp
//					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("");
					failureView.forward(req, res);
				}
			}
		
		
	
		 if ("add".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String stuno = req.getParameter("Stuno");
					String coano = req.getParameter("Coano");	
					
					System.out.println(stuno);
					System.out.println(coano);

					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
		
		            
		            req.setAttribute("coano", coano);
					req.setAttribute("stuno", stuno);
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/complaint/addComplaint.jsp"); // complaint_sucess.jsp
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
			ComplaintService complaintSvc = new ComplaintService();
			 ComplaintVO complaintVO = complaintSvc.updatecomstaComplaint(comno, "1");
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("complaintVO", complaintVO);         // 資料庫取出的complaintVO物件,存入req
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
			ComplaintService empSvc = new ComplaintService();
			empSvc.deleteComplaint(comno);
			
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
