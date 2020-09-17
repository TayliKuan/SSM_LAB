package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.*;
import com.product_order.model.Product_orderJDBCDAO;
import com.product_order.model.Product_orderService;
import com.product_order.model.Product_orderVO;
import com.product_order_list.model.Product_order_listVO;

public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

	
		
		
		String action = req.getParameter("action");
		System.out.println("action=" + action);
		
		@SuppressWarnings("unchecked")
		List<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("prodno");
				System.out.println(str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
					failureView.forward(req, res);
					return;
				}

				String prodno = null;
				try {
					prodno = new String(str);
					System.out.println(2);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
					System.out.println("商品編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProd(prodno);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO);
				System.out.println(productVO);
				String url = "/front-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
				failureView.forward(req, res);
			}
		}

		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中的商品
			if (action.equals("DELETE")) {
				System.out.println("del");
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			// 新增商品至購物車中
			else if (action.equals("ADD")) {
				// 取得後來新增的商品
				ProductVO aproduct = getProductVO(req);

				if (buylist == null) {
					buylist = new Vector<ProductVO>();
					buylist.add(aproduct);
				} else {
					if (buylist.contains(aproduct)) {
						ProductVO innerProduct = buylist.get(buylist.indexOf(aproduct));
						innerProduct.setQty(innerProduct.getQty() + aproduct.getQty());
					} else {
						buylist.add(aproduct);
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/front-end/product/textshoppingcar.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 結帳，計算購物車商品價錢總數
		else if (action.equals("CHECKOUT")) {
			int prodtotal = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				Integer prodprice = order.getProdprice();
				Integer qty = order.getQty();
				prodtotal += (prodprice * qty);
			}

			String amount = String.valueOf(prodtotal);
			req.setAttribute("amount", amount);
			String url = "/front-end/product/testCheckout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	

		
		if ("insert".equals(action)) {
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

				/************* 2.開始新增資料 **************************/
				Product_orderService prod_ordSvc = new Product_orderService();
				product_orderVO = prod_ordSvc.addPo(stuno, pordtotal,recipient,phonenumber, pordadd, "未出貨", 80);

				
				req.setAttribute("product_orderVO", product_orderVO);
				String url = "/back-end/product/thankBuy.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/textindex.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		
		
		
	}
	
	

	private ProductVO getProductVO(HttpServletRequest req) {

		String prodno = req.getParameter("prodno");
		String prodname = req.getParameter("prodname");
		String prodprice = req.getParameter("prodprice");
		String qty = req.getParameter("qty");

		System.out.println(prodno);
		System.out.println(prodname);
		System.out.println(prodprice);
		System.out.println(qty);

		ProductVO productVO = new ProductVO();

		productVO.setProdno(prodno);
		productVO.setProdname(prodname);
		productVO.setProdprice(new Integer(prodprice));
		productVO.setQty((new Integer(qty)).intValue());
		return productVO;

	}

}
