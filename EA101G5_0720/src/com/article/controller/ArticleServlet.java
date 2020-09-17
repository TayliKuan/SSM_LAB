package com.article.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ArticleServlet extends HttpServlet {
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
    		String action = req.getParameter("action");
    		
    		if ("getOne_For_Display".equals(action)) { 
    			try {
    				
    				String artno = req.getParameter("artno");
    				
    				System.out.println("artno:" + artno);

    				ArticleService articleSvc = new ArticleService();
    				ArticleVO articleVO = articleSvc.getOneArtno(artno);


    				req.setAttribute("articleVO", articleVO); 
    				String url = "/front-end/article/blogindex.jsp";
    				RequestDispatcher successView = req.getRequestDispatcher(url);
    				successView.forward(req, res);

    			} catch (Exception e) {
    				System.out.println("error: "+e.getMessage());
    			}
    		}    		
    		
    		doPost(req, res);
    	}
    
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
    	req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		if ("getArticleFromStuno_For_Display".equals(action)) { 

			try {

				String stuno = req.getParameter("stuno");


				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> list = articleSvc.getByStuno(stuno);
					


				req.setAttribute("stunoOfList", list); 
				String url = "/front-end/article/listStuArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
			}
		}

		if ("addArticle".equals(action)) { 


			try {
				
				InputStream inputStream = null;	
				
				Part filePart = req.getPart("photo");
				if (filePart != null) {
					System.out.println(filePart.getName());
					System.out.println(filePart.getSize());
					System.out.println(filePart.getContentType());
					
					// obtains input stream of the upload file
					inputStream = filePart.getInputStream();
				}
				
				
				int nRead;
				byte[] data = new byte[16384];
				byte[] artpic = new byte[16384];
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();

				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				  buffer.write(data, 0, nRead);
				}

				artpic = buffer.toByteArray();
				/***********************  *************************/
				String artTitle = req.getParameter("artTitle");
//				String artNo = req.getParameter("artNo").trim();
				String artCon = req.getParameter("artCon");
				
				Date mydate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String artDate = sdf.format(mydate);
				
				String stuNo = req.getParameter("stuNo");
				
				System.out.println("stuNo="+stuNo);
				String coaNo = req.getParameter("coaNo");
				System.out.println("coaNo="+coaNo);
				String artSta = "文章顯示";

				ArticleService articleSvc = new ArticleService();
				articleSvc.add( artTitle, artCon, artDate, stuNo,coaNo,artpic,artSta);

				String url = "/front-end/article/blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
			}
		}
		
		if ("getOne_For_Update".equals(action)) { 
			
			try {
				String artNo =req.getParameter("artNo");
				
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArtno(artNo);
				
				req.setAttribute("articleVO", articleVO);        
				String url = "/front-end/article/update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
			}
		}
		
		
		if ("getOne_For_BackendUpdate".equals(action)) { 
			
			try {
				String artNo =req.getParameter("artNo");
				
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArtno(artNo);
				
				req.setAttribute("articleVO", articleVO);        
				String url = "/back-end/article/update_article_backend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
			}
		}
		
		
		
		if ("backend_update".equals(action)) { 
			
			
			try {
				String artNo = req.getParameter("artno");
				String stuNo = req.getParameter("stuNo");
				String artTitle = req.getParameter("artTitle");
				String artCon = req.getParameter("artCon");
				String coaNo = req.getParameter("coaNo");
				String artSta = req.getParameter("artSta");
				
				
				InputStream inputStream = null;	
				Part filePart = req.getPart("photo");
				if (filePart != null) {
					System.out.println(filePart.getName());
					System.out.println(filePart.getSize());
					System.out.println(filePart.getContentType());
					inputStream = filePart.getInputStream();
				}
				int nRead;
				byte[] data = new byte[16384];
				byte[] artpic = new byte[16384];
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				  buffer.write(data, 0, nRead);
				}
				artpic = buffer.toByteArray();

				String artDate = req.getParameter("artDate");
				

				ArticleVO articleVO = new ArticleVO();
				articleVO.setArtNo(artNo);
				articleVO.setArtTitle(artTitle);
				articleVO.setArtCon(artCon);
				articleVO.setArtDate(artDate);
				articleVO.setStuNo(stuNo);
				articleVO.setCoaNo(coaNo);
				articleVO.setArtPic(artpic);
				articleVO.setArtSta(artSta);

				
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.update(artNo, artTitle, artCon, artDate, stuNo,coaNo,artpic,artSta);
				
				req.setAttribute("articleVO", articleVO);        
				String url = "/back-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
			}
		}
		
		
		if ("update".equals(action)) { 
			
		
			try {
				String artNo = req.getParameter("artno");
				String stuNo = req.getParameter("stuNo");
				String artTitle = req.getParameter("artTitle");
				String artCon = req.getParameter("artCon");
				String coaNo = req.getParameter("coaNo");
				
				InputStream inputStream = null;	
				Part filePart = req.getPart("photo");
				if (filePart != null) {
					System.out.println(filePart.getName());
					System.out.println(filePart.getSize());
					System.out.println(filePart.getContentType());
					inputStream = filePart.getInputStream();
				}
				int nRead;
				byte[] data = new byte[16384];
				byte[] artpic = new byte[16384];
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				  buffer.write(data, 0, nRead);
				}
				artpic = buffer.toByteArray();

				String artDate = req.getParameter("artDate");
				
				String artSta ="文章顯示";

				ArticleVO articleVO = new ArticleVO();
				articleVO.setArtNo(artNo);
				articleVO.setArtTitle(artTitle);
				articleVO.setArtCon(artCon);
				articleVO.setArtDate(artDate);
				articleVO.setStuNo(stuNo);
				articleVO.setCoaNo(coaNo);
				articleVO.setArtPic(artpic);
				articleVO.setArtSta(artSta);

				
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.update(artNo, artTitle, artCon, artDate, stuNo,coaNo,artpic,artSta);
				
				req.setAttribute("articleVO", articleVO); 
				
				if(stuNo.equals("")) {
				String url = "/front-end/article/listAllArticlebycao.jsp";
				RequestDispatcher successCoa = req.getRequestDispatcher(url); 
				successCoa.forward(req, res);
				
				}else {
				String url = "/front-end/article/listAllArticlebystu.jsp";
				System.out.println(2);
				RequestDispatcher successStu = req.getRequestDispatcher(url); 
				successStu.forward(req, res);
				}
				
			} catch (Exception e) {
			}
		}
		
		
	}
}
