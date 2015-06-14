package com.lj.taosserver.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.model.AbstractModel;
import com.lj.taosserver.model.ConditionModel;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.ResultModel;
import com.lj.taosserver.model.data.DailyReportModel;

public class DailyReportSearch implements SearchDao {

	private Configuration cfg =null;
	private SessionFactory factory = null;
	
	public SessionFactory getFactory() {
		return factory;
	}
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public DailyReportSearch(){
		
	}
	public DailyReportSearch(String configPath){
		cfg =new Configuration().configure(configPath);
		factory=cfg.buildSessionFactory();
	}
	
	
	@Override
	public AbstractModel get(Class paramClass, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findAll(AbstractModel condition, Object result) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractModel find(AbstractModel condition) {
		if(condition instanceof ConditionModel){
			long rId=(long) ((ConditionModel) condition).getCondition().get("rId");
			Date date=(Date) ((ConditionModel) condition).getCondition().get("date");
			
			Calendar calendar=Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(Calendar.HOUR_OF_DAY,0);
	        calendar.set(Calendar.MINUTE,0);
	        calendar.set(Calendar.SECOND,0);
	        calendar.set(Calendar.MILLISECOND,0);
	        Date dateBegin=calendar.getTime();
	        calendar.set(Calendar.HOUR_OF_DAY,23);
	        calendar.set(Calendar.MINUTE,59);
	        calendar.set(Calendar.SECOND,59);
	        calendar.set(Calendar.MILLISECOND,999);
	        Date dateEnd=calendar.getTime();
	        
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String beginStr=sdf.format(dateBegin);
	        String endStr=sdf.format(dateEnd);
	        
	        String hqlStr="select r from ";
			hqlStr+="DailyReportModel as r where restaurantModel.id="+rId+" and date>='"+beginStr+"' and date<='"+dateEnd+"'";
			
			Session session = null;
			try{
				session=factory.openSession();
				session.beginTransaction();
				List<DataModel> resultList=session.createQuery(hqlStr).list();
				session.getTransaction().commit();
				DailyReportModel dailyReportModel=null;
				if(resultList!=null&&resultList.size()>0){
					dailyReportModel=(DailyReportModel) resultList.get(0);
				}
				return dailyReportModel;
			}catch(Exception ex){
				session.getTransaction().rollback();
				ex.printStackTrace();
			}finally{
				//System.out.println("test finally");
				if(session!=null){
					session.close();
				}
			}
	        
		}
		return null;
	}

}
