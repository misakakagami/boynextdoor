package com.autotestplatform.controller.project;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.autotestplatform.contants.Contants;
import com.autotestplatform.controller.base.BaseController;
import com.autotestplatform.dto.common.ModelIdNameDto;
import com.autotestplatform.dto.model.delete.ModelDeleteInDto;
import com.autotestplatform.dto.model.detail.ModelDetailInDto;
import com.autotestplatform.dto.model.detail.ModelDetailOutDto;
import com.autotestplatform.dto.model.insert.ModelSaveInDto;
import com.autotestplatform.dto.model.list.ModelListInDto;
import com.autotestplatform.dto.model.list.ModelListOutDto;
import com.autotestplatform.dto.model.update.ModelUpdateInDto;
import com.autotestplatform.dto.project.delete.ProjectDeleteInDto;
import com.autotestplatform.dto.project.detail.ProjectDetailInDto;
import com.autotestplatform.dto.project.detail.ProjectDetailOutDto;
import com.autotestplatform.dto.project.insert.ProjectSaveInDto;
import com.autotestplatform.dto.project.list.ProjectListInDto;
import com.autotestplatform.dto.project.list.ProjectListOutDto;
import com.autotestplatform.dto.project.update.ProjectUpdateInDto;
import com.autotestplatform.service.project.ProjectService;
import com.autotestplatform.utils.CustomException;
import com.autotestplatform.utils.StringUtils;

/**
 * 项目、模块controller
 * @author : 孔德华
 * @date : 2018年5月11日 上午10:00:32  
 * @version : 2018年5月11日  孔德华 创建类
 */
@Controller
@RequestMapping(value = "/projectModel")
public class ProjectModelController extends BaseController {

    @Autowired
    private ProjectService projectService;

