package com.autotestplatform.dto.useCase.list;

import java.io.Serializable;
import java.util.Date;

import com.autotestplatform.dto.common.PageDto;

public class UseCaseListDetailDto implements Serializable {

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

    private Integer           useCaseNum;

    private Date              updateTime;

    private PageDto           pageDto;

    public UseCaseListDetailDto() {
        super();
    }

    public UseCaseListDetailDto(Integer projectId, String projectName, Integer modelId, String modelName,
                                Integer useCaseId, String useCaseName, Integer useCaseType, Integer useCaseStatus,
                                Integer useCaseNum, Date updateTime, PageDto pageDto) {
        super();
        this.projectId = projectId;
        this.projectName = projectName;
        this.modelId = modelId;
        this.modelName = modelName;
        this.useCaseId = useCaseId;
        this.useCaseName = useCaseName;
        this.useCaseType = useCaseType;
        this.useCaseStatus = useCaseStatus;
        this.useCaseNum = useCaseNum;
        this.updateTime = updateTime;
        this.pageDto = pageDto;
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

    public Integer getUseCaseNum() {
        return useCaseNum;
    }

    public void setUseCaseNum(Integer useCaseNum) {
        this.useCaseNum = useCaseNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }

}
