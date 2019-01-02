package com.autotestplatform.dto.plan.result;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SingleCaseDto implements Serializable{

    /** 
    * @Fields serialVersionUID  
    */
    private static final long serialVersionUID = 1L;
	//用例中案例总数
    private Integer sumExample;
	//失败案例数
	private Integer errExample;
	//日志地址
	private String logUrl;
	//syslog的id
	private Integer logId;
	//错误案例信息
	private List<String> errMes;
	//useCase的id
	private Integer useCaseId;
	//useCase名称
	private String useCaseName;
	//日志信息 失败数-总数-失败信息
	private String logName;
	//日志生成时间
	private Date logTime;
	
	public SingleCaseDto(Integer sumExample, Integer errExample, String logUrl, Integer logId, List<String> errMes,
			Integer useCaseId, String useCaseName, String logName, Date logTime) {
		super();
		this.sumExample = sumExample;
		this.errExample = errExample;
		this.logUrl = logUrl;
		this.logId = logId;
		this.errMes = errMes;
		this.useCaseId = useCaseId;
		this.useCaseName = useCaseName;
		this.logName = logName;
		this.logTime = logTime;
	}
	public SingleCaseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getSumExample() {
		return sumExample;
	}
	public void setSumExample(Integer sumExample) {
		this.sumExample = sumExample;
	}
	public Integer getErrExample() {
		return errExample;
	}
	public void setErrExample(Integer errExample) {
		this.errExample = errExample;
	}
	public String getLogUrl() {
		return logUrl;
	}
	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public List<String> getErrMes() {
		return errMes;
	}
	public void setErrMes(List<String> errMes) {
		this.errMes = errMes;
	}
	public Integer getUseCaseId() {
		return useCaseId;
	}
	public void setUseCaseId(Integer useCaseId) {
		this.useCaseId = useCaseId;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getUseCaseName() {
		return useCaseName;
	}
	public void setUseCaseName(String useCaseName) {
		this.useCaseName = useCaseName;
	}
	
}
