package com.product_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_class.model.Product_classService;
import com.product_order.model.*;
import com.product_order_list.model.*;
import org.json.*;
import com.product_order_list.controller.*;

public class product_orderServlet_ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("testestestestestestest");
		String action = req.getParameter("action");
		System.out.println(action);
		
		
		if ("cart".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
		String cart_list = req.getParameter("cart_list");
	
		if (cart_list == null || cart_list.trim().length() == 0) {
			errorMsgs.put("cart_list","購物車是空的");
		}
		
		String stuno = req.getParameter("stuno");
		
		if (cart_list == null || cart_list.trim().length() == 0) {
			errorMsgs.put("stuno","沒有stuno");
		}
		System.out.println(cart_list);
		System.out.println(stuno);
		
		
		HttpSession session = req.getSession();
		session.setAttribute("cart_list", cart_list);
		
		String requestURL = req.getParameter("requestURL");

		
		JSONObject jsonObject = new JSONObject(cart_list);
		
		Iterator<String> keys = jsonObject.keys();
		
		List  <Product_order_listVO> list = new ArrayList<Product_order_listVO>();
		while(keys.hasNext()) {
		    String key = keys.next();
		    JSONObject json_one= (JSONObject) jsonObject.get(key);
		    Product_order_listVO product_order_listVO = new Product_order_listVO();
		    
		    product_order_listVO.setPordno(json_one.get("name").toString());
		    product_order_listVO.setProdno(json_one.get("pordno").toString());
		    product_order_listVO.setPord_listprice(Integer.parseInt(json_one.getString("pro_price").toString()));
		    product_order_listVO.setPord_listqty(Integer.parseInt(json_one.get("pord_listqty").toString()));
		    
		    list.add(product_order_listVO);
		    System.out.println(product_order_listVO.getPordno());
		    System.out.println(product_order_listVO.getProdno());
		}
		
//		List<String> errorMsgs = new LinkedList<String>();
//		req.setAttribute("errorMsgs", errorMsgs);
		
		try {	
		

		req.setAttribute("list", list);
		req.setAttribute("stuno", stuno);
		
		res.setContentType("text/html; charset=utf-8");
      PrintWriter out = res.getWriter();


//		String url = requestURL;
//		ProductService prodSvc = new ProductService();
//		if(requestURL.equals("/front-end/product/listOneProduct.jsp")) {
//			req.setAttribute("productVO",prodSvc.getOneProd("prodno"));
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			return;
//		}
			

		RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/Checkout.jsp");
		successView.forward(req, res);
		
		
	} catch (Exception e) {
		res.setContentType("text/html; charset=utf-8");
        PrintWriter out = res.getWriter();
        RequestDispatcher error = req.getRequestDispatcher("/front-end/product/product.jsp");
		error.forward(req, res);
        System.out.println("GG");
	}
		}
		
		
		//單一商品
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String cart_list = req.getParameter("cart_list");
			String stuno = req.getParameter("stuno");
			System.out.println(cart_list);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String prodno = req.getParameter("prodno");
				System.out.println(prodno);
				if (prodno == null || (prodno.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
					failureView.forward(req, res);
					return;
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
				
				HttpSession session = req.getSession();
				session.setAttribute("cart_list", cart_list);
				
				
				String url = "/front-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
				failureView.forward(req, res);
			}
		}
		
	
	
		if ("insert".equals(action)) {
			
			System.out.println("action"+action);
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String stuno = req.getParameter("stuno").trim();
				
				System.out.println("stuno"+stuno);
	
//				String stunonameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
//				if (stuno == null || stuno.trim().length() == 0) {
//					errorMsgs.add("姓名請勿空白");	
//				} else if (stuno.trim().matches(stunonameReg)) {
//					errorMsgs.add("姓名只能是中文、英文，且長度需在2-10之間");
//				}

				String recipient = req.getParameter("recipient");
//				String recipientReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (recipient == null || recipient.trim().length() == 0) {
					errorMsgs.put("recipient","收件人姓名請勿空白");
				} 
