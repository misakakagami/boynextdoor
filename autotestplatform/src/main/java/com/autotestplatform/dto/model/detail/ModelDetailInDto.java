package com.autotestplatform.dto.model.detail;

import java.io.Serializable;

/**
 * 模块列表
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:48:40  
 * @version : 2018年5月10日  孔德华 创建
 */
public class ModelDetailInDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = -5937714398184201951L;

    /**
     * 模块id
     */
    private Integer           modelId;

    public ModelDetailInDto(Integer modelId) {
        super();
        this.modelId = modelId;
    }

    public ModelDetailInDto() {
        super();
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

}
