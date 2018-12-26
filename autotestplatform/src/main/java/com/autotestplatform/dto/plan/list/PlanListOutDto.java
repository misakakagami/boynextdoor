package com.autotestplatform.dto.plan.list;

import java.io.Serializable;
import java.util.List;

import com.autotestplatform.dto.common.PageDto;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月15日 下午6:27:36  
 * @version : 2018年5月15日  张文博 TODO简要描述修改内容，修改原因
 */
public class PlanListOutDto implements Serializable{

	
	/** 
	* @Fields serialVersionUID  
	*/  
	
	private static final long serialVersionUID = 1L;
	
	private List<PlanListDetailDto> planListDetailDtoList;
	
	private PageDto pageDto;

	public PlanListOutDto() {
		super();
	}

	public PlanListOutDto(List<PlanListDetailDto> planListDetailDtoList, PageDto pageDto) {
		super();
		this.planListDetailDtoList = planListDetailDtoList;
		this.pageDto = pageDto;
	}

	public List<PlanListDetailDto> getPlanListDetailDtoList() {
		return planListDetailDtoList;
	}

	public void setPlanListDetailDtoList(List<PlanListDetailDto> planListDetailDtoList) {
		this.planListDetailDtoList = planListDetailDtoList;
	}

	public PageDto getPageDto() {
		return pageDto;
	}

	public void setPageDto(PageDto pageDto) {
		this.pageDto = pageDto;
	}

}
