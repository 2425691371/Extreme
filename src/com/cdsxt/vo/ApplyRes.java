package com.cdsxt.vo;

public class ApplyRes {
private int aid;
private String aname;
private String useTime;
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
public ApplyRes(int aid, String aname, String useTime) {
	super();
	this.aid = aid;
	this.aname = aname;
	this.useTime = useTime;
}
public ApplyRes() {
	super();
}


}
