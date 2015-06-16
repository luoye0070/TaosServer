package com.lj.taosserver.controller.page;

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
import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.data.dao.UpdateDao;

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
	
	
	protected static final Logger LOG = Logger.getLogger(RestaurantManage.class.getSimpleName());
	
	@RequestMapping("/list")
	@ResponseBody
	public SimpleListResult getList(HttpServletRequest request,int page,int rows){
		
		LOG.info("params->"+request.getParameterNames().toString());
		LOG.info("params->"+page+"-"+rows);
		
		
		List<SimpleRestaurantResult> srrList=new ArrayList<SimpleRestaurantResult>();
		
		SimpleRestaurantResult simpleResult=new SimpleRestaurantResult();
		simpleResult.setAddress("sdfsljflsjf");
		simpleResult.setCuisineName("sdfsdfsdfsdf");
		simpleResult.setEnabled(true);
		simpleResult.setLicenseEnable(false);
		simpleResult.setLicenseLastTime(new Date());
		simpleResult.setName("sdfsdfsd");
		simpleResult.setPhone("1342222222222");
		srrList.add(simpleResult);
		
		SimpleListResult slr=new SimpleListResult();
		slr.setTotal(20);
		slr.setRows(srrList);
		
		return slr;
	}
}
