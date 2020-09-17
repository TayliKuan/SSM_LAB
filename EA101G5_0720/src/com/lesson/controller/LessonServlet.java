package com.lesson.controller;

import java.io.IOException;
import java.io.InputStream;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.lesson.model.LessonService;
import com.lesson.model.LessonVO;
import com.lessonTime.model.LessonTimeService;

@WebServlet("/LessonVO")
@MultipartConfig
public class LessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LessonServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String coano = req.getParameter("coano");
				System.out.println("coano="+coano);
				String lessname = req.getParameter("lessname");
//				System.out.println("lessname"+lessname);
				if (lessname == null || lessname.trim().length() == 0) {
					errorMsgs.add("課堂名稱: 請勿空白");
				}
				
				String lesstype = req.getParameter("lesstype");
				if(lesstype ==  null || lesstype.trim().length() == 0) {
					errorMsgs.add("請選擇課堂類型");
				}
				Integer lessmax = null;

				try {
					lessmax = new Integer(req.getParameter("lessmax"));

				} catch (Exception e) {
					errorMsgs.add("上限人數請填數字");
				}

				Integer lessmin = null;
				try {
					lessmin = new Integer(req.getParameter("lessmin"));
					if(lessmax<lessmin) {
						errorMsgs.add("下限人數不可高於上限人數");
					}
					
				} catch (Exception e) {
					errorMsgs.add("下限人數請填數字");
				}

				Integer lessprice = null;
				try {
					lessprice = new Integer(req.getParameter("lessprice"));

				} catch (Exception e) {
					errorMsgs.add("課程欲售點數請填數字");
				}

				String city = req.getParameter("city");
//				System.out.println(city);
				String town = req.getParameter("town");
//				System.out.println(town);
				String lesslocAdd = req.getParameter("lesslocAdd");
