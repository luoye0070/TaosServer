package com.lj.taosserver.controller.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.taosserver.controller.data.SimpleListResult;
import com.lj.taosserver.controller.data.SimpleRestaurantResult;
import com.lj.taosserver.data.dao.PageSearchDao;
import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.data.dao.UpdateDao;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.ResultModel;
import com.lj.taosserver.model.data.RestaurantModel;

@Controller
@RequestMapping("/restaurant-manage")
public class RestaurantManage {
	@Resource(name="simpleSearchDao")
	SearchDao simpleSearchDao;
	@Resource
	SaveDao simpleSaveDao; 
	@Resource
	UpdateDao simpleUpdateDao; 
	@Resource
	SearchDao dailyReportSearch;
	
	@Resource(name="simplePageSearchDao")
	PageSearchDao simplePageSearchDao;
	
	
	protected static final Logger LOG = Logger.getLogger(RestaurantManage.class.getSimpleName());
	
	@RequestMapping("/list")
	@ResponseBody
	public SimpleListResult getList(HttpServletRequest request,int page,int rows){
		
		LOG.info("params->"+request.getParameterNames().toString());
		LOG.info("params->"+page+"-"+rows);
		
		ResultModel resultModel=new ResultModel(){};
		
		int count=simplePageSearchDao.findAll(new RestaurantModel(), resultModel, page, rows);
		
		List<SimpleRestaurantResult> srrList=new ArrayList<SimpleRestaurantResult>();
		
		List<DataModel> restaurantList=resultModel.getDataModelList();
		if(restaurantList!=null&&restaurantList.size()>0){
			for(DataModel dataModel:restaurantList){
				if(dataModel instanceof RestaurantModel){
					RestaurantModel restaurantModel=(RestaurantModel)dataModel;
					
					SimpleRestaurantResult simpleResult=new SimpleRestaurantResult();
					simpleResult.setId(restaurantModel.getId());
					simpleResult.setAddress(restaurantModel.getAddress());
					simpleResult.setCuisineName(restaurantModel.getCuisineName());
					simpleResult.setEnabled(restaurantModel.isEnabled());
					simpleResult.setLicenseEnable(restaurantModel.getLicenseModel().isEnable());
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String lastTimeStr=sdf.format(restaurantModel.getLicenseModel().getLastTime());
					simpleResult.setLicenseLastTime(lastTimeStr);
					simpleResult.setName(restaurantModel.getName());
					simpleResult.setPhone(restaurantModel.getPhone());
					srrList.add(simpleResult);
				}
			}
		}
		
		SimpleListResult slr=new SimpleListResult();
		slr.setTotal(resultModel.getCount());
		slr.setRows(srrList);
		
		return slr;
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,int rId){
		

		LOG.info("params->"+request.getParameterNames().toString());
		LOG.info("rId->"+rId+"-"+rId);
		
		return "restaurant_edit";
	}
}
