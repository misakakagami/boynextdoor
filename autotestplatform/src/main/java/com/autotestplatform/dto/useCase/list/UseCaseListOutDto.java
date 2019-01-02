package com.autotestplatform.dto.useCase.list;

import java.io.Serializable;
import java.util.List;

import com.autotestplatform.dto.common.ModelIdNameDto;
import com.autotestplatform.dto.common.PageDto;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月9日 下午2:26:05  
 * @version : 2018年5月9日  张文博 TODO简要描述修改内容，修改原因
 */
public class UseCaseListOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID 
    */

    private static final long          serialVersionUID = 1L;

    private List<UseCaseListDetailDto> testCaseInfoList;

    private Integer                    modelId;

    private Integer                    projectId;

    private List<ModelIdNameDto>       modelIdNameList;

    private PageDto                    pageDto;

    public UseCaseListOutDto() {
        super();
    }

    public UseCaseListOutDto(List<UseCaseListDetailDto> testCaseInfoList, Integer modelId, Integer projectId,
                             List<ModelIdNameDto> modelIdNameList, PageDto pageDto) {
        super();
        this.testCaseInfoList = testCaseInfoList;
        this.modelId = modelId;
        this.projectId = projectId;
        this.modelIdNameList = modelIdNameList;
        this.pageDto = pageDto;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public List<ModelIdNameDto> getModelIdNameList() {
        return modelIdNameList;
    }

    public void setModelIdNameList(List<ModelIdNameDto> modelIdNameList) {
        this.modelIdNameList = modelIdNameList;
    }

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }

    public List<UseCaseListDetailDto> getTestCaseInfoList() {
        return testCaseInfoList;
    }

    public void setTestCaseInfoList(List<UseCaseListDetailDto> testCaseInfoList) {
        this.testCaseInfoList = testCaseInfoList;
    }

}
