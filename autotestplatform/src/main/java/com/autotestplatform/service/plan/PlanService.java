package com.autotestplatform.service.plan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autotestplatform.dao.PlanMapper;
import com.autotestplatform.dao.PlanResultMapper;
import com.autotestplatform.dao.ProjectMapper;
import com.autotestplatform.dao.SysLogMapper;
import com.autotestplatform.dao.UseCaseMapper;
import com.autotestplatform.dao.UseCasePlanRelationMapper;
import com.autotestplatform.dto.common.PageDto;
import com.autotestplatform.dto.common.UseCasePlanRelationDto;
import com.autotestplatform.dto.plan.delete.PlanDeleteInDto;
import com.autotestplatform.dto.plan.detail.PlanDetailInDto;
import com.autotestplatform.dto.plan.detail.PlanDetailOutDto;
import com.autotestplatform.dto.plan.detail.PlanUseCaseListDetailDto;
import com.autotestplatform.dto.plan.insert.PlanInsertInDto;
import com.autotestplatform.dto.plan.list.PlanListDetailDto;
import com.autotestplatform.dto.plan.list.PlanListInDto;
import com.autotestplatform.dto.plan.list.PlanListOutDto;
import com.autotestplatform.dto.plan.result.ExcResult;
import com.autotestplatform.dto.plan.result.PlanResultInDto;
import com.autotestplatform.dto.plan.result.PlanResultOutDto;
import com.autotestplatform.dto.plan.result.SingleCaseDto;
import com.autotestplatform.dto.plan.update.PlanUpdateInDto;
import com.autotestplatform.entity.Plan;
import com.autotestplatform.entity.PlanResult;
import com.autotestplatform.entity.SysLog;
import com.autotestplatform.entity.UseCasePlanRelation;
import com.autotestplatform.entity.User;
import com.autotestplatform.service.base.BaseService;
import com.autotestplatform.utils.StringUtils;

/**
 * 
 * 执行计划服务
 * @author : 张文博
 * @date : 2018年5月21日 上午10:39:38  
 * @version : 2018年5月21日  张文博 TODO简要描述修改内容，修改原因
 */
@Service
public class PlanService extends BaseService {

    @Autowired
    private PlanMapper                planDao;

    @Autowired
    private ProjectMapper             projectDao;

    @Autowired
    private UseCaseMapper             useCaseDao;
    
    @Autowired
    private SysLogMapper             sysLogDao;
    
    @Autowired
    private PlanResultMapper            planResultDao;

    @Autowired
    private UseCasePlanRelationMapper useCasePlanRelationDao;

    @Transactional
    public PlanListOutDto getPlanList(PlanListInDto planListInDto) {
        //		init
        PlanListOutDto planListOutDto = new PlanListOutDto();
        List<PlanListDetailDto> planList = new ArrayList<>();
        Map<String, Object> selectMap = new HashMap<String, Object>();
        //		search
        selectMap.put("planName",
                StringUtils.isEmpty(planListInDto.getPlanName()) ? null : planListInDto.getPlanName());
        selectMap.put("planType", planListInDto.getPlanType());
        //		Integer planId = planListInDto.getPlanId();
        selectMap.put("pageNow", planListInDto.getPageDto().getLimitPageNow());
        selectMap.put("pageSize", planListInDto.getPageDto().getPageSize());
        planList = planDao.selectPlanList(selectMap);
        Integer count = planDao.selectPlanListCount(selectMap);
        //		make outDto
        planListOutDto.setPlanListDetailDtoList(planList);
        planListOutDto.setPageDto(
                new PageDto(planListInDto.getPageDto().getPageNow(), planListInDto.getPageDto().getPageSize(), count));
        return planListOutDto;
    }

