package com.lj.taosserver.model.data;

import com.lj.taosserver.model.DataModel;

public class RestaurantModel extends DataModel {
	private LicenseModel licenseModel;
	private String name;
	private String address;
	private String phone;
	
	
	public LicenseModel getLicenseModel() {
		return licenseModel;
	}
	public void setLicenseModel(LicenseModel licenseModel) {
		this.licenseModel = licenseModel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
