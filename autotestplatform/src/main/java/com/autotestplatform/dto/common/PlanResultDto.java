package com.autotestplatform.dto.common;

import java.io.Serializable;
import java.util.Date;

public class PlanResultDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 6504796283417242932L;

    private Integer           planResultId;

    private Integer           planId;

    private String            planResultName;

    private String            planResultLogUrl;

    private Integer           sumNum;

    private Integer           successNum;

    private String            warnFlag;

    private Integer           planResultStatus;

    private Date              createTime;

    public PlanResultDto(Integer planResultId, Integer planId, String planResultName, String planResultLogUrl,
                         Integer sumNum, Integer successNum, String warnFlag, Integer planResultStatus,
                         Date createTime) {
        super();
        this.planResultId = planResultId;
        this.planId = planId;
        this.planResultName = planResultName;
        this.planResultLogUrl = planResultLogUrl;
        this.sumNum = sumNum;
        this.successNum = successNum;
        this.warnFlag = warnFlag;
        this.planResultStatus = planResultStatus;
        this.createTime = createTime;
    }

    public PlanResultDto() {
        super();
    }

    public Integer getPlanResultId() {
        return planResultId;
    }

    public void setPlanResultId(Integer planResultId) {
        this.planResultId = planResultId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanResultName() {
        return planResultName;
    }

    public void setPlanResultName(String planResultName) {
        this.planResultName = planResultName;
    }

    public String getPlanResultLogUrl() {
        return planResultLogUrl;
    }

    public void setPlanResultLogUrl(String planResultLogUrl) {
        this.planResultLogUrl = planResultLogUrl;
    }

    public Integer getSumNum() {
        return sumNum;
    }

    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public String getWarnFlag() {
        return warnFlag;
    }

    public void setWarnFlag(String warnFlag) {
        this.warnFlag = warnFlag;
    }

    public Integer getPlanResultStatus() {
        return planResultStatus;
    }

    public void setPlanResultStatus(Integer planResultStatus) {
        this.planResultStatus = planResultStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
