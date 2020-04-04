package com.cdsxt.po;

public class Manager {
	private int id;
	private String uname;
	private String pwd;
	private String phone;
	private String email;
	public Manager(){super();}
	public Manager(int id, String uname, String pwd, String phone, String email) {
		super();
		this.id = id;
		this.uname = uname;
		this.pwd = pwd;
		this.phone = phone;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
 }
