package com.cdsxt.po;

public class Admin {
	private int uid;
	private String uname;
	private String password;
	private String phone;
	private String emal;
	public Admin(){super();}
	public Admin(int uid, String uname, String password, String phone, String emal) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.password = password;
		this.phone = phone;
		this.emal = emal;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmal() {
		return emal;
	}
	public void setEmal(String emal) {
		this.emal = emal;
	}
	
	
 }
