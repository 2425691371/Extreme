package com.cdsxt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdsxt.dao.RenShuDao;
import com.cdsxt.po.Xm;
import com.cdsxt.util.PageUtil;
import com.cdsxt.vo.FeiYong;
import com.cdsxt.vo.RenShu;
import com.cdsxt.vo.Shu;
import com.cdsxt.vo.ZongJinE;
import com.google.gson.Gson;

/**
 * Servlet implementation class RenShuAction
 */
@WebServlet("/renShuAction")
public class RenShuAction extends HttpServlet {
	private RenShuDao renShuDao=new RenShuDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenShuAction() {
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
		response.setHeader("content-type", "text/html;charset=utf-8");
		String mark=request.getParameter("mark");
		if("queryRen".equals(mark)){
			queryRen(request,response);
		}else if("queryShu".equals(mark)){
			queryShu(request,response);
		}else if("queryZong".equals(mark)){
			queryZong(request,response);
		}else if("queryFei".equals(mark)){
			queryFei(request,response);
		}else if("danZi".equals(mark)){
			danZi(request,response);
		}
	}
	
	protected void queryRen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RenShu> renList=renShuDao.queryUser();
		response.getWriter().write(new Gson().toJson(renList));
	}
	protected void queryShu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname=request.getParameter("pname");
		int curPage=request.getParameter("curPage")==null?1:Integer.parseInt(request.getParameter("curPage"));
		int count=Integer.parseInt(request.getParameter("count"));
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<Shu> renList=renShuDao.queryShu(pname,startRow,pageRow);
		request.setAttribute("renList", renList);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/bcc/resident.jsp").forward(request, response);
	}
	protected void queryZong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ZongJinE> zongList=renShuDao.queryZongJinE();
		response.getWriter().write(new Gson().toJson(zongList));
	}
	protected void queryFei(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname=request.getParameter("pname");
		int curPage=request.getParameter("curPage")==null?1:Integer.parseInt(request.getParameter("curPage"));
		int count=renShuDao.yeCount(pname);
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<Xm> xmList=renShuDao.queryQuanXian();
		List<FeiYong> feiList=renShuDao.queryFeiYong(pname,startRow,pageRow);
		request.setAttribute("xmList", xmList);
		request.setAttribute("feiList", feiList);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/bcc/query.jsp").forward(request, response);
	}
	protected void danZi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname=request.getParameter("pname");
		int curPage=request.getParameter("curPage")==null?1:Integer.parseInt(request.getParameter("curPage"));
		int count=renShuDao.yeCount(pname);
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<Xm> xmList=renShuDao.queryQuanXian();
		List<FeiYong> feiList=renShuDao.queryFeiYong(pname,startRow,pageRow);
		request.setAttribute("xmList", xmList);
		request.setAttribute("feiList", feiList);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/bcc/query.jsp").forward(request, response);
	}

}
