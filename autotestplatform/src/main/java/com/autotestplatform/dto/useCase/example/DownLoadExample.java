package com.autotestplatform.dto.useCase.example;

import java.io.Serializable;

public class DownLoadExample implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -7949055528310889439L;

    private Integer           exampleId;

    public DownLoadExample(Integer exampleId) {
        super();
        this.exampleId = exampleId;
    }

    public DownLoadExample() {
        super();
    }

    public Integer getExampleId() {
        return exampleId;
    }

    public void setExampleId(Integer exampleId) {
        this.exampleId = exampleId;
    }

}
