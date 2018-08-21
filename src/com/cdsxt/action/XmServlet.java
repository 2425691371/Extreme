package com.cdsxt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdsxt.dao.XmDao;
import com.cdsxt.po.Xm;
import com.cdsxt.util.PageUtil;
import com.cdsxt.vo.Dan;
import com.cdsxt.vo.Dong;
import com.cdsxt.vo.Fang;
import com.cdsxt.vo.YongHu;
import com.google.gson.Gson;

/**
 * Servlet implementation class XmServlet
 */
@WebServlet("/xmServlet")
public class XmServlet extends HttpServlet {
	private XmDao xmDao=new XmDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String mark=request.getParameter("mark");
		if("query".equals(mark)){
			queryXm(request,response);
		}else if("add".equals(mark)){
			add(request,response);
		}else if("update".equals(mark)){
			update(request,response);
		}else if("queryById".equals(mark)){
			queryById(request,response);
		}else if("pay".equals(mark)){
			pay(request,response);
		}else if("lou".equals(mark)){
			lou(request,response);
		}else if("dong".equals(mark)){
			dong(request,response);
		}else if("fang".equals(mark)){
			fang(request,response);
		}else if("yonghu".equals(mark)){
			yonghu(request,response);
		}
	}
	protected void queryXm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage=request.getParameter("curPage")==null?1:Integer.parseInt(request.getParameter("curPage"));
		int count=xmDao.Count();
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<Xm> xmList=xmDao.queryXm(startRow,pageRow);
		request.setAttribute("xmList", xmList);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/bcc/tab.jsp").forward(request, response);
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname=request.getParameter("pname");
		String unitp=request.getParameter("unitp");
		String unit=request.getParameter("unit");
		int ds=Integer.parseInt(request.getParameter("ds"));
		System.out.println(pname+"-"+unitp+"-"+unit+"-"+ds);
		if(pname.isEmpty()||unitp.isEmpty()||unit.isEmpty()){
			request.setAttribute("msg", "<font color='red'>请将信息填写完整后添加</font>");
			request.getRequestDispatcher("/bcc/add.jsp").forward(request, response);
		}else{
			xmDao.addXm(pname, unitp, unit, ds);
			queryXm(request,response);
		}
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int zid=Integer.parseInt(request.getParameter("id"));
		String pname=request.getParameter("pname");
		String unitp=request.getParameter("unitp");
		String unit=request.getParameter("unit");
		int ds=Integer.parseInt(request.getParameter("ds"));
		xmDao.updateXm(zid, pname, unitp, unit, ds);
		queryXm(request,response);
	}
	protected void queryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int zid=Integer.parseInt(request.getParameter("id"));
		Xm xm=XmDao.queryById(zid);
		request.setAttribute("xm", xm);
		String a=request.getParameter("a");
		if(a!=null){
			request.getRequestDispatcher("/bcc/update.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/bcc/pay.jsp").forward(request, response);
		}
	}
	protected void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String number=request.getParameter("number");
		String price=request.getParameter("price");
		int zid=Integer.parseInt(request.getParameter("id"));
		Xm xm=XmDao.queryById(zid);
		request.setAttribute("xm", xm);
		if(name.isEmpty()||number.isEmpty()||price.isEmpty()){
			request.setAttribute("msg", "<font color='red'>请将信息填写完整</font>");
			request.getRequestDispatcher("/bcc/pay.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/bcc/pays.jsp").forward(request, response);
		}
	}
	protected void lou(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Dong> faList=xmDao.queryFang();
		response.getWriter().write(new Gson().toJson(faList));
	}
	protected void dong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dan=Integer.parseInt(request.getParameter("dan"));
		List<Dan> faList=xmDao.queryDong(dan);
		response.getWriter().write(new Gson().toJson(faList));
	}
	protected void fang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fang=Integer.parseInt(request.getParameter("fang"));
		List<Fang> faList=xmDao.queryDan(fang);
		response.getWriter().write(new Gson().toJson(faList));
	}
	protected void yonghu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fid=Integer.parseInt(request.getParameter("fid"));
		YongHu yongHu=xmDao.queryYongHu(fid);
		response.getWriter().write(new Gson().toJson(yongHu));
	}

}
