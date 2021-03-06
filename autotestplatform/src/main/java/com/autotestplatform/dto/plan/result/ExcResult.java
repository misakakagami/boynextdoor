package com.autotestplatform.dto.plan.result;

import java.io.Serializable;
import java.util.List;

public class ExcResult implements Serializable{

    /** 
    * @Fields serialVersionUID  
    */
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String planResultName;
    
    private Integer sumUseCase;
    
    private Integer errUseCase = 0;
    //计划结果状态，r正在运行 e已经完成
    private String status = "e";
    
    private List<SingleCaseDto> caseList;
    

	public ExcResult(Integer id, String planResultName, Integer sumUseCase, Integer errUseCase, String status,
			List<SingleCaseDto> caseList) {
		super();
		this.id = id;
		this.planResultName = planResultName;
		this.sumUseCase = sumUseCase;
		this.errUseCase = errUseCase;
		this.status = status;
		this.caseList = caseList;
	}

	public ExcResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<SingleCaseDto> getCaseList() {
		return caseList;
	}

	public void setCaseList(List<SingleCaseDto> caseList) {
		this.caseList = caseList;
	}

	public Integer getSumUseCase() {
		return sumUseCase;
	}

	public void setSumUseCase(Integer sumUseCase) {
		this.sumUseCase = sumUseCase;
	}

	public Integer getErrUseCase() {
		return errUseCase;
	}

	public void setErrUseCase(Integer errUseCase) {
		this.errUseCase = errUseCase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlanResultName() {
		return planResultName;
	}

	public void setPlanResultName(String planResultName) {
		this.planResultName = planResultName;
	}
    
}
