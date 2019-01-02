package com.autotestplatform.dto.plan.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlanResultOutDto implements Serializable {
    /** 
    * @Fields serialVersionUID  
    */
    private static final long serialVersionUID = 1L;
	//历史本计划用例总数
    private List<NumDto> historyResult = new ArrayList<NumDto>();
    private Integer topNum;
	//总执行次数
    private Integer excNum = 0;
    //各用例结果
	private List<ExcResult> resultList;
	
	public PlanResultOutDto(List<NumDto> historyResult, Integer topNum, Integer excNum, List<ExcResult> resultList) {
		super();
		this.historyResult = historyResult;
		this.topNum = topNum;
		this.excNum = excNum;
		this.resultList = resultList;
	}
	public PlanResultOutDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<NumDto> getHistoryResult() {
		return historyResult;
	}
	public void setHistoryResult(List<NumDto> historyResult) {
		this.historyResult = historyResult;
	}
	public Integer getExcNum() {
		return excNum;
	}
	public void setExcNum(Integer excNum) {
		this.excNum = excNum;
	}
	public List<ExcResult> getResultList() {
		return resultList;
	}
	public void setResultList(List<ExcResult> resultList) {
		this.resultList = resultList;
	}
	public Integer getTopNum() {
		return topNum;
	}
	public void setTopNum(Integer topNum) {
		this.topNum = topNum;
	}
	


}
