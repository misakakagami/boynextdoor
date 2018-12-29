package com.autotestplatform.dto.plan.result;

import java.io.Serializable;

public class DownloadLog implements Serializable {

    /** 
    * @Fields serialVersionUID 
    */
    private static final long serialVersionUID = 3095228830734052911L;

    private Integer           logId;

	public DownloadLog(Integer logId) {
		super();
		this.logId = logId;
	}

	public DownloadLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

}
