package com.product_fav.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_fav.model.*;
import com.product.model.*;

public class Product_favServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action=" + action);

		// 從學員編號查詢商品追蹤
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String stuno = req.getParameter("stuno");
				System.out.println(stuno);
				if (stuno == null || (stuno.trim()).length() == 0) {
					errorMsgs.add("請輸入你想查詢的學員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/product_favManage.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				Product_favService product_favSvc = new Product_favService();
				List<Product_favVO> product_favVO = product_favSvc.getOnePf(stuno);
				if (product_favVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/product_favManage.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("product_favVO", product_favVO);
				String url = "/back-end/product/listOneProductFav.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
	
			} catch (Exception e) {
				errorMsgs.add("無法取得要查詢的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/product_favManage.jsp");
				failureView.forward(req, res);
			}
		}

		
		/* 新增 */
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/* 接受請求參數 */
				String prodno = req.getParameter("prodno");
				System.out.println("得到得商品編號為"+prodno);
				String stuno = req.getParameter("stuno");
				System.out.println("得到的學員編號為"+stuno);
				String requestURL = req.getParameter("requestURL");
				System.out.println("requestURL為"+requestURL);
				
				Product_favVO aproduct = getProduct_favVO(req);
				Product_favService product_favSvc = new Product_favService();
				List<Product_favVO> list = product_favSvc.getOnePf("stuno");
				
				if(list==null) {
					list=new Vector<Product_favVO>();
					list.add(aproduct);
				}else {
					if(list.contains(aproduct)) {
						return;
					}else {
						list.add(aproduct);
					}
				}
				
				Product_favVO product_favVO = new Product_favVO();
				product_favVO.setStuno(stuno);
				product_favVO.setProdno(prodno);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_favVO", product_favVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product/product.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				product_favVO = product_favSvc.addProduct_fav(stuno, prodno);
				req.setAttribute("product_favVO", product_favVO);
				
				String url = requestURL;
			    RequestDispatcher successView = req.getRequestDispatcher(url);
//				String url = "/front-end/product/product.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String prodno = req.getParameter("prodno");
				System.out.println("要刪除的商品編號為"+prodno);
				if(prodno==null||prodno.trim().length()==0) {
					errorMsgs.add("商品編號為空");
				}
				
				String stuno = req.getParameter("stuno");
				System.out.println("要刪除的學員編號為"+stuno);
				if(stuno==null||stuno.trim().length()==0) {
					errorMsgs.add("學員編號為空");
				}
				
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/product/product_fav.jsp");
				failureView.forward(req, res);
				return;
			}
		/***********************************************************/
			Product_favService product_favVO= new Product_favService();
			product_favVO.deleteProduct_fav(stuno, prodno);
			System.out.println(product_favVO);
			
//			if(!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/product/product_fav.jsp");
//				failureView.forward(req, res);
//				return;
//			}
			req.setAttribute("product_favVO", product_favVO);
			String url="/front-end/product/product_fav.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req,res);
			
			}catch(Exception e) {
				errorMsgs.add("無法刪除追蹤中的商品"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product_fav.jsp");
				failureView.forward(req, res);
			}
		}
	}

	private Product_favVO getProduct_favVO(HttpServletRequest req) {

		String stuno = req.getParameter("stuno");
		String prodno = req.getParameter("prodno");

		System.out.println("getProduct_favVO=" + stuno);
		System.out.println("getProduct_favVO=" + prodno);

		Product_favVO product_favVO = new Product_favVO();

		product_favVO.setStuno(stuno);
		product_favVO.setProdno(prodno);
		return product_favVO;

	}

}