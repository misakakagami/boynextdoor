package com.autotestplatform.dto.project.list;

import java.io.Serializable;
import com.autotestplatform.dto.common.PageDto;

/**
 * 项目列表
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:48:40  
 * @version : 2018年5月10日  孔德华 创建
 */
public class ProjectListInDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = -5937714398184201951L;

    /**
     * 页码
     */
    private PageDto           pageDto;

    /**
     * 查询内容
     */
    private String            searchContent;

    public ProjectListInDto(PageDto pageDto, String searchContent) {
        super();
        this.pageDto = pageDto;
        this.searchContent = searchContent;
    }

    public ProjectListInDto() {
        super();
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
