package com.cdsxt.vo;

public class ApplyDel {
	private int aid;
	private String aname;
	private String useTime;
	private String buyUser;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getBuyUser() {
		return buyUser;
	}
	public void setBuyUser(String buyUser) {
		this.buyUser = buyUser;
	}
	public ApplyDel(int aid, String aname, String useTime, String buyUser) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.useTime = useTime;
		this.buyUser = buyUser;
	}
	public ApplyDel() {
		super();
	}
	
}
