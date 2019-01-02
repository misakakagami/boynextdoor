package com.autotestplatform.dto.model.detail;

import java.io.Serializable;
import java.util.Date;

/**
 * 模块详情out
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:48:40  
 * @version : 2018年5月10日  孔德华 创建
 */
public class ModelDetailOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = -5937714398184201951L;

    /**
     * 模块信息
     */
    private Integer           modelId;

    private Integer           projectId;

    private String            modelName;

    private String            modelUrl;

    private Integer           modelStatus;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;
    /**
     * 项目名称
     */
    private String            projectName;

    public ModelDetailOutDto() {
        super();
    }

    public ModelDetailOutDto(Integer modelId, Integer projectId, String modelName, String modelUrl, Integer modelStatus,
                             Date createTime, Date updateTime, Integer createUserId, Integer updateUserId,
                             String projectName) {
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
        this.projectName = projectName;
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
        this.modelName = modelName;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
