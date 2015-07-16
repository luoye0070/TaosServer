package com.lj.taosserver.controller.page;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class Manage {
	protected static final Logger LOG = Logger.getLogger(Manage.class.getSimpleName());
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model){
		

		LOG.info("params->"+request.getParameterNames().toString());
		//LOG.info("rId->"+rId+"-"+rId);
		
		return "manage";
	}
}
