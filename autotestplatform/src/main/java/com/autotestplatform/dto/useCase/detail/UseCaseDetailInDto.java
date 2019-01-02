package com.autotestplatform.dto.useCase.detail;

import java.io.Serializable;

/**
 * 
 * 案例詳情dto
 * @author : 张文博
 * @date : 2018年5月11日 下午5:15:45  
 * @version : 2018年5月11日  张文博 TODO简要描述修改内容，修改原因
 */
public class UseCaseDetailInDto implements Serializable{

	
	/** 
	* @Fields serialVersionUID  
	*/  
	
	private static final long serialVersionUID = 1L;
	
	private Integer useCaseId;
	
	private Integer exampleId;
	
	private Integer scriptId;

	public UseCaseDetailInDto() {
		super();
	}

	public UseCaseDetailInDto(Integer useCaseId, Integer exampleId, Integer scriptId) {
		super();
		this.useCaseId = useCaseId;
		this.exampleId = exampleId;
		this.scriptId = scriptId;
	}

	public Integer getUseCaseId() {
		return useCaseId;
	}

	public void setUseCaseId(Integer useCaseId) {
		this.useCaseId = useCaseId;
	}

	public Integer getExampleId() {
		return exampleId;
	}

	public void setExampleId(Integer exampleId) {
		this.exampleId = exampleId;
	}

	public Integer getScriptId() {
		return scriptId;
	}

	public void setScriptId(Integer scriptId) {
		this.scriptId = scriptId;
	}

}
