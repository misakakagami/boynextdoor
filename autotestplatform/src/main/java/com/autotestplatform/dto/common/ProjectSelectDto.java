package com.autotestplatform.dto.common;

import java.io.Serializable;

/**
 * 项目 下拉框选项
 * @author : 孔德华
 * @date : 2018年5月14日 下午4:03:32  
 * @version : 2018年5月14日  孔德华创建
 */
public class ProjectSelectDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = -6727221848610620931L;
    /**
     * 项目id
     */
    private Integer           projectId;
    /**
     * 项目名
     */
    private String            projectName;

    public ProjectSelectDto() {
        super();
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

    public ProjectSelectDto(Integer projectId, String projectName) {
        super();
        this.projectId = projectId;
        this.projectName = projectName;
    }

}
