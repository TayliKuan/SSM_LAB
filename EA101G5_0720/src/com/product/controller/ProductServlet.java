package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.*;
import com.sale_list.model.Sale_listService;
import com.sale_list.model.Sale_listVO;
import com.sale_project.model.Sale_projectService;
import com.sale_project.model.Sale_projectVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("prodno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/productManage.jsp");
					failureView.forward(req, res);
					return;
				}

				String prodno = null;
				try {
					prodno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/productManage.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/productManage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO);

				String url = "/back-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listOneProduct.jsp");
				failureView.forward(req, res);
			}
		}

		//修改:轉交
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/**************************1.接收請求參數 *****************************/
				String prodno = new String(req.getParameter("prodno"));
				/**************************2.開始查詢資料*****************/
				ProductService prodSvc = new ProductService();
				ProductVO productVO = prodSvc.getOneProd(prodno);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);
				String url = "/back-end/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/productManage.jsp");
				failureView.forward(req, res);
			}

		}
		
//修改:update
		
		if("update".equals(action)) {
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				//商品編號
				String prodno = req.getParameter("prodno").trim();
				//商品類別編號
				String pclass_id = req.getParameter("pclass_id");
				//商品名稱
				String prodname = req.getParameter("prodname").trim();
				if (prodname == null || prodname.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}
				
				//商品價格
				Integer prodprice = null;
				try {
					prodprice = new Integer(req.getParameter("prodprice").trim());
					System.out.println(prodprice);
					
				} catch (NumberFormatException e) {
					prodprice = 0;
					errorMsgs.add("價格請填數字");
				}
				
				//商品數量
				Integer prodqty = null;
				try {
					prodqty = new Integer(req.getParameter("prodqty").trim());
					System.out.println(prodqty);
				} catch (NumberFormatException e) {
					prodqty = 0;
					errorMsgs.add("商品數量請填數字");
				}
				
				//商品狀態
				String prodsta = req.getParameter("prodsta");
				System.out.println(prodsta);
				if (prodsta == null || prodsta.trim().length() == 0) {
					errorMsgs.add("商品狀態不得為空");
				}
				
				//圖片
				byte[] prodpic;
				Part picpart2 = req.getPart("prodpic"); //把prodpic包裝成part物件
				InputStream in2 = picpart2.getInputStream();//用inputstream傳輸
				System.out.println(in2.available());
				
				 //把資料傳入陣列中
				if(in2.available()>0) { //錯誤處理
					prodpic = new byte[in2.available()];
					in2.read(prodpic);
					in2.close();
				}else {
					ProductService productSvc = new ProductService();
					ProductVO productVO = productSvc.getOneProd(prodno);
					prodpic = productVO.getProdpic();
				}
					
				String proddesc=req.getParameter("proddesc");
				System.out.println(proddesc);
				if (proddesc == null || proddesc.trim().length() == 0) {
					errorMsgs.add("商品描述不得為空");
				}
				
				ProductVO productVO = new ProductVO();
				
				productVO.setProdno(prodno);
				productVO.setPclass_id(pclass_id);
				productVO.setProdname(prodname);
				productVO.setProdprice(prodprice);
				productVO.setProdqty(prodqty);
				productVO.setProdpic(prodpic);
				productVO.setProddesc(proddesc);
				productVO.setProdsta(prodsta);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/update_product_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/*******************2.開始修改資料************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProd(prodno,pclass_id, prodname, prodprice, prodqty, prodpic, proddesc, prodsta);
				/*******************3.修改完成轉交資料********************/
				req.setAttribute("productVO", productVO);
				String url = "/back-end/product/productManage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/****************4.其他可能的錯誤************************************/
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/update_product_input.jsp");
				failureView.forward(req, res);
			}
		}

//新增
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			try {
				
				//商品類別編號
				String pclass_id = req.getParameter("pclass_id");
				//商品名稱
				String prodnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				String prodname = req.getParameter("prodname").trim();
				if (prodname == null || prodname.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}else if(!prodname.trim().matches(prodnameReg)) {
					errorMsgs.add("商品名稱只能是中文、英文、數字和底線，且長度需在2-10之間");
				}
				
				//商品價格
				Integer prodprice = null;
				try {
					prodprice = new Integer(req.getParameter("prodprice").trim());
					System.out.println(prodprice);
					
				} catch (NumberFormatException e) {
					prodprice = 0;
					errorMsgs.add("價格請填數字");
				}
				
				
				//商品數量
				Integer prodqty = null;
				try {
					prodqty = new Integer(req.getParameter("prodqty").trim());
					System.out.println(prodqty);
				} catch (NumberFormatException e) {
					prodqty = 0;
					errorMsgs.add("商品數量不得為零");
				}
				
				//商品狀態
				String prodsta = req.getParameter("prodsta");
				System.out.println(prodsta);
				if (prodsta == null || prodsta.trim().length() == 0) {
					errorMsgs.add("商品狀態不得為空");
				}
				
				//圖片
				byte[] prodpic;
				Part picpart = req.getPart("prodpic"); //把prodpic包裝成part物件
				System.out.println(picpart);
				InputStream in = picpart.getInputStream();//用inputstream傳輸
				prodpic = new byte[in.available()]; //把資料傳入陣列中
				if(in.available()==0) { //錯誤處理
					errorMsgs.add("請上傳圖片");
				}
				in.read(prodpic);
				in.close();
				
				
				String proddesc=req.getParameter("proddesc");
				System.out.println(proddesc);
				if (proddesc == null || proddesc.trim().length() == 0) {
					errorMsgs.add("商品描述不得為空");
				}
				

				ProductVO productVO = new ProductVO();
				
				productVO.setPclass_id(pclass_id);
				productVO.setProdname(prodname);
				productVO.setProdprice(prodprice);
				productVO.setProdqty(prodqty);
				productVO.setProdpic(prodpic);
				productVO.setProddesc(proddesc);
				productVO.setProdsta(prodsta);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProd(pclass_id,prodname,prodprice,prodqty,prodpic,proddesc,prodsta);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO);
				String url = "/back-end/product/productManage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/update_product_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)) {
			List<String>errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String prodno =new String(req.getParameter("prodno"));
				ProductService productSvc = new ProductService();
				productSvc.deleteProd(prodno);
				
				String url = "/back-end/product/productManage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);			
				}catch(Exception e){
				errorMsgs.add("刪除資料失敗"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/productManage.jsp");
				failureView.forward(req, res);
				
			}
		}
		
