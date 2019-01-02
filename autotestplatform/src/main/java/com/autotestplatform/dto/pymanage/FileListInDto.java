package com.autotestplatform.dto.pymanage;

import java.io.Serializable;

public class FileListInDto implements Serializable {

    /** 
    * @Fields serialVersionUID 
    */
    private static final long serialVersionUID = -1292108740913960114L;

    private String            path;

    public FileListInDto(String path) {
        super();
        this.path = path;
    }

    public FileListInDto() {
        super();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
