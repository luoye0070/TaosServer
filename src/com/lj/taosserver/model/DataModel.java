package com.lj.taosserver.model;

public abstract class DataModel extends AbstractModel {
	protected long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
