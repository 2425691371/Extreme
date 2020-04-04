package com.cdsxt.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.CORBA.portable.OutputStream;

import com.cdsxt.po.Manager;
import com.cdsxt.util.BaseDao;
import com.cdsxt.util.DbBackUpMethod;
import com.cdsxt.util.Delete;
import com.cdsxt.util.PageUtil;
import com.cdsxt.util.ZipUtil;

public class AdminServlet extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mark=request.getParameter("mark");
		if("login".equals(mark)){
			tologin(request,response);
		}else if("addadmin".equals(mark)){
			addadmin(request,response);
		}else if("success".equals(mark)){
			success(request,response);
		}else if("verify".equals(mark)){
			verify(request,response);
		}else if("goquerry".equals(mark)){
			goquerry(request,response);
		}else if("exit".equals(mark)){
			exit(request,response);
		}else if("del".equals(mark)){
			del(request,response);
		}else if("jump".equals(mark)){
			jump(request,response);
		}else if("jumpupdata".equals(mark)){
			jumpupdata(request,response);
		}else if("updata".equals(mark)){
			updata(request,response);
		}else if("jumpuppwd".equals(mark)){
			jumpuppwd(request,response);
		}else if("uppwd".equals(mark)){
			uppwd(request,response);
		}else if("upthispwd".equals(mark)){
			upthispwd(request,response);
		}else if("beifeng".equals(mark)){
			beifeng(request,response);
		}else if("bfdata".equals(mark)){
			bfdata(request,response);
		}else if("querrydata".equals(mark)){
			querrydata(request,response);
		}else if("download".equals(mark)){
			download(request,response);
		}else if("deletedata".equals(mark)){
			deletedata(request,response);
		}else if("delcheck".equals(mark)){
			delcheck(request,response);
		}else if("recover".equals(mark)){
			recover(request,response);
		}else if("jumphuanyuan".equals(mark)){
			jumphuanyuan(request,response);
		}else if("uploaddata".equals(mark)){
			uploaddata(request,response);
		}
	}
	
	public void uploaddata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("/db.properties");
		  Properties pros = new Properties();
		  try {
			pros.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    String sqlFilePath = pros.getProperty("sqlFilePath");
		    sqlFilePath=sqlFilePath.replaceAll("//", "/");
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
            // 解析请求
            items=upload.parseRequest(request);
            for(FileItem item:items){
                //判断该元素是否是普通表单域元素    true是普通表单域元素  false 是文件元素
                boolean result=item.isFormField();
                if(result){
                    //普通表单域元素
                    //获取表单域的name值
                    String name=item.getFieldName();
                    //获取普通表单域的值   getString(获取值)
                    String val=item.getString("utf-8");
                }else{
                    //文件域元素
                    //获取文件名
                    String name=item.getName();
                    //获取文件的输入流
                    InputStream in=item.getInputStream();
                    FileOutputStream out=new FileOutputStream(new File(sqlFilePath+name));
                    byte[] b=new byte[8192];
                    int temp=0;
                    while((temp=in.read(b))!=-1){
                        out.write(b,0,temp);
                    }
                    out.flush();
                    out.close();
                    in.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	public void jumphuanyuan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("/db.properties");
		  Properties pros = new Properties();
		  try {
			pros.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    String sqlFilePath = pros.getProperty("sqlFilePath");
		    sqlFilePath=sqlFilePath.replaceAll("//", "/");
		    String filename=(String) request.getParameter("dataname");
		    ZipUtil.unZip(sqlFilePath+filename);
		    try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    File file = new File(sqlFilePath+filename.split("\\.")[0]);
		    String[] sqlfile = file.list();
		    request.setAttribute("sqlfile", sqlfile);
		    request.setAttribute("dataname", filename);
		    Delete.deleteDirectory(sqlFilePath+filename.split("\\.")[0]);
		request.getRequestDispatcher("/admin/huanyuan.jsp").forward(request,response);
	}
	public void recover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String[] strs=request.getParameterValues("rightTabCb");
			String dataname = (String) request.getParameter("dataname");
			String datafold = dataname.split("\\.")[0];
		try {
			InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("/db.properties");
			Properties pros = new Properties();
			  try {
				pros.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    String sqlFilePath = pros.getProperty("sqlFilePath");
			    sqlFilePath=sqlFilePath.replaceAll("//", "/");
			    try {
					ZipUtil.unZip(sqlFilePath+dataname);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    Thread.sleep(400);
				for (String string : strs) {
					DbBackUpMethod.load(sqlFilePath+datafold+"/"+string);
				}
				Delete.deleteDirectory(sqlFilePath+datafold);
				request.getSession().invalidate();
				request.getRequestDispatcher("/admin/loadfinish.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}
	public void delcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dataname = (String) request.getParameter("dataname");
		String[] datanames = dataname.split(",");
		
	try {
		
		InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("/db.properties");
		  Properties pros = new Properties();
		  try {
			pros.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    String sqlFilePath = pros.getProperty("sqlFilePath");
		    sqlFilePath=sqlFilePath.replaceAll("//", "/");
		    for (String string : datanames) {
		    	File filepath= new File(sqlFilePath+string);
		    	 Delete.deleteFile(filepath.toString());
			}
		    PrintWriter pw = response.getWriter();
		    pw.write("删除成功");
		    pw.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
	public void deletedata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dataname = (String) request.getParameter("dataname");
	try {
		
		InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("/db.properties");
		  Properties pros = new Properties();
		  try {
			pros.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    String sqlFilePath = pros.getProperty("sqlFilePath");
		    sqlFilePath=sqlFilePath.replaceAll("//", "/");
		    File filepath= new File(sqlFilePath+dataname);
		    Delete.deleteFile(filepath.toString());
		    PrintWriter pw = response.getWriter();
		    pw.write("删除成功");
		    pw.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
	public void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String dataname = (String) request.getParameter("dataname");
		try {
			
			InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("/db.properties");
			  Properties pros = new Properties();
			  try {
				pros.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    String sqlFilePath = pros.getProperty("sqlFilePath");
			    sqlFilePath=sqlFilePath.replaceAll("//", "/");
			    File filepath= new File(sqlFilePath+dataname);
			    InputStream is = new FileInputStream(filepath);
			    ServletOutputStream os = response.getOutputStream();
			    response.setHeader("content-disposition", "attachment;fileName="+dataname);
			    byte[] b = new byte[8192];
			    int len = 0;
			    while((len=is.read(b))!=-1){
			    	os.write(b, 0, len);
			    }
			    os.close();
			    is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}
	public void querrydata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("/db.properties");
			  Properties pros = new Properties();
			  try {
				pros.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    String sqlFilePath = pros.getProperty("sqlFilePath");
			    sqlFilePath=sqlFilePath.replaceAll("//", "/");
			    File filepath= new File(sqlFilePath);
			File[] filearr = filepath.listFiles();
			String[] filenamearr = filepath.list();
			List<String> list  = new ArrayList<String>();
			for (String string : filenamearr) {
				if(new File(sqlFilePath+string).isFile()){
					list.add(string);
				}
			}
			int count=filenamearr.length;
			int curPage=request.getParameter("curPage")==null?1:Integer.parseInt(request.getParameter("curPage"));
			if(curPage>count/10+1){
				curPage=count/10+1;
			}else if(curPage<1){
				curPage=1;
			}
			PageUtil page=new PageUtil(count, curPage);
			request.setAttribute("filenames",list);
			request.setAttribute("filearr", filearr);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/admin/querrybeifeng.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}
	public void bfdata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] strs=request.getParameterValues("rightTabCb");
			String shuoming =request.getParameter("backupsname")==null?"":request.getParameter("backupsname");
			Calendar  cl=Calendar.getInstance();
			Date date = cl.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String filename=shuoming+"_"+sdf.format(date)+"//";
			String filename1=shuoming+"_"+sdf.format(date);
			for (String string : strs) {
				DbBackUpMethod.getDbBackUpMethod().backup(string,filename);
			}
			Thread.sleep(1000);
			InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("/db.properties");
			  Properties pros = new Properties();
			  try {
				pros.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    String sqlFilePath = pros.getProperty("sqlFilePath");
			    sqlFilePath=sqlFilePath.replaceAll("//", "/");
			try {
				ZipUtil.zip(sqlFilePath+filename1,filename1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Delete.deleteDirectory(sqlFilePath+filename1);
			request.setAttribute("mmm",filename);
			request.getRequestDispatcher("/admin/bfsuccess.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}
	public void beifeng(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","root");
			Set<String> tablename = getTableNameByCon(conn);
			request.setAttribute("tablename", tablename);
			request.getRequestDispatcher("/admin/beifeng.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}
	public void jumpuppwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		Manager admin = BaseDao.queryById("select * from manager where id=?",Manager.class, Integer.parseInt(uid));
		request.setAttribute("admin", admin);
		String page = request.getParameter("page");
		request.setAttribute("xxxpage", page);
		request.getRequestDispatcher("/admin/uppwd.jsp").forward(request, response);
}
	public void upthispwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname =request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		BaseDao.changePos("update manager set pwd=? where uname=?",pwd,uname );	
		request.getRequestDispatcher("/main.jsp").forward(request, response);
}
	public void uppwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		String pwd = request.getParameter("pwd");
		String courpage = request.getParameter("courpage");
		System.out.println(courpage);
		BaseDao.changePos("update manager set pwd=? where id=?",pwd,uid );	
		request.getRequestDispatcher("adminServlet?mark=goquerry&curPage="+courpage).forward(request, response);
}
	
	public void jumpupdata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		Manager admin = BaseDao.queryById("select * from manager where id=?",Manager.class, Integer.parseInt(uid));
		request.setAttribute("admin", admin);
		String page = request.getParameter("page");
		request.setAttribute("xxxpage", page);
		request.getRequestDispatcher("/admin/updata.jsp").forward(request, response);
}
	public void updata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		String uname = request.getParameter("uname");
		String phone = request.getParameter("phone");
		String emal = request.getParameter("emal");
		String courpage = request.getParameter("courpage");
		System.out.println(courpage);
		BaseDao.changePos("update manager set uname=?,phone=?,email=? where id=?",uname,phone,emal,uid );	
		request.getRequestDispatcher("adminServlet?mark=goquerry&curPage="+courpage).forward(request, response);
}
	public void jump(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = (String)request.getSession().getAttribute("uname");
		List<Manager> admin = BaseDao.queryPos("select * from manager where uname=?", Manager.class, uname);
		 request.setAttribute("admin",admin.get(0));
         request.getRequestDispatcher("/admin/upthispwd.jsp").forward(request, response);
}
	public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取总条数
		PrintWriter pw = response.getWriter();
		String str = request.getParameter("data");
		String[] strarr=str.split(",");
		int[] arr = new int[strarr.length];
		for (int i = 0; i < strarr.length; i++) {
			BaseDao.changePos("delete from manager where id=?",Integer.parseInt(strarr[i]));
		}
	    pw.write("删除成功");
		pw.close();
}
	public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		request.getSession().invalidate();
		request.setAttribute("msg", "已成功退出");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	public void goquerry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//获取总条数
			int count=BaseDao.queryCount("select count(*) from manager ",null);
			//获取当前页
			int curPage=request.getParameter("curPage")==null?1:Integer.parseInt(request.getParameter("curPage"));
			if(curPage>count/10+1){
				curPage=count/10+1;
			}else if(curPage<1){
				curPage=1;
			}
			PageUtil page=new PageUtil(count, curPage);
			int startRow=page.getStartRow();
			int pageRow=page.getPageRow();
			//查询数据库中的员工信息}
			List<Manager> empList=BaseDao.queryPos("select * from manager where uname!=? limit ?,?", Manager.class,(String)request.getSession().getAttribute("uname"), startRow, pageRow);
			request.setAttribute("empList", empList);
			request.setAttribute("page", page);
			//请求转发给成功页面 
			request.getRequestDispatcher("/admin/querryAdmin.jsp").forward(request, response);
	}
	public void addadmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String emal = request.getParameter("emal");
		PrintWriter pw = response.getWriter();
		BaseDao.changePos("insert into manager(uname,pwd,phone,email) values(?,?,?,?)", uname,pwd,phone,emal);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
		pw.close();
		
		
		
	}
	public void verify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Manager> list = new ArrayList<Manager>();
		String uname = request.getParameter("uname");
		PrintWriter pw = response.getWriter();
		list=BaseDao.queryPos("select * from manager where uname=?", Manager.class,uname);
		if(list.size()<1){
			pw.write("nosuccess");
		}else{
			pw.write("success");
		}
		pw.close();
		
	}
	public void tologin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Manager> list = new ArrayList<Manager>();
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		PrintWriter pw = response.getWriter();
		list=BaseDao.queryPos("select * from manager where uname=? and pwd=?", Manager.class,uname,pwd );
		if(list.size()<1){
			pw.write("unsuccess");
		}else{
			pw.write("success");
		}
		pw.close();
		
	}
	public void success(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		ServletContext sc = request.getServletContext();
		List<String> userlist = (List<String>) sc.getAttribute("userList");
		if(userlist.contains(uname)){
			if(uname.equals((String)request.getSession().getAttribute("uname"))){
				//request.getRequestDispatcher("/main.jsp").forward(request, response);
				response.sendRedirect("main.jsp");
			}
			request.setAttribute("msg", "此用户已登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			request.getSession().setAttribute("uname", uname);
			//request.getRequestDispatcher("/main.jsp").forward(request, response);
			response.sendRedirect("main.jsp");
		}

	}
	//获取连接的数据库的表名
	public Set<String> getTableNameByCon(Connection con) {
        Set<String> set = new HashSet<String>();
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getTables(null, null, null,
                    new String[] { "TABLE" });
            while (rs.next()) {
                set.add(rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            try {
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return set;
    }
	//备份整个库
	 public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException {  
		       File saveFile = new File(savePath);  
		        if (!saveFile.exists()) {// 如果目录不存在  
		           saveFile.mkdirs();// 创建文件夹  
		        }  
		      if(!savePath.endsWith(File.separator)){  
		         savePath = savePath + File.separator;  
		       }  
		          
		      PrintWriter printWriter = null;  
		       BufferedReader bufferedReader = null;  
		        try {  
		             printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));  
		             Process process = Runtime.getRuntime().exec(" mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);  
		             InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");  
		             bufferedReader = new BufferedReader(inputStreamReader);  
		            String line;  
		             while((line = bufferedReader.readLine())!= null){  
		                 printWriter.println(line);  
		             }  
		             printWriter.flush();  
		             if(process.waitFor() == 0){//0 表示线程正常终止。  
		                 return true;  
		             }  
		         }catch (IOException e) {  
		             e.printStackTrace();  
		         } finally {  
		             try {  
		                 if (bufferedReader != null) {  
		                     bufferedReader.close();  
		                 }  
		                 if (printWriter != null) {  
		                     printWriter.close();  
		                 }  
		             } catch (IOException e) {  
		                 e.printStackTrace();  
		             }  
		        }  
		         return false;  
		    }  

	public void beifeng1(String tableName,String filename){
		 InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("/db.properties");
		  Properties pros = new Properties();
		  try {
			pros.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  	String username = pros.getProperty("username");
		    String password = pros.getProperty("password");
		    String mysqlpaths = pros.getProperty("mysqlpath");
		    String address = pros.getProperty("dbAddress");
		    String databaseName = pros.getProperty("databaseName");
		    File backupath = new File(filename);
		    if (!backupath.exists()) {
		     backupath.mkdir();
		    }
		StringBuffer sb = new StringBuffer();
	    sb.append(mysqlpaths);
	    sb.append("mysqldump ");
	    sb.append("--opt ");
	    sb.append("-h ");
	    sb.append(address);
	    sb.append(" ");
	    sb.append("--user=");
	    sb.append(username);
	    sb.append(" ");
	    sb.append("--password=");
	    sb.append(password);
	    sb.append(" ");
	    sb.append("--lock-all-tables=true ");
	    sb.append("--result-file=");
	    sb.append(filename);
	    sb.append(tableName+".sql");
	    sb.append(" ");
	    sb.append("--default-character-set=utf8 ");
	    sb.append(databaseName);
	    sb.append(" ");
	    sb.append(tableName);
	    System.out.println(sb);
	    Runtime cmd = Runtime.getRuntime();
	    try {
			Process p = cmd.exec(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
