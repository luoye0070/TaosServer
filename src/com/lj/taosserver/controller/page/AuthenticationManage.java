package com.lj.taosserver.controller.page;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.taosserver.model.data.RestaurantModel;

@Controller
@RequestMapping("/auth")
public class AuthenticationManage {
	
	protected static final Logger LOG = Logger.getLogger(AuthenticationManage.class.getSimpleName());
	
	@RequestMapping("/login")
	public String edit(HttpServletRequest request,Model model){
		

		//LOG.info("params->"+request.getParameterNames().toString());
		//LOG.info("rId->"+rId+"-"+rId);
		
		return "login";
	}
}
