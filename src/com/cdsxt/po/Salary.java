package com.cdsxt.po;

public class Salary {
	private int seNumber;
	private String ename;
	private int salary;
	private String job;
	public int getSeNumber() {
		return seNumber;
	}
	public void setSeNumber(int seNumber) {
		this.seNumber = seNumber;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Salary(int seNumber, String ename, int salary, String job) {
		super();
		this.seNumber = seNumber;
		this.ename = ename;
		this.salary = salary;
		this.job = job;
	}
	
}