//					else if (!stuno.trim().matches(recipientReg)) {
//					errorMsgs.add("收件人姓名只能是中文、英文，且長度需在2-10之間");
//				}

				int pordtotal = Integer.parseInt(req.getParameter("amount").trim());

				String phonenumber = req.getParameter("phonenumber").trim();
//				String phonenumberReg = "/^09[0-9]{8}$/";
				if (phonenumber == null || phonenumber.trim().length() == 0) {
					errorMsgs.put("phonenumber","電話請勿空白");

				} 
//				else if (!phonenumber.trim().matches(phonenumberReg)) {
//					errorMsgs.put("phonenumber","電話格式錯誤");
//
//				}

				String pordadd = req.getParameter("pordadd").trim();

				if (pordadd == null || pordadd.trim().length() == 0) {
					errorMsgs.put("pordadd","地址請勿空白");

				}
				

				
				
				System.out.println("pordadd:"+pordadd);
				System.out.println("phonenumber:"+phonenumber);
				System.out.println("pordtotal:"+pordtotal);
				System.out.println("recipient:"+recipient);

				
//*****************				
				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setStuno(stuno);
				product_orderVO.setPordtotal(pordtotal);
				product_orderVO.setRecipient(recipient);
				product_orderVO.setPhonenumber(phonenumber);
				product_orderVO.setPordadd(pordadd);
				product_orderVO.setFare(80);
				product_orderVO.setPordsta("未出貨");
				
				
			System.out.println("學員編號:"+product_orderVO.getStuno());
			System.out.println("訂單總價:"+product_orderVO.getPordtotal());	
			System.out.println("訂單狀態"+product_orderVO.getPordsta());	
			System.out.println("收件人姓名:"+product_orderVO.getRecipient());		
			System.out.println("收件人地址:"+product_orderVO.getPordadd());	
			System.out.println("收件人電話:"+product_orderVO.getPhonenumber());	
			System.out.println("運費:"+product_orderVO.getFare());	

				
				req.setAttribute("product_orderVO",product_orderVO);
				
				
			
				
				List<Product_order_listVO> list = new ArrayList<Product_order_listVO>();
				String[] prodno = req.getParameterValues("prodno");
//
//				for (String item : prodno) {
//					System.out.println(item);
//				}

				String[] prodname = req.getParameterValues("prodname");
//				for (String item : prodname) {
//					System.out.println(item);
//				}
				String[] prodprice = req.getParameterValues("prodprice");
//				for (String item : prodprice) {
//					System.out.println(item);
//				}
				String[] qty = req.getParameterValues("qty");
//				for (String item : qty) {
//					System.out.println(item);
//				}
//				
			
				
				for(int i =0; i<prodno.length;i++) {
					Product_order_listVO product_order_listVO = new Product_order_listVO();
					product_order_listVO.setPordno(prodname[i]);
					product_order_listVO.setProdno(prodno[i]);
					product_order_listVO.setPord_listqty(Integer.parseInt(qty[i]));
					product_order_listVO.setPord_listprice(Integer.parseInt(prodprice[i]));
					list.add(product_order_listVO);
				}
				req.setAttribute("list", list);
//				for(Product_order_listVO vo : list) {
//					System.out.println(vo.getPord_listprice());
//					System.out.println(vo.getProdno());
//					System.out.println(vo.getPord_listqty());
//				}
				
				

				
				String stupoint = req.getParameter("newpoint");
				
			    int point = Integer.parseInt(stupoint);
				
				
			    
			    if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product/Checkout.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/************* 2.開始新增資料 **************************/
				Product_orderService prod_ordSvc = new Product_orderService();
//****************				
				prod_ordSvc.insertWithPordList(product_orderVO, list,point);
				
//				product_orderVO = prod_ordSvc.addPo(stuno, pordtotal, recipient, phonenumber, pordadd, "未出貨", 80);
//				req.setAttribute("product_orderVO", product_orderVO);
				String url = "/front-end/product/thankBuy.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/Checkout.jsp");
				failureView.forward(req, res);
			}
		}
	
			
	}
		



}
