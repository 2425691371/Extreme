package com.cdsxt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cdsxt.po.Apply;
import com.cdsxt.util.BaseDao;
import com.cdsxt.util.DBUtil;
import com.cdsxt.vo.ApplyDel;
import com.cdsxt.vo.ApplyRes;

public class ApplyDao {
	/*public List<Apply> queryApply(int startRow,int pageRow){
		return BaseDao.queryPos("select Aid,Aname,UseTime,BuyUser from Apply limit ?,?", Apply.class, startRow,pageRow);
		
	}*/
	//获取count
	public int querycount(){
		return BaseDao.queryCount("select count(*) from Apply");
	}
	/*public List<Apply> queryAllOf(){
		return BaseDao.queryAll("select * from Apply", Apply.class);
	}*/
	//查询id
	public boolean checkId(int id){
		//return BaseDao.queryPos("select * from Apply where Aid=?", Apply.class, id).size()>0?true:false;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement("select * from Apply where Aid=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			//遍历结果集
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return false;
	}
	public Apply checkIt(int id){
		//return BaseDao.queryPos("select * from Apply where Aid=?", Apply.class, id).size()>0?true:false;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Apply result=null;
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement("select * from Apply where Aid=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			//遍历结果集
			while(rs.next()){
				int Aid=rs.getInt(1);
				String Aname=rs.getString(2);
				String maker=rs.getString(3);
				String price=rs.getString(4);
				String AppState=rs.getString(5);
				String AppType=rs.getString(6);
				String UseTime=rs.getString(7);
				String BuyUser=rs.getString(8);
				String FixUser=rs.getString(9);
				result=new Apply(Aid, Aname, maker, price, AppState, AppType, UseTime, BuyUser, FixUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return result;
	}
	//添加设备
	public void addApply(int Aid,String Aname,String maker,String price,String AppState,String AppType,String UseTime,String BuyUser,String FixUser)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("insert into Apply values(?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, Aid);
			ps.setString(2, Aname);
			ps.setString(3, maker);
			ps.setString(4, price);
			ps.setString(5, AppState);
			ps.setString(6, AppType);
			ps.setString(7, UseTime);
			ps.setString(8, BuyUser);
			ps.setString(9, FixUser);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
	}
	//查询所有
	public List<ApplyRes> queryApp(int startRow,int pageRow){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ApplyRes> result=new ArrayList<ApplyRes>();
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement("select Aid,Aname,UseTime from Apply limit ?,?");
			ps.setInt(1, startRow);
			ps.setInt(2, pageRow);
			rs=ps.executeQuery();
			//遍历结果集
			while(rs.next()){
				int aid =rs.getInt(1);
				String aname=rs.getString(2);
				String useTime=rs.getString(3);
				result.add(new ApplyRes(aid, aname, useTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return result;
	}
	//查询所有
	public List<ApplyRes> queryApp(int id,String name){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ApplyRes> result=new ArrayList<ApplyRes>();
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement("select Aid,Aname,UseTime from Apply where Aid=? and Aname=?");
			ps.setInt(1, id);
			ps.setString(2, name);
			rs=ps.executeQuery();
			//遍历结果集
			while(rs.next()){
				int aid =rs.getInt(1);
				String aname=rs.getString(2);
				String useTime=rs.getString(3);
				result.add(new ApplyRes(aid, aname, useTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return result;
	}
	public void  delApply(int Aid){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("delete from Apply where Aid=?");
			ps.setInt(1, Aid);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
	}
	}
	public void update(int Aid,String Aname,String maker,String price,String AppState,String AppType,String UseTime,String BuyUser,String FixUser)
	{
		Connection conn1=null;
		PreparedStatement ps1=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps1=conn1.prepareStatement("update Apply set Aname=?,maker=?,price=?,AppState=?,AppType=?,UseTime=?,BuyUser=?,FixUser=? where Aid=?");
			ps1.setInt(9, Aid);
			ps1.setString(1, Aname);
			ps1.setString(2, maker);
			ps1.setString(3, price);
			ps1.setString(4, AppState);
			ps1.setString(5, AppType);
			ps1.setString(6, UseTime);
			ps1.setString(7, BuyUser);
			ps1.setString(8, FixUser);
			ps1.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				ps1.close();
				conn1.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	public List<ApplyDel> queryDel(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ApplyDel> result=new ArrayList<ApplyDel>();
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement("select Aid,Aname,UseTime,BuyUser from Apply");
			rs=ps.executeQuery();
			//遍历结果集
			while(rs.next()){
				int aid =rs.getInt(1);
				String aname=rs.getString(2);
				String useTime=rs.getString(3);
				String buyUser=rs.getString(4);
				result.add(new ApplyDel(aid, aname, useTime, buyUser));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return result;
	}
}
