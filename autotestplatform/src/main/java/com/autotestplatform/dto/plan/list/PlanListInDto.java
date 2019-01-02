package com.autotestplatform.dto.plan.list;

import java.io.Serializable;

import com.autotestplatform.dto.common.PageDto;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月15日 下午6:25:19  
 * @version : 2018年5月15日  张文博 TODO简要描述修改内容，修改原因
 */
public class PlanListInDto implements Serializable{

	
	/** 
	* @Fields serialVersionUID  
	*/  
	
	private static final long serialVersionUID = 1L;

	private Integer planId;
	
	private String planName;
	
	private Integer planType;
	
	private PageDto pageDto;

	public PlanListInDto() {
		super();
	}

	public PlanListInDto(Integer planId, String planName, Integer planType, PageDto pageDto) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.planType = planType;
		this.pageDto = pageDto;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}

	public PageDto getPageDto() {
		return pageDto;
	}

	public void setPageDto(PageDto pageDto) {
		this.pageDto = pageDto;
	}
	
}
