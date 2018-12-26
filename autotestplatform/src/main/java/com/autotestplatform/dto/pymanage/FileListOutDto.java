package com.autotestplatform.dto.pymanage;

import java.io.Serializable;
import java.util.List;

import com.autotestplatform.vo.FileMsg;

public class FileListOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = -1041040472479476352L;

    private List<FileMsg>     fileList;

    private String            nowPath;

    private String            lastPath;

    public FileListOutDto(List<FileMsg> fileList, String nowPath, String lastPath) {
        super();
        this.fileList = fileList;
        this.nowPath = nowPath;
        this.lastPath = lastPath;
    }

    public FileListOutDto() {
        super();
    }

    public String getNowPath() {
        return nowPath;
    }

    public void setNowPath(String nowPath) {
        this.nowPath = nowPath;
    }

    public String getLastPath() {
        return lastPath;
    }

    public void setLastPath(String lastPath) {
        this.lastPath = lastPath;
    }

    public List<FileMsg> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileMsg> fileList) {
        this.fileList = fileList;
    }

}
