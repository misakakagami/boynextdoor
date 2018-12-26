package com.autotestplatform.entity;

import java.io.Serializable;
import java.util.Date;

public class Example implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = -8403995349266321296L;

    private Integer           exampleId;

    private Integer           useCaseId;

    private String            exampleName;

    private String            exampleUrl;

    private Integer           exampleStatus;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    public Example() {
        super();
    }

    public Example(Integer exampleId, Integer useCaseId, String exampleName, String exampleUrl, Integer exampleStatus,
                   Date createTime, Date updateTime, Integer createUserId, Integer updateUserId) {
        super();
        this.exampleId = exampleId;
        this.useCaseId = useCaseId;
        this.exampleName = exampleName;
        this.exampleUrl = exampleUrl;
        this.exampleStatus = exampleStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    public Integer getExampleId() {
        return exampleId;
    }

    public void setExampleId(Integer exampleId) {
        this.exampleId = exampleId;
    }

    public Integer getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Integer useCaseId) {
        this.useCaseId = useCaseId;
    }

    public String getExampleName() {
        return exampleName;
    }

    public void setExampleName(String exampleName) {
        this.exampleName = exampleName == null ? null : exampleName.trim();
    }

    public String getExampleUrl() {
        return exampleUrl;
    }

    public void setExampleUrl(String exampleUrl) {
        this.exampleUrl = exampleUrl == null ? null : exampleUrl.trim();
    }

    public Integer getExampleStatus() {
        return exampleStatus;
    }

    public void setExampleStatus(Integer exampleStatus) {
        this.exampleStatus = exampleStatus;
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
