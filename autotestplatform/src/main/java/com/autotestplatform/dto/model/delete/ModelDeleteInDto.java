package com.autotestplatform.dto.model.delete;

import java.io.Serializable;
import java.util.List;

public class ModelDeleteInDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 6443064425283816549L;

    /**
     * 项目id
     */
    private List<Integer>     modelIdList;

    public ModelDeleteInDto(List<Integer> modelIdList) {
        super();
        this.modelIdList = modelIdList;
    }

    public ModelDeleteInDto() {
        super();
    }

    public List<Integer> getModelIdList() {
        return modelIdList;
    }

    public void setModelIdList(List<Integer> modelIdList) {
        this.modelIdList = modelIdList;
    }

}
