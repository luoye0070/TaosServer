package com.lj.taosserver.service.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lj.taosserver.data.dao.PageSearchDao;
import com.lj.taosserver.model.AbstractModel;
import com.lj.taosserver.model.ConditionModel;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.ResultModel;

public class DailyReportPageSearch implements PageSearchDao {
	private Configuration cfg =null;
	private SessionFactory factory = null;
	
	public SessionFactory getFactory() {
		return factory;
	}
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public DailyReportPageSearch(){
		
	}
	public DailyReportPageSearch(String configPath){
		cfg =new Configuration().configure(configPath);
		factory=cfg.buildSessionFactory();
	}
	
	@Override
	public int getTotalCount(Class paramClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findAll(AbstractModel condition, Object result, int page,
			int rows) {
		if(condition instanceof ConditionModel){
			System.out.println("is DailyReportConditionModel");
			
			ConditionModel dailyReportCondition=(ConditionModel)condition;
			String className="DailyReportModel";
			String hqlStr=" from ";
			hqlStr+=className+" as r where 1=1";
			System.out.println("is DataModel:"+className);
			if(dailyReportCondition.getCondition()!=null){
				String beginDateStr=(String) dailyReportCondition.getCondition().get("beginDate");
				String endDateStr=(String) dailyReportCondition.getCondition().get("endDate");
				String restaurantStr=(String) dailyReportCondition.getCondition().get("restaurant");
				if(beginDateStr!=null){
					hqlStr+=" and r.date>='"+beginDateStr+"'";
				}
				if(endDateStr!=null){
					hqlStr+=" and r.date<='"+endDateStr+" 23:59:59'";
				}
				if(restaurantStr!=null){
					hqlStr+=" and r.restaurantModel.id="+restaurantStr+"";
				}
			}
			System.out.println("hqlStr::"+hqlStr);
			
			Session session = null;
			try{
				session=factory.openSession();
				session.beginTransaction();
				
				String countHql="select count(r.id)"+hqlStr;
				String entryHql="select r "+hqlStr;
				
				int totalCount = Integer.parseInt(session.createQuery(countHql).list().get(0).toString());
				
				int offset=(page-1)*rows;
				List<DataModel> resultList=session.createQuery(entryHql).setFirstResult(offset).setMaxResults(rows).list();
				
				session.getTransaction().commit();
				int count=resultList.size();
				ResultModel resultModel=(ResultModel)result;
				resultModel.setCount(totalCount);
				resultModel.setDataModelList(resultList);
				//result=resultModel;
				return count;
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
		return 0;
	}

}
