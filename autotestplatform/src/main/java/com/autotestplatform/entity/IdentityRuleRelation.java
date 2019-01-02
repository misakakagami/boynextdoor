package com.autotestplatform.entity;

import java.io.Serializable;
import java.util.Date;

public class IdentityRuleRelation implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 6992621377845216561L;

    private Integer           identityRuleRelationId;

    private Integer           ruleId;

    private Integer           identityId;

    private Integer           ruleStatus;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    public IdentityRuleRelation() {
        super();
    }

    public IdentityRuleRelation(Integer identityRuleRelationId, Integer ruleId, Integer identityId, Integer ruleStatus,
                                Date createTime, Date updateTime, Integer createUserId, Integer updateUserId) {
        super();
        this.identityRuleRelationId = identityRuleRelationId;
        this.ruleId = ruleId;
        this.identityId = identityId;
        this.ruleStatus = ruleStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    public Integer getIdentityRuleRelationId() {
        return identityRuleRelationId;
    }

    public void setIdentityRuleRelationId(Integer identityRuleRelationId) {
        this.identityRuleRelationId = identityRuleRelationId;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    public Integer getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(Integer ruleStatus) {
        this.ruleStatus = ruleStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
}
