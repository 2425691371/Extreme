package com.cdsxt.vo;

public class SalaryArea {
	private String salaryArea;
	private long count;
	public String getSalaryArea() {
		return salaryArea;
	}
	public void setSalaryArea(String salaryArea) {
		this.salaryArea = salaryArea;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public SalaryArea() {
		super();
	}
	public SalaryArea(String salaryArea, long count) {
		super();
		this.salaryArea = salaryArea;
		this.count = count;
	}
	
}
