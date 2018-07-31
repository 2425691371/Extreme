package com.cdsxt.vo;

public class House {
	//房间编号
	private int id;
	//楼栋号
	private int buildingId;
	//单元号
	private int unitId;
	//房间号
	private String fangId;
	//户型图编号
	private int floorPlanId ;
	//卧室数量
	private int roomCount;
	//客厅数量
	private int livingRoomCount;
	//厨房数量
	private int kitchenCount;
	//卫生间 数量
	private int  toiletCount;
	//房产变更编号
	private int changeId;
	//变更时间
	private String time;
	//变更前业主
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getFangId() {
		return fangId;
	}
	public void setFangId(String fangId) {
		this.fangId = fangId;
	}
	public int getFloorPlanId() {
		return floorPlanId;
	}
	public void setFloorPlanId(int floorPlanId) {
		this.floorPlanId = floorPlanId;
	}
	public int getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
	public int getLivingRoomCount() {
		return livingRoomCount;
	}
	public void setLivingRoomCount(int livingRoomCount) {
		this.livingRoomCount = livingRoomCount;
	}
	public int getKitchenCount() {
		return kitchenCount;
	}
	public void setKitchenCount(int kitchenCount) {
		this.kitchenCount = kitchenCount;
	}
	public int getToiletCount() {
		return toiletCount;
	}
	public void setToiletCount(int toiletCount) {
		this.toiletCount = toiletCount;
	}
	public int getChangeId() {
		return changeId;
	}
	public void setChangeId(int changeId) {
		this.changeId = changeId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public House() {
		// TODO Auto-generated constructor stub
	}
	public House(int id, int buildingId, int unitId, String fangId, int floorPlanId, int roomCount, int livingRoomCount,
			int kitchenCount, int toiletCount, int changeId, String time, String name) {
		super();
		this.id = id;
		this.buildingId = buildingId;
		this.unitId = unitId;
		this.fangId = fangId;
		this.floorPlanId = floorPlanId;
		this.roomCount = roomCount;
		this.livingRoomCount = livingRoomCount;
		this.kitchenCount = kitchenCount;
		this.toiletCount = toiletCount;
		this.changeId = changeId;
		this.time = time;
		this.name = name;
	}
	
	
}
