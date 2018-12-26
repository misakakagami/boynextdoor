package com.autotestplatform.facade.usecase;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autotestplatform.dao.PlanMapper;
import com.autotestplatform.dto.plan.ajax.RunPlanInDto;
import com.autotestplatform.entity.Plan;
import com.autotestplatform.entity.User;
import com.autotestplatform.facade.base.BaseFacade;

/**
 * 执行计划
 * @author : 孔德华
 * @date : 2018年5月22日 下午2:50:39  
 * @version : 2018年5月22日  
 */
@Service
public class PlanFacade extends BaseFacade {

    @Autowired
    private PlanMapper             planDao;

    @Autowired
    private PlanRunInstanceFactory planRunInstanceFactory;

    /**
     * 
     * @Description：修改计划(执行、停止)
     * @param planUpdateInDto
     * @return: 返回结果描述
     * @return PlanListOutDto: 返回值类型
     * @throws
     */
    //    @Transactional
    public void updatePlan(RunPlanInDto runPlanInDto) {
        //init      
        Plan plan = planDao.selectByPrimaryKey(runPlanInDto.getPlanId());
        User loginUser = (User) getSession().getAttribute("loginUser");
        //update data
        plan.setPlanStatus(runPlanInDto.getStatus());
        plan.setUpdateTime(new Date());
        plan.setUpdateUserId(loginUser.getUserId());
        planDao.updateByPrimaryKeySelective(plan);

        //update run status
        new PlanRunInstanceFactory();
        //type=0:点击执行;type=1:定时执行;status=0:停止;status=1:执行中;
        if (plan.getPlanStatus() == 0) {
            // status=0,stop
            //通过设置计划状态为0、修改时间（版本号）以使plan变更，下次执行前检查plan时定时器就会中止
        } else if (plan.getPlanType() == 1 && plan.getPlanStatus() == 1) {
            // type=1 && status=1,run plan sum times
            planRunInstanceFactory.getInstance("scheduler").startCron(plan.getPlanId());
        } else if (plan.getPlanType() == 0 && plan.getPlanStatus() == 1) {
            // type=0 && status=1,run one time
            planRunInstanceFactory.getInstance("single").startCron(plan.getPlanId());
        }

    }
}
