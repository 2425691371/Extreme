package com.cdsxt.filter;

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

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//过滤器一般都是先强转对象
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//获取访问地址
		String uri=req.getRequestURI();
		String uri1=req.getParameter("mark");
		if(uri.endsWith("login.jsp")||uri.endsWith("loadfinish.jsp")||uri.indexOf("images")!=-1||"login".equals(uri1)||"success".equals(uri1)){
		chain.doFilter(req, resp);
		}else{
			//获取session
			HttpSession session=req.getSession();
			Object uname=session.getAttribute("uname");
			if(uname==null){
				req.setAttribute("msg", "请登陆后使用本系统");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}else{
				chain.doFilter(req, resp);
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
