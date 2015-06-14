package com.lj.taosserver.controller.page;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.data.dao.UpdateDao;
import com.lj.taosserver.model.ConditionModel;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.data.DailyReportModel;
import com.lj.taosserver.model.data.RestaurantModel;

@Controller
@RequestMapping("/daily-report")
public class DailyReport {
	@Resource(name="simpleSearchDao")
	SearchDao simpleSearchDao;
	@Resource
	SaveDao simpleSaveDao; 
	@Resource
	UpdateDao simpleUpdateDao; 
	@Resource
	SearchDao dailyReportSearch;
	
	@RequestMapping("/report")
	@ResponseBody
	public DataModel report(long rId,DailyReportModel dailyReportModel){
		RestaurantModel restaurantModel=(RestaurantModel) simpleSearchDao.get(RestaurantModel.class, rId);
		if(restaurantModel!=null){
			//检查是否存在当天记录
			ConditionModel condition=new ConditionModel(){};
			HashMap<String,Object> conditionMap=new HashMap<String, Object>();
			conditionMap.put("rId", rId);
			conditionMap.put("date", new Date());
			condition.setCondition(conditionMap);
			DailyReportModel dailyReport=(DailyReportModel) dailyReportSearch.find(condition);
			if(dailyReport==null){
				dailyReportModel.setRestaurantModel(restaurantModel);
				dailyReportModel.setDate(new Date());
				simpleSaveDao.save(dailyReportModel);
			}else{
				dailyReportModel.setId(dailyReport.getId());
				dailyReportModel.setRestaurantModel(restaurantModel);
				dailyReportModel.setDate(new Date());
				simpleUpdateDao.update(dailyReportModel);
			}
		}
		
		return dailyReportModel;
	}
}
