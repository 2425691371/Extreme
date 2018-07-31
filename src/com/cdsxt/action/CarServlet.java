package com.cdsxt.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdsxt.dao.CarDao;
import com.cdsxt.po.CarInfo;
import com.google.gson.Gson;

/**
 * Servlet implementation class CarServlet
 */
@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
	CarDao carDao=new CarDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String mark=request.getParameter("mark");
		if("carUpdate".equals(mark)) {
			carUpdate(request,response);
		}else if("carBrandQuery".equals(mark)){
				brandQuery(request,response);
			}else if("carIdQuery".equals(mark)) {  //通过车牌查询车辆是否存在
			carIdQuery(request,response);
		}else if("carSpace".equals(mark)){  //查询空余车位
			carSpasceQuery(request,response);
		}else if("carAdd".equals(mark)) {  //添加车辆信息
			carAdd(request,response);
		}else if("carQuery".equals(mark)) {  //查询车主是否是住户
			carQuery(request,response);
		}else if("carBrand".equals(mark)){  //获取汽车品牌
			carBrandQuery(request,response);
		}else if("carQueryAll".equals(mark)) {  //查询所有车辆信息
			carQueryAll(request,response);
		}else if("delete".equals(mark)) {   	//删除车辆信息
			carDelete(request,response);
		}else if("queryById".equals(mark)) {
				try {
					queryById(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	//修改车辆信息  carUpdate
	private void carUpdate(HttpServletRequest request,HttpServletResponse response) {
		String oldCarId=request.getParameter("oldCarId");
		String newCarId=request.getParameter("newCarId");
		String brand=request.getParameter("brand");

		carDao.carUpdate(oldCarId, newCarId, brand);
		
	}
	//查询车牌号是否存在
	private void carIdQuery(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String carId=request.getParameter("carId");
		boolean boo= carDao.carIdQuery(carId);
		PrintWriter pw=response.getWriter();
		if(boo) {
			pw.write("车牌号已存在");
			pw.flush();
			pw.close();
		}else {
			pw.write("");
			pw.flush();
			pw.close();
		}
	}
	//查询汽车品牌是否存在
	private void brandQuery(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String brand=request.getParameter("brand");

		boolean boo= carDao.brandQuery(brand);
		PrintWriter pw=response.getWriter();
		if(boo) {
			pw.write("");
			pw.flush();
			pw.close();
		}else {
			pw.write("类型不存在");
			pw.flush();
			pw.close();
		}
	}
	
	//查询未停车的车位编号
	public void carSpasceQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List list=carDao.carSpaceQuery();
		Gson gson=new Gson();
		String jsonStr=gson.toJson(list);
		PrintWriter pw=response.getWriter();
		pw.write(jsonStr);
		pw.flush();
		pw.close();
	}
	//查询汽车品牌
	public void carBrandQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List list=carDao.carBrandQuery();
		Gson gson=new Gson();
		String jsonStr=gson.toJson(list);
		PrintWriter pw=response.getWriter();
		pw.write(jsonStr);
		pw.flush();
		pw.close();
	}
	
	
	//查询身份证和姓名是否正确
	public void carQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String IdCard=request.getParameter("IdCard");
		String uname=request.getParameter("uname");
		boolean boo= carDao.carUserQuery(uname,IdCard);
		PrintWriter pw=response.getWriter();
		if(boo) {
			pw.write(""); 
			pw.flush();
			pw.close();
		}else {
			pw.write("身份证号码或姓名错误");
			pw.flush();
			pw.close();
		}
	}
	
	//添加车辆信息
	public void carAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String carId=request.getParameter("carId");
		String address=request.getParameter("carSpace");
		String userId=request.getParameter("idCard");
		String uname=request.getParameter("uname");
		String brand=request.getParameter("se1");
		CarInfo carUser=new CarInfo(carId, brand, address, userId);
		carDao.carAdd(carUser) ;
		request.getRequestDispatcher("/car/carQuery.jsp").forward(request, response);
	}
	
	//查询所有车辆信息 carQueryAll
	public void carQueryAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
	//	select car.*,res.name from carinfo car,resident res where car.userId = res.idCard
		List list=carDao.carQueryAll();
		Gson gson=new Gson();
		String jsonStr=gson.toJson(list);
		PrintWriter pw=response.getWriter();
		pw.write(jsonStr);
		pw.flush();
		pw.close();
	}
	// 删除车辆信息  carDelete
	public void carDelete(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
			String carId=request.getParameter("carId");

			String[] temp=carId.split(",");
			for(String str:temp) {
				carDao.carDelete(str);
			}
		}
	//通过Id查询车辆信息
	public void queryById(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		String carId1=new String( request.getParameter("carId").getBytes("ISO8859_1"),"utf8");
		String carId1=request.getParameter("carId");
		request.setCharacterEncoding("utf-8");

		String[] result=carDao.carIdQuery1(carId1);
		String name=result[0];
		String carId=result[1];
		String userId=result[2];
		String brand=result[3];
		request.setAttribute("name", name);
		request.setAttribute("carId", carId);
		request.setAttribute("userId", userId);
		request.setAttribute("brand", brand);
		request.getRequestDispatcher("/car/carUpdate.jsp").forward(request, response);
		return;
	}
}
