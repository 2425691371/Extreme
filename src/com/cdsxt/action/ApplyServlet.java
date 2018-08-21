package com.cdsxt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cdsxt.dao.ApplyDao;
import com.cdsxt.po.Apply;
import com.cdsxt.util.PageUtil;
import com.cdsxt.vo.ApplyDel;
import com.cdsxt.vo.ApplyRes;
import com.google.gson.Gson;

/**
 * Servlet implementation class ApplyServlet
 */
public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplyDao applyDao=new ApplyDao();
    public ApplyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		String mark=request.getParameter("mark");
		if("add".equals(mark)){
			addApply(request,response);
		}else if("query".equals(mark)){
			query(request,response);
		}else if("delApp".equals(mark)){
			delApp(request,response);
		}else if("querySome".equals(mark)){
			querySome(request,response);
		}else if("updateIt".equals(mark)){
			updateIt(request,response);
		}else if("updateApp".equals(mark)){
			updateApp(request,response);
		}
	}
	
	public void queryDel(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<ApplyDel> appDel=applyDao.queryDel();
		PrintWriter pw=response.getWriter();
		pw.write(new Gson().toJson(appDel));
		pw.close();
		
	}
	public void updateIt(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int Aid=Integer.parseInt(request.getParameter("Aid"));
		Apply appresult=applyDao.checkIt(Aid);
		request.setAttribute("app", appresult);
		request.getRequestDispatcher("ApplyJsp/update.jsp").forward(request, response);
	}
	public void updateApp(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int Aid=Integer.parseInt(request.getParameter("Aid"));
		String Aname=request.getParameter("Aname");
		String maker=request.getParameter("maker");
		String price=request.getParameter("price");
		String AppState=request.getParameter("AppState");
		String AppType=request.getParameter("AppType");
		String UseTime=request.getParameter("UseTime");
		String BuyUser=request.getParameter("BuyUser");
		String FixUser=request.getParameter("FixUser");
		applyDao.update(Aid,Aname,maker,price,AppState,AppType,UseTime,BuyUser,FixUser);
		query(request,response);
	}
	public void addApply (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int Aid=Integer.parseInt(request.getParameter("Aid"));
		String Aname=request.getParameter("Aname");
		String maker=request.getParameter("maker");
		String price=request.getParameter("price");
		String AppState=request.getParameter("AppState");
		String AppType=request.getParameter("AppType");
		String UseTime=request.getParameter("UseTime");
		String BuyUser=request.getParameter("BuyUser");
		String FixUser=request.getParameter("FixUser");
		//去数据库查询id
		boolean result=applyDao.checkId(Aid);
		if(result){
			request.setAttribute("msg", "<font color='red'>编号已存在</font>");
			request.getRequestDispatcher("ApplyJsp/addApply.jsp").forward(request, response);
		}else{
			//去数据库添加设备
			applyDao.addApply(Aid,Aname,maker,price,AppState,AppType,UseTime,BuyUser,FixUser);
			query(request,response);
		}
	}
	public void query(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		int count=applyDao.querycount();
		//List<Apply> apply1=applyDao.queryAllOf();
		int curPage=request.getParameter("curPage")==null?1:Integer.parseInt(request.getParameter("curPage"));
		PageUtil  page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		//去数据库查询
		List<ApplyRes> appResult=applyDao.queryApp(startRow,pageRow);
		//List<Apply> applyRe=applyDao.queryApply(startRow,pageRow);
		//总数
		//当前页
		session.setAttribute("page", page);
		session.setAttribute("appResult", appResult);
		//session.setAttribute("apply", apply1);
		//session.setAttribute("applyRe", applyRe);
		request.getRequestDispatcher("ApplyJsp/checkApply.jsp").forward(request, response);
		return;
		/*PrintWriter pw=response.getWriter();
		pw.write(new Gson().toJson(appResult));
		pw.close();*/
	}
	public void delApp(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int Aid=Integer.parseInt(request.getParameter("Val"));
		applyDao.delApply(Aid);
		query(request,response);
	}
	public void querySome(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name=request.getParameter("AName");
		int id=Integer.parseInt(request.getParameter("AID"));
		System.out.println(name+"........."+id);
		List<ApplyRes> appResult=applyDao.queryApp(id,name);
		System.out.println(appResult);
		PrintWriter pw=response.getWriter();
		pw.write(new Gson().toJson(appResult));
		pw.close();
	}
}
