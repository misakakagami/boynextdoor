package com.autotestplatform.dto.plan.detail;

import java.io.Serializable;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月15日 下午6:55:06  
 * @version : 2018年5月15日  张文博 TODO简要描述修改内容，修改原因
 */
public class PlanDetailInDto implements Serializable {

	
	/** 
	* @Fields serialVersionUID  
	*/  
	private static final long serialVersionUID = 1L;
	
	private Integer planId;

	public PlanDetailInDto() {
		super();
	}

	public PlanDetailInDto(Integer planId) {
		super();
		this.planId = planId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}	

}
