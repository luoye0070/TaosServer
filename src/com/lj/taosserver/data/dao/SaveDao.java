package com.lj.taosserver.data.dao;

import com.lj.taosserver.model.AbstractModel;

public interface SaveDao {
	public boolean save(AbstractModel model);
}
