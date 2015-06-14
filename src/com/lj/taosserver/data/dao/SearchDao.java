package com.lj.taosserver.data.dao;

import com.lj.taosserver.model.AbstractModel;

public interface SearchDao {
	public AbstractModel get(Class paramClass,long id);
	public int findAll(AbstractModel condition,Object result);
	public AbstractModel find(AbstractModel condition);
}
