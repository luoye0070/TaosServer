package com.lj.taosserver.model.data;

import com.lj.taosserver.model.DataModel;

public class UserModel extends DataModel {
	private String userName;
	private String passWord;
	private String auths;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getAuths() {
		return auths;
	}
	public void setAuths(String auths) {
		this.auths = auths;
	}
	@Override
	public String toString() {
		return "UserModel [userName=" + userName + ", passWord=" + passWord
				+ ", auths=" + auths + "]";
	}
	
}
