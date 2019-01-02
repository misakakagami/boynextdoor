package com.autotestplatform.vo;

import java.io.Serializable;

public class FileMsg implements Serializable {

    /** 
    * @Fields serialVersionUID 
    */
    private static final long serialVersionUID = -102068730665934251L;

    private String            path;

    private String            fileName;

    private String            fileType;

    public FileMsg(String path, String fileName, String fileType) {
        super();
        this.path = path;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public FileMsg() {
        super();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
