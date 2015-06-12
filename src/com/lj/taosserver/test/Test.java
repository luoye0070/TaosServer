package com.lj.taosserver.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.data.dao.impl.SimpleDao;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.ResultModel;
import com.lj.taosserver.model.data.DailyReportModel;
import com.lj.taosserver.model.data.LicenseModel;
import com.lj.taosserver.model.data.RestaurantModel;
import com.lj.taosserver.service.LicenseGenerator;
import com.lj.taosserver.service.impl.SimpleLicenseGenerator;

public class Test {
	public static void main(String []args){
//		LicenseModel licenseModel=new LicenseModel();
//		licenseModel.setEnable(true);
//		licenseModel.setLicenseSerial("12346576778");
//		licenseModel.setCreateTime(new Date());
//		licenseModel.setExpire(15);
//		
//		RestaurantModel restaurantModel=new RestaurantModel();
//		restaurantModel.setLicenseModel(licenseModel);
//		restaurantModel.setName("dddd");
//		restaurantModel.setAddress("sdfdsfsf");
//		
//		SimpleDao simpleDao=new SimpleDao();
//		simpleDao.save(restaurantModel);
//		
//		System.out.println("restaurantModel.id->"+restaurantModel.toString());
//		System.out.println("licenseModel.id->"+licenseModel.toString());
//		
//		licenseModel.setLicenseSerial("11111111111");
//		simpleDao.update(licenseModel);
//		
//		ResultModel restaurantModels=new ResultModel(){};
//		int count=simpleDao.findAll(new RestaurantModel(), restaurantModels);
//		System.out.println("restaurant count::"+count);
//		for(DataModel dataModel:restaurantModels.getDataModelList()){
//			System.out.println("dataModel:"+dataModel.toString());
//		}
//		
//		
//		ResultModel licenseModels=new ResultModel(){};
//		int lcount=simpleDao.findAll(licenseModel, licenseModels);
//		System.out.println("lcount::"+lcount);
//		LicenseModel ll=(LicenseModel) simpleDao.get(LicenseModel.class, 11);
//		System.out.println("ll.r::"+ll.getRestaurantModel());
		
		
		LicenseGenerator licenseGenerator=new SimpleLicenseGenerator();
		LicenseModel licenseModelG=(LicenseModel) licenseGenerator.generator();
		System.out.println("licenseModelG::"+licenseModelG);
		
		DailyReportModel dataModel=new DailyReportModel();
		dataModel.setId(0);
		dataModel.setAvailableCount(10);
		dataModel.setTotalCount(100);
		dataModel.setDate(new Date());
		
		SaveDao saveDao=new SimpleDao("dataconfig/hibernate.cfg.xml");
		saveDao.save(dataModel);
		
		System.out.println("DailyReportModel->"+dataModel);
		
		
		ClassPathXmlApplicationContext appContext = 
                new ClassPathXmlApplicationContext(new String[] {"springconfig/applicationContext.xml"});
		SearchDao searchDao=(SearchDao) appContext.getBean("simpleDao");
		
		DailyReportModel dataModel1=(DailyReportModel) searchDao.get(DailyReportModel.class, 1);
		System.out.println("DailyReportModel1->"+dataModel1);
		
		
	}
}
