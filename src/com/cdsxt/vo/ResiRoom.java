package com.cdsxt.vo;

public class ResiRoom {
	private int hid;
	private int buildingld;
	private int unitId;
	private int fangId;
	public ResiRoom(int hid, int buildingld, int unitId, int fangId) {
		super();
		this.hid = hid;
		this.buildingld = buildingld;
		this.unitId = unitId;
		this.fangId = fangId;
	}
	public ResiRoom() {
		super();
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
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public int getFangId() {
		return fangId;
	}
	public void setFangId(int fangId) {
		this.fangId = fangId;
	}
	
}
