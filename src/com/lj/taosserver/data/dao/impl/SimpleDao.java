package com.lj.taosserver.data.dao.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lj.taosserver.data.dao.DeleteDao;
import com.lj.taosserver.data.dao.SaveDao;
import com.lj.taosserver.data.dao.SearchDao;
import com.lj.taosserver.data.dao.UpdateDao;
import com.lj.taosserver.model.AbstractModel;
import com.lj.taosserver.model.DataModel;
import com.lj.taosserver.model.ResultModel;

public class SimpleDao implements SaveDao, SearchDao, DeleteDao,UpdateDao {
	//定义基本数据类型名称
	
	
	private Configuration cfg =null;
	private SessionFactory factory = null;
	
	public SessionFactory getFactory() {
		return factory;
	}
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public SimpleDao(){
		
	}
	public SimpleDao(String configPath){
		cfg =new Configuration().configure(configPath);
		factory=cfg.buildSessionFactory();
	}
	
	@Override
	public boolean delete(Class paramClass,long id) {
		//根据id删除一个DataModel
		DataModel dataModel=null;
		Session session = null;
		try{
			session=factory.openSession();
			session.beginTransaction();
			dataModel=(DataModel) session.get(paramClass, id);
			if(dataModel!=null){
				session.delete(dataModel);
			}
			session.getTransaction().commit();
			return true;
		}catch(Exception ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			//System.out.println("test finally");
			if(session!=null){
				session.close();
			}
		}
		return false;
	}

	@Override
	public int delete(AbstractModel model) {
		if(model instanceof DataModel){//删除和DataModel值相同的记录
			System.out.println("is DataModel");
			String hqlStr="delete from ";
			Class modelClass=model.getClass();
			String className=modelClass.getSimpleName();
			hqlStr+=className+" where 1=1";
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
						value=field.getInt(model);
						if(value!=0){
							hqlStr+=" and "+fieldName+"="+value;
						}
					} else if ("long".equals(fieldType)) {
						long value=0;//Long.MIN_VALUE;
						value=field.getLong(model);
						if(value!=0){
							hqlStr+=" and "+fieldName+"="+value;
						}
					} else if ("float".equals(fieldType)) {
						float value=0;//Float.MIN_VALUE;
						value=field.getFloat(model);
						if(value!=0){
							hqlStr+=" and "+fieldName+"="+value;
						}
					} else if ("double".equals(fieldType)) {
						double value=0;//Double.MIN_VALUE;
						value=field.getDouble(model);
						if(value!=0){
							hqlStr+=" and "+fieldName+"="+value;
						}
					} else if ("char".equals(fieldType)) {
						char value=0;//Character.MIN_VALUE;
						value=field.getChar(model);
						if(value!=0){
							hqlStr+=" and "+fieldName+"="+value;
						}
					} else if ("byte".equals(fieldType)) {
						byte value=0;//Byte.MIN_VALUE;
						value=field.getByte(model);
						if(value!=0){
							hqlStr+=" and "+fieldName+"="+value;
						}
					} else if ("short".equals(fieldType)) {
						short value=0;//Short.MIN_VALUE;
						value=field.getShort(model);
						if(value!=0){
							hqlStr+=" and "+fieldName+"="+value;
						}
					}else if ("boolean".equals(fieldType)) {
						boolean value=field.getBoolean(model);
						hqlStr+=" and "+fieldName+"="+value;
					}else{
						String value=null;
						value=(String) field.get(model);
						if(value!=null){
							hqlStr+=" and "+fieldName+"='"+value+"'";
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
			
			Session session = null;
			try{
				session=factory.openSession();
				session.beginTransaction();
				int count=session.createQuery(hqlStr).executeUpdate();
				session.getTransaction().commit();
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

	@Override
	public AbstractModel get(Class paramClass,long id) {
		//根据id获取一个DataModel
		DataModel dataModel=null;
		Session session = null;
		try{
			session=factory.openSession();
			dataModel=(DataModel) session.get(paramClass, id);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//System.out.println("test finally");
			if(session!=null){
				session.close();
			}
		}
		return dataModel;
	}

	@Override
	public int findAll(AbstractModel condition, Object result) {
		if(condition instanceof DataModel){//删除和DataModel值相同的记录
			System.out.println("is DataModel");
			
			Class modelClass=condition.getClass();
			String className=modelClass.getSimpleName();
			String hqlStr="select r from ";
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
					}
//					else if ("boolean".equals(fieldType)) {
//						boolean value=field.getBoolean(condition);
//						hqlStr+=" and r."+fieldName+"="+value;
//					}
					else if ("Date".equals(fieldType)) {
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
				List<DataModel> resultList=session.createQuery(hqlStr).list();
				session.getTransaction().commit();
				int count=resultList.size();
				ResultModel resultModel=(ResultModel)result;
				resultModel.setCount(count);
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

	@Override
	public boolean save(AbstractModel model) {
		//保存一个DataModel到数据库
		if(model instanceof DataModel){
			Session session = null;
			try{
				session=factory.openSession();
				session.beginTransaction();
				session.save(model);
				session.getTransaction().commit();
				return true;
			}catch(Exception ex){
				session.getTransaction().rollback();
				ex.printStackTrace();
				return false;
			}finally{
				//System.out.println("test finally");
				if(session!=null){
					session.close();
				}
			}
			
		}
		//System.out.println("tst");
		return false;
	}

	@Override
	public boolean update(AbstractModel model) {
		//保存一个DataModel到数据库
		if(model instanceof DataModel){
			Session session = null;
			try{
				session=factory.openSession();
				session.beginTransaction();
				session.update(model);
				session.getTransaction().commit();
				return true;
			}catch(Exception ex){
				session.getTransaction().rollback();
				ex.printStackTrace();
				return false;
			}finally{
				//System.out.println("test update finally");
				if(session!=null){
					session.close();
				}
			}
			
		}
		return false;
	}
	@Override
	public AbstractModel find(AbstractModel condition) {
		// TODO Auto-generated method stub
		ResultModel resultModel=new ResultModel(){};
		findAll(condition,resultModel);
		if(resultModel.getCount()>0){
			return resultModel.getDataModelList().get(0);
		}
		return null;
	}

}
