package com.cdsxt.vo;

public class RenShu {
	private String pname;
	private int count;
	public RenShu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RenShu(String pname, int count) {
		super();
		this.pname = pname;
		this.count = count;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
