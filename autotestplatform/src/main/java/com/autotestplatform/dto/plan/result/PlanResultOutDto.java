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
    private List<Integer> historyAllResult = new ArrayList<Integer>();
	//历史本计划错误数
    private List<Integer> historyErrResult = new ArrayList<Integer>();
	//总执行次数
    private Integer excNum = 0;
    //各用例结果
	private List<ExcResult> resultList;
	


	public PlanResultOutDto(List<Integer> historyAllResult, List<Integer> historyErrResult, Integer excNum,
			List<ExcResult> resultList) {
		super();
		this.historyAllResult = historyAllResult;
		this.historyErrResult = historyErrResult;
		this.excNum = excNum;
		this.resultList = resultList;
	}

	public PlanResultOutDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Integer> getHistoryAllResult() {
		return historyAllResult;
	}

	public void setHistoryAllResult(List<Integer> historyAllResult) {
		this.historyAllResult = historyAllResult;
	}

	public List<Integer> getHistoryErrResult() {
		return historyErrResult;
	}

	public void setHistoryErrResult(List<Integer> historyErrResult) {
		this.historyErrResult = historyErrResult;
	}

	public List<ExcResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<ExcResult> resultList) {
		this.resultList = resultList;
	}

	public Integer getExcNum() {
		return excNum;
	}

	public void setExcNum(Integer excNum) {
		this.excNum = excNum;
	}

	
}
