package com.cdsxt.po;

public class Xm {
	private int zid;
	private String pname;
	private String unitp;
	private String unit;
	private int ds;
	public Xm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Xm(int zid, String pname, String unitp, String unit, int ds) {
		super();
		this.zid = zid;
		this.pname = pname;
		this.unitp = unitp;
		this.unit = unit;
		this.ds = ds;
	}
	public int getZid() {
		return zid;
	}
	public void setZid(int zid) {
		this.zid = zid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getUnitp() {
		return unitp;
	}
	public void setUnitp(String unitp) {
		this.unitp = unitp;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getDs() {
		return ds;
	}
	public void setDs(int ds) {
		this.ds = ds;
	}
}
