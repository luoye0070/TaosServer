package com.lj.taosserver.controller.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.taosserver.constant.AuthorityConstant;
import com.lj.taosserver.controller.data.SimpleListResult;
import com.lj.taosserver.controller.data.SimpleUserResult;
import com.lj.taosserver.data.dao.DeleteDao;
import com.lj.taosserver.data.dao.PageSearchDao;
import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.data.dao.UpdateDao;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.ResultModel;
import com.lj.taosserver.model.data.RestaurantModel;
import com.lj.taosserver.model.data.UserModel;


@Controller
@RequestMapping("/auth")
public class AuthenticationManage {
	
	@Resource
	UserDetailsService customUserDetailsService;
	
	@Resource
	PasswordEncoder passwordEncoder;
	
	
	@Resource(name="simpleSearchDao")
	SearchDao simpleSearchDao;
	@Resource
	SaveDao simpleSaveDao; 
	@Resource
	UpdateDao simpleUpdateDao; 
	@Resource
	DeleteDao simpleDeleteDao;
	
	@Resource(name="simplePageSearchDao")
	PageSearchDao simplePageSearchDao;
	
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
	
	@Deprecated
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
	@RequestMapping("/userlistview")
	public String listView(HttpServletRequest request,Model model){
		return "user_list";
	}
	@RequestMapping("/userlist")
	@ResponseBody
	public SimpleListResult getList(HttpServletRequest request,int page,int rows){
		
		LOG.info("params->"+request.getParameterNames().toString());
		LOG.info("params->"+page+"-"+rows);
		
		ResultModel resultModel=new ResultModel(){};
		
		int count=simplePageSearchDao.findAll(new UserModel(), resultModel, page, rows);
		
		List<SimpleUserResult> srrList=new ArrayList<SimpleUserResult>();
		
		List<DataModel> userList=resultModel.getDataModelList();
		if(userList!=null&&userList.size()>0){
			for(DataModel dataModel:userList){
				if(dataModel instanceof UserModel){
					UserModel userModel=(UserModel)dataModel;
					
					SimpleUserResult simpleResult=new SimpleUserResult();
					simpleResult.setId(userModel.getId());
					simpleResult.setUserName(userModel.getUserName());
					simpleResult.setPassWord(userModel.getPassWord());
					if(!StringUtils.isEmpty(userModel.getAuths())){
						String [] authsStrs=userModel.getAuths().split(","); 
						String authNames="";
				    	if(authsStrs.length>0){
				    		int i=0;
				    		for(String auth:authsStrs){
				    			AuthorityConstant authority=AuthorityConstant.getByCode(auth);
				    			if(authority!=null){
				    				if(i>0){
				    					authNames+=",";
				    				}
				    				authNames+=authority.name;
				    			}
				    			i++;
				    		}
				    	}
						simpleResult.setAuths(authNames);
					}
					srrList.add(simpleResult);
				}
			}
		}
		
		SimpleListResult slr=new SimpleListResult();
		slr.setTotal(resultModel.getCount());
		slr.setRows(srrList);
		
		return slr;
	}
	
	@RequestMapping("/useredit")
	public String edit(HttpServletRequest request,long uId,Model model){
		

		LOG.info("params->"+request.getParameterNames().toString());
		LOG.info("uId->"+uId+"-"+uId);
		
		//根据ID查询出相应user
		UserModel userModel = (UserModel) simpleSearchDao.get(UserModel.class, uId);
		if(userModel==null){
			userModel=new UserModel();
		}else{
			userModel.setPassWord("");
		}
		
		model.addAttribute("userModel", userModel);
		AuthorityConstant[] auths=AuthorityConstant.getAll();
		model.addAttribute("auths", auths);
		
		return "user_edit";
	}
	

	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
	    dateFormat.setLenient(false);    
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));    
	}
	@RequestMapping("/usersave")
	@ResponseBody
	public String save(HttpServletRequest request,UserModel userModel, Errors errors){
		
		LOG.info("userModel->"+userModel);
		UserModel oldUserModel = (UserModel) simpleSearchDao.get(UserModel.class, userModel.getId());
		if(oldUserModel==null){
			userModel.setPassWord(passwordEncoder.encodePassword(userModel.getPassWord(), userModel.getUserName()));
			simpleSaveDao.save(userModel);
		}else{
			if(StringUtils.isEmpty(userModel.getPassWord())){
				userModel.setPassWord(oldUserModel.getPassWord());
			}
			else{
				userModel.setPassWord(passwordEncoder.encodePassword(userModel.getPassWord(), userModel.getUserName()));
			}
			simpleUpdateDao.update(userModel);
		}
		return "ok";
	}
	
	@RequestMapping("/userdelete")
	@ResponseBody
	public String delete(HttpServletRequest request,long uId){		
		simpleDeleteDao.delete(UserModel.class, uId);
		return "ok";
	}
}
