package com.cdsxt.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cdsxt.dao.ResidentDao;
import com.cdsxt.po.Resident;
import com.cdsxt.util.PageUtil;
import com.cdsxt.vo.ResiAge;
import com.cdsxt.vo.ResiElement;
import com.cdsxt.vo.ResiRoom;
import com.cdsxt.vo.ResiTower;
import com.cdsxt.vo.ResiquAge;
import com.google.gson.Gson;

public class ResidentTest extends HttpServlet{
	ResidentDao resi=new ResidentDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
			String mark=request.getParameter("mark");
			if("add".equals(mark)) {
				addTest(request,response);
			}else if("studys".equals(mark)) {
				studyTest(request,response);
			}else if("tower".equals(mark)){
				towerTest(request,response);
			}else if("unit".equals(mark)) {
				unitTest(request,response);
			}else if("room".equals(mark)) {
				roomTest(request,response);
			}else if("query".equals(mark)) {
				queryTest(request,response);
			}else if("alter".equals(mark)) {
				alterTest(request,response);
			}else if("alteId".equals(mark)) {
				alteIdTest(request,response);
			}else if("deltId".equals(mark)) {
				deltIdTest(request,response);
			}else if("pgshow".equals(mark)) {
				pgshowTest(request,response);
			}else if("ageShow".equals(mark)) {
				ageShow(request,response);
			}else if("queryByAge".equals(mark)) {
				ageTest(request,response);
			}else if("downEmp".equals(mark)) {
				downEmp(request,response);
			}
	}
	
	//添加
	public void addTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name="";
		int gender=0;
		int age=0;
		String idCard="";
		String study="";
		String email="";
		String sect1="";
		String sect2="";
		String sect3="";
		String room="";
		String phone="";
		String startdate="";
		String enddate="";
		String fileName="";
		String photo="";
		
		//创建一个工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		//给工厂设置临时文件的地址
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		//通过工厂创建解析request的对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置文件的中文编码方式
		upload.setHeaderEncoding("utf-8");
		List<FileItem> items = null;
		try {
			items=upload.parseRequest(request);
			for(FileItem item:items){
				boolean result=item.isFormField();
				if(result){
					String name1=item.getFieldName();
					 if("name".equals(name1)){
						  name=item.getString("utf-8");
					 }else if("gender".equals(name1)){
						 gender=Integer.parseInt(item.getString("utf-8"));
					 }else if("age".equals(name1)){
						 age=Integer.parseInt(item.getString("utf-8"));
					 }else if("idCard".equals(name1)) {
						 idCard=item.getString("utf-8");
					 }else if("study".equals(name1)) {
						 study=item.getString("utf-8");
					 }else if("email".equals(name1)) {
						 email=item.getString("utf-8");
					 }else if("sect1".equals(name1)) {
						 sect1=item.getString("utf-8");
						 System.out.println(sect1);
					 }else if("sect2".equals(name1)) {
						 sect2=item.getString("utf-8");
						 System.out.println(sect2);
					 }else if("sect3".equals(name1)) {
						 sect3=item.getString("utf-8");
						 System.out.println(sect3);
					 }else if("phone".equals(name1)) {
						 phone=item.getString("utf-8");
					 }else if("startdate".equals(name1)) {
						 startdate=item.getString("utf-8");
					 }else if("enddate".equals(name1)) {
						 enddate=item.getString("utf-8");
					 }
					 //字符串拼接
				}else{
					 fileName=item.getName();
					InputStream in=item.getInputStream();
					String path=servletContext.getRealPath("/house/img");
//					System.out.println("path="+path);
					photo = "house/img/"+fileName;
//					System.out.println(photo);
					OutputStream out=new FileOutputStream(new File(path+"\\"+fileName));			
					byte[] b=new byte[8192];
					int len=0;
					while((len=in.read(b))!=-1) {
						out.write(b, 0, len);
					}
					out.flush();
					out.close();
					in.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//D:\Eclipse\Extreme\WebContent\house\img
		//D:\Eclipse\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Extreme\house\img\2.jpg
		
		room=sect1+"-"+sect2+"-"+sect3;
		resi.addDao(name,gender,age,idCard,study,email, room,phone,startdate, enddate, photo);
		queryTest(request,response);
		
	}
	
	//专业返回ajax
	public void studyTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setHeader("content-type", "text/html;charset=utf-8");
			List list=resi.queryStdudy();
			PrintWriter pw=response.getWriter();
			Gson gson=new Gson();
			String result=gson.toJson(list);
			pw.write(result);
			pw.flush();
			pw.close();
	}
	
	//楼房
	public void towerTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		List<ResiTower> list=resi.towerDao();
		PrintWriter pw=response.getWriter();
		Gson gson=new Gson();
		String result=gson.toJson(list);
		pw.write(result);
		pw.flush();
		pw.close();
		
	}
	
	//单元
	public void unitTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		List<ResiElement> list=resi.unitDao();
		PrintWriter pw=response.getWriter();
		Gson gson=new Gson();
		String result=gson.toJson(list);
		pw.write(result);
		pw.flush();
		pw.close();
		
		
	}
	
	//房间号
	
	public void roomTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		List<ResiRoom> list=resi.roomDao();
		PrintWriter pw=response.getWriter();
		Gson gson=new Gson();
		String result=gson.toJson(list);
		pw.write(result);
		pw.flush();
		pw.close();
	}
	
	//分页
	public void queryTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//在数据库里查询用户信息总条数
			int count=resi.counDao();
			//当前页如果为null，就显示第一页，如果不为null就跳转到是几页就是几页
			int curpage=request.getParameter("curPage")==null?1:Integer.parseInt(request.getParameter("curPage"));
			//New一个page获取里面的信息
			PageUtil page=new PageUtil(count,curpage);
			//获取起始行
			int startRow=page.getStartRow();
			//获取每页显示条数
			int pagRow=page.getPageRow();
			
			//在数据库规定页面显示的条数
			List<Resident> reside=resi.showPage(startRow,pagRow);
			request.setAttribute("page", page);
			request.setAttribute("reside", reside);
			request.getRequestDispatcher("house/showHouse.jsp").forward(request, response);
	}
	
	//修改数据
	public void alterTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idNum=0;
		String name="";
		int gender=0;
		int age=0;
		String idCard="";
		String study="";
		String email="";
		String sect1="";
		String sect2="";
		String sect3="";
		String room="";
		String phone="";
		String startdate="";
		String enddate="";
		String fileName="";
		String photo="";
		
		//创建一个工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		//给工厂设置临时文件的地址
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		//通过工厂创建解析request的对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置文件的中文编码方式
		upload.setHeaderEncoding("utf-8");
		List<FileItem> items = null;
		try {
			items=upload.parseRequest(request);
			for(FileItem item:items){
				boolean result=item.isFormField();
				if(result){
					String name1=item.getFieldName();
					 if("idNum".equals(name1)) {
						 idNum=Integer.parseInt(item.getString("utf-8"));
					 }else if("name".equals(name1)){
						  name=item.getString("utf-8");
					 }
					 else if("gender".equals(name1)){
						 gender=Integer.parseInt(item.getString("utf-8"));
					 }else if("age".equals(name1)){
						 age=Integer.parseInt(item.getString("utf-8"));
					 }else if("idCard".equals(name1)) {
						 idCard=item.getString("utf-8");
					 }else if("study".equals(name1)) {
						 study=item.getString("utf-8");
					 }else if("email".equals(name1)) {
						 email=item.getString("utf-8");
					 }else if("sect1".equals(name1)) {
						 sect1=item.getString("utf-8");
					 }else if("sect2".equals(name1)) {
						 sect2=item.getString("utf-8");
					 }else if("sect3".equals(name1)) {
						 sect3=item.getString("utf-8");
					 }else if("phone".equals(name1)) {
						 phone=item.getString("utf-8");
					 }else if("startdate".equals(name1)) {
						 startdate=item.getString("utf-8");
					 }else if("enddate".equals(name1)) {
						 enddate=item.getString("utf-8");
					 }
					 //字符串拼接
				}else{
					 fileName=item.getName();
					InputStream in=item.getInputStream();
					photo=servletContext.getRealPath("/house/img/"+fileName);
					OutputStream out=new FileOutputStream(new File(photo));
					byte[] b=new byte[8192];
					int len=0;
					while((len=in.read(b))!=-1) {
						out.write(b, 0, len);
					}
					out.flush();
					out.close();
					in.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		resi.alterDao( idNum, name,  gender, age,  idCard,  study,  email,  room, phone,
				startdate,  enddate, photo);
		queryTest(request, response);
	}
	
	//showHouse页面通过ID来修改
	public void alteIdTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idNum=Integer.parseInt(request.getParameter("idNum"));
		Resident rest=resi.idAlert(idNum);
		request.setAttribute("rest", rest);
		request.getRequestDispatcher("house/alterHouse.jsp").forward(request, response);
	}
	
	
	//通过id来删除  表头删除
	public void deltIdTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		int idNum=Integer.parseInt(request.getParameter("idNum"));
		String[] boxs =request.getParameterValues("box");
//		if(boxs==null || boxs.length<0) {
//			return;
//		}
//		System.out.println("//////////////"+Arrays.toString(boxs));
		for (String box : boxs) {
			if(box!=null && box!="") {
				resi.delDao(Integer.parseInt(box));
			}
		}
//		resi.delDao(idNum);
		queryTest(request, response);
	}
	
	//上传图片
	
	public void pgshowTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idNum=Integer.parseInt(request.getParameter("idNum"));
		Resident reside=resi.idAlert(idNum);
		request.setAttribute("reside", reside);
		request.getRequestDispatcher("house/pigeHouse.jsp").forward(request, response);
		
	}
	
	//年龄分布图
	
	public void ageShow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ResiAge> listArr=resi.ageShow();
		PrintWriter pw=response.getWriter();
		Gson gson=new Gson();
		String result=gson.toJson(listArr);
		pw.write(result);
		pw.flush();
		pw.close();
		
	}
	
	//根据传入的年龄段查询对应的员工
	public void ageTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String ageArea=request.getParameter("ageArea");
			ageArea=new String(ageArea.getBytes("ISO-8859-1"),"UTF-8");
			if(!ageArea.contains("-")) {
				ageArea=ageArea.substring(0,2);
			}else {
				ageArea=ageArea.substring(0,ageArea.length()-1);
			}
