package com.lj.taosserver.model;

import java.util.List;

public abstract class ResultModel extends AbstractModel {
	protected List<DataModel> dataModelList=null;
	protected int count=0;
	public List<DataModel> getDataModelList() {
		return dataModelList;
	}
	public void setDataModelList(List<DataModel> dataModelList) {
		this.dataModelList = dataModelList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
