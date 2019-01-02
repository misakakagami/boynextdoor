package com.autotestplatform.dto.useCase.insert;

import java.io.Serializable;

import com.autotestplatform.entity.UseCase;

public class UseCaseInsertInDto implements Serializable {

	
	/** 
	* @Fields serialVersionUID  
	*/  
	
	private static final long serialVersionUID = 1L;
	
	private UseCase useCaseDto;

	public UseCaseInsertInDto() {
		super();
	}

	public UseCaseInsertInDto(UseCase useCaseDto) {
		super();
		this.useCaseDto = useCaseDto;
	}

	public UseCase getUseCaseDto() {
		return useCaseDto;
	}

	public void setUseCaseDto(UseCase useCaseDto) {
		this.useCaseDto = useCaseDto;
	}

}
