package com.lj.taosserver.model.data;

import java.util.Date;

import com.lj.taosserver.model.DataModel;

public class DailyReportModel extends DataModel {
	private RestaurantModel restaurantModel;
	private Date date;
	private int totalCount;
	private int availableCount;
	public RestaurantModel getRestaurantModel() {
		return restaurantModel;
	}
	public void setRestaurantModel(RestaurantModel restaurantModel) {
		this.restaurantModel = restaurantModel;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}
	@Override
	public String toString() {
		return "DailyReportModel [restaurantModel=" + restaurantModel
				+ ", date=" + date + ", totalCount=" + totalCount
				+ ", availableCount=" + availableCount + ", id=" + id + "]";
	}
	
	
}
