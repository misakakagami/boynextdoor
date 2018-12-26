package com.autotestplatform.dto.useCase.uploadscript;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UploadScriptInDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -3205934291934813932L;
    private MultipartFile     scriptFile;
    private Integer           useCaseId;

    public UploadScriptInDto(MultipartFile scriptFile, Integer useCaseId) {
        super();
        this.scriptFile = scriptFile;
        this.useCaseId = useCaseId;
    }

    public UploadScriptInDto() {
        super();
    }

    public MultipartFile getScriptFile() {
        return scriptFile;
    }

    public void setScriptFile(MultipartFile scriptFile) {
        this.scriptFile = scriptFile;
    }

    public Integer getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Integer useCaseId) {
        this.useCaseId = useCaseId;
    }

}
