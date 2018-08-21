package com.cdsxt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdsxt.dao.FangDao;
import com.cdsxt.po.House;
import com.google.gson.Gson;

@WebServlet("/fangAction")
public class FangAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FangDao fangDao=new FangDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mark=request.getParameter("mark");
		if("query".equals(mark)){
			queryFang(request,response);
		}else if("update".equals(mark)){
			updateFang(request,response);
		}else if("queryById".equals(mark)){
			queryByIdFang(request,response);
		}else if("show".equals(mark)){
			showFang(request,response);
		}else if("queryId".equals(mark)){
			queryIdFang(request,response);
		}else if("change".equals(mark)){
			changeFang(request,response);
		}else if("showImg".equals(mark)){
			showImgFang(request,response);
		}else if("queryIds".equals(mark)){
			queryIdsFang(request,response);
		}
	}
	public void queryFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<House> houseList=fangDao.queryFang();
		request.setAttribute("houseList", houseList);
		PrintWriter pw=response.getWriter();
		pw.write(new Gson().toJson(houseList));
		pw.flush();
		pw.close();
	}
	public void showFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid=Integer.parseInt(request.getParameter("hid"));
		List<House> house=fangDao.queryById(hid);
		request.setAttribute("house", house);
		request.getRequestDispatcher("/fang/show.jsp").forward(request, response);
	
	}
	public void updateFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid=Integer.parseInt(request.getParameter("hid"));
		int buildingId=Integer.parseInt(request.getParameter("buildingId"));
		 int unitId=Integer.parseInt(request.getParameter("unitId"));
		 String fangId=request.getParameter("fangId");
		 int floorPlanId=Integer.parseInt(request.getParameter("floorPlanId"));
		 int roomCount=Integer.parseInt(request.getParameter("roomCount"));
		 int livingRoomCount=Integer.parseInt(request.getParameter("livingRoomCount"));
		 int kitchenCount=Integer.parseInt(request.getParameter("kitchenCount"));
		 int  toiletCount=Integer.parseInt(request.getParameter("toiletCount"));
		 int  changeId=Integer.parseInt(request.getParameter("changeId"));
		 String time=request.getParameter("time");
		 String name=request.getParameter("name");
		fangDao.updateEmp(hid, buildingId, unitId, fangId, floorPlanId, roomCount, livingRoomCount, kitchenCount, toiletCount, changeId, time, name);
		showFang(request,response);
	}
	public void queryByIdFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ids = Integer.parseInt(request.getParameter("ids"));
		String num=request.getParameter("num");
		if( ids==1  &&  Pattern.matches("[0-9]+", num)){
			int hid=Integer.parseInt(num);
			List<House> houseList=fangDao.queryById(hid);
			PrintWriter pw=response.getWriter();
			pw.write(new Gson().toJson(houseList));
			pw.flush();
			pw.close();
		}else if(ids==2){
			int count=0;
			Matcher ma = Pattern.compile("-").matcher(num);
			while(ma.find()){
				count++;
			}if(count==3){
				String[] split = num.split("-");
				for (int i=0;i<split.length;i++) {
					if(Pattern.matches("[0-9]+", split[i])){
						int roomCount=Integer.parseInt(split[0]);
						int livingRoomCount=Integer.parseInt(split[1]);
						int kitchenCount=Integer.parseInt(split[2]);
						int toiletCount=Integer.parseInt(split[3]);
						List<House> houseList =fangDao.showFangs(roomCount,livingRoomCount,kitchenCount,toiletCount);	
						PrintWriter pw=response.getWriter();
						pw.write(new Gson().toJson(houseList));
						pw.flush();
						pw.close();
					}
				}
			}
		}
	}
	public void queryIdFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid=Integer.parseInt(request.getParameter("hid"));
		House h=fangDao.queryId(hid);
		request.setAttribute("h", h);
		request.getRequestDispatcher("/fang/updateFang.jsp").forward(request, response);
		return;
	}
	public void changeFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid=Integer.parseInt(request.getParameter("hid"));
		int buildingId=Integer.parseInt(request.getParameter("buildingId"));
		 int unitId=Integer.parseInt(request.getParameter("unitId"));
		 String fangId=request.getParameter("fangId");
		 int floorPlanId=Integer.parseInt(request.getParameter("floorPlanId"));
		 int roomCount=Integer.parseInt(request.getParameter("roomCount"));
		 int livingRoomCount=Integer.parseInt(request.getParameter("livingRoomCount"));
		 int kitchenCount=Integer.parseInt(request.getParameter("kitchenCount"));
		 int  toiletCount=Integer.parseInt(request.getParameter("toiletCount"));
		 int  changeId=Integer.parseInt(request.getParameter("changeId"));
		 String time=request.getParameter("time");
		 String name=request.getParameter("name");
		fangDao.updateEmp(hid, buildingId, unitId, fangId, floorPlanId, roomCount, livingRoomCount, kitchenCount, toiletCount, changeId, time, name);
		showImgFang(request,response);
	}
	public void showImgFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid=Integer.parseInt(request.getParameter("hid"));
		List<House> house=fangDao.queryById(hid);
		request.setAttribute("house", house);
		request.getRequestDispatcher("/fang/views.jsp").forward(request, response);
	}
	
	public void queryIdsFang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid=Integer.parseInt(request.getParameter("hid"));
		House h=fangDao.queryId(hid);
		request.setAttribute("h", h);
		request.getRequestDispatcher("/fang/changeFang.jsp").forward(request, response);
		return;
	}

}
