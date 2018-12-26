package com.autotestplatform.dto.useCase.delete;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月15日 下午3:41:10  
 * @version : 2018年5月15日  张文博 TODO简要描述修改内容，修改原因
 */
public class UseCaseDeleteInDto implements Serializable{

	
	/** 
	* @Fields serialVersionUID 
	*/  
	
	private static final long serialVersionUID = 1L;
	
	private List<Integer> useCaseIdList;

	public UseCaseDeleteInDto() {
		super();
	}

	public UseCaseDeleteInDto(List<Integer> useCaseIdList) {
		super();
		this.useCaseIdList = useCaseIdList;
	}

	public List<Integer> getUseCaseIdList() {
		return useCaseIdList;
	}

	public void setUseCaseIdList(List<Integer> useCaseIdList) {
		this.useCaseIdList = useCaseIdList;
	}
	
}
