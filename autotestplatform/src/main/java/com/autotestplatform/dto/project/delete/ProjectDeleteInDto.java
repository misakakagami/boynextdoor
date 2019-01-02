package com.autotestplatform.dto.project.delete;

import java.io.Serializable;
import java.util.List;

public class ProjectDeleteInDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 6443064425283816549L;

    /**
     * 项目id
     */
    private List<Integer>     projectIdList;

    public ProjectDeleteInDto(List<Integer> projectIdList) {
        super();
        this.projectIdList = projectIdList;
    }

    public ProjectDeleteInDto() {
        super();
    }

    public List<Integer> getProjectIdList() {
        return projectIdList;
    }

    public void setProjectIdList(List<Integer> projectIdList) {
        this.projectIdList = projectIdList;
    }

}