//				System.out.println(lesslocAdd);
				String lessloc = city+town+lesslocAdd;

				java.sql.Date lessstart = java.sql.Date.valueOf(req.getParameter("lessstart"));
				java.sql.Date lessend = java.sql.Date.valueOf(req.getParameter("lessend"));
				Integer lesstimes = null;
				try {
					lesstimes = new Integer(req.getParameter("lesstimes"));

				} catch (Exception e) {
					lessprice = 0;
					errorMsgs.add("課程堂數請填數字");
				}

				String lessdesc = req.getParameter("lessdesc");

				LessonVO lessonVO = new LessonVO();
				lessonVO.setCoano(coano);
				lessonVO.setLessname(lessname);
				lessonVO.setLessmax(lessmax);
				lessonVO.setLessmin(lessmin);

				lessonVO.setLesscur(0);
				lessonVO.setLesstype(lesstype);
				lessonVO.setLessloc(lessloc);
				lessonVO.setLessprice(lessprice);
				lessonVO.setLessdesc(lessdesc);

				lessonVO.setLessstart(lessstart);
				lessonVO.setLessend((lessend));
				lessonVO.setLesssta("未成團");
				lessonVO.setLesstimes(lesstimes);

				Part part = req.getPart("lesspic");
				InputStream in = part.getInputStream();
				byte[] img = new byte[in.available()];
				in.read(img);
				in.close();
				lessonVO.setLesspic(img);


				if (!errorMsgs.isEmpty()) {
					for (String str : errorMsgs) {
						System.out.println(str);
					}
					req.setAttribute("lessonVO", lessonVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/lesson/addLesson.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				LessonService lessonSvc = new LessonService();
				String lessno_seq = lessonSvc.addLesson(coano, lessname, lessmax, lessmin, 0, lesstype, lessloc, lessprice,
						lessdesc, lessstart, lessend, "未成團", lesstimes, img);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				
				
				req.setAttribute("lessno_seq", lessno_seq);
				
				String url = "/front-end/lesson/addTime.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/lesson/addLesson.jsp");
				failureView.forward(req, res);

			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.接收請求參數****************************************/
				String lessno = new String(req.getParameter("lessno"));
				/***************************2.開始查詢資料****************************************/
				LessonService lessonSvc = new LessonService();
				LessonVO lessonVO = lessonSvc.getOneByPK(lessno);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("lessonVO", lessonVO);
				String url ="/front-end/lesson/updateLesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理************************************/

			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
		
		if("show_lesson_detail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String lessno = new String(req.getParameter("lessno"));
//				String coano = new String(req.getParameter("coano"));
				/***************************2.開始查詢資料****************************************/
				LessonService lessonSvc = new LessonService();
				LessonVO lessonVO = lessonSvc.getOneByPK(lessno);
				
//				CoachService coachSvc = new CoachService();
//				CoachVO coachVO = coachSvc.getOneByPK();
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("lessonVO", lessonVO);
//				req.setAtrribute("coachVO",coachVO);
				String url ="/front-end/lesson/lesson_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理************************************/

			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String lessno = req.getParameter("lessno");
				
				String lessname = req.getParameter("lessname");
				
				if (lessname == null || lessname.trim().length() == 0) {
					errorMsgs.add("課堂名稱: 請勿空白");
				}
				String lesstype = req.getParameter("lesstype");
				
				if(lesstype ==  null || lesstype.trim().length() == 0) {
					errorMsgs.add("請選擇課堂類型");
				}

				Integer lessmax = null;
				try {
					lessmax = new Integer(req.getParameter("lessmax"));

				} catch (Exception e) {
					errorMsgs.add("上限人數請填數字");
				}
				

				Integer lessmin = null;
				try {
					lessmin = new Integer(req.getParameter("lessmin"));
					if(lessmax<lessmin) {
						errorMsgs.add("下限人數不可高於上限人數");
					}
				} catch (Exception e) {
						errorMsgs.add("下限人數請填數字");					
				}
				
				
				Integer lessprice = null;
				try {
					lessprice = new Integer(req.getParameter("lessprice"));

				} catch (Exception e) {
					errorMsgs.add("課程欲售點數請填數字");
				}

				String lessloc = req.getParameter("lessloc");

				java.sql.Date lessstart = java.sql.Date.valueOf(req.getParameter("lessstart"));
				java.sql.Date lessend = java.sql.Date.valueOf(req.getParameter("lessend"));

				
				Integer lesstimes = null;
				try {
					lesstimes = new Integer(req.getParameter("lesstimes"));

				} catch (Exception e) {
					lessprice = 0;
					errorMsgs.add("課程堂數請填數字");
				}

				String lessdesc = req.getParameter("lessdesc");
				
				
				int lesscur =0;
				String lesssta = null;
				String coano = null;
				
				LessonService lessSvc = new LessonService();
				LessonVO lessVO = lessSvc.getOneByPK(lessno);
				coano = lessVO.getCoano();
				lesssta = lessVO.getLesssta();
				lesscur = lessVO.getLesscur(); 
				
				//圖片
				byte[] img=null;
				//前端接收圖片 
				Part part = req.getPart("lesspic");
				InputStream in = part.getInputStream();
				if(in.available()>0) {
					img = new byte[in.available()];
					in.read(img);
					in.close();
					
				//如果沒有圖片 就拿原本VO內的圖片	
				}else {
					img = lessVO.getLesspic();
				}				
			
				LessonVO lessonVO = new LessonVO();
				lessonVO.setCoano(coano);
				lessonVO.setLessno(lessno);
				lessonVO.setLessname(lessname);
				lessonVO.setLessmax(lessmax);
				lessonVO.setLessmin(lessmin);
				
				lessonVO.setLesscur(lesscur);
				lessonVO.setLesstype(lesstype);
				lessonVO.setLessloc(lessloc);
				lessonVO.setLessprice(lessprice);
				lessonVO.setLessdesc(lessdesc);

				lessonVO.setLessstart(lessstart);
				lessonVO.setLessend(lessend);
				lessonVO.setLesssta(lesssta);
				lessonVO.setLesstimes(lesstimes);

				lessonVO.setLesspic(img);
				System.out.println(img);

				if (!errorMsgs.isEmpty()) {
					for (String str : errorMsgs) {
						System.out.println(str);
					}
					
					req.setAttribute("lessonVO", lessonVO); // 含有輸入格式錯誤的LessonVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/lesson/updateLesson.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 2.開始修改資料***************************************/
				LessonService lessonSvc = new LessonService();
				lessonVO = lessonSvc.updateLesson(lessno,coano,lessname, lessmax, lessmin,lesscur,lesstype, lessloc, lessprice,
						lessdesc, lessstart, lessend, lesssta, lesstimes, img);

				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("lessonVO",lessonVO);// 資料庫update成功後,正確的的LessonVO物件,存入req
				String url = "/front-end/lesson/selectOneLesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				req.setAttribute("updateLesson","課程修改成功");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/lesson/updateLesson.jsp");
				failureView.forward(req, res);

			}
		}
		if("off_lesson".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
				
				
			try {
				/***************************1.接收請求參數****************************************/
				String lessno = new String(req.getParameter("lessno"));
				System.out.println(lessno);
				/***************************2.開始查詢資料****************************************/
				//手動下架課程 改狀態為下架  (課程不刪 只改狀態 )
				LessonService lessonSvc = new LessonService();
				lessonSvc.update_off(lessno);
				//刪除對應的時段跟明細 課表更新
				LessonTimeService ltimeSvc = new LessonTimeService();
				ltimeSvc.deleteLessonTime(lessno);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				System.out.println("OK");
				String url ="/front-end/lesson/selectLesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理************************************/

			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
		if("getListFromType".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String lesstype = new String(req.getParameter("lesstype"));
//				System.out.println("lesstype="+lesstype);
				/***************************2.開始查詢資料****************************************/
				LessonService lessonSvc = new LessonService();
				List<LessonVO> getTypeList = lessonSvc.findLessonByLessonType(lesstype);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("gettype","gettype");
				req.setAttribute("lesstype", lesstype);
				req.setAttribute("getTypeList", getTypeList);
				String url ="/front-end/lesson/listAll_lesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				/***************************其他可能的錯誤處理************************************/

			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
	}

}
