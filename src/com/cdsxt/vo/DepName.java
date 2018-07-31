package com.cdsxt.vo;

public class DepName {
	private String depName;
	private int seNumber;
	private int salary;
	public DepName() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepName(String depName, int seNumber, int salary) {
		super();
		this.depName = depName;
		this.seNumber = seNumber;
		this.salary = salary;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public int getSeNumber() {
		return seNumber;
	}
	public void setSeNumber(int seNumber) {
		this.seNumber = seNumber;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}