    /**
     * 
     * @Description：获取计划详情
     * @param planDetailInDto
     * @return: 返回结果描述
     * @return PlanDetailOutDto: 返回值类型
     * @throws
     */
    public PlanDetailOutDto getPlanDetail(PlanDetailInDto planDetailInDto) {
        //		init
        PlanDetailOutDto planDetailOutDto = new PlanDetailOutDto();
        //		 select
        Integer planId = planDetailInDto.getPlanId();
        Plan plan = planDao.selectByPrimaryKey(planId);
        //		makedto
        planDetailOutDto.setProjectIdNameList(projectDao.selectAllIdAndName());
        planDetailOutDto.setPlanUseCaseList(useCaseDao.selectUseCaseDetailOutDtoListByPlanId(planId));
        planDetailOutDto.setPlanId(planId);
        planDetailOutDto.setPlanName(plan.getPlanName());
        planDetailOutDto.setPlanIntro(plan.getPlanIntro());
        planDetailOutDto.setPlanType(plan.getPlanType());
        planDetailOutDto.setPlanStatus(plan.getPlanStatus());
        planDetailOutDto.setPlanStartTime(plan.getPlanStartTime());
        planDetailOutDto.setPlanSumTimes(plan.getPlanSumTimes());
        planDetailOutDto.setPlanInterval(plan.getPlanInterval());
        return planDetailOutDto;
    }

    /**
     * 
     * @Description：追加计划
     * @param planInsertInDto
     * @return: 返回结果描述
     * @return PlanDetailOutDto: 返回值类型
     * @throws
     */
    @Transactional
    public String insertPlan(PlanInsertInDto planInsertInDto) {
        //init
        Integer planId = null;
        Plan plan = new Plan();
        PlanDetailInDto planDetailInDto = new PlanDetailInDto();
        //        PlanDetailOutDto planDetailOutDto = new PlanDetailOutDto();
        User loginUser = (User) getSession().getAttribute("loginUser");
        //insert data 		
        plan.setPlanName(planInsertInDto.getPlanDto().getPlanName());
        plan.setPlanIntro(planInsertInDto.getPlanDto().getPlanIntro());
        plan.setPlanType(planInsertInDto.getPlanDto().getPlanType());
        plan.setPlanSumTimes(planInsertInDto.getPlanDto().getPlanSumTimes());
        plan.setPlanInterval(planInsertInDto.getPlanDto().getPlanInterval());
        plan.setPlanStatus(0);
        plan.setPlanStartTime(new Date());
        plan.setCreateTime(new Date());
        plan.setCreateUserId(loginUser.getUserId());
        plan.setUpdateTime(new Date());
        plan.setUpdateUserId(loginUser.getUserId());
        planDao.insert(plan);
        //return dto		
        //        planDetailInDto.setPlanId(planId);
        //        planDetailOutDto = getPlanDetail(planDetailInDto);
        return "success";
    }

    /**
     * 
     * @Description：逻辑删除计划
     * @param planDeleteInDto: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    @Transactional
    public void deletePlan(PlanDeleteInDto planDeleteInDto) {
        //init	
        Plan plan = null;
        List<Integer> planIdList = planDeleteInDto.getPlanIdList();
        User loginUser = (User) getSession().getAttribute("loginUser");
        //delete
        for (Integer deletePlanId : planIdList) {
            plan = planDao.selectByPrimaryKey(deletePlanId);
            if (plan.getPlanStatus() == 1) {
                //正在执行的计划不删除
                continue;
            }
            plan.setPlanId(deletePlanId);
            plan.setPlanStatus(-1);
            plan.setUpdateUserId(loginUser.getUserId());
            plan.setUpdateTime(new Date());
            planDao.updateByPrimaryKeySelective(plan);
        }

    }

    /**
     * @Description：增加用例计划关系
     * @param useCasePlanRelationDto: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    public void insertUseCasePlanRelation(UseCasePlanRelationDto useCasePlanRelationDto) {
        UseCasePlanRelation upr = new UseCasePlanRelation();
        upr.setPlanId(useCasePlanRelationDto.getPlanId());
        upr.setUseCaseId(useCasePlanRelationDto.getUseCaseId());
        upr.setPlanStatus(0);//0:运行完成或未运行
        useCasePlanRelationDao.insertSelective(upr);
    }

    /**
     * @Description：刷新用例计划关系
     * @param planId
     * @return void: 返回值类型
     * @throws
     */
    public List<PlanUseCaseListDetailDto> refreshUseCasePlanRelation(Integer planId) {
        return useCaseDao.selectUseCaseDetailOutDtoListByPlanId(planId);
    }

