package com.autotestplatform.controller.plan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.autotestplatform.contants.Contants;
import com.autotestplatform.controller.base.BaseController;
import com.autotestplatform.dto.common.PageDto;
import com.autotestplatform.dto.common.UseCasePlanRelationDto;
import com.autotestplatform.dto.plan.ajax.RunPlanInDto;
import com.autotestplatform.dto.plan.delete.PlanDeleteInDto;
import com.autotestplatform.dto.plan.detail.PlanDetailInDto;
import com.autotestplatform.dto.plan.detail.PlanDetailOutDto;
import com.autotestplatform.dto.plan.detail.PlanUseCaseListDetailDto;
import com.autotestplatform.dto.plan.insert.PlanInsertInDto;
import com.autotestplatform.dto.plan.list.PlanListInDto;
import com.autotestplatform.dto.plan.list.PlanListOutDto;
import com.autotestplatform.dto.plan.result.DownloadLog;
import com.autotestplatform.dto.plan.result.PlanResultInDto;
import com.autotestplatform.dto.plan.result.PlanResultOutDto;
import com.autotestplatform.dto.plan.update.PlanUpdateInDto;
import com.autotestplatform.dto.useCase.script.DownloadScript;
import com.autotestplatform.dto.useCase.script.ScriptListInDto;
import com.autotestplatform.dto.useCase.script.ScriptListOutDto;
import com.autotestplatform.facade.usecase.PlanFacade;
import com.autotestplatform.service.plan.PlanService;
import com.autotestplatform.vo.LogNameAndUrl;

/**
 * 
 * 测试计划
 * @author : 张文博
 * @date : 2018年5月15日 下午5:10:31  
 * @version : 2018年5月15日  张文博 TODO简要描述修改内容，修改原因
 */
@Controller
@RequestMapping(value = "/plan")
public class PlanController extends BaseController {

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanFacade  planFacade;

    /**
     * 
     * @Description：计划列表
     * @param planListInDto
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/planList")
    public ModelAndView getPlanList(PlanListInDto planListInDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (planListInDto.getPageDto() == null) {
            Integer pageSize = getSession().getAttribute(Contants.PROJECTPAGESIZE) == null
                    ? (Integer) getSession().getAttribute(Contants.PROJECTPAGESIZE)
                    : 20;
            planListInDto.setPageDto(new PageDto(1, pageSize));
        }
        PlanListOutDto planListOutDto = new PlanListOutDto();
        planListOutDto = planService.getPlanList(planListInDto);
        modelAndView.addObject("planListOutDto", planListOutDto);
        modelAndView.setViewName("ftl/page/testPlanList");
        return modelAndView;
    }

    /**
     * 
     * @Description：计划详情
     * @param planDetailInDto
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/planDetail")
    public ModelAndView getPlanDetail(PlanDetailInDto planDetailInDto) {
        ModelAndView modelAndView = new ModelAndView();
        PlanDetailOutDto planDetailOutDto = new PlanDetailOutDto();
        planDetailOutDto = planService.getPlanDetail(planDetailInDto);
        modelAndView.addObject("planDetailOutDto", planDetailOutDto);
        modelAndView.setViewName("ftl/page/planDetail");
        return modelAndView;
    }

    /**
     *
     * @Description：追加计划
     * @param planInsertInDto
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/insertPlan")
    @ResponseBody
    public String insertPlan(PlanInsertInDto planInsertInDto) {
        planService.insertPlan(planInsertInDto);
        return "addPlanSuccess";
    }

    @RequestMapping(value = "/updatePlan")
    @ResponseBody
    public String updatePlan(PlanUpdateInDto planUpdateInDto) {
        planService.updatePlan(planUpdateInDto);
        return "updatePlanSuccess";
    }

    @RequestMapping(value = "/deletePlan")
    @ResponseBody
    public String deletePlan(PlanDeleteInDto planDeleteInDto) {
        planService.deletePlan(planDeleteInDto);
        return "deletePlanSuccess";
    }

    /**
     * @Description：添加用例到计划
     * @return: 返回结果描述
     * @throws
     */
    @RequestMapping(value = "/insertUseCasePlan")
    @ResponseBody
    public String insertUseCasePlan(UseCasePlanRelationDto useCasePlanRelationDto) {
        planService.insertUseCasePlanRelation(useCasePlanRelationDto);
        return "success";
    }

    /**
     * @Description：刷新用例到计划
     * @return: 返回结果描述
     * @throws
     */
    @RequestMapping(value = "/refreshUseCasePlanRelation")
    @ResponseBody
    public List<PlanUseCaseListDetailDto> refreshUseCasePlanRelation(Integer planId) {
        return planService.refreshUseCasePlanRelation(planId);
    }
    
    /**
     * @Description：刷新计划执行状态
     * @return: 返回结果描述
     * @throws
     */
    @RequestMapping(value = "/refreshPlanStatus")
    @ResponseBody
    public Integer refreshPlanStatus(Integer planId) {
        return planService.refreshPlanStatus(planId);
    }

    /**
     * @Description：删除用例到计划
     * @return: 返回结果描述
     * @throws
     */
    @RequestMapping(value = "/deleteUseCasePlanRelation")
    @ResponseBody
    public String deleteUseCasePlanRelation(UseCasePlanRelationDto useCasePlanRelationDto) {
        planService.deleteUseCasePlanRelation(useCasePlanRelationDto);
        return "success";
    }

    @RequestMapping(value = "/runPlan")
    public String runPlan(RunPlanInDto runPlanInDto) {
        planFacade.updatePlan(runPlanInDto);
        return "";
    }

    @RequestMapping(value = "/stopPlan")
    public String stopPlan(RunPlanInDto runPlanInDto) {
        planFacade.updatePlan(runPlanInDto);
        return "";
    }

    /**
     * @Description：脚本历史版本列表
     * @param scriptInDto
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/getPlanResult")
    public ModelAndView getPlanResult(PlanResultInDto planResultInDto) {
        logger.info("查看计划执行结果 planId=" + planResultInDto.getPlanId());
        ModelAndView modelAndView = new ModelAndView();
        PlanResultOutDto planResultOutDto = planService.getPlanResult(planResultInDto);
        modelAndView.addObject("planResultOutDto", planResultOutDto);
        modelAndView.setViewName("ftl/page/planResultList");
        return modelAndView;
    }

    /**
     * @Description：下载日志
     * @return void: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/downloadLog")
    public void downloadLog(DownloadLog downloadScript, HttpServletResponse response) {
        LogNameAndUrl logDownloadMessage = planService.getLogNameAndUrl(downloadScript.getLogId());
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(logDownloadMessage.getLogUrl().getBytes("utf-8"), "ISO_8859_1"));
        } catch (UnsupportedEncodingException e1) {
            logger.info("下载脚本" + downloadScript.getLogId() + "出错，文件名编码错误", e1);
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadScript.getLogId() + "."
                    + logDownloadMessage.getLogUrl().split("\\.")[1]);
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(getSession().getServletContext().getRealPath(logDownloadMessage.getLogUrl()))));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            logger.info("下载脚本" + downloadScript.getLogId() + "出错", e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.info("下载脚本" + downloadScript.getLogId() + "关闭bis出错");
                }
            }
        }
    }

}
