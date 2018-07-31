package com.cdsxt.po;

public class Employee {
	private int seNumber;
	private String ename;
	private int empNum;
	private String phone;
	private int age;
	private int gender;
	private int education;
	private String perId;
	private String hireDate;
	private int salary;
	private String depName;
	private String mail;
	private String 	photoPath;
	public int getSeNumber() {
		return seNumber;
	}
	public void setSeNumber(int seNumber) {
		this.seNumber = seNumber;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getEducation() {
		return education;
	}
	public void setEducation(int education) {
		this.education = education;
	}
	public String getPerId() {
		return perId;
	}
	public void setPerId(String perId) {
		this.perId = perId;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public Employee(int seNumber, String ename, int empNum, String phone, int age, int gender, int education,
			String perId, String hireDate, int salary, String depName, String mail, String photoPath) {
		super();
		this.seNumber = seNumber;
		this.ename = ename;
		this.empNum = empNum;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.education = education;
		this.perId = perId;
		this.hireDate = hireDate;
		this.salary = salary;
		this.depName = depName;
		this.mail = mail;
		this.photoPath = photoPath;
	}
	public Employee() {
		super();
	}
	
}
