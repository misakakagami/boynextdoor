package com.autotestplatform.dto.plan.ajax;

import java.io.Serializable;

public class RunPlanInDto implements Serializable {

    /** 
    * @Fields serialVersionUID 
    */
    private static final long serialVersionUID = -2611325874664207402L;

    private Integer           planId;

    private Integer           status;

    public RunPlanInDto(Integer planId, Integer status) {
        super();
        this.planId = planId;
        this.status = status;
    }

    public RunPlanInDto() {
        super();
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
