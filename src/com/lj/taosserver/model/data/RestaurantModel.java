package com.lj.taosserver.model.data;

import java.util.Date;

import com.lj.taosserver.model.DataModel;

public class RestaurantModel extends DataModel {
	private LicenseModel licenseModel;
	private String name;
	private String address;
	private String phone;
	
	private double longitude=0.0;//经度
	private double latitude=0.0;//维度
    private Date shopHoursBeginTime;//营业时间起
    private Date shopHoursEndTime;//营业时间止
    private boolean enabled;//开启关闭状态true开启，false关闭
    private String cuisineName;//菜系 
    private double averageConsume;//人均消费水平，单位元
    private String description;//简单描述
    private String baseUrl;//店铺基础url地址
    private int intervalTime;// //订单间隔时间，单位分钟,默认为60分钟
    private Date updateTime;//最后信息更新时间
	
	
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
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Date getShopHoursBeginTime() {
		return shopHoursBeginTime;
	}
	public void setShopHoursBeginTime(Date shopHoursBeginTime) {
		this.shopHoursBeginTime = shopHoursBeginTime;
	}
	public Date getShopHoursEndTime() {
		return shopHoursEndTime;
	}
	public void setShopHoursEndTime(Date shopHoursEndTime) {
		this.shopHoursEndTime = shopHoursEndTime;
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
	public double getAverageConsume() {
		return averageConsume;
	}
	public void setAverageConsume(double averageConsume) {
		this.averageConsume = averageConsume;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public int getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "RestaurantModel [licenseModel=" + licenseModel + ", name="
				+ name + ", address=" + address + ", phone=" + phone
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", shopHoursBeginTime=" + shopHoursBeginTime
				+ ", shopHoursEndTime=" + shopHoursEndTime + ", enabled="
				+ enabled + ", cuisineName=" + cuisineName
				+ ", averageConsume=" + averageConsume + ", description="
				+ description + ", baseUrl=" + baseUrl + ", intervalTime="
				+ intervalTime + ", updateTime=" + updateTime + ", id=" + id
				+ "]";
	}
	
}
