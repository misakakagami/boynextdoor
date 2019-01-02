package com.autotestplatform.dto.common;

import java.io.Serializable;

/**
 * 用例下拉框用列表Dto
 * @author : 孔德华
 * @date : 2018年5月23日 下午6:02:57  
 * @version : 2018年5月23日
 */
public class UseCaseIdNameDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -1377877249610005465L;
    private Integer           useCaseId;
    private String            useCaseName;

    public UseCaseIdNameDto() {
        super();
    }

    public UseCaseIdNameDto(Integer useCaseId, String useCaseName) {
        super();
        this.useCaseId = useCaseId;
        this.useCaseName = useCaseName;
    }

    public Integer getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Integer useCaseId) {
        this.useCaseId = useCaseId;
    }

    public String getUseCaseName() {
        return useCaseName;
    }

    public void setUseCaseName(String useCaseName) {
        this.useCaseName = useCaseName;
    }

}
