package com.autotestplatform.dto.pymanage;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -6758464027583753316L;

    private MultipartFile     file;

    private String            path;

    public UploadFileDto(MultipartFile file, String path) {
        super();
        this.file = file;
        this.path = path;
    }

    public UploadFileDto() {
        super();
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
