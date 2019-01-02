package com.autotestplatform.dto.common;

import java.io.Serializable;

/**
 * 莫夸下拉框用列表Dto
 * @author : 孔德华
 * @date : 2018年5月23日 下午6:02:57  
 * @version : 2018年5月23日
 */
public class ModelIdNameDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -1377877249610005465L;
    private Integer           modelId;
    private String            modelName;

    public ModelIdNameDto() {
        super();
    }

    public ModelIdNameDto(Integer modelId, String modelName) {
        super();
        this.modelId = modelId;
        this.modelName = modelName;
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

}
