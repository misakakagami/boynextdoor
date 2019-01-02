package com.autotestplatform.dto.useCase.example;

import java.io.Serializable;

import com.autotestplatform.dto.common.PageDto;

public class ExampleListInDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 8588314336698507377L;

    private Integer           useCaseId;

    private PageDto           pageDto;

    public ExampleListInDto() {
        super();
    }

    public ExampleListInDto(Integer useCaseId, PageDto pageDto) {
        super();
        this.useCaseId = useCaseId;
        this.pageDto = pageDto;
    }

    public Integer getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Integer useCaseId) {
        this.useCaseId = useCaseId;
    }

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }

}
