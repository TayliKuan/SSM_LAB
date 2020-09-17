package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmployeeFilter implements Filter{

	@SuppressWarnings("unused")
	private FilterConfig config ;
	
	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
	
		HttpSession session = req.getSession();	
		Object empVO = session.getAttribute("empVO");
		if(empVO==null) {
			session.setAttribute("location", req.getRequestURI());
			//返回首頁登入
			res.sendRedirect(req.getContextPath()+"/back-end/backend_index.jsp");
			return ;
		} else {
			chain.doFilter(request, response);
		}
	}

}
