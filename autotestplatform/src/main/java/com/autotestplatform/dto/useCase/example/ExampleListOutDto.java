package com.autotestplatform.dto.useCase.example;

import java.io.Serializable;
import java.util.List;

import com.autotestplatform.dto.common.ExampleDto;
import com.autotestplatform.dto.common.PageDto;

public class ExampleListOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 8588314336698507377L;

    private Integer           useCaseId;

    private List<ExampleDto>  exampleList;

    private PageDto           pageDto;

    public ExampleListOutDto() {
        super();
    }

    public ExampleListOutDto(Integer useCaseId, List<ExampleDto> exampleList, PageDto pageDto) {
        super();
        this.useCaseId = useCaseId;
        this.exampleList = exampleList;
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

    public List<ExampleDto> getExampleList() {
        return exampleList;
    }

    public void setExampleList(List<ExampleDto> exampleList) {
        this.exampleList = exampleList;
    }

}
