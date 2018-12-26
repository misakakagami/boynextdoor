package com.autotestplatform.dto.useCase.script;

import java.io.Serializable;
import java.util.List;

import com.autotestplatform.dto.common.PageDto;
import com.autotestplatform.dto.common.ScriptDto;

public class ScriptListOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 8588314336698507377L;

    private Integer           useCaseId;

    private List<ScriptDto>   scriptList;

    private PageDto           pageDto;

    public ScriptListOutDto() {
        super();
    }

    public ScriptListOutDto(Integer useCaseId, List<ScriptDto> scriptList, PageDto pageDto) {
        super();
        this.useCaseId = useCaseId;
        this.scriptList = scriptList;
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

    public List<ScriptDto> getScriptList() {
        return scriptList;
    }

    public void setScriptList(List<ScriptDto> scriptList) {
        this.scriptList = scriptList;
    }

}
