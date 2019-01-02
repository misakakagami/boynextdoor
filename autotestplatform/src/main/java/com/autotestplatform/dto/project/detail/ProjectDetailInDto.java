package com.autotestplatform.dto.project.detail;

import java.io.Serializable;

/**
 * 项目列表
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:48:40  
 * @version : 2018年5月10日  孔德华 创建
 */
public class ProjectDetailInDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = -5937714398184201951L;

    /**
     * 项目id
     */
    private Integer           projectId;

    public ProjectDetailInDto(Integer projectId) {
        super();
        this.projectId = projectId;
    }

    public ProjectDetailInDto() {
        super();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
