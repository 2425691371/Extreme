package com.cdsxt.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 用于数据库备份操作
 */
public class DbBackUpMethod {
 private static Log logger = LogFactory.getLog(DbBackUpMethod.class);
 private static Properties pros = getPprVue("db.properties");
 public static Map<String, String> backUpTableList 
     = new ConcurrentHashMap<String, String>();
 private static DbBackUpMethod backObj = new DbBackUpMethod();
 public static DbBackUpMethod getDbBackUpMethod(){
  return backObj;
 }
 public void backup(String tableName,String fileName) {
  if(null != backUpTableList.get(tableName)) return ;
  backUpTableList.put(tableName, tableName); // 标记已经用于备份
  new Thread(new DbBackUpThread(tableName,fileName)).start();
 }
 /**
  * 用于执行某表的备份
  */
 class DbBackUpThread implements Runnable {
  String tableName = null;
  String fileName = null;
  public DbBackUpThread(String tableName,String fileName){
   this.tableName = tableName;
   this.fileName=fileName;
  }
  @Override
  public void run() {
   try {
    String username = pros.getProperty("username");
    String password = pros.getProperty("password");
    String mysqlpaths = pros.getProperty("mysqlpath");
    String address = pros.getProperty("dbAddress");
    String databaseName = pros.getProperty("databaseName");
    String sqlpath = pros.getProperty("sqlFilePath");
    File backupath = new File(sqlpath+fileName);
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
    sb.append(sqlpath+fileName);
    sb.append(tableName+".sql");
    sb.append(" ");
    sb.append("--default-character-set=utf8 ");
    sb.append(databaseName);
    sb.append(" ");
    sb.append(tableName);
    Runtime cmd = Runtime.getRuntime();
    Process p = cmd.exec(sb.toString());
    p.waitFor(); // 该语句用于标记，如果备份没有完成，则该线程持续等待
   } catch (Exception e) {
    logger.error("备份操作出现问题", e);
   }finally{
    backUpTableList.remove(tableName); // 最终都将解除
   }
  }
 }
 public static Properties getPprVue(String properName) {
  InputStream inputStream 
      = DbBackUpMethod.class.getClassLoader().getResourceAsStream(properName);
  Properties p = new Properties();
  try {
   p.load(inputStream);
   inputStream.close();
  } catch (IOException e) {
   logger.error("无法读取用于备份数据的配置文件", e);
  }
  return p;
 }
 /** 
  * 还原数据库 
  * @param sql 要还的SQL文件 
  * @throws Exception 
  */  
// public static void restore (String sql) throws Exception {
//	 InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("db.properties");
// Properties p = new Properties();
// try {
//  p.load(inputStream);
//  inputStream.close();
// } catch (IOException e) {
// 
// }
//	 String username = pros.getProperty("username");
//	    String password = pros.getProperty("password");
//	    String mysqlpaths = pros.getProperty("mysqlpath");
//	    String address = pros.getProperty("dbAddress");
//	    String databaseName = pros.getProperty("databaseName");
//	    String sqlpath = pros.getProperty("sqlFilePath");
//     String targetFile =   sql;  // SQL文件路径  
//     String[] execCMD = new String[]{"mysql", "test", " -u" + username, " -p" + password, " -e source ", targetFile};  
//     Process process = Runtime.getRuntime().exec(execCMD);  
//   
//     int processComplete = process.waitFor();  
//     if (processComplete == 0) {  
//         System.out.println("还原成功.");  
//     } else {  
//         throw new RuntimeException("还原数据库失败.");  
//     }  
// }  

 public static boolean load(String tablename) {//还原    
	 InputStream inputStream = DbBackUpMethod.class.getClassLoader().getResourceAsStream("db.properties");
	 Properties p = new Properties();
	 try {
	  p.load(inputStream);
	  inputStream.close();
	 } catch (IOException e) {
	 
	 }
		 String username = pros.getProperty("username");
		    String password = pros.getProperty("password");
		    String mysqlpaths = pros.getProperty("mysqlpath");
		    String address = pros.getProperty("dbAddress");
		    String databaseName = pros.getProperty("databaseName");
		    String sqlpath = pros.getProperty("sqlFilePath");
     try {  
         String fPath = tablename;    
         System.out.println("正在恢复:"+fPath);
         Runtime rt = Runtime.getRuntime();    
         // 调用 mysql 的 cmd:    
         Process child = rt.exec("mysql -u"+username+" -p"+password+" --default-character-set=utf8 test ");    
         OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流    
         String inStr;    
         StringBuffer sb = new StringBuffer("");    
         String outStr;    
         BufferedReader br = new BufferedReader(new InputStreamReader(    
                 new FileInputStream(fPath), "utf-8"));    
         while ((inStr = br.readLine()) != null) {    
             sb.append(inStr + "\r\n");    
         }    
         outStr = sb.toString();    
     
         OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");    
         writer.write(outStr);    
         // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免    
         writer.flush();    
         // 别忘记关闭输入输出流    
         out.close();    
         br.close();    
         writer.close();    
             
         System.out.println("/* Load OK! */");    
     } catch (Exception e) {    
         e.printStackTrace();    
     }    
     return true;    
 }     
}  