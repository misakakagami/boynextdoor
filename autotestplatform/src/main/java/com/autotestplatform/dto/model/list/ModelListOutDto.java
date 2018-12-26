package com.autotestplatform.dto.model.list;

import java.io.Serializable;
import java.util.List;

import com.autotestplatform.dto.common.PageDto;
import com.autotestplatform.dto.common.ProjectIdNameDto;

/**
 * 模块列表
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:48:40  
 * @version : 2018年5月10日  孔德华 创建
 */
public class ModelListOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long           serialVersionUID = -5937714398184201951L;

    /**
     * 模块列表
     */
    private List<ModelListOutDetailDto> modelListOutDtoList;

    /**
     * 当前项目id,可为空
     */
    private Integer                     projectId;

    /**
     * 项目列表
     */
    private List<ProjectIdNameDto>      projectIdNameList;

    /**
     * 页码
     */
    private PageDto                     pageDto;

    /**
     * 查询内容
     */
    private String                      searchContent;

    public ModelListOutDto() {
        super();
    }

    public ModelListOutDto(List<ModelListOutDetailDto> modelListOutDtoList, Integer projectId,
                           List<ProjectIdNameDto> projectIdNameList, PageDto pageDto, String searchContent) {
        super();
        this.modelListOutDtoList = modelListOutDtoList;
        this.projectId = projectId;
        this.projectIdNameList = projectIdNameList;
        this.pageDto = pageDto;
        this.searchContent = searchContent;
    }

    public List<ModelListOutDetailDto> getModelListOutDtoList() {
        return modelListOutDtoList;
    }

    public void setModelListOutDtoList(List<ModelListOutDetailDto> modelListOutDtoList) {
        this.modelListOutDtoList = modelListOutDtoList;
    }

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public List<ProjectIdNameDto> getProjectIdNameList() {
        return projectIdNameList;
    }

    public void setProjectIdNameList(List<ProjectIdNameDto> projectIdNameList) {
        this.projectIdNameList = projectIdNameList;
    }

}
