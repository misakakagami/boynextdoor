package com.autotestplatform.dto.plan.list;

import java.io.Serializable;
import java.util.Date;

import com.autotestplatform.dto.common.PageDto;

public class PlanListDetailDto implements Serializable {

    /** 
    * @Fields serialVersionUID  
    */

    private static final long serialVersionUID = 1L;

    private Integer           planId;

    private String            planName;

    private String            planIntro;

    private Integer           planType;

    private Integer           planStatus;

    private Date              planStartTime;

    private Integer           planSumTimes;

    private Integer           planInterval;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    private PageDto           pageDto;

    public PlanListDetailDto() {
        super();
    }

    public PlanListDetailDto(Integer planId, String planName, String planIntro, Integer planType, Integer planStatus,
                             Date planStartTime, Integer planSumTimes, Integer planInterval, Date createTime,
                             Date updateTime, Integer createUserId, Integer updateUserId, PageDto pageDto) {
        super();
        this.planId = planId;
        this.planName = planName;
        this.planIntro = planIntro;
        this.planType = planType;
        this.planStatus = planStatus;
        this.planStartTime = planStartTime;
        this.planSumTimes = planSumTimes;
        this.planInterval = planInterval;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
        this.pageDto = pageDto;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanIntro() {
        return planIntro;
    }

    public void setPlanIntro(String planIntro) {
        this.planIntro = planIntro;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
    }

    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Integer getPlanSumTimes() {
        return planSumTimes;
    }

    public void setPlanSumTimes(Integer planSumTimes) {
        this.planSumTimes = planSumTimes;
    }

    public Integer getPlanInterval() {
        return planInterval;
    }

    public void setPlanInterval(Integer planInterval) {
        this.planInterval = planInterval;
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

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }

}
