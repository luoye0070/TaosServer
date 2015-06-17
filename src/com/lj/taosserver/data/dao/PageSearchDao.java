package com.lj.taosserver.data.dao;

import com.lj.taosserver.model.AbstractModel;

public interface PageSearchDao {
	int getTotalCount(Class paramClass);
	public int findAll(AbstractModel condition,Object result,int page,int rows);
}
