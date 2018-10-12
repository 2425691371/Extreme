package com.cdsxt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cdsxt.po.Resident;
import com.cdsxt.util.BaseDao;
import com.cdsxt.vo.Resi;
import com.cdsxt.vo.ResiAge;
import com.cdsxt.vo.ResiElement;
import com.cdsxt.vo.ResiRoom;
import com.cdsxt.vo.ResiTower;
import com.cdsxt.vo.ResiquAge;

//添加所有字段
public class ResidentDao {
	public void addDao(String name, int gender, int age, String idCard, String study, String email, String room,String phone,
			String startdate, String enddate, String photo) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			ps=conn.prepareStatement("insert into resident(name,gender,age,idCard,study,email,room,phone,startdate,enddate,photo) values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, gender);
			ps.setInt(3, age);
			ps.setString(4, idCard);
			ps.setString(5, study);
			ps.setString(6, email);
			ps.setString(7, room);
			ps.setString(8, phone);
			ps.setString(9, startdate);
			ps.setString(10, enddate);
			ps.setString(11, photo);
		
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
	
	//专业
	public List queryStdudy() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List list=new ArrayList();
		//建立一个新数组
		try {
			
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			//sql连接
			ps=conn.prepareStatement("select distinct study from resident");
			//sql初始化
			
			rs=ps.executeQuery();
			while(rs.next()){
				String study1=rs.getString("study");
				list.add(study1);
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
	
	//三级联动 楼房
	public List<ResiTower> towerDao() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ResiTower> list=new ArrayList<ResiTower>();
		//建立一个新数组
		try {
			
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			//sql连接
			ps=conn.prepareStatement("select * from houses;");
			//sql初始化
			rs=ps.executeQuery();
			while(rs.next()){
				int buildingId=rs.getInt("buildingId");
				ResiTower r= new ResiTower();
				r.setBuildingld(buildingId);
				list.add(r);
			}
			
		} catch (Exception e) {
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
	
	//三级联动单元
	public List<ResiElement> unitDao(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ResiElement> list=new ArrayList<ResiElement>();
		//建立一个新数组
		try {
			
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			//sql连接
			ps=conn.prepareStatement("select h.unitId from resident r,houses h where r.idNum=h.hid");
			//sql初始化
			rs=ps.executeQuery();
			while(rs.next()){
				int unitId=rs.getInt("unitId");
				ResiElement r= new ResiElement();
				r.setUnitId(unitId);
				list.add(r);
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
	
	public List<ResiRoom> roomDao(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ResiRoom> list=new ArrayList<ResiRoom>();
		//建立一个新数组
		try {
			
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			//sql连接
			ps=conn.prepareStatement("select distinct h.fangId from resident r,houses h where r.idNum=h.hid");
			//sql初始化
			rs=ps.executeQuery();
			while(rs.next()){
				int fangId=rs.getInt("fangId");
				ResiRoom r= new ResiRoom();
				r.setFangId(fangId);
				list.add(r);
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
//	public List showDao(){
//		Connection conn=null;
//		PreparedStatement ps=null;
//		ResultSet rs=null;
//		List<Resident>list=new ArrayList<>();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");		
//			ps=conn.prepareStatement("select * from resident");
//			rs=ps.executeQuery();
//			while(rs.next()){
//				int idNum=rs.getInt("idNum");
//				String name=rs.getString("name");
//				int gender=rs.getInt("gender");
//				int age=rs.getInt("age");
//				String idCard=rs.getString("idCard");
//				String study=rs.getString("study");
//				String email=rs.getString("email");
//				String room=rs.getString("room");
//				String phone=rs.getString("phone");
//				String startdate=rs.getString("startdate");
//				String enddate=rs.getNString("enddate");
//				String photo=rs.getString("photo");
//				int carNum=rs.getInt("carNum");
//				list.add(new Resident(idNum,name,gender,age,idCard,study,email,room,phone,startdate,enddate,photo,carNum));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				rs.close();
//				ps.close();
//				conn.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return list;
//	}
	//获取分页总条数
		public int counDao(){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			int count=0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
				ps=conn.prepareStatement("select COUNT(*) from resident");
				rs=ps.executeQuery();
				while(rs.next()){
					count=rs.getInt(1);
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
			return count;
		}
		
	//显示分页条数
		public List<Resident> showPage(int startRow,int pageRow){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			List<Resident>list=new ArrayList<>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
				ps=conn.prepareStatement("select  * from resident limit ?,?");
				ps.setInt(1,startRow);
				ps.setInt(2,pageRow );
				rs=ps.executeQuery();
				while(rs.next()) {
					int idNum=rs.getInt("idNum");
					String name=rs.getString("name");
					int gender=rs.getInt("gender");
					int age=rs.getInt("age");
					String idCard=rs.getString("idCard");
					String study=rs.getString("study");
					String email=rs.getString("email");
					String room=rs.getString("room");
					String phone=rs.getString("phone");
					String startdate=rs.getString("startdate");
					String enddate=rs.getNString("enddate");
					String photo=rs.getString("photo");
					int carNum=rs.getInt("carNum");
					list.add(new Resident(idNum,name,gender,age,idCard,study,email,room,phone,startdate,enddate,photo,carNum));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
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
		
		//修改数据库
		public void alterDao(int idNum,String name, int gender, int age, String idCard, String study, String email, String room,String phone,
				String startdate, String enddate, String photo) {
			Connection conn=null;
			PreparedStatement ps=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
				ps=conn.prepareStatement("update resident set name=?, gender=?,age=?,idCard=?,study=?,email=?,room=?,phone=?,startdate=?,enddate=?,photo=? where idNum=?");
				ps.setString(1, name);
				ps.setInt(2, gender);
				ps.setInt(3, age);
				ps.setString(4, idCard);
				ps.setString(5, study);
				ps.setString(6, email);
				ps.setString(7, room);
				ps.setString(8, phone);
				ps.setString(9, startdate);
				ps.setString(10, enddate);
				ps.setString(11, photo);
				ps.setInt(12, idNum);
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
		
		//通过id 来获取信息
		public Resident idAlert(int idNum){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			Resident resid=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
				ps=conn.prepareStatement("select * from Resident where  idNum=?");
				ps.setInt(1, idNum);
				rs=ps.executeQuery();
				while(rs.next()){
					String name=rs.getString("name");
					int gender=rs.getInt("gender");
					int age=rs.getInt("age");
					String idCard=rs.getString("idCard");
					String study=rs.getString("study");
					String email=rs.getString("email");
					String room=rs.getString("room");
					String phone=rs.getString("phone");
					String startdate=rs.getString("startdate");
					String enddate=rs.getNString("enddate");
					String photo=rs.getString("photo");
					int carNum=rs.getInt("carNum");
					resid=new Resident(idNum,name,gender,age,idCard,study,email,room,phone,startdate,enddate,photo,carNum);
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
			return resid;
		}
		
		//通过id删除
		
		public void delDao(int idNum){
			BaseDao.changePos("delete from Resident where idNum=?",idNum);
		}
		
		//年龄数据
		public List<ResiAge> ageShow(){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			List<ResiAge> list=new ArrayList<ResiAge>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8","root","root");
				String sql="select '0-10岁' agearea ,count(*) count from resident e1 where e1.age>=0 and e1.age<=10 "+
							"union all "+
							"select '11-20岁' agearea ,count(*) count from resident e1 where e1.age>=11 and e1.age<=20 "+
							"union all "+
							"select '21-30岁' agearea ,count(*) count  from resident e1 where e1.age>=21 and e1.age<=30 "+
							"union all "+
							"select '31-40岁' agearea ,count(*) count from resident e1 where e1.age>=31 and e1.age<=40 "+
							"union all "+
							"select '41-50岁' agearea ,count(*) count from resident e1 where e1.age>=41 and e1.age<=50 "+
							"union all "+
							"select '51-60岁' agearea ,count(*) count from resident e1 where e1.age>=51 and e1.age<=60 "+
							"union all "+
							"select '60岁及以上' agearea ,count(*) count  from resident e1 where e1.age>=61 and e1.age<=150 ";
							
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					String agearea=rs.getString(1);
					int count=rs.getInt(2);
					list.add(new ResiAge(agearea,count));
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
	//查询数据库年龄段
		public List<ResiquAge> agequery(int minAge,int maxAge,int startRow,int pageRow){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			List<ResiquAge> empList=new ArrayList<ResiquAge>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
				ps=conn.prepareStatement("select * from resident where age>=? and age<=? limit ?,?");
				ps.setInt(1, minAge);
				ps.setInt(2, maxAge);
				ps.setInt(3, startRow);
				ps.setInt(4, pageRow);
				rs=ps.executeQuery();
				while(rs.next()){
					int idNum=rs.getInt("idNum");
					String name=rs.getString("name");
					int age=rs.getInt("age");
					String idCard=rs.getString("idCard");
					empList.add(new ResiquAge(idNum,name,age,idCard));
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
			return empList;
		}
		
		public List<ResiquAge> agequery(int minAge,int startRow,int pageRow){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			List<ResiquAge> empList=new ArrayList<ResiquAge>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
				ps=conn.prepareStatement("select * from resident where age>? limit ?,?");
				ps.setInt(1, minAge);
				ps.setInt(2, startRow);
				ps.setInt(3, pageRow);
				//初始化sql
				rs=ps.executeQuery();
				while(rs.next()){
					int idNum=rs.getInt("idNum");
					String name=rs.getString("name");
					int age=rs.getInt("age");
					String idCard=rs.getString("idCard");
					empList.add(new ResiquAge(idNum,name,age,idCard));
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
			return empList;
		}
		
		//excel表导入
//		public List<Resident> queryEmps(){
//			return BaseDao.queryAll("select * from Resident", Resident.class);
//		}
		//excel表导出
		public List showDao(){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			List<Resident>list=new ArrayList<>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");		
				ps=conn.prepareStatement("select * from resident");
				rs=ps.executeQuery();
				while(rs.next()){
					int idNum=rs.getInt("idNum");
					String name=rs.getString("name");
					int gender=rs.getInt("gender");
					int age=rs.getInt("age");
					String idCard=rs.getString("idCard");
					String study=rs.getString("study");
					String email=rs.getString("email");
					String room=rs.getString("room");
					String phone=rs.getString("phone");
					String startdate=rs.getString("startdate");
					String enddate=rs.getNString("enddate");
					String photo=rs.getString("photo");
					int carNum=rs.getInt("carNum");
					list.add(new Resident(idNum,name,gender,age,idCard,study,email,room,phone,startdate,enddate,photo,carNum));
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
		
}
