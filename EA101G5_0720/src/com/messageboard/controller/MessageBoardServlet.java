package com.messageboard.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.messageboard.model.MessageBoardService;
import com.messageboard.model.MessageBoardVO;

public class MessageBoardServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		// 來自 article/listOneArticle.jsp的請求
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String artno = req.getParameter("artno");
				
				/*************************** 2.開始查詢資料 ****************************************/
				MessageBoardService messageboardSvc = new MessageBoardService();
				MessageBoardVO messageboardVO = messageboardSvc.getMsgByArtNo(artno);
				List<MessageBoardVO> list = messageboardSvc.getAll();
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageboardVO", messageboardVO);
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/messageboard/listOneMessageBoard.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/messageboard/listAllMessageBoard.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("addMessage".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String artno = req.getParameter("artno");
				if (artno == null || (artno.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號ART");
				}
				String askdesc = req.getParameter("askdesc");
				String mesrep = req.getParameter("mesrep");
				String repdesc = req.getParameter("repdesc");
				String mesdate = req.getParameter("mesdate");
				// Send the use back to the form, if there were errors
				
				MessageBoardVO messageBoardVO = new MessageBoardVO();
				messageBoardVO.setArtNo(artno);
				messageBoardVO.setAskDesc(askdesc);
				messageBoardVO.setMesRep(mesrep);
				messageBoardVO.setRepDesc(repdesc);
				messageBoardVO.setMesDate(mesdate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageBoardVO", messageBoardVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/messageboard/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/messageboard/listOneMessageBoard.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料****************************************/
				MessageBoardService messageBoardSvc = new MessageBoardService();
				messageBoardVO = messageBoardSvc.addMessageBoard(artno, askdesc, mesrep, repdesc, mesdate);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String url = "/front-end/messageboard/listOneMessageBoard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/messageboard/listOneMessageBoard.jsp");
				failureView.forward(req, res);
			}
		}
	/////////////////////////	
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String asknone = new String(req.getParameter("asknone").trim());
				
				String stuno = req.getParameter("stuno");
				String stunoReg = "S{1}{3}$";
				if (stuno == null || stuno.trim().length() == 0) {
					errorMsgs.add("學員編號: 請勿空白");
				} else if(!stuno.trim().matches(stunoReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("學員編號: 只能是英文字母、數字 , 且長度必需在4以內");
	            }
				
				
				String artno = req.getParameter("artno").trim();
				if (artno == null || artno.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}	

				String askdesc = null;
				try {
					askdesc = new String(req.getParameter("askdesc").trim());
				} catch (NumberFormatException e) {
					askdesc = null;
					errorMsgs.add("請填寫發問內容.");
				}
				
				String mesrep = null;
				try {
					mesrep = new String(req.getParameter("mesrep").trim());
				} catch (NumberFormatException e) {
					mesrep = null;
					errorMsgs.add("回復編號.");
				}
				
				String repdesc = null;
				try {
					repdesc = new String(req.getParameter("mesrep").trim());
				} catch (NumberFormatException e) {
					repdesc = null;
					errorMsgs.add("請填寫回復內容");
				}
				
				String mesdate = req.getParameter("mesdate").trim();
				if (mesdate == null || mesdate.trim().length() == 0) {
					errorMsgs.add("請輸入日期!");
				}

				MessageBoardVO messageboardVO = new MessageBoardVO();
				messageboardVO.setAskNone(asknone);
				messageboardVO.setArtNo(artno);
				messageboardVO.setAskDesc(askdesc);
				messageboardVO.setMesRep(mesrep);
				messageboardVO.setRepDesc(repdesc);
				messageboardVO.setMesDate(mesdate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("MessageBoardVO", messageboardVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/messageboard/update_Messageboard_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MessageBoardService messageboardSvc = new MessageBoardService();
				messageboardVO = messageboardSvc.updateMessageBoard(asknone,artno,askdesc,mesrep,repdesc,mesdate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageboardVO", messageboardVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/messageboard/update_Messageboard_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/messageboard/update_Messageboard_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
