package com.lj.taosserver.controller.page;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.taosserver.controller.data.SimpleLicenseResult;
import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.data.dao.UpdateDao;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.data.LicenseModel;
import com.lj.taosserver.model.data.RestaurantModel;
import com.lj.taosserver.service.LicenseGenerator;

@Controller
@RequestMapping("/restaurant")
public class RestaurantInformation {
	@Resource(name="simpleSearchDao")
	SearchDao simpleSearchDao;
	@Resource
	SaveDao simpleSaveDao; 
	@Resource
	UpdateDao simpleUpdateDao; 
	@Resource
	LicenseGenerator licenseGenerator;
	
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
	    dateFormat.setLenient(false);    
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));    
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public DataModel updateRestaurantInfo(HttpServletRequest request,RestaurantModel restaurantModel, Errors errors){
		
		if(restaurantModel==null){
			return new SimpleLicenseResult();
		}
		String ip=getClientIp(request);
		restaurantModel.setIp(ip);
		restaurantModel.setUpdateTime(new Date());
		
		LicenseModel licenseModel=null;
		RestaurantModel resultRestaurantModel=(RestaurantModel)simpleSearchDao.get(RestaurantModel.class, restaurantModel.getId());
		if(resultRestaurantModel==null){
			licenseModel=(LicenseModel) licenseGenerator.generate();
			restaurantModel.setLicenseModel(licenseModel);
			simpleSaveDao.save(restaurantModel);
		}else{
			licenseModel=resultRestaurantModel.getLicenseModel();
			restaurantModel.setLicenseModel(licenseModel);
			simpleUpdateDao.update(restaurantModel);
		}
		
		SimpleLicenseResult simpleLicenseResult=new SimpleLicenseResult();
		simpleLicenseResult.setCreateTime(licenseModel.getCreateTime());
		simpleLicenseResult.setEnable(licenseModel.isEnable());
		simpleLicenseResult.setExpire(licenseModel.getExpire());
		simpleLicenseResult.setId(licenseModel.getId());
		simpleLicenseResult.setLastTime(licenseModel.getLastTime());
		simpleLicenseResult.setLicenseSerial(licenseModel.getLicenseSerial());
		simpleLicenseResult.setRemoteId(restaurantModel.getId());
		
		return simpleLicenseResult;
	}
	
	@RequestMapping("/getLicense")
	@ResponseBody
	public DataModel geLicense(long rId){
		
		LicenseModel licenseModel=null;
		RestaurantModel restaurantModel=(RestaurantModel)simpleSearchDao.get(RestaurantModel.class, rId);
		if(restaurantModel==null){
			licenseModel=new LicenseModel();
		}else{
			licenseModel=restaurantModel.getLicenseModel();
			if(licenseModel==null){
				licenseModel=(LicenseModel) licenseGenerator.generate();
				restaurantModel.setLicenseModel(licenseModel);
				simpleUpdateDao.update(restaurantModel);
			}
			licenseModel.setLastTime(new Date());
			simpleUpdateDao.update(licenseModel);
		}
		
		SimpleLicenseResult simpleLicenseResult=new SimpleLicenseResult();
		simpleLicenseResult.setCreateTime(licenseModel.getCreateTime());
		simpleLicenseResult.setEnable(licenseModel.isEnable());
		simpleLicenseResult.setExpire(licenseModel.getExpire());
		simpleLicenseResult.setId(licenseModel.getId());
		simpleLicenseResult.setLastTime(licenseModel.getLastTime());
		simpleLicenseResult.setLicenseSerial(licenseModel.getLicenseSerial());
		simpleLicenseResult.setRemoteId(rId);
		
		return simpleLicenseResult;
	}
	
	/**
     * 获取客户端ip
     * <p>获取客户端ip</p>
     * @author 刘兆国
     * @param
     * @return
     * @Date: 2013-11-28
     * @Time: 上午11: 43
     */
    public String getClientIp(HttpServletRequest request){
        String clientIp="";
        /*****获取外网ip********/
        if (request.getHeader("X-Forwarded-For") != null)
            clientIp = request.getHeader("X-Forwarded-For");
        else if(request.getHeader("X-Real-IP") != null)
            clientIp = request.getHeader("X-Real-IP");
        else
            clientIp = "0.0.0.0";

        /********获取内网ip**********/
        String vcip= request.getRemoteAddr();
        if(vcip==null||vcip==""){
            clientIp=clientIp+"."+"0.0.0.0";
        }else{
            clientIp=clientIp+"."+vcip;
        }
        //println "clientIp: "+clientIp
        return clientIp;
    }
}
