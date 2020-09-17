package com.reportt.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.reportt.model.ReporttService;
import com.reportt.model.ReporttVO;


public class ReporttServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insertReport".equals(action)) { 
			
			try {
				String artno = req.getParameter("artNo");
				String repdesc = req.getParameter("repdesc");
				Date mydate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String repdate = sdf.format(mydate);
				
				ReporttService reporttSvc = new ReporttService();
				reporttSvc.addReportt(artno,repdesc,repdate);
				
				
				String url = "/front-end/article/blog.jsp";
				req.setAttribute("artno", artno);
				
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
			}
		}
		
		
		if ("update".equals(action)) {
			
			try {
				String repno = req.getParameter("repNo");
				String artno =req.getParameter("artno");
				String repdesc = req.getParameter("repdesc");
				String repdate = req.getParameter("repdate");
				String stuno = req.getParameter("stuno");
				String coano = req.getParameter("coano");
				String repsta = req.getParameter("repsta");

				
				ReporttVO reporttVO = new ReporttVO();
				reporttVO.setRepNo(repno);
				reporttVO.setArtNo(artno);
				reporttVO.setRepDesc(repdesc);
				reporttVO.setRepDate(repdate);
				reporttVO.setStuNo(stuno);
				reporttVO.setCoaNo(coano);
				reporttVO.setRepSta(repsta);
				
				
				ReporttService  reporttSvc = new ReporttService();
				reporttVO =reporttSvc.updateReportt(repno,artno,repdesc,repdate,stuno,coano,repsta);
				
				
				req.setAttribute("reporttVO", reporttVO);
				
				String url = "/back-end/reportt/blog_reportt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			}catch (Exception e) {
			}
		}
		
				if ("updateaudited".equals(action)) {
					
					try {
						String repno = req.getParameter("repno");
						String artno = req.getParameter("artno");
						String repdesc = req.getParameter("repdesc");
						String repdate = req.getParameter("repdate");
						String stuno = req.getParameter("stuno");
						String coano = req.getParameter("coano");
						String repsta = req.getParameter("repsta");
						ReporttService  reporttSvc = new ReporttService();
						reporttSvc.updateReportt(repno,artno,repdesc,repdate,stuno,coano,repsta);
					
						String url = "/back-end/reportt/listOneReportt.jsp";
						
						res.sendRedirect(req.getContextPath()+ url);
						return;
						
					}catch (Exception e) {
					}
				}
		
		if ("getOne_For_Display".equals(action)) { 

				try {
					String repno = req.getParameter("repno");
					

					ReporttService reporttSvc = new ReporttService();
					ReporttVO reporttVO = reporttSvc.getOneReportt(repno);
					req.setAttribute("reporttVO", reporttVO);
					String url = "/back-end/reportt/listOneReportt.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);

				} catch (Exception e) {
				}
			}
		
		
		if ("getOne_For_Update".equals(action)) { 

			
			try {
				String repno =req.getParameter("repNo");
				String artno =req.getParameter("artNo");
				
				ReporttService reporttSvc = new ReporttService();
				ReporttVO reporttVO = reporttSvc.getOneReportt(repno);
				
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArtno(artno);
				
				req.setAttribute("reporttVO", reporttVO);
				req.setAttribute("articleVO", articleVO);
				String url = "/back-end/reportt/update_reportt_status.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
			}
		}
		
		
		if ("Reportt_OneArticle".equals(action)) {
		
		try {
		String artno = req.getParameter("artNo");
		ArticleService articlesvc = new ArticleService();
		ArticleVO articleVO = articlesvc.getOneArtno(artno);
					

		req.setAttribute("articleVO", articleVO); 
		String url = "/back-end/reportt/addReportt.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);

		} catch (Exception e) {
		}
	}
		
		if ("addReportt".equals(action)) { 


			try {
				String repdesc = req.getParameter("repdesc");
				String repdate = req.getParameter("repdate");
				String artno = req.getParameter("artno");				
				Integer reportstatus = 0;
				ReporttVO reporttVO = new ReporttVO();				
				reporttVO.setArtNo(artno);
				reporttVO.setRepDesc(repdesc);
				reporttVO.setRepDate(repdate);

				ReporttService reporttSvc = new ReporttService();
				reporttVO = reporttSvc.addReportt(artno,repdesc,repdate);

				
				String url = "/front-end/article/listAllMessageBoard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
			}
		}

	}
}