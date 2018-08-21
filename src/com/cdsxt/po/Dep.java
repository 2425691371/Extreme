package com.cdsxt.po;

public class Dep {
	private String depName;
	private int number;
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Dep() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dep(String depName, int number) {
		super();
		this.depName = depName;
		this.number = number;
	}
	
}
