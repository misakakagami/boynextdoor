package com.autotestplatform.dto.project.update;

import java.io.Serializable;
import com.autotestplatform.dto.common.ProjectDto;

/**
 * 项目改
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:48:40  
 * @version : 2018年5月10日  孔德华 创建
 */
public class ProjectUpdateInDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -5937714398184201951L;

    /**
     * 储存\修改对象
     */
    private ProjectDto        projectDto;

    public ProjectUpdateInDto(ProjectDto projectDto) {
        super();
        this.projectDto = projectDto;
    }

    public ProjectUpdateInDto() {
        super();
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

}
