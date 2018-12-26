package com.autotestplatform.dto.model.update;

import java.io.Serializable;

import com.autotestplatform.dto.common.ModelDto;

/**
 * 模块修改
 * @author : 孔德华
 * @date : 2018年5月14日 下午4:13:27  
 * @version : 2018年5月14日  孔德华 创建类
 */
public class ModelUpdateInDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 8936906619213245696L;
    /**
     * modelDto
     */
    private ModelDto          modelDto;

    public ModelUpdateInDto(ModelDto modelDto) {
        super();
        this.modelDto = modelDto;
    }

    public ModelUpdateInDto() {
        super();
    }

    public ModelDto getModelDto() {
        return modelDto;
    }

    public void setModelDto(ModelDto modelDto) {
        this.modelDto = modelDto;
    }

}
