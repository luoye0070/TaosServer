package com.lj.taosserver.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lj.taosserver.controller.page.Manage;

public class CustomUserDetailsService implements UserDetailsService {

	protected static final Logger LOG = Logger.getLogger(CustomUserDetailsService.class.getSimpleName());
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("j_username->"+username);
		UserDetails user = null;  
		  
        try {  
  
            // 搜索数据库以匹配用户登录名.  
            // 我们可以通过dao使用JDBC来访问数据库  
            //DbUser dbUser = userDAO.getDatabase(username);  
  
            // Populate the Spring User object with details from the dbUser  
            // Here we just pass the username, password, and access level  
            // getAuthorities() will translate the access level to the correct  
            // role type  
  
            user = new User(username, "1f2a2216f7e77fa761e18eecd90b68ac"  
                    .toLowerCase(), true, true, true, true,  
                    getAuthorities(1));  
  
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
    public Collection<GrantedAuthority> getAuthorities(Integer access) {  
  
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);  
  
        // 所有的用户默认拥有ROLE_USER权限  
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));  
  
        // 如果参数access为1.则拥有ROLE_ADMIN权限  
        if (access.compareTo(1) == 0) {  
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));  
        }  
  
        return authList;  
    }  
}
