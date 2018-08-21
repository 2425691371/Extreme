package com.cdsxt.vo;

public class Resi {
	private int idNum;
	private String name;
	private int gender;
	private int age;
	private String idCard;
	private String study;
	private String email;
	private String phone;
	private String startdate;
	private String enddate;
	private String photo;
	private String carNum;
	public Resi(int idNum, String name, int gender, int age, String idCard, String study, String email, String phone,
			String startdate, String enddate, String photo, String carNum) {
		super();
		this.idNum = idNum;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.idCard = idCard;
		this.study = study;
		this.email = email;
		this.phone = phone;
		this.startdate = startdate;
		this.enddate = enddate;
		this.photo = photo;
		this.carNum = carNum;
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
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
	public String getStudy() {
		return study;
	}
	public void setStudy(String study) {
		this.study = study;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public Resi() {
		super();
	}
	
}
