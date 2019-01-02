package com.autotestplatform.facade.usecase.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
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
 * 定时执行
 * @author : 孔德华
 * @date : 2018年5月23日 上午10:17:44  
 * @version : 2018年5月23日  
 */
@Service
@Component
public class RunPlanForScheduler extends RunScriptClass implements PlanRunInstance {

    @Autowired
    private PlanMapper                planDao;

    @Autowired
    private SysLogMapper              syslogDao;

    @Autowired
    private UseCaseMapper             useCaseDao;

    @Autowired
    private UseCasePlanRelationMapper useCasePlanRelationDao;

    @Autowired
    private ThreadPoolTaskScheduler   threadPoolTaskScheduler;

    @Autowired
    private PlanResultMapper          planResultDao;

    private ScheduledFuture<?>        future;

    private Integer                   planId;
    /*
     * 计划最后修改时间，用于每次执行前判断计划是否在此间隔期间修改过。如果修改过则停止本定时器
     */
    private Date                      planUpdateTime;

    private String                    model;

    /**
     * @Description：开始计划
     * @param time
     * @return: 返回结果描述
     * @return String: 返回值类型
     * @throws
     */
    @Override
    public void startCron(Integer planId) {
        //init
        Plan plan = planDao.selectByPrimaryKey(planId);
        this.planId = planId;
        this.planUpdateTime = plan.getUpdateTime();
        Date date = new Date();
        String nowMin = new SimpleDateFormat("mm").format(date);
        String cornStr;
        switch (plan.getPlanInterval()) {
            case 0:
                //1分钟
                cornStr = "0 " + nowMin + "/1 * * * ?";
                break;
            case 1:
                //5分钟
                cornStr = "0 " + nowMin + "/5 * * * ?";
                break;
            case 2:
                //10分钟
                cornStr = "0 " + nowMin + "10 * * * ?";
                break;
            case 3:
                //30分钟
                cornStr = "0 " + nowMin + "/30 * * * ?";
                break;
            case 4:
                //1小时
                cornStr = "0 " + nowMin + " 0/1 * * ?";
                break;
            case 5:
                //12小时
                cornStr = "0 " + nowMin + " 12,23 * * ?";
                break;
            case 6:
                //1天
                cornStr = "0 " + nowMin + " 12 * * ?";
                break;
            case 7:
                //1周(周一)
                cornStr = "0 " + nowMin + " 12 ? * MON";
                break;
            case 8:
                //1月
                cornStr = "0 " + nowMin + " 12 L * ?";
                break;

            default:
                //每天12点
                cornStr = "0 " + nowMin + " 12 * * ?";
                break;
        }
        //execute
        future = threadPoolTaskScheduler.schedule(new ExecutePlan(), new CronTrigger(cornStr));
    }

    /**
     * @Description：停止计划
     * @return: 返回结果描述
     * @return String: 返回值类型
     * @throws
     */
    @Override
    public void stopCron() {
        if (future != null) {
            future.cancel(true);
        }
    }

    private void loadPlan(Integer planId) {
        //定时计划每次执行前检查计划是否已被修改、计划状态以决定定时器是否关闭
        Plan plan = planDao.selectByPrimaryKey(planId);
        if (plan.getUpdateTime().equals(planUpdateTime) && plan.getPlanStatus() == 0) {
            this.stopCron();
            return;
        }
        //重置关系表状态，每个用例执行完成后修改状态，所有关系的状态改变后视为计划执行完成
        List<UseCasePlanRelation> ucprList = useCasePlanRelationDao.selectListByPlanId(planId);
        for (UseCasePlanRelation ucpr : ucprList) {
            ucpr.setPlanStatus(1);
            useCasePlanRelationDao.updateByPrimaryKeySelective(ucpr);
        }
        //新建本轮的计划执行结果对象
        PlanResult planResult = new PlanResult();
        planResult.setCreateTime(new Date());
        planResult.setSumNum(ucprList.size());
        planResult.setPlanId(planId);
        planResult.setPlanResultStatus(0);
        planResult.setSuccessNum(0);
        planResult.setWarnFlag("定时执行计划-");
        Integer planResultId = planResultDao.insert(planResult);
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
                        //日志
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
                        //执行结果
                        if (t.getExitVal() != 0) {
                            //失败
                            planResult.setWarnFlag(planResult.getWarnFlag() + "用例" + uc.getUseCaseName() + "有失败场景；");
                        } else {
                            //成功
                            planResult.setSuccessNum(planResult.getSuccessNum() + 1);//
                        }
                        //完成后修改状态
                        UseCasePlanRelation ucpr = useCasePlanRelationDao.selectByPlanIdAndUseCaseId(planId,
                                uc.getUseCaseId());
                        ucpr.setPlanStatus(0);
                        useCasePlanRelationDao.updateByPrimaryKeySelective(ucpr);
                    }
                }
            });
        }
    }

    private CaseResult runPlan(String scriptUrl) {
        return runScript(scriptUrl);
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 内部类，执行计划
     * @author : 孔德华
     * @date : 2018年5月23日 上午11:06:57  
     * @version : 2018年5月23日 
     */
    private class ExecutePlan implements Runnable {
        @Override
        public void run() {
            loadPlan(planId);
        }
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
