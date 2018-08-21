package com.cdsxt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cdsxt.po.Xm;
import com.cdsxt.util.DBUtil;
import com.cdsxt.vo.Dan;
import com.cdsxt.vo.Dong;
import com.cdsxt.vo.Fang;
import com.cdsxt.vo.YongHu;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class XmDao {
	public static List<Xm> queryXm(int startRow,int pageRow){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Xm> xmList=new ArrayList<Xm>();
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select * from xm limit ?,?");
			ps.setInt(1, startRow);
			ps.setInt(2, pageRow);
			rs=ps.executeQuery();
			while(rs.next()){
				int zid=rs.getInt(1);
				String pname=rs.getString(2);
				String unitp=rs.getString(3);
				String unit=rs.getString(4);
				int ds=rs.getInt(5);
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
	public static int Count(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select count(*) from xm");
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
	public static void addXm(String pname,String unitp,String unit,int ds){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("insert into xm(pname,unitp,unit,ds) values(?,?,?,?)");
			ps.setString(1, pname);
			ps.setString(2, unitp);
			ps.setString(3, unit);
			ps.setInt(4, ds);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(ps,conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static Xm queryById(int zid){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Xm xm=null;
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select * from xm where zid=?");
			ps.setInt(1, zid);
			rs=ps.executeQuery();
			while(rs.next()){
				String pname=rs.getString("pname");
				String unitp=rs.getString("unitp");
				String unit=rs.getString("unit");
				int ds=rs.getInt("ds");
				xm=new Xm(zid, pname, unitp, unit, ds); 
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
		return xm;
	}
	public static void updateXm(int zid,String pname,String unitp,String unit,int ds){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("update xm set pname=?,unitp=?,unit=?,ds=? where zid=?");
			ps.setString(1, pname);
			ps.setString(2, unitp);
			ps.setString(3, unit);
			ps.setInt(4, ds);
			ps.setInt(5, zid);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(ps,conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static List<Dong> queryFang(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Dong> faList=new ArrayList<Dong>();
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select * from lou");
			rs=ps.executeQuery();
			while(rs.next()){
				int lid=rs.getInt(1);
				String lou=rs.getString(2);
				faList.add(new Dong(lid, lou));
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
		return faList;
	}
	public static List<Dan> queryDong(int lid){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Dan> faList=new ArrayList<Dan>();
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select d.did,d.dan from lou l,dan d where l.lid=d.lid and l.lid=?");
			ps.setInt(1, lid);
			rs=ps.executeQuery();
			while(rs.next()){
				int did=rs.getInt(1);
				String dan=rs.getString(2);
				faList.add(new Dan(did, dan));
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
		return faList;
	}
	public static List<Fang> queryDan(int did){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Fang> faList=new ArrayList<Fang>();
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select f.fid,f.fang from dan d,fang f where d.did=f.did and d.did=?");
			ps.setInt(1, did);
			rs=ps.executeQuery();
			while(rs.next()){
				int fid=rs.getInt(1);
				String fang=rs.getString(2);
				faList.add(new Fang(fid, fang));
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
		return faList;
	}
	public static YongHu queryYongHu(int fid){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		YongHu yongHu=null;
		try {
			conn=(Connection) DBUtil.getConn();
			ps=(PreparedStatement) conn.prepareStatement("select f.fang,f.yonghu from fang f where f.fid=?");
			ps.setInt(1, fid);
			rs=ps.executeQuery();
			while(rs.next()){
				String fang=rs.getString(1);
				String yonghu=rs.getString(2);
				yongHu=new YongHu(fang, yonghu);
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
		return yongHu;
	}
	
}
