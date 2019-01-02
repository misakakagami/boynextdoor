package com.autotestplatform.dto.project.insert;

import java.io.Serializable;
import com.autotestplatform.dto.common.ProjectDto;

/**
 * 项目增
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:48:40  
 * @version : 2018年5月10日  孔德华 创建
 */
public class ProjectSaveInDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -5937714398184201951L;

    /**
     * 储存\修改对象
     */
    private ProjectDto        projectDto;

    public ProjectSaveInDto(ProjectDto projectDto) {
        super();
        this.projectDto = projectDto;
    }

    public ProjectSaveInDto() {
        super();
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

}
