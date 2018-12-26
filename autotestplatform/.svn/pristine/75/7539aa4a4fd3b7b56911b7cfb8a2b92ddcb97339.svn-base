package com.autotestplatform.facade.usecase.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autotestplatform.dao.PlanMapper;
import com.autotestplatform.dao.PlanResultMapper;
import com.autotestplatform.dao.SysLogMapper;
import com.autotestplatform.dao.UseCaseMapper;
import com.autotestplatform.dao.UseCasePlanRelationMapper;
import com.autotestplatform.entity.Plan;
import com.autotestplatform.entity.PlanResult;
import com.autotestplatform.entity.SysLog;
import com.autotestplatform.entity.UseCase;
import com.autotestplatform.entity.UseCasePlanRelation;
import com.autotestplatform.facade.usecase.PlanRunInstance;
import com.autotestplatform.utils.StringUtils;
import com.autotestplatform.vo.CaseResult;

/**
 * 单次执行
 * @author : 孔德华
 * @date : 2018年5月23日 上午10:17:27  
 * @version : 2018年5月23日 
 */
@Service
public class RunPlanForOneTime extends RunScriptClass implements PlanRunInstance {

    @Autowired
    private PlanMapper                planDao;

    @Autowired
    private SysLogMapper              syslogDao;

    @Autowired
    private UseCaseMapper             useCaseDao;

    @Autowired
    private UseCasePlanRelationMapper useCasePlanRelationDao;

    @Autowired
    private PlanResultMapper          planResultDao;

    private String                    model;

    @Override
    public void startCron(Integer planId) {
        loadPlan(planId);
    }

    @Override
    public void stopCron() {
        //无
    }

    private void loadPlan(Integer planId) {
        //重置关系表状态，每个用例执行完成后修改状态，所有关系的状态改变后视为计划执行完成
        List<UseCasePlanRelation> ucprList = useCasePlanRelationDao.selectListByPlanId(planId);
        for (UseCasePlanRelation ucpr : ucprList) {
            ucpr.setPlanStatus(1);
            useCasePlanRelationDao.updateByPrimaryKeySelective(ucpr);
        }
        List<UseCase> useCaseList = useCaseDao.selectUseCaseEntityList(planId);
        for (UseCase uc : useCaseList) {
            //执行脚本(异步)
            ExecutorService executor = Executors.newCachedThreadPool();
            CompletableFuture<CaseResult> future = CompletableFuture.supplyAsync(new Supplier<CaseResult>() {
                @Override
                public CaseResult get() {
                    CaseResult caseResult = runPlan(uc.getUseCaseScriptUrl());
                    return caseResult;
                }
            }, executor);
            //执行完成(异步)
            future.thenAccept(new Consumer<CaseResult>() {
                @Override
                public void accept(CaseResult t) {
                    //回调
                    if (!StringUtils.isEmpty(t.getLogUrl())) {
                        //计划执行结果
                        PlanResult planResult = new PlanResult();
                        planResult.setCreateTime(new Date());
                        planResult.setSumNum(1);
                        planResult.setPlanId(planId);
                        planResult.setPlanResultStatus(0);
                        planResult.setSuccessNum(0);
                        planResult.setWarnFlag("1");
                        planResultDao.insert(planResult);
                        Integer planResultId = planResult.getPlanResultId();
                        //日志记录
                        SysLog log = new SysLog();
                        log.setPlanResultId(planResultId);
                        log.setUseCaseId(uc.getUseCaseId());
                        log.setLogName(uc.getUseCaseName());
                        log.setLogStatus(0);
                        log.setLogUrl(t.getLogUrl());
                        log.setLogType(0);
                        log.setPlanId(planId);
                        log.setCreateTime(new Date());
                        log.setUpdateTime(new Date());
                        syslogDao.insert(log);
                        //完成后修改状态
                        UseCasePlanRelation ucpr = useCasePlanRelationDao.selectByPlanIdAndUseCaseId(planId,
                                uc.getUseCaseId());
                        ucpr.setPlanStatus(0);
                        useCasePlanRelationDao.updateByPrimaryKeySelective(ucpr);
                        //检查计划中所有用例执行情况
                        List<UseCasePlanRelation> checkLcprList = useCasePlanRelationDao.selectListByPlanId(planId);
                        boolean completeFlag = true;
                        for (UseCasePlanRelation checkUcpr : checkLcprList) {
                            if (checkUcpr.getPlanStatus() == 1) {
                                completeFlag = false;
                                break;
                            }
                        }
                        if (completeFlag) {
                            //全部执行完成
                            Plan plan = planDao.selectByPrimaryKey(planId);
                            plan.setPlanStatus(0);
                            planDao.updateByPrimaryKeySelective(plan);
                            planResult.setPlanResultId(planResultId);
                            planResult.setPlanResultName(plan.getPlanName() + "-单次执行结果-"
                                    + new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()));
                            //                            planResult.setPlanResultLogUrl("");---总结日志，待开发
                            planResult.setPlanResultStatus(1);//待修改
                            planResult.setSuccessNum(1);//待修改
                            planResult.setWarnFlag("2");//待修改
                            planResultDao.updateSelective(planResult);
                        }
                    }
                }
            });
        }
    }

    private CaseResult runPlan(String scriptUrl) {
        return runScript(scriptUrl);
    }

    /**
     * @Description：spring注入方法
     * @param model: single scheduler
     * @return void: 返回值类型
     * @throws
     */
    public void setModel(String model) {
        this.model = model;
    }
}
