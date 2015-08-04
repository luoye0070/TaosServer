package com.lj.taosserver.listener;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jboss.logging.Logger.Level;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.lj.taosserver.constant.AuthorityConstant;
import com.lj.taosserver.controller.page.AuthenticationManage;
import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.model.data.UserModel;

public class InitListener implements ServletContextListener {
	@Resource(name="simpleSearchDao")
	SearchDao simpleSearchDao;
	@Resource
	SaveDao simpleSaveDao; 
	@Resource
	PasswordEncoder passwordEncoder;
	
	protected static final Logger LOG = Logger.getLogger(InitListener.class.getSimpleName());
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		UserModel userModel=new UserModel();
		userModel.setAuths(AuthorityConstant.ROLE_ADMIN.code);
		userModel=(UserModel) simpleSearchDao.find(userModel);
		if(userModel==null){
			userModel=new UserModel();
			userModel.setAuths(AuthorityConstant.ROLE_ADMIN.code);
			userModel.setUserName("luoye_lj");
			userModel.setPassWord(passwordEncoder.encodePassword("L.07a@xa", "luoye_lj"));
			if(simpleSaveDao.save(userModel)){
				LOG.info("创建初始化用户luoye_lj1L.07a@xa成功");
			}else{
				LOG.severe("创建初始化用户失败");
			}
		}else{
			LOG.info("超级用户已经存在");
		}
	}

}
