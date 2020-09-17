package com.employee.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class EmployeePicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Connection con ;
   
    private static final String EMP_PIC = "SELECT EPIC FROM EMPLOYEE WHERE EMPNO = ?";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");	//設定圖片
		ServletOutputStream out = res.getOutputStream();
		
		try{
		String empno = req.getParameter("empno");
		PreparedStatement pstmt = con.prepareStatement(EMP_PIC);
		pstmt.setString(1,empno);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			BufferedInputStream bis = new BufferedInputStream(rs.getBinaryStream("epic"));
			byte[] pic = new byte[16 * 1024];	//16K
			int len ;
			while((len=bis.read(pic))!=-1) {
				out.write(pic, 0 , len);
			}
			bis.close();
		} else {
			InputStream is = getServletContext().getResourceAsStream("/employee/nodata/9.jpg");
			byte[] fail = new byte[is.available()];
			is.read(fail);
			out.write(fail);
			is.close();
		}
		rs.close();
		pstmt.close();
		} catch (Exception e) {
			InputStream is = getServletContext().getResourceAsStream("/employee/nodata/9.jpg");
			byte[] fail = new byte[is.available()];
			is.read(fail);
			out.write(fail);
			is.close();
		}
	}

	
	//建立連線池
	public void init() throws ServletException {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
				con = ds.getConnection();
				
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	//關閉連線
	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}


