package com.cdsxt.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
	//select * from employee; 
	public static <T> List<T> queryAll(String sql,Class<T> clazz){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<T> list=new ArrayList<T>();
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement(sql);
			//初始化sql
			rs=ps.executeQuery();
			//获取字段信息对象
			ResultSetMetaData rsmd=rs.getMetaData();
			//获取列数
			int count=rsmd.getColumnCount();
			while(rs.next()){
				T t=clazz.newInstance();
				for(int i=0;i<count;i++){
					//获取属性名
					String fieldName=rsmd.getColumnName(i+1);
					//获取属性
					Field field=clazz.getDeclaredField(fieldName);
					//获取设定器
					Method setter=clazz.getMethod(getSetter(fieldName), field.getType());
					//调用设定器
					setter.invoke(t, rs.getObject(i+1));
				}
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return list;
	}
	//select from employee where gender=? and age>?    
	public static <T> List<T> queryPos(String sql,Class<T> clazz,Object...params){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<T> list=new ArrayList<T>();
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement(sql);
			//填坑
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			//初始化sql
			rs=ps.executeQuery();
			//获取字段信息对象
			ResultSetMetaData rsmd=rs.getMetaData();
			//获取列数
			int count=rsmd.getColumnCount();
			while(rs.next()){
				T t=clazz.newInstance();
				for(int i=0;i<count;i++){
					//获取属性名
					String fieldName=rsmd.getColumnName(i+1);
					//获取属性
					Field field=clazz.getDeclaredField(fieldName);
					//获取设定器
					Method setter=clazz.getMethod(getSetter(fieldName), field.getType());
					//调用设定器
					setter.invoke(t, rs.getObject(i+1));
				}
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return list;
	}
	
	//增删改
	// delete from employee where id=?
	public static void changePos(String sql,Object...params){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement(sql);
			//填坑
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps,conn);
		}
	}
	
	//通过id查询单条数据  select * from xxx where id=?
	public static <T> T queryById(String sql,Class<T> clazz,int id){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement(sql);
			//填坑
			ps.setInt(1, id);
			//初始化sql
			rs=ps.executeQuery();
			//获取字段信息对象
			ResultSetMetaData rsmd=rs.getMetaData();
			//获取列数
			int count=rsmd.getColumnCount();
			while(rs.next()){
				T t=clazz.newInstance();
				for(int i=0;i<count;i++){
					//获取属性名
					String fieldName=rsmd.getColumnName(i+1);
					//获取属性
					Field field=clazz.getDeclaredField(fieldName);
					//获取设定器
					Method setter=clazz.getMethod(getSetter(fieldName), field.getType());
					//调用设定器
					setter.invoke(t, rs.getObject(i+1));
				}
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return null;
	}
	//查询个数   select count(*) from xxx where ....
	public static int queryCount(String sql,Object...params){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConn();
			ps=conn.prepareStatement(sql);
			//填坑
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			//初始化sql
			rs=ps.executeQuery();
			while(rs.next()){
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return 0;
	}
	
	//通过属性名获取方法
	public static String getSetter(String fieldName){
		return "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
	}
}
