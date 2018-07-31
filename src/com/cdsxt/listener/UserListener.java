package com.cdsxt.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserListener implements HttpSessionAttributeListener,ServletContextListener,HttpSessionListener{
	private ServletContext context=null;
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		String name=se.getName();
		//确定一定存的是登陆的用户名
		if("uname".equals(name)){
			//获取用户名
			String uname=(String) se.getValue();
			//获取存放的管理员的list
			List<String> userList=(List<String>) context.getAttribute("userList");
			//判断list中是否有该用户信息
			if(!userList.contains(uname)){
				//将用户信息存入list中
				userList.add(uname);
			}
		}
	}

	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session=se.getSession();
		String uname=(String) session.getAttribute("uname");
		if(uname!=null){
			List<String> userList=(List<String>) context.getAttribute("userList");
			userList.remove(uname);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		context=sce.getServletContext();
		context.setAttribute("userList", new ArrayList<String>());
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	
}
