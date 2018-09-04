package com.cdsxt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cdsxt.po.Xm;
import com.cdsxt.util.BaseDao;
import com.cdsxt.util.DBUtil;
import com.cdsxt.vo.FeiYong;
import com.cdsxt.vo.RenShu;
import com.cdsxt.vo.Shu;
import com.cdsxt.vo.ZongJinE;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class RenShuDao {
	public static List<RenShu> queryUser(){
//		return BaseDao.queryAll("SELECT x.pname,count(*) from xmm b,xm x where b.zid=x.zid GROUP BY b.zid",RenShu.class);
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<RenShu>renList=new ArrayList<RenShu>();
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("SELECT x.pname,count(*) from xm x,resident r LEFT JOIN xmm_resident xs on xs.idNum=r.idNum LEFT JOIN xmm m on m.xmm_id=xs.xmm_id where m.zid=x.zid GROUP BY m.zid");
			rs=ps.executeQuery();
			while(rs.next()){
				String pname=rs.getString(1);
				int count=rs.getInt(2);
				renList.add(new RenShu(pname, count));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(rs,ps,conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return renList;
	}
	public static List<Shu> queryShu(String pname,int startRow,int pageRow){
//		return BaseDao.queryAll("SELECT x.pname,count(*) from xmm b,xm x where b.zid=x.zid GROUP BY b.zid",RenShu.class);
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Shu> renList=new ArrayList<Shu>();
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select r.idNum,r.`name`,r.age,r.idCard from xm x,resident r LEFT JOIN xmm_resident xs on xs.idNum=r.idNum LEFT JOIN xmm m on m.xmm_id=xs.xmm_id where m.zid=x.zid and x.pname=? limit ?,?");
			ps.setString(1, pname);
			ps.setInt(2, startRow);
			ps.setInt(3, pageRow);
			rs=ps.executeQuery();
			while(rs.next()){
				int idNum=rs.getInt("idNum");
				String name=rs.getString("name");
				int age=rs.getInt("age");
				String idCard=rs.getString("idCard");
				renList.add(new Shu(idNum, name, age, idCard));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(rs,ps,conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return renList;
	}
	public static List<ZongJinE> queryZongJinE(){
//		return BaseDao.queryAll("SELECT x.pname,count(*) from xmm b,xm x where b.zid=x.zid GROUP BY b.zid",RenShu.class);
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ZongJinE> zongList=new ArrayList<ZongJinE>();
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select x.pname,sum(x.unitp*m.number) price " + 
					"from xm x,resident r LEFT JOIN xmm_resident xs on xs.idNum=r.idNum LEFT JOIN xmm m on m.xmm_id=xs.xmm_id where m.zid=x.zid GROUP BY m.zid ");
			rs=ps.executeQuery();
			while(rs.next()){
				String pname=rs.getString(1);
				float price=rs.getFloat(2);
				zongList.add(new ZongJinE(pname, price));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(rs,ps,conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return zongList;
	}
	public static List<FeiYong> queryFeiYong(String pname,int startRow,int pageRow){
//		return BaseDao.queryAll("SELECT x.pname,count(*) from xmm b,xm x where b.zid=x.zid GROUP BY b.zid",RenShu.class);
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<FeiYong> feiList=new ArrayList<FeiYong>();
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select x.pname,x.unit,x.unitp,m.number,r.`name` " + 
					"from xm x,resident r LEFT JOIN xmm_resident xs on xs.idNum=r.idNum LEFT JOIN xmm m on m.xmm_id=xs.xmm_id where m.zid=x.zid and x.pname=? limit ?,?");
			ps.setString(1, pname);
			ps.setInt(2, startRow);
			ps.setInt(3, pageRow);
			rs=ps.executeQuery();
			while(rs.next()){
				String unit=rs.getString("unit");
				String unitp=rs.getString("unitp");
				int number=rs.getInt("number");
				String name=rs.getString("name");
				feiList.add(new FeiYong(pname, unit, unitp, number, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(rs,ps,conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return feiList;
	}
	public static List<Xm> queryQuanXian(){
//		return BaseDao.queryAll("SELECT x.pname,count(*) from xmm b,xm x where b.zid=x.zid GROUP BY b.zid",RenShu.class);
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Xm> xmList=new ArrayList<Xm>();
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("SELECT * from xm");
			rs=ps.executeQuery();
			while(rs.next()){
				int zid=rs.getInt("zid");
				String pname=rs.getString("pname");
				String unitp=rs.getString("unitp");
				String unit=rs.getString("unit");
				int ds=rs.getInt("ds");
				xmList.add(new Xm(zid, pname, unitp, unit, ds));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(rs,ps,conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return xmList;
	}
	public static int yeCount(String pname){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareCall("select count(*) " + 
					"from xm x,resident r LEFT JOIN xmm_resident xs on xs.idNum=r.idNum LEFT JOIN xmm m on m.xmm_id=xs.xmm_id where m.zid=x.zid and x.pname=?");
			ps.setString(1, pname);
			rs=ps.executeQuery();
			while(rs.next()){
				int count=rs.getInt(1);
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(rs,ps,conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}
	
}
