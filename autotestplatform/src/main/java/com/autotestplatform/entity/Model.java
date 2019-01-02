package com.autotestplatform.entity;

import java.io.Serializable;
import java.util.Date;

public class Model implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 4189820813924813813L;

    private Integer           modelId;

    private Integer           projectId;

    private String            modelName;

    private String            modelUrl;

    private Integer           modelStatus;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    public Model() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Model(Integer modelId, Integer projectId, String modelName, String modelUrl, Integer modelStatus,
                 Date createTime, Date updateTime, Integer createUserId, Integer updateUserId) {
        super();
        this.modelId = modelId;
        this.projectId = projectId;
        this.modelName = modelName;
        this.modelUrl = modelUrl;
        this.modelStatus = modelStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl == null ? null : modelUrl.trim();
    }

    public Integer getModelStatus() {
        return modelStatus;
    }

    public void setModelStatus(Integer modelStatus) {
        this.modelStatus = modelStatus;
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
