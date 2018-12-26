package com.autotestplatform.controller.testCase;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.autotestplatform.contants.Contants;
import com.autotestplatform.controller.base.BaseController;
import com.autotestplatform.dto.common.PageDto;
import com.autotestplatform.dto.common.UseCaseIdNameDto;
import com.autotestplatform.dto.useCase.delete.UseCaseDeleteInDto;
import com.autotestplatform.dto.useCase.detail.UseCaseDetailInDto;
import com.autotestplatform.dto.useCase.detail.UseCaseDetailOutDto;
import com.autotestplatform.dto.useCase.example.DownLoadExample;
import com.autotestplatform.dto.useCase.example.ExampleListInDto;
import com.autotestplatform.dto.useCase.example.ExampleListOutDto;
import com.autotestplatform.dto.useCase.insert.UseCaseInsertInDto;
import com.autotestplatform.dto.useCase.list.UseCaseListInDto;
import com.autotestplatform.dto.useCase.list.UseCaseListOutDto;
import com.autotestplatform.dto.useCase.script.DownloadScript;
import com.autotestplatform.dto.useCase.script.ScriptListInDto;
import com.autotestplatform.dto.useCase.script.ScriptListOutDto;
import com.autotestplatform.dto.useCase.update.UseCaseUpdateInDto;
import com.autotestplatform.dto.useCase.uploadexample.UploadExampleInDto;
import com.autotestplatform.dto.useCase.uploadscript.UploadScriptInDto;
import com.autotestplatform.service.useCase.UseCaseService;
import com.autotestplatform.utils.CustomException;
import com.autotestplatform.utils.StringUtils;
import com.autotestplatform.vo.ExampleNameAndUrl;
import com.autotestplatform.vo.ScriptNameAndUrl;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月9日 上午11:34:39  
 * @version : 2018年5月9日  张文博 TODO简要描述修改内容，修改原因
 */
@Controller
@RequestMapping(value = "/useCase")
public class UseCaseController extends BaseController {

    @Autowired
    private UseCaseService useCaseService;

    /**
     * 
     * @Description：获取用例列表
     * @param testCaseListInDto
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/getUseCaseList")
    public ModelAndView selectTestCaseList(UseCaseListInDto testCaseListInDto) {
        logger.info("进入查询测试案例页面：");
        ModelAndView modelAndView = new ModelAndView();
        if (testCaseListInDto.getPageDto() == null) {
            Integer pageSize = getSession().getAttribute(Contants.USECASEPAGESIZE) == null ? 20
                    : Integer.parseInt(getSession().getAttribute(Contants.USECASEPAGESIZE).toString());
            testCaseListInDto.setPageDto(new PageDto(1, pageSize));
        }
        UseCaseListOutDto useCaseList = useCaseService.getUserCaseList(testCaseListInDto);
        modelAndView.addObject("useCaseListOutDto", useCaseList);
        modelAndView.setViewName("ftl/page/testUseCaseList");
        return modelAndView;
    }

    /**
     * 
     * @Description：获取用例详情
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/useCaseDetail")
    public ModelAndView selectTestCaseDetail(UseCaseDetailInDto useCaseDetailInDto) {
        logger.info("进入查询测试案例页面：");
        ModelAndView modelAndView = new ModelAndView();
        UseCaseDetailOutDto useCaseDetail = useCaseService.getUseCaseDetail(useCaseDetailInDto);
        modelAndView.addObject("useCaseDetailOutDto", useCaseDetail);
        modelAndView.setViewName("ftl/page/useCaseDetail");
        return modelAndView;
    }

    @RequestMapping(value = "/insertUseCase", method = RequestMethod.POST)
    @ResponseBody
    public String insertUseCase(UseCaseInsertInDto useCaseInsertInDto) {
        logger.info("进入新增测试案例页面：");
        useCaseService.insertUseCase(useCaseInsertInDto);
        return "addUseCaseSuccess";
    }

    @RequestMapping(value = "/updateUseCase", method = RequestMethod.POST)
    @ResponseBody
    public String updateUseCase(UseCaseUpdateInDto useCaseUpdateInDto) {
        logger.info("进入修改测案例页面：");
        useCaseService.updateUseCase(useCaseUpdateInDto);
        return "updateUseCaseSuccess";
    }

    @RequestMapping(value = "/deleteUseCase", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUseCase(UseCaseDeleteInDto useCaseDeleteInDto) {
        logger.info("进入删除测案例方法：");
        String unDelUseCaseId = useCaseService.deleteUseCase(useCaseDeleteInDto);
        if (!StringUtils.isBlank(unDelUseCaseId)) {
            return "faild" + unDelUseCaseId;
        }
        return "deleteUseCaseSuccess";
    }

    /**
     * @Description：上传案例
     * @param uploadExampleInDto: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/uploadExample")
    @ResponseBody
    public String uploadExample(UploadExampleInDto uploadExampleInDto) {
        String url = "faild";
        try {
            url = useCaseService.uploadExample(uploadExampleInDto);
        } catch (CustomException e) {
            logger.info("上传案例失败", e);
            return "faild";
        }
        return url;
    }

    /**
     * @Description：上传脚本
     * @param uploadScriptInDto: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/uploadScript")
    @ResponseBody
    public String uploadScript(UploadScriptInDto uploadScriptInDto) {
        String url = "faild";
        try {
            url = useCaseService.uploadScript(uploadScriptInDto);
        } catch (CustomException e) {
            logger.info("上传脚本失败", e);
            return "faild";
        }
        return url;
    }

    /**
     * @Description：用例联动
     * @return: 返回结果描述
     * @throws
     */
    @RequestMapping(value = "/useCaseIdName")
    @ResponseBody
    public List<UseCaseIdNameDto> findUseCaseIdName(Integer modelId) {
        return useCaseService.findUseCaseIdName(modelId);
    }

