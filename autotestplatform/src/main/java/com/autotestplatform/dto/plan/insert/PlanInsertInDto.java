package com.autotestplatform.dto.plan.insert;

import java.io.Serializable;

import com.autotestplatform.dto.common.PlanDto;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月15日 下午7:03:14  
 * @version : 2018年5月15日  张文博 TODO简要描述修改内容，修改原因
 */
public class PlanInsertInDto implements Serializable{
	
	
	/** 
	* @Fields serialVersionUID  
	*/  
	
	private static final long serialVersionUID = 1L;
	private PlanDto planDto;

	public PlanInsertInDto() {
		super();
	}

	public PlanInsertInDto(PlanDto planDto) {
		super();
		this.planDto = planDto;
	}

	public PlanDto getPlanDto() {
		return planDto;
	}

	public void setPlanDto(PlanDto planDto) {
		this.planDto = planDto;
	}
	
	

}
