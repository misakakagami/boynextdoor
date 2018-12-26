package com.autotestplatform.dto.common;

import java.io.Serializable;

/**
 * 模块 下拉框项
 * @author : 孔德华
 * @date : 2018年5月14日 下午4:05:18  
 * @version : 2018年5月14日  孔德华 创建类
 */
public class ModelSelectDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 7082192556519218581L;
    /**
     * 模块id
     */
    private Integer           modelId;
    /**
     * 模块名
     */
    private String            modelName;

    public ModelSelectDto() {
        super();
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public ModelSelectDto(Integer modelId, String modelName) {
        super();
        this.modelId = modelId;
        this.modelName = modelName;
    }
}
