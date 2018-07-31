package com.cdsxt.vo;

public class ResiquAge {
	private int idNum;
	private String name;
	private int age ;
	private String idCard;
	public ResiquAge(int idNum, String name, int age, String idCard) {
		super();
		this.idNum = idNum;
		this.name = name;
		this.age = age;
		this.idCard = idCard;
	}
	public int getIdNum() {
		return idNum;
	}
	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public ResiquAge() {
		super();
	}
	
}
