package com.lj.taosserver.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lj.taosserver.controller.page.Manage;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.model.data.UserModel;

public class CustomUserDetailsService implements UserDetailsService {

//	@Resource(name="simpleSearchDao")
	SearchDao simpleSearchDao;
	
	public SearchDao getSimpleSearchDao() {
		return simpleSearchDao;
	}

	public void setSimpleSearchDao(SearchDao simpleSearchDao) {
		this.simpleSearchDao = simpleSearchDao;
	}

	protected static final Logger LOG = Logger.getLogger(CustomUserDetailsService.class.getSimpleName());
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("j_username->"+username);
		UserDetails user = null;  
		  
        try {  
        	// 搜索数据库以匹配用户登录名.
        	UserModel condition=new UserModel();
        	condition.setUserName(username);
        	UserModel userModel=(UserModel) simpleSearchDao.find(condition);
        	LOG.info("userModel->"+userModel.toString());
        	if(userModel!=null){
        		user = new User(username, userModel.getPassWord()  
                        .toLowerCase(), true, true, true, true,  
                        getAuthorities(userModel.getAuths()));  
        	} 
  
        } catch (Exception e) {  
        	LOG.warning("Error in retrieving user");  
            throw new UsernameNotFoundException("Error in retrieving user");  
        }  
  
        return user;  
	}

	/** 
     * 获得访问角色权限 
     *  
     * @param access 
     * @return 
     */  
    public Collection<GrantedAuthority> getAuthorities(String auths) {  
    	List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2); 
    	if(auths!=null){
	    	String [] authsStrs=auths.split(",");       
	    	if(authsStrs.length>0){
	    		for(String auth:authsStrs){
	    			authList.add(new SimpleGrantedAuthority(auth)); 
	    			LOG.info("auth->"+auth);
	    		}
	    	}
    	}
        return authList;  
    }  
}
