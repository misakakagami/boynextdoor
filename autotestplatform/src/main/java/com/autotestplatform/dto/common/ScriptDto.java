package com.autotestplatform.dto.common;

import java.io.Serializable;
import java.util.Date;

public class ScriptDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 241827730549569794L;

    private Integer           scriptId;

    private Integer           useCaseId;

    private String            scriptName;

    private Integer           scriptStatus;

    private String            scriptUrl;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    public ScriptDto() {
        super();
    }

    public ScriptDto(Integer scriptId, Integer useCaseId, String scriptName, Integer scriptStatus, String scriptUrl,
                     Date createTime, Date updateTime, Integer createUserId, Integer updateUserId) {
        super();
        this.scriptId = scriptId;
        this.useCaseId = useCaseId;
        this.scriptName = scriptName;
        this.scriptStatus = scriptStatus;
        this.scriptUrl = scriptUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    public Integer getScriptId() {
        return scriptId;
    }

    public void setScriptId(Integer scriptId) {
        this.scriptId = scriptId;
    }

    public Integer getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Integer useCaseId) {
        this.useCaseId = useCaseId;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName == null ? null : scriptName.trim();
    }

    public Integer getScriptStatus() {
        return scriptStatus;
    }

    public void setScriptStatus(Integer scriptStatus) {
        this.scriptStatus = scriptStatus;
    }

    public String getScriptUrl() {
        return scriptUrl;
    }

    public void setScriptUrl(String scriptUrl) {
        this.scriptUrl = scriptUrl == null ? null : scriptUrl.trim();
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
