package com.lj.taosserver.test;

import java.util.Date;
import java.util.List;

import com.lj.taosserver.data.dao.impl.SimpleDao;
import com.lj.taosserver.model.ResultModel;
import com.lj.taosserver.model.data.LicenseModel;
import com.lj.taosserver.model.data.RestaurantModel;

public class Test {
	public static void main(String []args){
		LicenseModel licenseModel=new LicenseModel();
		licenseModel.setEnable(true);
		licenseModel.setLicenseSerial("12346576778");
		licenseModel.setCreateTime(new Date());
		licenseModel.setExpire(15);
		
		RestaurantModel restaurantModel=new RestaurantModel();
		restaurantModel.setLicenseModel(licenseModel);
		restaurantModel.setName("dddd");
		restaurantModel.setAddress("sdfdsfsf");
		
		SimpleDao simpleDao=new SimpleDao();
		simpleDao.save(restaurantModel);
		
		System.out.println("restaurantModel.id->"+restaurantModel.getId());
		System.out.println("licenseModel.id->"+licenseModel.getId());
		
		
		ResultModel restaurantModels=null;
		int count=simpleDao.findAll(restaurantModel, restaurantModels);
		System.out.println("restaurant count::"+count);
		
		ResultModel licenseModels=null;
		int lcount=simpleDao.findAll(licenseModel, licenseModels);
		System.out.println("lcount::"+lcount);
	}
}
