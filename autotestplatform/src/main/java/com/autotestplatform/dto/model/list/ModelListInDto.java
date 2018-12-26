package com.autotestplatform.dto.model.list;

import java.io.Serializable;

import com.autotestplatform.dto.common.PageDto;

/**
 * 模块列表
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:48:40  
 * @version : 2018年5月10日  孔德华 创建
 */
public class ModelListInDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = -5937714398184201951L;
    /**
     * 项目id
     */
    private Integer           projectId;

    /**
     * 页码
     */
    private PageDto           pageDto;

    /**
     * 查询内容
     */
    private String            searchContent;

    public ModelListInDto(Integer projectId, PageDto pageDto, String searchContent) {
        super();
        this.projectId = projectId;
        this.pageDto = pageDto;
        this.searchContent = searchContent;
    }

    public ModelListInDto() {
        super();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

}
