package com.autotestplatform.dto.plan.result;

import java.io.Serializable;

public class PlanResultInDto implements Serializable {
    /** 
    * @Fields serialVersionUID  
    */
    private static final long serialVersionUID = 1L;
    //planId
    private Integer planId;
    
	public PlanResultInDto(Integer planId) {
		super();
		this.planId = planId;
	}
	public PlanResultInDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
    
}
