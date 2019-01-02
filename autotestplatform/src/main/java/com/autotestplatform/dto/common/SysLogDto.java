package com.autotestplatform.dto.common;

import java.io.Serializable;
import java.util.Date;

public class SysLogDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -5213742953618567986L;

    private Integer           sysLogId;

    private Integer           useCaseId;

    private Integer           planId;

    private Integer           planResultId;

    private String            logName;

    private String            logUrl;

    private Integer           logType;

    private Integer           logStatus;

    private Date              createTime;

    private Date              updateTime;

    public SysLogDto() {
        super();
    }

    public SysLogDto(Integer sysLogId, Integer useCaseId, Integer planId, Integer planResultId, String logName,
                     String logUrl, Integer logType, Integer logStatus, Date createTime, Date updateTime) {
        super();
        this.sysLogId = sysLogId;
        this.useCaseId = useCaseId;
        this.planId = planId;
        this.planResultId = planResultId;
        this.logName = logName;
        this.logUrl = logUrl;
        this.logType = logType;
        this.logStatus = logStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getSysLogId() {
        return sysLogId;
    }

    public void setSysLogId(Integer sysLogId) {
        this.sysLogId = sysLogId;
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

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName == null ? null : logName.trim();
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl == null ? null : logUrl.trim();
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Integer getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(Integer logStatus) {
        this.logStatus = logStatus;
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

    public Integer getPlanResultId() {
        return planResultId;
    }

    public void setPlanResultId(Integer planResultId) {
        this.planResultId = planResultId;
    }
}
