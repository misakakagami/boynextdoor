package com.autotestplatform.dto.useCase.detail;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 用例詳情出參Dto
 * 
 * @author : 张文博
 * @date : 2018年5月11日 下午5:24:28
 * @version : 2018年5月11日 张文博 
 */
public class UseCaseDetailOutDto implements Serializable {

    /**
     * @Fields serialVersionUID
     */

    private static final long serialVersionUID = 1L;

    private Integer           projectId;

    private String            projectName;

    private Integer           modelId;

    private String            modelName;

    private Integer           useCaseId;

    private String            useCaseName;

    private Integer           useCaseType;

    private Integer           useCaseStatus;

    private String            useCaseContent;

    private Integer           useCaseNum;

    private String            scriptUrl;

    private String            exampleUrl;

    private Integer           configId;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    private Integer           scriptId;

    private Integer           exampleId;

    public UseCaseDetailOutDto() {
        super();
    }

    public UseCaseDetailOutDto(Integer projectId, String projectName, Integer modelId, String modelName,
                               Integer useCaseId, String useCaseName, Integer useCaseType, Integer useCaseStatus,
                               String useCaseContent, Integer useCaseNum, String scriptUrl, String exampleUrl,
                               Integer configId, Date createTime, Date updateTime, Integer createUserId,
                               Integer updateUserId, Integer scriptId, Integer exampleId) {
        super();
        this.projectId = projectId;
        this.projectName = projectName;
        this.modelId = modelId;
        this.modelName = modelName;
        this.useCaseId = useCaseId;
        this.useCaseName = useCaseName;
        this.useCaseType = useCaseType;
        this.useCaseStatus = useCaseStatus;
        this.useCaseContent = useCaseContent;
        this.useCaseNum = useCaseNum;
        this.scriptUrl = scriptUrl;
        this.exampleUrl = exampleUrl;
        this.configId = configId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
        this.scriptId = scriptId;
        this.exampleId = exampleId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Integer useCaseId) {
        this.useCaseId = useCaseId;
    }

    public String getUseCaseName() {
        return useCaseName;
    }

    public void setUseCaseName(String useCaseName) {
        this.useCaseName = useCaseName;
    }

    public Integer getUseCaseType() {
        return useCaseType;
    }

    public void setUseCaseType(Integer useCaseType) {
        this.useCaseType = useCaseType;
    }

    public Integer getUseCaseStatus() {
        return useCaseStatus;
    }

    public void setUseCaseStatus(Integer useCaseStatus) {
        this.useCaseStatus = useCaseStatus;
    }

    public String getUseCaseContent() {
        return useCaseContent;
    }

    public void setUseCaseContent(String useCaseContent) {
        this.useCaseContent = useCaseContent;
    }

    public Integer getUseCaseNum() {
        return useCaseNum;
    }

    public void setUseCaseNum(Integer useCaseNum) {
        this.useCaseNum = useCaseNum;
    }

    public String getScriptUrl() {
        return scriptUrl;
    }

    public void setScriptUrl(String scriptUrl) {
        this.scriptUrl = scriptUrl;
    }

    public String getExampleUrl() {
        return exampleUrl;
    }

    public void setExampleUrl(String exampleUrl) {
        this.exampleUrl = exampleUrl;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
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

    public Integer getScriptId() {
        return scriptId;
    }

    public void setScriptId(Integer scriptId) {
        this.scriptId = scriptId;
    }

    public Integer getExampleId() {
        return exampleId;
    }

    public void setExampleId(Integer exampleId) {
        this.exampleId = exampleId;
    }

}
