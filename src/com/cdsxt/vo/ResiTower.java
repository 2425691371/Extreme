package com.cdsxt.vo;

public class ResiTower {
	//楼房id
	private int hid;
	//楼栋
	private int buildingld;
	public ResiTower(int hid, int buildingld) {
		super();
		this.hid = hid;
		this.buildingld = buildingld;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getBuildingld() {
		return buildingld;
	}
	public void setBuildingld(int buildingld) {
		this.buildingld = buildingld;
	}
	public ResiTower() {
		super();
	}
}
