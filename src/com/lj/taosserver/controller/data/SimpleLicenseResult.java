package com.lj.taosserver.controller.data;

import java.util.Date;

import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.data.LicenseModel;

public class SimpleLicenseResult extends LicenseModel {
	protected long  remoteId;//饭店ID
	
	
	public long getRemoteId() {
		return remoteId;
	}
	public void setRemoteId(long remoteId) {
		this.remoteId = remoteId;
	}
	
	@Override
	public String toString() {
		return "SimpleLicenseModel [remoteId=" + remoteId + ", licenseSerial="
				+ licenseSerial + ", enable=" + enable + ", expire=" + expire
				+ ", createTime=" + createTime + ", lastTime=" + lastTime
				+ ", id=" + id + "]";
	}
	
}
