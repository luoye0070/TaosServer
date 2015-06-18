package com.lj.taosserver.controller.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.taosserver.controller.data.SimpleDailyReportResult;
import com.lj.taosserver.controller.data.SimpleListResult;
import com.lj.taosserver.controller.data.SimpleRestaurantResult;
import com.lj.taosserver.data.dao.PageSearchDao;
import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.data.dao.UpdateDao;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.ResultModel;
import com.lj.taosserver.model.data.DailyReportModel;
import com.lj.taosserver.model.data.RestaurantModel;

@Controller
@RequestMapping("/dailyreport-manage")
public class DailyReportManage {
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
	
	
	protected static final Logger LOG = Logger.getLogger(DailyReportManage.class.getSimpleName());
	
	@RequestMapping("/list")
	@ResponseBody
	public SimpleListResult getList(HttpServletRequest request,int page,int rows){
		
		LOG.info("params->"+request.getParameterNames().toString());
		LOG.info("params->"+page+"-"+rows);
		
		ResultModel resultModel=new ResultModel(){};
		
		int count=simplePageSearchDao.findAll(new DailyReportModel(), resultModel, page, rows);
		
		List<SimpleDailyReportResult> srrList=new ArrayList<SimpleDailyReportResult>();
		
		List<DataModel> restaurantList=resultModel.getDataModelList();
		if(restaurantList!=null&&restaurantList.size()>0){
			for(DataModel dataModel:restaurantList){
				if(dataModel instanceof DailyReportModel){
					DailyReportModel dailyReportModel=(DailyReportModel)dataModel;
					
					SimpleDailyReportResult simpleResult=new SimpleDailyReportResult();
					
					
					simpleResult.setId(dailyReportModel.getId());
					if(dailyReportModel.getRestaurantModel()!=null)
						simpleResult.setRestaurantName(dailyReportModel.getRestaurantModel().getName());
					simpleResult.setTotalCount(dailyReportModel.getTotalCount());
					simpleResult.setAvailableCount(dailyReportModel.getAvailableCount());
					simpleResult.setCompleteCount(dailyReportModel.getCompleteCount());
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateStr="";
					if(dailyReportModel.getDate()!=null){
						dateStr=sdf.format(dailyReportModel.getDate());
					}
					simpleResult.setDate(dateStr);
					srrList.add(simpleResult);
				}
			}
		}
		
		SimpleListResult slr=new SimpleListResult();
		slr.setTotal(resultModel.getCount());
		slr.setRows(srrList);
		
		return slr;
	}
}
