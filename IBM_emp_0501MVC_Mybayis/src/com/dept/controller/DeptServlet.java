package com.dept.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.dept.model.*;
import com.emp.model.*;

public class DeptServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		    // �Ӧ�select_page.jsp���ШD                                  // �Ӧ� dept/listAllDept.jsp���ШD
		if ("listEmps_ByDeptno_A".equals(action) || "listEmps_ByDeptno_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer deptno = new Integer(req.getParameter("deptno"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				DeptService deptSvc = new DeptService();
				Set<EmpVO> set = deptSvc.getEmpsByDeptno(deptno);
				List<DeptVO> list = deptSvc.getAll();
				for(DeptVO dept : list)
					for(EmpVO emp : dept.getEmps())
						System.out.println(emp.getDeptVO());
				
				
				
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listEmps_ByDeptno", set);    // ��Ʈw���X��set����,�s�Jrequest

				String url = null;
				if ("listEmps_ByDeptno_A".equals(action))
					url = "/dept/listEmps_ByDeptno.jsp";        // ���\��� dept/listEmps_ByDeptno.jsp
				else if ("listEmps_ByDeptno_B".equals(action))
					url = "/dept/listAllDept.jsp";              // ���\��� dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("delete_Dept".equals(action)) { // �Ӧ�/dept/listAllDept.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer deptno = new Integer(req.getParameter("deptno"));
				
				/***************************2.�}�l�R�����***************************************/
				DeptService deptSvc = new DeptService();
//				deptSvc.deleteDept(deptno);
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				String url = "/dept/listAllDept.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��, ���\��� �^�� /dept/listAllDept.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z***********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dept/listAllDept.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
