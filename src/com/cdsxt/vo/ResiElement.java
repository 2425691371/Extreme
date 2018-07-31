package com.cdsxt.vo;

public class ResiElement {
	private int hid;
	private int unitId;
	private int buildingld;
	public ResiElement() {
		super();
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public int getBuildingld() {
		return buildingld;
	}
	public void setBuildingld(int buildingld) {
		this.buildingld = buildingld;
	}
	public ResiElement(int hid, int unitId, int buildingld) {
		super();
		this.hid = hid;
		this.unitId = unitId;
		this.buildingld = buildingld;
	}
	
}