    /**
     * @Description：案例历史版本列表
     * @param exampleInDto
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/getExampleList")
    public ModelAndView getExampleList(ExampleListInDto exampleInDto) {
        logger.info("案例历史版本列表 useCaseId=" + exampleInDto.getUseCaseId());
        ModelAndView modelAndView = new ModelAndView();
        if (exampleInDto.getPageDto() == null) {
            exampleInDto.setPageDto(new PageDto(1, 40));
        }
        ExampleListOutDto exampleList = useCaseService.getExampleList(exampleInDto);
        modelAndView.addObject("exampleListOutDto", exampleList);
        modelAndView.setViewName("ftl/page/exampleList");
        return modelAndView;
    }

    /**
     * @Description：脚本历史版本列表
     * @param scriptInDto
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/getScriptList")
    public ModelAndView getScriptList(ScriptListInDto scriptInDto) {
        logger.info("脚本历史版本列表 useCaseId=" + scriptInDto.getUseCaseId());
        ModelAndView modelAndView = new ModelAndView();
        if (scriptInDto.getPageDto() == null) {
            scriptInDto.setPageDto(new PageDto(1, 40));
        }
        ScriptListOutDto scriptList = useCaseService.getScriptList(scriptInDto);
        modelAndView.addObject("scriptListOutDto", scriptList);
        modelAndView.setViewName("ftl/page/scriptList");
        return modelAndView;
    }

    /**
     * @Description：下载案例
     * @return void: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/downloadExample")
    public void downloadExample(DownLoadExample downloadExample, HttpServletResponse response) {
        ExampleNameAndUrl exampleDownloadMessage = useCaseService
                .getExampleFileNameAndUrl(downloadExample.getExampleId());
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(exampleDownloadMessage.getExampleName().getBytes("utf-8"), "ISO_8859_1"));
        } catch (UnsupportedEncodingException e1) {
            logger.info("下载案例" + downloadExample.getExampleId() + "出错，文件名编码错误", e1);
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadExample.getExampleId() + "."
                    + exampleDownloadMessage.getExampleName().split("\\.")[1]);
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(getSession().getServletContext().getRealPath(exampleDownloadMessage.getExampleUrl()))));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            logger.info("下载案例" + downloadExample.getExampleId() + "出错", e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.info("下载案例" + downloadExample.getExampleId() + "关闭bis出错");
                }
            }
        }
    }

    /**
     * @Description：下载脚本
     * @return void: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/downloadScript")
    public void downloadScript(DownloadScript downloadScript, HttpServletResponse response) {
        ScriptNameAndUrl scriptDownloadMessage = useCaseService.getScriptFileNameAndUrl(downloadScript.getScriptId());
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(scriptDownloadMessage.getScriptName().getBytes("utf-8"), "ISO_8859_1"));
        } catch (UnsupportedEncodingException e1) {
            logger.info("下载脚本" + downloadScript.getScriptId() + "出错，文件名编码错误", e1);
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadScript.getScriptId() + "."
                    + scriptDownloadMessage.getScriptName().split("\\.")[1]);
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(
                    new File(getSession().getServletContext().getRealPath(scriptDownloadMessage.getScriptUrl()))));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            logger.info("下载脚本" + downloadScript.getScriptId() + "出错", e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.info("下载脚本" + downloadScript.getScriptId() + "关闭bis出错");
                }
            }
        }
    }

}
