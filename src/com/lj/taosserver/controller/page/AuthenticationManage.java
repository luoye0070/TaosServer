package com.lj.taosserver.controller.page;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/auth")
public class AuthenticationManage {
	
	@Resource
	UserDetailsService customUserDetailsService;
	
	@Resource
	PasswordEncoder passwordEncoder;
	
	protected static final Logger LOG = Logger.getLogger(AuthenticationManage.class.getSimpleName());
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model model){
//		while(request.getParameterNames().hasMoreElements()){
//			LOG.info("params->"+request.getParameterNames().nextElement());
//		}

		LOG.info("params->"+request.getParameterNames().toString());
		//LOG.info("rId->"+rId+"-"+rId);
		LOG.info("password->"+passwordEncoder.encodePassword("111111", "111111"));
		
		LOG.info("params-error->"+request.getParameter("error"));
		
		model.addAttribute("error", request.getParameter("error"));
		return "login";
	}
	
	@RequestMapping(value="/auth",method=RequestMethod.POST)
	public String auth(HttpServletRequest request,Model model){
		String userName="test";
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);  
		Authentication authentication = new UsernamePasswordAuthenticationToken(    
		        userDetails, userDetails.getPassword(), userDetails.getAuthorities());  
		SecurityContext securityContext = SecurityContextHolder.getContext();  
		securityContext.setAuthentication(authentication);  
		HttpSession session = request.getSession(true);  
		       session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);  
		
		       return "login";
	}
}
