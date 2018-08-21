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

import com.cdsxt.dao.EmpDao;
import com.cdsxt.po.Dep;
import com.cdsxt.po.Employee;
import com.cdsxt.po.Salary;
import com.cdsxt.po.WorkTime;
import com.cdsxt.util.PageUtil;
import com.cdsxt.vo.DepName;
import com.cdsxt.vo.SalaryArea;
import com.google.gson.Gson;

public class EmployeeServelet extends HttpServlet{
	EmpDao empDao=new EmpDao();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("content-type", "text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String mark=req.getParameter("mark");
		if("query".equals(mark)){
			queryEmp(req,resp);
		}else if("delEmp".equals(mark)){
			delEmp(req,resp);
		}else if("add".equals(mark)){
			addEmp(req,resp);
		}else if("showEmp".equals(mark)){
			showEmp(req,resp);
		}else if("updateEmp".equals(mark)){
			updateEmp(req,resp);
		}else if("queryName".equals(mark)){
			queryName(req,resp);
		}else if("queryDepName".equals(mark)){
			querDepName(req,resp);
		}else if("downExcel".equals(mark)){
			downExcel(req,resp);
		}else if("salaryArea".equals(mark)){
			querySalaryArea(req,resp);
		}else if("queryBySalary".equals(mark)){
			queryBySalary(req,resp);
		}else if("queryDep".equals(mark)){
			queryDep(req,resp);
		}else if("queryByDepName".equals(mark)){
			queryByDepName(req,resp);
		}else if("showTime".equals(mark)){
			showTime(req,resp);
		}else if("queryTime".equals(mark)){
			queryTime(req,resp);
		}else if("updateTime".equals(mark)){
			updateTime(req,resp);
		}
	}
	protected void queryEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取总条数
		int count=empDao.queryCount();
		//获取当前页
		int curPage=req.getParameter("curPage")==null?1:Integer.parseInt(req.getParameter("curPage"));
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<Employee> empList=empDao.queryAll(startRow,pageRow);
		req.setAttribute("empList", empList);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/emp/empShow.jsp").forward(req, resp);
	}
	protected void queryTime(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取总条数
		int count=empDao.queryTimeCount();
		//获取当前页
		int curPage=req.getParameter("curPage")==null?1:Integer.parseInt(req.getParameter("curPage"));
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<WorkTime> workTimeList=empDao.queryTime(startRow,pageRow);
		req.setAttribute("workTimeList", workTimeList);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/emp/showWorkTime.jsp").forward(req, resp);
	}
	protected void delEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seNumbers =req.getParameter("seNumbers");
		String[] temp=seNumbers.split(",");
		for(int i=0;i<temp.length;i++){
			empDao.delEmp(Integer.parseInt(temp[i]));
		}
		queryEmp(req,resp);
	}
	protected void addEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//初始化表单参数用户名 密码  重复密码
		String ename="";
		int empNum=0;
		int gender=0;
		int age=0;
		String perId="";
		int education=0;
		String mail="";
		String hireDate="";
		int salary=0;
		String phone="";
		String depName="";
		String photoName="";
		//初始化输入流
		InputStream in=null;
		//创建解析请求的对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置文件的中文编码方式
		upload.setHeaderEncoding("utf-8");

		List<FileItem> items = null;
		try {
			items=upload.parseRequest(req);
			for(FileItem item:items){
				boolean result=item.isFormField();
				if(result){
					String name=item.getFieldName();
					if("ename".equals(name)){
						ename=item.getString("utf-8");
					}else if("empNum".equals(name)){
						empNum=Integer.parseInt(item.getString("utf-8"));
					}else if("phone".equals(name)){
						phone=item.getString("utf-8");
					}else if("age".equals(name)){
						age=Integer.parseInt(item.getString("utf-8"));
					}else if("gender".equals(name)){
						gender=Integer.parseInt(item.getString("utf-8"));
					}else if("education".equals(name)){
						education=Integer.parseInt(item.getString("utf-8"));
					}else if("perId".equals(name)){
						perId=item.getString("utf-8");
					}else if("hireDate".equals(name)){
						hireDate=item.getString("utf-8");
					}else if("salary".equals(name)){
						salary=Integer.parseInt(item.getString("utf-8"));
					}else if("depName".equals(name)){
						depName=item.getString("utf-8");
					}else if("mail".equals(name)){
						mail=item.getString("utf-8");
					}
				}else{
					in=item.getInputStream();
					photoName=item.getName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//判断是否有未填写表单域元素
		if(empNum==0||perId.isEmpty()){
			req.setAttribute("msg", "信息不完整,请重新填写");
			req.getRequestDispatcher("/emp/addEmp.jsp").forward(req, resp);
			return;
		}else{
			//result  true  存在  false  不存在
			boolean result=empDao.checkEmpNum(empNum);
			if(result){
				req.setAttribute("msg", "员工号已存在");
				req.getRequestDispatcher("/emp/addEmp.jsp").forward(req, resp);
				return;
			}else{
				if(photoName.isEmpty()){
					String savePath="/Extreme/photo/baoma.jpg";
					empDao.add(ename,empNum,phone,age,gender,education,perId,hireDate,salary,depName,mail,savePath);
					queryEmp(req, resp);
				}else{
					//获取存放头像的目录的运行绝对路径
					String imgPath=empNum+photoName.substring(photoName.lastIndexOf("."));
					String str=new String(imgPath.getBytes("utf-8"),"ISO-8859-1");
					String photoPath=servletContext.getRealPath("/photo/"+str);
					System.out.println(photoPath);
					String savePath="/Extreme/photo/"+str;
					OutputStream out=new FileOutputStream(new File(photoPath));
					byte[] b=new byte[8192];
					int temp=0;
					while((temp=in.read(b))!=-1){
						out.write(b,0,temp);
					}
					out.flush();
					out.close();
					in.close();
					empDao.add(ename,empNum,phone,age,gender,education,perId,hireDate,salary,depName,mail,savePath);
					queryEmp(req, resp);
				}
			}
		}
	}
	protected void showEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int seNumber=Integer.valueOf(req.getParameter("seNumber")).intValue();
		Employee emp=empDao.showEmp(seNumber);
		req.setAttribute("emp", emp);
		req.getRequestDispatcher("/emp/updateEmp.jsp").forward(req, resp);
	}
	protected void updateEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//初始化表单参数用户名 密码  重复密码
		int seNumber=0;
		String ename="";
		int empNum=0;
		int gender=0;
		int age=0;
		String perId="";
		int education=0;
		String mail="";
		String hireDate="";
		int salary=0;
		String phone="";
		String depName="";
		String photoName="";
		//初始化输入流
		InputStream in=null;
		//创建解析请求的对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置文件的中文编码方式
		upload.setHeaderEncoding("utf-8");

		List<FileItem> items = null;
		try {
			items=upload.parseRequest(req);
			for(FileItem item:items){
				boolean result=item.isFormField();
				if(result){
					String name=item.getFieldName();
					if("seNumber".equals(name)){
						seNumber=Integer.parseInt(item.getString("utf-8"));
					}else if("ename".equals(name)){
						ename=item.getString("utf-8");
					}else if("empNum".equals(name)){
						empNum=Integer.parseInt(item.getString("utf-8"));
					}else if("phone".equals(name)){
						phone=item.getString("utf-8");
					}else if("age".equals(name)){
						age=Integer.parseInt(item.getString("utf-8"));
					}else if("gender".equals(name)){
						gender=Integer.parseInt(item.getString("utf-8"));
					}else if("education".equals(name)){
						education=Integer.parseInt(item.getString("utf-8"));
					}else if("perId".equals(name)){
						perId=item.getString("utf-8");
					}else if("hireDate".equals(name)){
						hireDate=item.getString("utf-8");
					}else if("salary".equals(name)){
						salary=Integer.parseInt(item.getString("utf-8"));
					}else if("depName".equals(name)){
						depName=item.getString("utf-8");
					}else if("mail".equals(name)){
						mail=item.getString("utf-8");
					}
				}else{
					in=item.getInputStream();
					photoName=item.getName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//判断是否有未填写表单域元素
		if(empNum==0||perId.isEmpty()){
			req.setAttribute("msg", "信息不完整,请重新填写");
			req.getRequestDispatcher("/emp/updateEmp.jsp").forward(req, resp);
			return;
		}else{
			if(photoName.isEmpty()){
				empDao.updateEmp(ename, empNum, phone, age, gender, education, perId, hireDate, salary, depName, mail,seNumber);
				queryEmp(req, resp);
			}else{
				//获取存放头像的目录的运行绝对路径
				String imgPath=empNum+photoName.substring(photoName.lastIndexOf("."));
				String str=new String(imgPath.getBytes("utf-8"),"ISO-8859-1");
				String photoPath=servletContext.getRealPath("/photo/"+str);
				System.out.println(photoPath);
				String savePath="/Extreme/photo/"+str;
				OutputStream out=new FileOutputStream(new File(photoPath));
				byte[] b=new byte[8192];
				int temp=0;
				while((temp=in.read(b))!=-1){
					out.write(b,0,temp);
				}
				out.flush();
				out.close();
				in.close();
				empDao.updateEmp(ename, empNum, phone, age, gender, education, perId, hireDate, salary, depName, mail,savePath,seNumber);
				queryEmp(req, resp);
			}
		}
	}
	protected void queryName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ename =req.getParameter("ename");
		//获取总条数
		int count=empDao.queryName(ename);
		//获取当前页
		int curPage=req.getParameter("curPage")==null?1:Integer.parseInt(req.getParameter("curPage"));
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<Employee> enameList=empDao.queryName(ename,startRow,pageRow);
		req.setAttribute("enameList", enameList);
		req.setAttribute("ename", ename);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/emp/queryName.jsp").forward(req, resp);
	}
	protected void querDepName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String depName =req.getParameter("depName");
		//获取总条数
		int count=empDao.queryDepName(depName);
		//获取当前页
		int curPage=req.getParameter("curPage")==null?1:Integer.parseInt(req.getParameter("curPage"));
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<Employee> depNameList=empDao.queryDepName(depName,startRow,pageRow);
		req.setAttribute("depNameList", depNameList);
		req.setAttribute("depName", depName);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/emp/queryDepName.jsp").forward(req, resp);
	}
	protected void downExcel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("用户信息");
		Row firstRow = sheet.createRow(0);
		firstRow.createCell(1).setCellValue("序号");
		firstRow.createCell(2).setCellValue("姓名");
		firstRow.createCell(3).setCellValue("员工号");
		firstRow.createCell(4).setCellValue("电话");
		firstRow.createCell(5).setCellValue("年龄");
		firstRow.createCell(6).setCellValue("性别");
		firstRow.createCell(7).setCellValue("学历");
		firstRow.createCell(8).setCellValue("身份证");
		firstRow.createCell(9).setCellValue("入职时间");
		firstRow.createCell(10).setCellValue("薪水");
		firstRow.createCell(11).setCellValue("所在部门");
		String down=req.getParameter("down");
		String ename=req.getParameter("ename");
		if(down==null){
			List<Employee> empList=empDao.queryExcel();
			for(int i=0;i<empList.size();i++){
				Row dataRow=sheet.createRow(i+1);
				dataRow.createCell(1).setCellValue(empList.get(i).getSeNumber());
				dataRow.createCell(2).setCellValue(empList.get(i).getEname());
				dataRow.createCell(3).setCellValue(empList.get(i).getEmpNum());
				dataRow.createCell(4).setCellValue(empList.get(i).getPhone());
				dataRow.createCell(5).setCellValue(empList.get(i).getAge());
				dataRow.createCell(6).setCellValue(empList.get(i).getGender()==1?"男":"女");
				dataRow.createCell(7).setCellValue(empList.get(i).getEducation());
				dataRow.createCell(8).setCellValue(empList.get(i).getPerId());
				dataRow.createCell(9).setCellValue(empList.get(i).getHireDate());
				dataRow.createCell(10).setCellValue(empList.get(i).getSalary());
				dataRow.createCell(11).setCellValue(empList.get(i).getDepName());
			}
			resp.setHeader("content-disposition", "attachment;fileName=worker.xls");
			OutputStream out=resp.getOutputStream();
			wb.write(out);
			wb.close();
		}else{ 
			String depName=req.getParameter("depName");
			if("depName".equals(down)){
				List<Employee> empList=empDao.queryExcel(depName,down);
				for(int i=0;i<empList.size();i++){
					Row dataRow=sheet.createRow(i+1);
					dataRow.createCell(1).setCellValue(empList.get(i).getSeNumber());
					dataRow.createCell(2).setCellValue(empList.get(i).getEname());
					dataRow.createCell(3).setCellValue(empList.get(i).getEmpNum());
					dataRow.createCell(4).setCellValue(empList.get(i).getPhone());
					dataRow.createCell(5).setCellValue(empList.get(i).getAge());
					dataRow.createCell(6).setCellValue(empList.get(i).getGender()==1?"男":"女");
					dataRow.createCell(7).setCellValue(empList.get(i).getEducation());
					dataRow.createCell(8).setCellValue(empList.get(i).getPerId());
					dataRow.createCell(9).setCellValue(empList.get(i).getHireDate());
					dataRow.createCell(10).setCellValue(empList.get(i).getSalary());
					dataRow.createCell(11).setCellValue(empList.get(i).getDepName());
				}
				resp.setHeader("content-disposition", "attachment;fileName=worker.xls");
				OutputStream out=resp.getOutputStream();
				wb.write(out);
				wb.close();
			}else if("ename".equals(down)){
				List<Employee> empList=empDao.queryExcel(ename);
				for(int i=0;i<empList.size();i++){
					Row dataRow=sheet.createRow(i+1);
					dataRow.createCell(1).setCellValue(empList.get(i).getSeNumber());
					dataRow.createCell(2).setCellValue(empList.get(i).getEname());
					dataRow.createCell(3).setCellValue(empList.get(i).getEmpNum());
					dataRow.createCell(4).setCellValue(empList.get(i).getPhone());
					dataRow.createCell(5).setCellValue(empList.get(i).getAge());
					dataRow.createCell(6).setCellValue(empList.get(i).getGender()==1?"男":"女");
					dataRow.createCell(7).setCellValue(empList.get(i).getEducation());
					dataRow.createCell(8).setCellValue(empList.get(i).getPerId());
					dataRow.createCell(9).setCellValue(empList.get(i).getHireDate());
					dataRow.createCell(10).setCellValue(empList.get(i).getSalary());
					dataRow.createCell(11).setCellValue(empList.get(i).getDepName());
				}
				resp.setHeader("content-disposition", "attachment;fileName=worker.xls");
				OutputStream out=resp.getOutputStream();
				wb.write(out);
				wb.close();
			}
		}
	}
	protected void querySalaryArea(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<SalaryArea> salaryAreaList=empDao.querySalaryArea();
		PrintWriter pw=resp.getWriter();
		pw.write(new Gson().toJson(salaryAreaList));
		pw.flush();
		pw.close();
	}
	protected void queryBySalary(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String salary=req.getParameter("salary");
		String[] salarys=salary.split("-");
		int curPage=req.getParameter("curPage")==null?1:Integer.parseInt(req.getParameter("curPage"));
		int count=Integer.parseInt(req.getParameter("count"));
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<Salary> salaryList=null;
		if(salarys.length==2){
			int minSalary=Integer.parseInt(salarys[0]);
			int maxSalary=Integer.parseInt(salarys[1]);
			salaryList=empDao.queryBySalary(minSalary,maxSalary,startRow,pageRow);
		}else if(salarys.length==1){
			int minAge=Integer.parseInt(salary.replaceAll("以上", ""));
			salaryList=empDao.queryBySalary(minAge,startRow,pageRow);
		}
		req.setAttribute("page",page);
		req.setAttribute("salaryList",salaryList);
		req.getRequestDispatcher("/emp/salaryEmps.jsp").forward(req, resp);
	}
	protected void queryDep(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Dep> depList=empDao.queryDep();
		PrintWriter pw=resp.getWriter();
		pw.write(new Gson().toJson(depList));
		pw.flush();
		pw.close();
	}
	protected void queryByDepName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String depName=req.getParameter("depName");
		int curPage=req.getParameter("curPage")==null?1:Integer.parseInt(req.getParameter("curPage"));
		int count=Integer.parseInt(req.getParameter("count"));
		PageUtil page=new PageUtil(count, curPage);
		int startRow=page.getStartRow();
		int pageRow=page.getPageRow();
		List<DepName> depNameList=null;
		depNameList=empDao.queryByDepName(depName,startRow,pageRow);
		req.setAttribute("page",page);
		req.setAttribute("depNameList",depNameList);
		req.getRequestDispatcher("/emp/depInfo.jsp").forward(req, resp);
	}
	protected void showTime(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int seNumber=Integer.valueOf(req.getParameter("seNumber")).intValue();
		WorkTime work=empDao.showTime(seNumber);
		req.setAttribute("work",work);
		req.getRequestDispatcher("/emp/updateTime.jsp").forward(req, resp);
	}
	protected void updateTime(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int seNumber=Integer.valueOf(req.getParameter("seNumber")).intValue();
		String ename=req.getParameter("ename");
		int mouth=Integer.parseInt(req.getParameter("mouth"));
		int workTime=Integer.parseInt(req.getParameter("workTime"));
		int rworkTime=Integer.parseInt(req.getParameter("rworkTime"));
		int overTime=Integer.parseInt(req.getParameter("overTime"));
		int late=Integer.parseInt(req.getParameter("late"));
		int qingjia=Integer.parseInt(req.getParameter("qingjia"));
		empDao.updateTime(ename,mouth,workTime,rworkTime,overTime,late,qingjia,seNumber);
		queryTime(req,resp);
	}
}
