package com.cdsxt.po;

public class WorkTime {
	private int seNumber;
	private String ename;
	private int mouth;
	private int workTime;
	private int rworkTime;
	private int overTime;
	private int late;
	private int qingjia;
	public WorkTime(int seNumber, String ename, int mouth, int workTime, int rworkTime, int overTime, int late,
			int qingjia) {
		super();
		this.seNumber = seNumber;
		this.ename = ename;
		this.mouth = mouth;
		this.workTime = workTime;
		this.rworkTime = rworkTime;
		this.overTime = overTime;
		this.late = late;
		this.qingjia = qingjia;
	}
	public WorkTime() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public int getMouth() {
		return mouth;
	}
	public void setMouth(int mouth) {
		this.mouth = mouth;
	}
	public int getWorkTime() {
		return workTime;
	}
	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}
	public int getRworkTime() {
		return rworkTime;
	}
	public void setRworkTime(int rworkTime) {
		this.rworkTime = rworkTime;
	}
	public int getOverTime() {
		return overTime;
	}
	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}
	public int getLate() {
		return late;
	}
	public void setLate(int late) {
		this.late = late;
	}
	public int getQingjia() {
		return qingjia;
	}
	public void setQingjia(int qingjia) {
		this.qingjia = qingjia;
	}
}
