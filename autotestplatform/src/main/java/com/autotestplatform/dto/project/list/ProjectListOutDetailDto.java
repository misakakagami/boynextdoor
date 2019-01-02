package com.autotestplatform.dto.project.list;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目列表 出参列表项
 * @author : 孔德华
 * @date : 2018年5月14日 下午4:18:19  
 * @version : 2018年5月14日  孔德华 创建类
 */
public class ProjectListOutDetailDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 647341357487910332L;

    private Integer           projectId;

    private String            projectName;

    private String            projectUrl;

    private Integer           projectMode;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    public ProjectListOutDetailDto(Integer projectId, String projectName, String projectUrl, Integer projectMode,
                                   Date createTime, Date updateTime, Integer createUserId, Integer updateUserId) {
        super();
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectUrl = projectUrl;
        this.projectMode = projectMode;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    public ProjectListOutDetailDto() {
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

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public Integer getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(Integer projectMode) {
        this.projectMode = projectMode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

}
