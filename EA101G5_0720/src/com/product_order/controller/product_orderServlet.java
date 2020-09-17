package com.product_order.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_order.model.*;


public class product_orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action=" + action);
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/****************************** 1.接收請求參數 *****************************/
				String pordno = new String(req.getParameter("pordno"));
				System.out.println("getOne_For_Update的訂單編號為" + pordno);
				/****************************** 2.開始查詢資料 ****************************/
				Product_orderService prod_ordSvc = new Product_orderService();
				Product_orderVO product_orderVO = prod_ordSvc.getOnePo(pordno);
				/********************** 3.查詢完成,準備轉交(Send the Success view) ********/
				req.setAttribute("product_orderVO", product_orderVO);
				String url = "/back-end/product/update_product_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/product_orderManage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pordadd = req.getParameter("pordadd");
				System.out.println("訂單地址為" + pordadd);
				String pordno = req.getParameter("pordno");
				System.out.println("訂單編號為:" + pordno);
				Integer pordtotal = null;
				pordtotal = new Integer(req.getParameter("pordtotal"));
				System.out.println("訂單總金額為" + pordtotal);
				String recipient = req.getParameter("recipient");
				System.out.println("收件人為" + recipient);
				String phonenumber = req.getParameter("phonenumber");
				System.out.println("收件人電話為" + phonenumber);
				String stuno = req.getParameter("stuno");
				System.out.println("學員編號為" + stuno);
				String pordsta = req.getParameter("pordsta");
				System.out.println("訂單狀態為" + pordsta);
				Integer fare = null;
				fare = new Integer(req.getParameter("fare"));
				System.out.println("運費為" + fare);

				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setPordno(pordno);
				product_orderVO.setStuno(stuno);
				product_orderVO.setPordtotal(pordtotal);
				product_orderVO.setRecipient(recipient);
				product_orderVO.setPhonenumber(phonenumber);
				product_orderVO.setPordadd(pordadd);
				product_orderVO.setPordsta(pordsta);
				product_orderVO.setFare(fare);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/update_product_order_input.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/************* 2.開始修改資料 **************************/
				Product_orderService prod_ordSvc = new Product_orderService();
				product_orderVO = prod_ordSvc.updatePo(pordno, stuno, pordtotal, recipient, phonenumber, pordadd,
						pordsta, fare);
				/************* 3.修改完成，準備轉交 **************************/
				req.setAttribute("product_orderVO", product_orderVO);
				String url = "/back-end/product/product_orderManage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/update_product_order_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			
			System.out.println("23132132123s");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("erroeMsgs", errorMsgs);

			try {
				String stuno = req.getParameter("stuno").trim();
				String stunonameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (stuno == null || stuno.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				} else if (!stuno.trim().matches(stunonameReg)) {
					errorMsgs.add("姓名只能是中文、英文，且長度需在2-10之間");
				}

				String recipient = req.getParameter("recipient").trim();
				String recipientReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (recipient == null || recipient.trim().length() == 0) {
					errorMsgs.add("收件人姓名請勿空白");
				} else if (!stuno.trim().matches(recipientReg)) {
					errorMsgs.add("收件人姓名只能是中文、英文，且長度需在2-10之間");
				}

				Integer pordtotal = Integer.parseInt(req.getParameter("amount"));

				String phonenumber = req.getParameter("phonenumber").trim();
				String phonenumberReg = "^[0-9]";
				if (phonenumber == null || phonenumber.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				} else if (!phonenumber.trim().matches(phonenumberReg)) {
					errorMsgs.add("電話只能填寫數字");
				}

				String pordadd = req.getParameter("pordadd").trim();

				if (pordadd == null || pordadd.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setStuno(stuno);
				product_orderVO.setPordtotal(pordtotal);
				product_orderVO.setRecipient(recipient);
				product_orderVO.setPhonenumber(phonenumber);
				product_orderVO.setPordadd(pordadd);

				String[] prodno = req.getParameterValues("prodno");

				for (String item : prodno) {
					System.out.println(item);
				}

				String[] prodname = req.getParameterValues("prodname");
				for (String item : prodname) {
					System.out.println(item);
				}
				String[] prodprice = req.getParameterValues("prodprice");
				for (String item : prodprice) {
					System.out.println(item);
				}
				String[] qty = req.getParameterValues("qty");
				for (String item : qty) {
					System.out.println(item);
				}
				/************* 2.開始新增資料 **************************/
				Product_orderService prod_ordSvc = new Product_orderService();
				product_orderVO = prod_ordSvc.addPo(stuno, pordtotal, recipient, phonenumber, pordadd, "未出貨", 80);
				req.setAttribute("product_orderVO", product_orderVO);
				String url = "/back-end/product/thankBuy.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/textindex.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneBoot".equals(action)) {

			try {
				// Retrieve form parameters.
				String pordno = req.getParameter("pordno");

				Product_orderDAO dao = new Product_orderDAO();
				Product_orderVO product_orderVO = dao.findByPrimaryKey(pordno);

				req.setAttribute("product_orderVO",product_orderVO); 
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal);
				RequestDispatcher successView = req
						.getRequestDispatcher("/back-end/product/product_orderManage.jsp");
				successView.forward(req, res);


				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

	}
}
