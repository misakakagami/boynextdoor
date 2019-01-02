package com.autotestplatform.dto.plan.ajax;

import java.io.Serializable;

/**
 * ajax增加用例到计划
 * @author : 孔德华
 * @date : 2018年5月25日 上午11:08:50  
 * @version : 2018年5月25日  
 */
public class UseCasePlanRelationAddDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 2862150158875767458L;

    private Integer           useCaseId;

    private Integer           planId;

    public UseCasePlanRelationAddDto(Integer useCaseId, Integer planId) {
        super();
        this.useCaseId = useCaseId;
        this.planId = planId;
    }

    public UseCasePlanRelationAddDto() {
        super();
    }

    public Integer getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Integer useCaseId) {
        this.useCaseId = useCaseId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

}
