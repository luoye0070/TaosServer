package com.lj.taosserver.controller.data;

import java.util.Date;

public class SimpleRestaurantResult {
	private String name;
	private String address;
	private String phone;
	private boolean enabled;//开启关闭状态true开启，false关闭
    private String cuisineName;//菜系 
    private boolean licenseEnable;//是否有效
    private Date licenseLastTime;//最后访问时间
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getCuisineName() {
		return cuisineName;
	}
	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}
	public boolean isLicenseEnable() {
		return licenseEnable;
	}
	public void setLicenseEnable(boolean licenseEnable) {
		this.licenseEnable = licenseEnable;
	}
	public Date getLicenseLastTime() {
		return licenseLastTime;
	}
	public void setLicenseLastTime(Date licenseLastTime) {
		this.licenseLastTime = licenseLastTime;
	}
    
    
    
}
