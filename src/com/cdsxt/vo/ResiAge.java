package com.cdsxt.vo;

public class ResiAge {
	private String agearea;
	private int count;
	public ResiAge(String agearea, int count) {
		super();
		this.agearea = agearea;
		this.count = count;
	}
	public ResiAge() {
		super();
	}
	public String getAgearea() {
		return agearea;
	}
	public void setAgearea(String agearea) {
		this.agearea = agearea;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
