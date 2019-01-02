package com.autotestplatform.vo;

import java.io.Serializable;

public class CaseResult implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 4412957378561814575L;
    private Integer           exitVal;
    private String            logUrl;

    public CaseResult(Integer exitVal, String logUrl) {
        super();
        this.exitVal = exitVal;
        this.logUrl = logUrl;
    }

    public CaseResult() {
        super();
    }

    public Integer getExitVal() {
        return exitVal;
    }

    public void setExitVal(Integer exitVal) {
        this.exitVal = exitVal;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

}
