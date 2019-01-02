package com.autotestplatform.dto.common;

import java.io.Serializable;

/**
 * 项目下拉框用列表Dto
 * @author : 孔德华
 * @date : 2018年5月23日 下午6:02:57  
 * @version : 2018年5月23日
 */
public class ProjectIdNameDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -1377877249610005465L;
    private Integer           projectId;
    private String            projectName;

    public ProjectIdNameDto() {
        super();
    }

    public ProjectIdNameDto(Integer projectId, String projectName) {
        super();
        this.projectId = projectId;
        this.projectName = projectName;
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

}
