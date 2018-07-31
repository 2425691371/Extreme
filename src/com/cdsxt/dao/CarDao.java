package com.cdsxt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cdsxt.po.CarInfo;
import com.cdsxt.util.BaseDao;


public class CarDao {
	
	// 根据传入的车牌号删除车辆信息  carDelete
	public void carDelete(String carId) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("delete from carInfo where carId=?");
			ps.setString(1, carId );
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
	//修改车辆信息
	public void carUpdate(String oldCarId,String newCarId,String brand) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("update carinfo set carId=?,brand=? where carId=?");
			ps.setString(1, newCarId);
			ps.setString(2, brand);
			ps.setString(3, oldCarId);
			//初始化sql
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
	
	
	//查询车辆信息  carQueryAll
	public List carQueryAll() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List list=new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select car.*,res.name from carinfo car,resident res where car.userId = res.idCard");
			//初始化sql
			rs=ps.executeQuery();
			while(rs.next()){
				String[] temp=new String[4];
				int id=rs.getInt(1);
				String carId=rs.getString(2);
				String brand=rs.getString(3);
				String address=rs.getString(4);
				String userId=rs.getString(5);
				String name=rs.getString(6);
				temp[0]=name;
				temp[1]=carId;
				temp[2]=userId;
				temp[3]=brand;
				list.add(temp);
			}
			return list;
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
		return null;
	}
	//查询汽车品牌是否存在  brandQuery
	public boolean brandQuery(String brand) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select brand from carBrand where brand=?");
			ps.setString(1, brand);
			//初始化sql
			rs=ps.executeQuery();
			while(rs.next()){
			return true;
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
		return false;
	}
	
	//查询汽车品牌
	public List carBrandQuery() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List list=new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select brand from carBrand");
			//初始化sql
			rs=ps.executeQuery();
			while(rs.next()){
				String result=rs.getString(1);
				list.add(result);
			}
			return list;
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
		return null;
	}
	
	
	
	//查询身份证和名字是否正确
	public  boolean carUserQuery(String name,String idCard){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select name,idCard from resident where name=? and idcard=?");
			System.out.println("name--"+name+"carId----"+idCard);
			ps.setString(1, name);
			ps.setString(2, idCard);
			//初始化sql
			rs=ps.executeQuery();
			while(rs.next()){
				
				return true;
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
		return false;
	}
	
//通过车牌号查询车辆是否存在数据库
	public  boolean carIdQuery(String carId){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select * from carInfo where carId=?");
			ps.setString(1, carId);
			//初始化sql
			rs=ps.executeQuery();
			while(rs.next()){
				
				return true;
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
		return false;
	}
		
//	查询未停车的车位
	public List carSpaceQuery() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List list=new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select car1.carSpace from carSpace car1 where car1.carSpace not in(select car2.carSpace from carinfo car1,carspace car2 where car1.address=car2.carSpace)");
			//初始化sql     
			rs=ps.executeQuery();
			while(rs.next()){
				String result=rs.getString(1);
				list.add(result);
			}
			return list;
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
		return null;
	}
	//添加车辆进数据库
	public  void carAdd(CarInfo carUser) {
		Connection conn=null;
		String   carId=carUser.getCarId();
		String   brand=carUser.getBrand();
		String   address=carUser.getAddress();
		String   userId=carUser.getUserId();
		System.out.println(carId+"---"+brand+"---"+address+"---"+userId);
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			//  insert into carinfo(carId,brand,address,userId) values("川A12345","奔驰","A05","123456");
			ps=conn.prepareStatement("insert into carinfo(carId,brand,address,userId) values(?,?,?,?)");
			ps.setString(1, carId );
			ps.setString(2, brand );
			ps.setString(3, address  );
			ps.setString(4, userId );
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
	//根据车牌号查询车辆信息
	public String[] carIdQuery1(String carIdNum) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select car.*, re.name from carInfo car,resident re where userId=idCard and carId=?");
			//初始化sql
			ps.setString(1, carIdNum);
			rs=ps.executeQuery();
			String[] temp=new String[4];
			while(rs.next()){
			
				int id=rs.getInt(1);
				String carId=rs.getString(2);
				String brand=rs.getString(3);
				String address=rs.getString(4);
				String userId=rs.getString(5);
				String name=rs.getString(6);
				System.out.println(name+"---"+carId+"---"+userId+"---"+brand);
				temp[0]=name;
				temp[1]=carId;
				temp[2]=userId;
				temp[3]=brand; 
				
			}
			return temp;
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
		return null;
	}
}