    //project
    /**
     * @Description：获取项目列表
     * @param projectInDto
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/getProjectList")
    public ModelAndView getProjectList(ProjectListInDto projectListInDto) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession s = getSession();
        Integer pageSize = s.getAttribute(Contants.PROJECTPAGESIZE) == null ? 10
                : Integer.parseInt(s.getAttribute(Contants.PROJECTPAGESIZE).toString());
        projectListInDto = (ProjectListInDto) setValueIfNull(projectListInDto, ProjectListInDto.class, pageSize);
        ProjectListOutDto projectListOutDto = projectService.getProjectList(projectListInDto);
        modelAndView.addObject("projectListOutDto", projectListOutDto);
        modelAndView.setViewName("ftl/page/projectList");
        return modelAndView;
    }

    /**
     * @Description：获取项目详情
     * @param projectInDto
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/getProjectDetail")
    public ModelAndView getProjectDetail(ProjectDetailInDto projectDetailInDto) {
        ModelAndView modelAndView = new ModelAndView();
        ProjectDetailOutDto projectDetailOutDto = projectService.getProjectDetail(projectDetailInDto);
        modelAndView.addObject("projectDetailOutDto", projectDetailOutDto);
        modelAndView.setViewName("ftl/page/projectDetail");
        return modelAndView;
    }

    /**
     * @Description：打开新增项目页面
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    //    @RequestMapping(value = "/toInsertProject")
    //    public ModelAndView toInsertProject() {
    //        ModelAndView modelAndView = new ModelAndView();
    //        modelAndView.setViewName("ftl/index/addProject");
    //        return modelAndView;
    //    }

    /**
     * @Description：打开修改项目页面
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    //    @RequestMapping(value = "/toUpdateProject")
    //    public ModelAndView toUpdateProject() {
    //        ModelAndView modelAndView = new ModelAndView();
    //        modelAndView.setViewName("ftl/index/updateProject");
    //        return modelAndView;
    //    }

    /**
     * @throws Exception 
     * @Description：增加项目
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    //    @RequestMapping(value = "/insertProject", method = RequestMethod.POST)
    //    public ModelAndView insertProject(ProjectSaveInDto projectSaveDto) throws Exception {
    //        ModelAndView modelAndView = null;
    //        projectService.saveProject(projectSaveDto);
    //        modelAndView = getProjectList(null);
    //        return modelAndView;
    //    }
    @RequestMapping(value = "/insertProject", method = RequestMethod.POST)
    @ResponseBody
    public String insertProject(ProjectSaveInDto projectSaveDto) throws Exception {
        projectService.saveProject(projectSaveDto);
        return "addProjectSuccess";
    }

    /**
     * @throws Exception 
     * @Description：修改项目
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    @ResponseBody
    public String updateProject(ProjectUpdateInDto projectUpdateDto) throws Exception {
        projectService.updateProject(projectUpdateDto);
        return "updateProjectSuccess";
    }

    /**
     * @Description：删除项目（逻辑）
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/deleteProject")
    @ResponseBody
    public String deleteProject(ProjectDeleteInDto projectDeleteInDto) {
        String unDelProjectId = projectService.deleteProject(projectDeleteInDto);
        if (!StringUtils.isBlank(unDelProjectId)) {
            return "faild" + unDelProjectId;
        }
        return "deleteProjectSuccess";
    }

    //model
    /**
     * @Description：获取模块列表
     * @param projectInDto
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/getModelList")
    public ModelAndView getModelList(ModelListInDto modelListInDto) {
        ModelAndView modelAndView = new ModelAndView();
        Integer pageSize = getSession().getAttribute(Contants.MODELPAGESIZE) == null ? 20
                : Integer.parseInt(getSession().getAttribute(Contants.MODELPAGESIZE).toString());
        modelListInDto = (ModelListInDto) setValueIfNull(modelListInDto, ModelListInDto.class, pageSize);
        ModelListOutDto modelListOutDto = projectService.getModelList(modelListInDto);
        modelAndView.addObject("modelListOutDto", modelListOutDto);
        modelAndView.setViewName("ftl/page/modelList");
        return modelAndView;
    }

    /**
     * @throws Exception 
     * @throws CustomException 
     * @Description：获取模块详情
     * @param ModelInDto
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/getModelDetail")
    public ModelAndView getModelDetail(ModelDetailInDto modelDetailInDto) throws CustomException, Exception {
        ModelAndView modelAndView = new ModelAndView();
        ModelDetailOutDto modelDetailOutDto = projectService.getModelDetail(modelDetailInDto);
        modelAndView.addObject("modelDetailOutDto", modelDetailOutDto);
        modelAndView.setViewName("ftl/page/modelDetail");
        return modelAndView;
    }

    /**
     * @throws Exception 
     * @Description：增加模块
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/insertModel", method = RequestMethod.POST)
    @ResponseBody
    public String insertModel(ModelSaveInDto modelSaveDto) throws Exception {
        projectService.saveModel(modelSaveDto);
        return "addModelSuccess";
    }

    /**
     * @throws Exception 
     * @Description：修改模块
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/updateModel", method = RequestMethod.POST)
    @ResponseBody
    public String updateModel(ModelUpdateInDto modelUpdateDto) throws Exception {
        projectService.updateModel(modelUpdateDto);
        return "updateModelSuccess";
    }

    /**
     * @Description：删除模块（逻辑）
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/deleteModel")
    @ResponseBody
    public String deleteModel(ModelDeleteInDto modelDeleteInDto) {
        String unDelModelId = projectService.deleteModel(modelDeleteInDto);
        if (!StringUtils.isBlank(unDelModelId)) {
            return "faild" + unDelModelId;
        }
        return "deleteModelSuccess";
    }

    /**
     * @Description：模块联动
     * @return: 返回结果描述
     * @throws
     */
    @RequestMapping(value = "/modelIdName")
    @ResponseBody
    public List<ModelIdNameDto> findModelIdName(Integer projectId) {
        return projectService.findModelIdName(projectId);
    }

}
