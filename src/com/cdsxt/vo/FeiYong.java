package com.cdsxt.vo;

public class FeiYong {
	private String pname;
	private String unit;
	private String unitp;
	private int number;
	private String name;
	public FeiYong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeiYong(String pname, String unit, String unitp, int number, String name) {
//		super();
		this.pname = pname;
		this.unit = unit;
		this.unitp = unitp;
		this.number = number;
		this.name = name;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitp() {
		return unitp;
	}
	public void setUnitp(String unitp) {
		this.unitp = unitp;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
