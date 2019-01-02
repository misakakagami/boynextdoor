package com.autotestplatform.dto.useCase.uploadexample;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UploadExampleInDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -3205934291934813932L;
    private MultipartFile     exampleFile;
    private Integer           useCaseId;

    public UploadExampleInDto() {
        super();
    }

    public UploadExampleInDto(MultipartFile exampleFile, Integer useCaseId) {
        super();
        this.exampleFile = exampleFile;
        this.useCaseId = useCaseId;
    }

    public MultipartFile getExampleFile() {
        return exampleFile;
    }

    public void setExampleFile(MultipartFile exampleFile) {
        this.exampleFile = exampleFile;
    }

    public Integer getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Integer useCaseId) {
        this.useCaseId = useCaseId;
    }

}
