package com.autotestplatform.dto.plan.detail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.autotestplatform.dto.common.PageDto;
import com.autotestplatform.dto.common.ProjectIdNameDto;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月15日 下午7:01:13  
 * @version : 2018年5月15日  张文博 TODO简要描述修改内容，修改原因
 */
public class PlanDetailOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID  
    */

    private static final long              serialVersionUID = 1L;

    private Integer                        planId;

    private String                         planName;

    private String                         planIntro;

    private Integer                        planType;

    private Integer                        planStatus;

    private Date                           planStartTime;

    private Integer                        planSumTimes;

    private Integer                        planInterval;

    private List<ProjectIdNameDto>         projectIdNameList;

    private List<PlanUseCaseListDetailDto> planUseCaseList;

    private PageDto                        pageDto;

    public PlanDetailOutDto() {
        super();
    }

    public PlanDetailOutDto(Integer planId, String planName, String planIntro, Integer planType, Integer planStatus,
                            Date planStartTime, Integer planSumTimes, Integer planInterval,
                            List<ProjectIdNameDto> projectIdNameList, List<PlanUseCaseListDetailDto> planUseCaseList,
                            PageDto pageDto) {
        super();
        this.planId = planId;
        this.planName = planName;
        this.planIntro = planIntro;
        this.planType = planType;
        this.planStatus = planStatus;
        this.planStartTime = planStartTime;
        this.planSumTimes = planSumTimes;
        this.planInterval = planInterval;
        this.projectIdNameList = projectIdNameList;
        this.planUseCaseList = planUseCaseList;
        this.pageDto = pageDto;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanIntro() {
        return planIntro;
    }

    public void setPlanIntro(String planIntro) {
        this.planIntro = planIntro;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
    }

    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Integer getPlanSumTimes() {
        return planSumTimes;
    }

    public void setPlanSumTimes(Integer planSumTimes) {
        this.planSumTimes = planSumTimes;
    }

    public Integer getPlanInterval() {
        return planInterval;
    }

    public void setPlanInterval(Integer planInterval) {
        this.planInterval = planInterval;
    }

    public List<ProjectIdNameDto> getProjectIdNameList() {
        return projectIdNameList;
    }

    public void setProjectIdNameList(List<ProjectIdNameDto> projectIdNameList) {
        this.projectIdNameList = projectIdNameList;
    }

    public List<PlanUseCaseListDetailDto> getPlanUseCaseList() {
        return planUseCaseList;
    }

    public void setPlanUseCaseList(List<PlanUseCaseListDetailDto> planUseCaseList) {
        this.planUseCaseList = planUseCaseList;
    }

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }

}
