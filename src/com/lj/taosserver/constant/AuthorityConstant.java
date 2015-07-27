package com.lj.taosserver.constant;

public enum AuthorityConstant {
	ROLE_MANAGE("ROLE_MANAGE","系统管理员"),
	ROLE_ADMIN("ROLE_ADMIN","超级管理员");
	
	public String code;
	public String name;
	
	AuthorityConstant(String code,String name){
		this.code=code;
		this.name=name;
	}
	
	public AuthorityConstant[] getAll(){
		return new AuthorityConstant[]{ROLE_MANAGE,ROLE_ADMIN};
	}
	public AuthorityConstant getByCode(String code){
		if("ROLE_MANAGE".equals(code)){
			return ROLE_MANAGE;
		}else{
			return ROLE_ADMIN;
		}
		
	}
}
