package com.autotestplatform.vo;

import java.io.Serializable;

public class LogNameAndUrl implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 3703337980849153870L;

    private String            logName;

    private String            logUrl;

    

    public LogNameAndUrl(String logName, String logUrl) {
		super();
		this.logName = logName;
		this.logUrl = logUrl;
	}

	public LogNameAndUrl() {
        super();
    }

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogUrl() {
		return logUrl;
	}

	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}
}

