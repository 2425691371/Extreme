package com.cdsxt.po;

public class CarInfo {
	String carId ;
	String brand;
	String address;
	String userId;
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public CarInfo(String carId, String brand, String address, String userId) {
		super();
		this.carId = carId;
		this.brand = brand;
		this.address = address;
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "CarInfo [carId=" + carId + ", brand=" + brand + ", address=" + address + ", userId=" + userId + "]";
	}
	
	
}
//PO包