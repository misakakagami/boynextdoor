package com.autotestplatform.dto.common;

import java.io.Serializable;
import java.util.Date;

public class ConfigContentDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -7374857297354395630L;

    private Integer           configContentId;

    private Integer           configId;

    private String            configName;

    private String            configType;

    private String            configContent;

    private Integer           contentStatus;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    public ConfigContentDto() {
        super();
    }

    public ConfigContentDto(Integer configContentId, Integer configId, String configName, String configType,
                            String configContent, Integer contentStatus, Date createTime, Date updateTime,
                            Integer createUserId, Integer updateUserId) {
        super();
        this.configContentId = configContentId;
        this.configId = configId;
        this.configName = configName;
        this.configType = configType;
        this.configContent = configContent;
        this.contentStatus = contentStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    public Integer getConfigContentId() {
        return configContentId;
    }

    public void setConfigContentId(Integer configContentId) {
        this.configContentId = configContentId;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType == null ? null : configType.trim();
    }

    public String getConfigContent() {
        return configContent;
    }

    public void setConfigContent(String configContent) {
        this.configContent = configContent == null ? null : configContent.trim();
    }

    public Integer getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(Integer contentStatus) {
        this.contentStatus = contentStatus;
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
