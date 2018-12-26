package com.autotestplatform.facade.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autotestplatform.facade.usecase.impl.RunPlanForOneTime;
import com.autotestplatform.facade.usecase.impl.RunPlanForScheduler;

@Service
public class PlanRunInstanceFactory {

    @Autowired
    private RunPlanForOneTime   planRun1;

    @Autowired
    private RunPlanForScheduler planRun2;

    /**
     * @Description：获得执行实例
     * @param runType-single:执行一次;scheduler:定期执行
     * @return: 返回结果描述
     * @return PlanRunInstance: 返回值类型
     * @throws
     */
    public PlanRunInstance getInstance(String runType) {
        if (runType == null) {
            return null;
        }
        if ("single".equals(runType)) {
            return planRun1;
        } else if ("scheduler".equals(runType)) {
            return planRun2;
        }
        return null;
    }

}
