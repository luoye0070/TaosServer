package com.lj.taosserver.data.dao.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lj.taosserver.data.dao.PageSearchDao;
import com.lj.taosserver.model.AbstractModel;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.ResultModel;

public class SimplePageSearchDao implements PageSearchDao {

	private Configuration cfg =null;
	private SessionFactory factory = null;
	
	public SessionFactory getFactory() {
		return factory;
	}
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public SimplePageSearchDao(){
		
	}
	public SimplePageSearchDao(String configPath){
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
		if(condition instanceof DataModel){//删除和DataModel值相同的记录
			System.out.println("is DataModel");
			
			Class modelClass=condition.getClass();
			String className=modelClass.getSimpleName();
			String hqlStr=" from ";
			hqlStr+=className+" as r where 1=1";
			System.out.println("is DataModel:"+className);
			Field[] fields=modelClass.getDeclaredFields();
			for(Field field:fields){
				try {
					String fieldType=field.getType().getSimpleName();
					System.out.println("fieldType Name-->"+field.getType().getSimpleName());
					String fieldName=field.getName();
					System.out.println("fieldName-->"+fieldName);
					field.setAccessible(true);
					if ("int".equals(fieldType)) {
						int value=0;//Integer.MIN_VALUE;
						value=field.getInt(condition);
						if(value!=0){
							hqlStr+=" and r."+fieldName+"="+value;
						}
					} else if ("long".equals(fieldType)) {
						long value=0;//Long.MIN_VALUE;
						value=field.getLong(condition);
						if(value!=0){
							hqlStr+=" and r."+fieldName+"="+value;
						}
					} else if ("float".equals(fieldType)) {
						float value=0;//Float.MIN_VALUE;
						value=field.getFloat(condition);
						if(value!=0){
							hqlStr+=" and r."+fieldName+"="+value;
						}
					} else if ("double".equals(fieldType)) {
						double value=0;//Double.MIN_VALUE;
						value=field.getDouble(condition);
						if(value!=0){
							hqlStr+=" and r."+fieldName+"="+value;
						}
					} else if ("char".equals(fieldType)) {
						char value=0;//Character.MIN_VALUE;
						value=field.getChar(condition);
						if(value!=0){
							hqlStr+=" and r."+fieldName+"="+value;
						}
					} else if ("byte".equals(fieldType)) {
						byte value=0;//Byte.MIN_VALUE;
						value=field.getByte(condition);
						if(value!=0){
							hqlStr+=" and r."+fieldName+"="+value;
						}
					} else if ("short".equals(fieldType)) {
						short value=0;//Short.MIN_VALUE;
						value=field.getShort(condition);
						if(value!=0){
							hqlStr+=" and r."+fieldName+"="+value;
						}
					}else if ("Date".equals(fieldType)) {
						Date value=(Date) field.get(condition);
						SimpleDateFormat sdtf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(value!=null){
							hqlStr+=" and r."+fieldName+"='"+sdtf.format(value)+"'";
						}
					}else if ("String".equals(fieldType)) {
						String value=null;
						try{
							value=(String) field.get(condition);
							if(value!=null){
								hqlStr+=" and r."+fieldName+"='"+value+"'";
							}
						}catch(ClassCastException ex){
							ex.printStackTrace();
						}
					} 
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
