package com.cdsxt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cdsxt.po.House;
import com.cdsxt.util.BaseDao;


public class FangDao {
	public void updateEmp(int hid,int buildingId,int unitId,String fangId,int floorPlanId,int roomCount,int livingRoomCount,int kitchenCount,int  toiletCount, int  changeId, String time, String name){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("update houses set buildingId=?,unitId=?,fangId=?,floorPlanId=?, roomCount=?,livingRoomCount=?,kitchenCount=?,toiletCount=?,changeId=?,time=?,name=? where hid=?");
			ps.setInt(1, buildingId);
			ps.setInt(2, unitId);
			ps.setString(3, fangId);
			ps.setInt(4, floorPlanId);
			ps.setInt(5, roomCount);
			ps.setInt(6, livingRoomCount);
			ps.setInt(7, kitchenCount);
			ps.setInt(8, toiletCount);
			ps.setInt(9, changeId);
			ps.setString(10, time);
			ps.setString(11, name);
			ps.setInt(12, hid);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public List<House> queryFang() {
		Connection conn=null;
		PreparedStatement ps=null;
		List<House> list=new ArrayList<>();
		ResultSet  rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select h.hid,h.buildingId,h.unitId,h.fangId,h.floorPlanId,h.roomCount,h.livingRoomCount,h.kitchenCount,h.toiletCount,h.changeId,h.time,h.name from houses h ");
			rs=ps.executeQuery();
			while(rs.next()){
				int hid=rs.getInt("hid");
				int buildingId=rs.getInt("buildingId");
				 int unitId=rs.getInt("unitId");
				 String fangId=rs.getString("fangId");
				 int floorPlanId=rs.getInt("floorPlanId");
				 int roomCount=rs.getInt("roomCount");
				 int livingRoomCount=rs.getInt("livingRoomCount");
				 int kitchenCount=rs.getInt("kitchenCount");
				 int  toiletCount=rs.getInt("toiletCount");
				 int  changeId=rs.getInt("changeId");
				 String time=rs.getString("time");
				 String name=rs.getString("name");
				 list.add(new House(hid, buildingId, unitId, fangId, floorPlanId, roomCount, livingRoomCount, kitchenCount, toiletCount, changeId, time, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	public List<House> queryById(int hid) {
		return BaseDao.queryPos("select *from houses where hid=?", House.class, hid);
	}
	public List<House> showFangs(int roomCount, int livingRoomCount, int kitchenCount, int toiletCount) {
		return BaseDao.queryPos("select *from houses where roomCount=? and livingRoomCount=? and kitchenCount=? and toiletCount=?", House.class, roomCount,livingRoomCount,kitchenCount,toiletCount);
	}
	public House queryId(int hid) {
		return BaseDao.queryById("select *from houses where hid=?", House.class, hid);
	}
}
