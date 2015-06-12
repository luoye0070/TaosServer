package com.lj.taosserver.controller.page;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.taosserver.model.data.DailyReportModel;
import com.lj.taosserver.model.data.RestaurantModel;

@Controller
@RequestMapping("/test")
public class Test {
	protected static final Logger LOG = Logger.getLogger(Test.class.getSimpleName());
	
	@RequestMapping("/one")
	public String testone(){
		
		return "one";
	}
	@RequestMapping("/two")
	@ResponseBody
	public DailyReportModel testtwo(){
		return new DailyReportModel();
	}
	@RequestMapping("/three")
	public void testThree(HttpServletResponse response){
		try {
			//response.setCharacterEncoding("utf-8");
			response.getWriter().println("第三个测试");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    dateFormat.setLenient(false);    
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));    
	}
	@RequestMapping("/model")
	public String testModel(HttpServletResponse response,final RestaurantModel restaurantModel, Errors errors,Model model){
		//response.setCharacterEncoding("utf-8");
		LOG.info("errors.getErrorCount()->"+errors.getErrorCount());
		//model.addAttribute("dailyReport", dailyReport);
		model.addAttribute("restaurantModel", restaurantModel);
		model.addAttribute("test", "测试啦");
		//LOG.info("dailyReport-->"+dailyReport);
		LOG.info("restaurantModel-->"+restaurantModel);
		
		try {
			response.getWriter().println("model");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "modeltest";
	}
}
