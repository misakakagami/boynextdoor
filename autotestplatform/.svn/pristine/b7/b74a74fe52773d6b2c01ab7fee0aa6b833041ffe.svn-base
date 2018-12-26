package com.autotestplatform.dto.project.list;

import java.io.Serializable;
import java.util.List;

import com.autotestplatform.dto.common.PageDto;

/**
 * 项目列表
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:48:40  
 * @version : 2018年5月10日  孔德华 创建
 */
public class ProjectListOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long             serialVersionUID = -5937714398184201951L;

    /**
     * 项目列表
     */
    private List<ProjectListOutDetailDto> projectDtoList;

    /**
     * 页码
     */
    private PageDto                       pageDto;

    /**
     * 查询内容
     */
    private String                        searchContent;

    public ProjectListOutDto() {
        super();
    }

    public ProjectListOutDto(List<ProjectListOutDetailDto> projectDtoList, PageDto pageDto, String searchContent,
                             String message) {
        super();
        this.projectDtoList = projectDtoList;
        this.pageDto = pageDto;
        this.searchContent = searchContent;
    }

    public List<ProjectListOutDetailDto> getProjectDtoList() {
        return projectDtoList;
    }

    public void setProjectDtoList(List<ProjectListOutDetailDto> projectDtoList) {
        this.projectDtoList = projectDtoList;
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
