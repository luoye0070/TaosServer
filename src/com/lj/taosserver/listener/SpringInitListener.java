package com.lj.taosserver.listener;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.web.context.ServletContextAware;

import com.lj.taosserver.constant.AuthorityConstant;
import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.model.data.UserModel;

public class SpringInitListener implements InitializingBean,
		ServletContextAware {
	//@Resource(name="simpleSearchDao")
	SearchDao simpleSearchDao;
	//@Resource
	SaveDao simpleSaveDao; 
	//@Resource
	PasswordEncoder passwordEncoder;
	
	public SearchDao getSimpleSearchDao() {
		return simpleSearchDao;
	}

	public void setSimpleSearchDao(SearchDao simpleSearchDao) {
		this.simpleSearchDao = simpleSearchDao;
	}

	public SaveDao getSimpleSaveDao() {
		return simpleSaveDao;
	}

	public void setSimpleSaveDao(SaveDao simpleSaveDao) {
		this.simpleSaveDao = simpleSaveDao;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	protected static final Logger LOG = Logger.getLogger(InitListener.class.getSimpleName());
	@Override
	public void setServletContext(ServletContext arg0) {
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

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}
