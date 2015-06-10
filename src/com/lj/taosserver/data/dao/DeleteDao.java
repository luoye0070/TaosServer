package com.lj.taosserver.data.dao;

import com.lj.taosserver.model.AbstractModel;

public interface DeleteDao {
	public boolean delete(Class paramClass,long id);
	public int delete(AbstractModel model);
}