//查詢一筆商品類別
		if ("pclass".equals(action)) {
			System.out.println("action="+action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/**************************1.接收請求參數 *****************************/
				String pclass_id = req.getParameter("pclass_id");
				System.out.println(pclass_id);
				
				/**************************2.開始查詢資料*****************/
				ProductService productSvc = new ProductService();
				List<ProductVO> productVOlist = productSvc.findyByPclass(pclass_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String url = "/front-end/product/product_class.jsp";
				req.getSession().setAttribute("productVOlist", productVOlist);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
				failureView.forward(req, res);
			}

		}
		
		//查詢
				if ("one_sapro".equals(action)) {
					System.out.println("action="+action);
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/**************************1.接收請求參數 *****************************/
						String sapro_no = req.getParameter("sapro_no");
						System.out.println(sapro_no);
						
						/**************************2.開始查詢資料*****************/
						Sale_projectService sale_projectSvc = new Sale_projectService();
						Sale_projectVO sale_projectVO_for_index = sale_projectSvc.getOneSp("sapro_no");
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						String url = "/front-end/product/sale_project.jsp";
						req.getSession().setAttribute("sale_projectVO_for_index", sale_projectVO_for_index);
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
						failureView.forward(req, res);
					}

				}
		
		
		
		
		if ("sale_project".equals(action)) {
//			System.out.println("action="+action);
	
			String requestURL = req.getParameter("requestURL");
			String whichPage = req.getParameter("whichPage");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/**************************1.接收請求參數 *****************************/
//				String sapro_no = req.getParameter("sapro_no");
//				System.out.println(sapro_no);
				/**************************2.開始查詢資料*****************/
				
				ProductService productSvc = new ProductService();

								
								
//----------------------------得到全部的特價商品		
//Sale_projectService saleSvc = new Sale_projectService();
//List<Sale_projectVO> Sale_projectVO_list = saleSvc.getAllfilter();
//
//Sale_listService sale_listSvc2 = new Sale_listService();
//List <Sale_listVO> sale_list = null;
//
//
//Map<String, String>  salemap = new HashMap<>();
//
//for(Sale_projectVO  Sale : Sale_projectVO_list){
//    sale_list = sale_listSvc2.getOneSl(Sale.getSapro_no());
//    for(Sale_listVO  Salelist : sale_list){
//        salemap.put(Salelist.getProdno(),String.valueOf(Salelist.getSapro_price()));
//    }
//}
//		
//
//		Set<String> sale_no = salemap.keySet();	
//
//        List<ProductVO> product_sale_VO_list = new ArrayList<>();
//
//
//	for(String prodno :sale_no) {
//		
//		product_sale_VO_list.add(productSvc.getOneProd(prodno));		
//	}

	
//------------------------------------得到特價專案的商品				
			
				
				String sale_id = req.getParameter("sale_id");
				System.out.println(sale_id);
				
				Sale_listService sale_listSvc2 = new Sale_listService();
				List <Sale_listVO> sale_list = null;

				Sale_projectService Sale_projectSvc =new Sale_projectService();
				Sale_projectVO saleprojectVO = Sale_projectSvc.getOneSp(sale_id);
				
				long start = saleprojectVO.getSapro_start().getTime();
				long end = saleprojectVO.getSapro_end().getTime();
	
if(start < System.currentTimeMillis() && end> System.currentTimeMillis()) {
			    sale_list = sale_listSvc2.getOneSl(sale_id);//特價專案的商品list
				System.out.println("sale_list.size"+sale_list.size());

//			    for(Sale_listVO sale:sale_list) {
//			    System.out.println("prodno"+sale.getProdno());
//			    System.out.println("price"+sale.getSapro_price());
//
//			    }
			  Map<String, String>  salemap = new HashMap<>();
			  
			      for(Sale_listVO  Salelist : sale_list){
			          salemap.put(Salelist.getProdno(),String.valueOf(Salelist.getSapro_price()));
			      }
		  		Set<String> sale_no = salemap.keySet();	 //促銷商品的商品編號<PRODNO>,商品促銷價格<SAPRO_PRICE>
		  		List<ProductVO> product_sale_VO_list = new ArrayList<>();	          	          
	          	for(String prodno :sale_no) {	                                       
	          		product_sale_VO_list.add(productSvc.getOneProd(prodno));		
	          	}

System.out.println("URL:"+requestURL);
System.out.println(product_sale_VO_list.size());
				

//				
//				System.out.println("活動開始日為="+sale_projectVO.getSapro_start());
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.getSession().setAttribute("product_sale_VO_list", product_sale_VO_list);
				req.setAttribute("salemap", salemap);	
}
				String url = "/front-end/product/sale_project.jsp";		
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("此專案已過期");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/product.jsp");
				failureView.forward(req, res);
			}	
		}	
	}
}