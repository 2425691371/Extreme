package com.cdsxt.test;

import com.cdsxt.util.BaseDao;
import com.cdsxt.util.DBUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Test {
	public static void main(String[] args) {
		/*for(int i = 1;i<=100;i++){
			BaseDao.changePos("insert into admin(uname,password,phone,emal) values(?,?,?,?)", "zhangsan"+i,"123456","12345678978","a@qq.com");
		}*/
//		for(int i = 12;i<=100;i++){
//			test(18,"13123");
////			test((int)(Math.random()*10+1),10,1232,"员工"+i);
//		}
		for(int i=6;i<=62;i++) {			
			test1(i);
		}
	}
	public static void test(int age,String sfid){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("update xm x,xmm m set m.price=? where x.zid=m.zid");
			ps.setInt(1, age);
			ps.setString(2, sfid);
//			ps.setInt(1, zid);
//			ps.setInt(2, number);
//			ps.setFloat(3, price);
//			ps.setString(4, name);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void test1(int i) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("update xmm set idNum=(RAND()*(22-3)+3) where xmm_id="+i);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
