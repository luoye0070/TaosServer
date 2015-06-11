package com.lj.taosserver.model.data;

import java.util.Date;

import com.lj.taosserver.model.DataModel;

public class LicenseModel extends DataModel {
	private RestaurantModel restaurantModel;
	private String licenseSerial;//标示的序列化字符串
	private boolean enable;//是否有效
	private int expire;//有效期时间，单位天
	private Date createTime;//创建时间
	private Date lastTime;//最后访问时间
	
	public RestaurantModel getRestaurantModel() {
		return restaurantModel;
	}
	public void setRestaurantModel(RestaurantModel restaurantModel) {
		this.restaurantModel = restaurantModel;
	}
	public String getLicenseSerial() {
		return licenseSerial;
	}
	public void setLicenseSerial(String licenseSerial) {
		this.licenseSerial = licenseSerial;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	@Override
	public String toString() {
		return "LicenseModel [licenseSerial=" + licenseSerial + ", enable=" + enable
				+ ", expire=" + expire + ", createTime=" + createTime
				+ ", lastTime=" + lastTime + ", id=" + id
				+ "]";
	}
	
}
