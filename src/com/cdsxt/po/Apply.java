package com.cdsxt.po;

public class Apply {
private int Aid;
private String Aname;
private String maker;
private String price;
private String AppState;
private String AppType;
private String UseTime;
private String BuyUser;
private String FixUser;
public int getAid() {
	return Aid;
}
public void setAid(int aid) {
	Aid = aid;
}
public String getAname() {
	return Aname;
}
public void setAname(String aname) {
	Aname = aname;
}
public String getMaker() {
	return maker;
}
public void setMaker(String maker) {
	this.maker = maker;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getAppState() {
	return AppState;
}
public void setAppState(String appState) {
	AppState = appState;
}
public String getAppType() {
	return AppType;
}
public void setAppType(String appType) {
	AppType = appType;
}
public String getUseTime() {
	return UseTime;
}
public void setUseTime(String useTime) {
	UseTime = useTime;
}
public String getBuyUser() {
	return BuyUser;
}
public void setBuyUser(String buyUser) {
	BuyUser = buyUser;
}
public String getFixUser() {
	return FixUser;
}
public void setFixUser(String fixUser) {
	FixUser = fixUser;
}
public Apply(int aid, String aname, String maker, String price, String appState, String appType, String useTime,
		String buyUser, String fixUser) {
	super();
	Aid = aid;
	Aname = aname;
	this.maker = maker;
	this.price = price;
	AppState = appState;
	AppType = appType;
	UseTime = useTime;
	BuyUser = buyUser;
	FixUser = fixUser;
}


}
