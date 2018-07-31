package com.cdsxt.po;

public class Resident {
	private int idNum;
	private String name;
	private int gender;
	private int age;
	private String idCard;
	private String study;
	private String email;
	private String room;
	private String phone;
	private String startdate;
	private String enddate;
	private String photo;
	private int carNum;
	
	public Resident() {
		super();
	}

	public Resident(int idNum, String name, int gender, int age, String idCard, String study, String email, String room,
			String phone, String startdate, String enddate, String photo, int carNum) {
		super();
		this.idNum = idNum;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.idCard = idCard;
		this.study = study;
		this.email = email;
		this.room = room;
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

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
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

	public int getCarNum() {
		return carNum;
	}

	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}
	
	
}
