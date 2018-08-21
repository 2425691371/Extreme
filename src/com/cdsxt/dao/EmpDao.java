package com.cdsxt.dao;

import java.util.List;

import com.cdsxt.po.Dep;
import com.cdsxt.po.Employee;
import com.cdsxt.po.Salary;
import com.cdsxt.po.WorkTime;
import com.cdsxt.util.BaseDao;
import com.cdsxt.vo.DepName;
import com.cdsxt.vo.SalaryArea;

public class EmpDao {
	public List<Employee> queryAll(int startRow,int pageRow){
		return BaseDao.queryPos("select * from employee limit ?,?", Employee.class,startRow,pageRow);
	}
	public List<WorkTime> queryTime(int startRow,int pageRow){
		return BaseDao.queryPos("select * from worktime limit ?,?", WorkTime.class,startRow,pageRow);
	}
	public void delEmp(int seNumber){
		 BaseDao.changePos("delete from employee where seNumber=? ", seNumber);
	}
	public  void add(String ename,int empNum,String phone,int age,int gender,int education,String perId,String hireDate,int salary,String depName,String mail,String photo){
		BaseDao.changePos("INSERT INTO employee(ename,empNum,phone,age,gender,education,perId,hireDate,salary,depName,mail,photoPath) values(?,?,?,?,?,?,?,?,?,?,?,?);",ename,empNum,phone,age,gender,education,perId,hireDate,salary,depName,mail,photo);
	}
	public Employee showEmp(int seNumber){
		return BaseDao.queryById("select * from employee where seNumber=? ", Employee.class, seNumber);
	}
	public void updateEmp(String ename,int empNum,String phone,int age,int gender,int education,String perId,String hireDate,int salary,String depName,String mail,String savePath,int seNumber){
		BaseDao.changePos("UPDATE employee set ename=?,empNum=?,phone=?,age=?,gender=?,education=?,perId=?,hireDate=?,salary=?,depName=?,mail=?,photoPath=? WHERE seNumber=?;",ename,empNum,phone,age,gender,education,perId,hireDate,salary,depName,mail,savePath,seNumber);
	}
	public void updateEmp(String ename,int empNum,String phone,int age,int gender,int education,String perId,String hireDate,int salary,String depName,String mail,int seNumber){
		BaseDao.changePos("UPDATE employee set ename=?,empNum=?,phone=?,age=?,gender=?,education=?,perId=?,hireDate=?,salary=?,depName=?,mail=? WHERE seNumber=?;",ename,empNum,phone,age,gender,education,perId,hireDate,salary,depName,mail,seNumber);
	}
	public List<Employee> queryName(String ename,int startRow,int pageRow){
		return BaseDao.queryPos("select * from employee where ename=? limit ?,? ", Employee.class,ename,startRow,pageRow);
	}
	public List<Employee> queryDepName(String depName,int startRow,int pageRow){
		return BaseDao.queryPos("select * from employee where depName=? limit ?,?", Employee.class,depName,startRow,pageRow);
	}
	public List<Employee> queryExcel(){
			return BaseDao.queryAll("select * from employee", Employee.class);
	}
	public List<Employee> queryExcel(String ename){
		return BaseDao.queryPos("select * from employee where ename=?", Employee.class, ename);
	}
	public List<Employee> queryExcel(String depName,String down ){
		return BaseDao.queryPos("select * from employee where depName=?", Employee.class, depName);
	}
	public boolean checkEmpNum(int empNum){
		return BaseDao.queryPos("select * from employee where empNum=?", Employee.class, empNum).size()==0?false:true;
	}
	public int queryCount(){
		int count=0;
		count=BaseDao.queryCount("select count(*) from employee");
		return count;
	}
	public int queryTimeCount(){
		int count=0;
		count=BaseDao.queryCount("select count(*) from worktime");
		return count;
	}
	public int queryName(String ename){
		int count=0;
		count=BaseDao.queryCount("select count(*) from employee where ename=?",ename);
		return count;
	}
	public int queryDepName(String depName){
		int count=0;
		count=BaseDao.queryCount("select count(*) from employee where depName=?",depName);
		return count;
	}
	public static List<SalaryArea> querySalaryArea(){
		String sql="select temp.salaryarea,count(*) count from ( "+
				"select "+
					"case when salary>=0 and salary<=500 then '0-500' "+
					"when salary>=501 and salary<=1000 then '501-1000' "+
				  "when salary>=1001 and salary<=2000 then '1001-2000' "+
					"else '2000以上' end salaryArea "+
				"from salary "+
			") temp group by temp.salaryarea";
		return BaseDao.queryPos(sql, SalaryArea.class);
	}
	public static List<Salary> queryBySalary(int minSalary,int startRow,int pageRow){
		return BaseDao.queryPos("select * from salary where salary>? limit ?,?",Salary.class,minSalary,startRow,pageRow);
	}
	public static List<Salary> queryBySalary(int minSalary,int maxSalary,int startRow,int pageRow){
		 return BaseDao.queryPos("select * from salary where salary>=? and salary<=? limit ?,?", Salary.class,minSalary,maxSalary,startRow,pageRow);
	}
	public static List<Dep> queryDep(){
		return BaseDao.queryPos("select * from dep ", Dep.class);
	}
	public static List<DepName> queryByDepName(String depName,int startRow,int pageRow){
		return BaseDao.queryPos("select e.seNumber,e.depName,e.salary from employee e where e.depName=? limit ?,?", DepName.class,depName,startRow,pageRow);
	}
	public   WorkTime showTime(int seNumber){
		return BaseDao.queryById("select * from worktime where seNumber=?",WorkTime.class,seNumber);
	}
	public void updateTime(String ename,int mouth,int workTime,int rworkTime,int overTime,int late,int qingjia,int seNumber){
		BaseDao.changePos("UPDATE worktime set ename=?,mouth=?,workTime=?,rworkTime=?,overTime=?,late=?,qingjia=? WHERE seNumber=?;",ename,mouth,workTime,rworkTime,overTime,late,qingjia,seNumber);
	}
	public void updateAddTime(String ename,int mouth,int overTime,int qingjia,int seNumber){
		BaseDao.changePos("UPDATE addtime set ename=?,mouth=?,overTime=?,qingjia=? WHERE seNumber=?;",ename,mouth,overTime,qingjia,seNumber);
	}
}