    /**
     * @Description：修改
     * @param planUpdateInDto
     * @return: 返回结果描述
     * @return PlanListOutDto: 返回值类型
     * @throws
     */
    public void updatePlan(PlanUpdateInDto planUpdateInDto) {
        //init      
        Plan plan = new Plan();
        User loginUser = (User) getSession().getAttribute("loginUser");
        //update data
        plan.setPlanId(planUpdateInDto.getPlanDto().getPlanId());
        plan.setPlanName(planUpdateInDto.getPlanDto().getPlanName());
        plan.setPlanIntro(planUpdateInDto.getPlanDto().getPlanIntro());
        plan.setPlanType(planUpdateInDto.getPlanDto().getPlanType());
        plan.setPlanStatus(planUpdateInDto.getPlanDto().getPlanStatus());
        plan.setUpdateTime(new Date());
        plan.setUpdateUserId(loginUser.getUserId());
        planDao.updateByPrimaryKeySelective(plan);
    }

    /**
     * @Description：删除用例计划关系
     * @param useCasePlanRelationDto: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    public void deleteUseCasePlanRelation(UseCasePlanRelationDto useCasePlanRelationDto) {
        useCasePlanRelationDao.deletePlanIdAndUseCaseId(useCasePlanRelationDto.getUseCasePlanRelationId());
    }
    
    /**
     * @Description：获取执行结果
     * @param planResultInDto: 返回结果描述
     * @return planResultOutDto: 返回值类型
     * @throws
     */
    public PlanResultOutDto getPlanResult(PlanResultInDto inDto) {
    	//init
    	List<Integer> historyAllResult = new ArrayList<Integer>();
    	List<Integer> historyErrResult = new ArrayList<Integer>();
    	List<SingleCaseDto> logList = null;
    	PlanResultOutDto res = new PlanResultOutDto();
    	List<SysLog> sysLogList = null;
    	ExcResult excRes = null;
    	SingleCaseDto scd = null;
    	List<ExcResult> excResList = new ArrayList<ExcResult>();
    	//set
    	List<PlanResult> planResList = planResultDao.selectAllPlanResultList(inDto.getPlanId());
    	res.setExcNum(planResList.size());
    	for(PlanResult pr:planResList) {
    		sysLogList = sysLogDao.selectByPlanIdAndPlanResultId(inDto.getPlanId(), pr.getPlanResultId());
    		excRes = new ExcResult();
    		excRes.setErrUseCase(0);
    		excRes.setSumUseCase(0);
    		if(pr.getPlanResultStatus()==0) {
    			//正在执行中
    			excRes.setStatus("r");
    		}
    		logList = new ArrayList<SingleCaseDto>();
    		for(SysLog sl:sysLogList) {
    			scd = new SingleCaseDto();
    			scd.setLogId(sl.getSysLogId());
    			scd.setLogName(sl.getLogName().split("-")[2].replaceAll("+++", "\r\n"));//失败的案例信息
    			scd.setUseCaseId(sl.getUseCaseId());
    			scd.setUseCaseName(useCaseDao.selectByPrimaryKey(sl.getUseCaseId()).getUseCaseName());
    			scd.setLogUrl(sl.getLogUrl());
    			scd.setLogTime(sl.getCreateTime());
    			try {
    				scd.setSumExample(Integer.parseInt(sl.getLogName().split("-")[1].trim()));
    			} catch (Exception e) {
    				scd.setSumExample(0);
    			}
    			try {
    				scd.setErrExample(Integer.parseInt(sl.getLogName().split("-")[0].trim()));
    			} catch (Exception e) {
    				scd.setErrExample(0);
    			}
    			logList.add(scd);
    			excRes.setErrUseCase(excRes.getErrUseCase() + scd.getErrExample());
    			excRes.setSumUseCase(excRes.getSumUseCase() + scd.getSumExample());
    		}
    		excRes.setCaseList(logList);
    		excResList.add(excRes);
    		historyAllResult.add(excRes.getSumUseCase());
    		historyErrResult.add(excRes.getErrUseCase());
    	}
    	Collections.reverse(historyAllResult);
    	Collections.reverse(historyErrResult);
    	res.setHistoryAllResult(historyAllResult);
    	res.setHistoryErrResult(historyErrResult);
    	res.setResultList(excResList);
    	//return
    	return res;
    }

}