//			ageArea=ageArea.substring(0,ageArea.length()-1);
			int curPage=request.getParameter("curPage")==null?1:Integer.parseInt(request.getParameter("curPage"));
			int count=Integer.parseInt(request.getParameter("count"));
			PageUtil page=new PageUtil(count, curPage);
			int startRow=page.getStartRow();
			int pageRow=page.getPageRow();
//			System.out.println("ageArea--------"+ageArea);
			String[] ages=ageArea.split("-");
			List<ResiquAge> empList=null;
			if(ages.length==2) {
				int minAge=Integer.parseInt(ages[0]);
				int maxAge=Integer.parseInt(ages[1]);
				 empList=resi.agequery(minAge,maxAge,startRow,pageRow);
//				System.out.println(empList.size());
			}else if(ages.length==1) {
				int minAge=Integer.parseInt(ageArea.replaceAll("以上", ""));
				empList=resi.agequery(minAge,startRow,pageRow);
			}
			
			request.setAttribute("page", page);
			request.setAttribute("empList", empList);
			request.getRequestDispatcher("house/agshowHouese.jsp").forward(request, response);
	}
	
	//excel表导出
	public void downEmp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Resident> empList=resi.showDao();
		Workbook wb = new HSSFWorkbook();
		Sheet sheet=wb.createSheet();
		Row headRow=sheet.createRow(0);
		headRow.createCell(0).setCellValue("编号");
		headRow.createCell(1).setCellValue("姓名");
		headRow.createCell(2).setCellValue("性别");
		headRow.createCell(3).setCellValue("年龄");
		headRow.createCell(4).setCellValue("身份证号");
		headRow.createCell(5).setCellValue("专业");
		headRow.createCell(6).setCellValue("邮编");
		headRow.createCell(7).setCellValue("几栋几单元");
		headRow.createCell(8).setCellValue("手机号");
		headRow.createCell(9).setCellValue("起始日期");
		headRow.createCell(10).setCellValue("到期时间");
		headRow.createCell(11).setCellValue("照片路径");
		for(int i=0;i<empList.size();i++){
			Row dataRow=sheet.createRow(i+1);
			dataRow.createCell(0).setCellValue(empList.get(i).getIdNum());
			dataRow.createCell(1).setCellValue(empList.get(i).getName());
			dataRow.createCell(2).setCellValue(empList.get(i).getGender()==1?"男":"女");
			dataRow.createCell(3).setCellValue(empList.get(i).getAge());
			dataRow.createCell(4).setCellValue(empList.get(i).getIdCard());
			dataRow.createCell(5).setCellValue(empList.get(i).getStudy());
			dataRow.createCell(6).setCellValue(empList.get(i).getEmail());
			dataRow.createCell(7).setCellValue(empList.get(i).getRoom());
			dataRow.createCell(8).setCellValue(empList.get(i).getPhone());
			dataRow.createCell(9).setCellValue(empList.get(i).getStartdate());
			dataRow.createCell(10).setCellValue(empList.get(i).getEnddate());
			dataRow.createCell(11).setCellValue(empList.get(i).getPhoto());
		}
		response.setHeader("content-disposition", "attachment;fileName=resident.xls");
		//以流的方式输出
		OutputStream out=response.getOutputStream();
		wb.write(out);
//		System.out.println(empList.size());
	}
}