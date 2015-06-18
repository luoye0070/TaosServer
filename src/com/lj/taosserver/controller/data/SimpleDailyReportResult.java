package com.lj.taosserver.controller.data;

import java.util.Date;

public class SimpleDailyReportResult {
	private long id;
	private String restaurantName;
	private String date;
	private int totalCount;
	private int availableCount;
	private int completeCount;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
	public int getCompleteCount() {
		return completeCount;
	}
	public void setCompleteCount(int completeCount) {
		this.completeCount = completeCount;
	}
	
}
