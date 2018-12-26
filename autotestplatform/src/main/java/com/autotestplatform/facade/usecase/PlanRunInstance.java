package com.autotestplatform.facade.usecase;

/**
 * 计划执行实例的接口
 * @author : 孔德华
 * @date : 2018年5月22日 下午3:02:48  
 * @version : 2018年5月22日 
 */
public interface PlanRunInstance {

    /**
     * @Description：执行计划
     * @param planId
     * @return: 返回结果描述
     * @return String: 返回值类型
     * @throws
     */
    void startCron(Integer planId);

    /**
     * @Description：停止计划
     * @throws
     */
    void stopCron();
}
