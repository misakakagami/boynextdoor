package com.autotestplatform.dto.useCase.update;

import java.io.Serializable;

import com.autotestplatform.entity.UseCase;

public class UseCaseUpdateInDto implements Serializable{

	
	/** 
	* @Fields serialVersionUID  
	*/  
	
	private static final long serialVersionUID = 1L;
	
	private UseCase useCaseDto;

	public UseCaseUpdateInDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UseCaseUpdateInDto(UseCase useCaseDto) {
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
