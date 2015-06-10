package com.lj.taosserver.model;

import java.util.HashMap;
import java.util.Map;

public abstract class ConditionModel extends AbstractModel {
	protected Map condition=null;

	public Map getCondition() {
		return condition;
	}

	public void setCondition(Map condition) {
		this.condition = condition;
	} 
	
	public void putCondition(Object key,Object value){
		if(this.condition==null){
			this.condition=new HashMap();
		}
		this.condition.put(key, value);
	}
}
