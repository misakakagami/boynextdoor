package com.autotestplatform.dto.plan.delete;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月15日 下午7:10:35  
 * @version : 2018年5月15日  张文博 TODO简要描述修改内容，修改原因
 */
public class PlanDeleteInDto implements Serializable {
	
	
	/** 
	* @Fields serialVersionUID 
	*/  
	
	private static final long serialVersionUID = 1L;
	
	private List<Integer> planIdList;

	public PlanDeleteInDto() {
		super();
	}

	public PlanDeleteInDto(List<Integer> planIdList) {
		super();
		this.planIdList = planIdList;
	}

	public List<Integer> getPlanIdList() {
		return planIdList;
	}

	public void setPlanIdList(List<Integer> planIdList) {
		this.planIdList = planIdList;
	}

}
