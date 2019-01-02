package com.autotestplatform.entity;

import java.io.Serializable;

public class UseCasePlanRelation implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 6366179550377474592L;

    private Integer           useCasePlanRelationId;

    private Integer           useCaseId;

    private Integer           planId;

    private Integer           planStatus;

    public UseCasePlanRelation() {
        super();
    }

    public UseCasePlanRelation(Integer useCasePlanRelationId, Integer useCaseId, Integer planId, Integer planStatus) {
        super();
        this.useCasePlanRelationId = useCasePlanRelationId;
        this.useCaseId = useCaseId;
        this.planId = planId;
        this.planStatus = planStatus;
    }

    public Integer getUseCasePlanRelationId() {
        return useCasePlanRelationId;
    }

    public void setUseCasePlanRelationId(Integer useCasePlanRelationId) {
        this.useCasePlanRelationId = useCasePlanRelationId;
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

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
    }
}
