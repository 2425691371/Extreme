package com.cdsxt.vo;

public class Fang {
	private int fid;
	private String fang;
	public Fang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Fang(int fid, String fang) {
		super();
		this.fid = fid;
		this.fang = fang;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFang() {
		return fang;
	}
	public void setFang(String fang) {
		this.fang = fang;
	}
}
