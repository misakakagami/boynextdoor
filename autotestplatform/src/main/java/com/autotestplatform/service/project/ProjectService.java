package com.autotestplatform.service.project;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autotestplatform.dao.ModelMapper;
import com.autotestplatform.dao.ProjectMapper;
import com.autotestplatform.dao.UseCaseMapper;
import com.autotestplatform.dto.common.ModelIdNameDto;
import com.autotestplatform.dto.common.PageDto;
import com.autotestplatform.dto.common.ProjectIdNameDto;
import com.autotestplatform.dto.model.delete.ModelDeleteInDto;
import com.autotestplatform.dto.model.detail.ModelDetailInDto;
import com.autotestplatform.dto.model.detail.ModelDetailOutDto;
import com.autotestplatform.dto.model.insert.ModelSaveInDto;
import com.autotestplatform.dto.model.list.ModelListInDto;
import com.autotestplatform.dto.model.list.ModelListOutDetailDto;
import com.autotestplatform.dto.model.list.ModelListOutDto;
import com.autotestplatform.dto.model.update.ModelUpdateInDto;
import com.autotestplatform.dto.project.delete.ProjectDeleteInDto;
import com.autotestplatform.dto.project.detail.ProjectDetailInDto;
import com.autotestplatform.dto.project.detail.ProjectDetailOutDto;
import com.autotestplatform.dto.project.insert.ProjectSaveInDto;
import com.autotestplatform.dto.project.list.ProjectListInDto;
import com.autotestplatform.dto.project.list.ProjectListOutDetailDto;
import com.autotestplatform.dto.project.list.ProjectListOutDto;
import com.autotestplatform.dto.project.update.ProjectUpdateInDto;
import com.autotestplatform.entity.Model;
import com.autotestplatform.entity.Project;
import com.autotestplatform.entity.User;
import com.autotestplatform.service.base.BaseService;
import com.autotestplatform.utils.CustomException;
import com.autotestplatform.utils.ObjectUtils;
import com.autotestplatform.utils.StringUtils;

/**
 * 项目信息
 * @author : 孔德华
 * @date : 2018年5月10日 上午8:46:41  
 * @version : 2018年5月10日  孔德华 创建类
 */
@Service
public class ProjectService extends BaseService {

    @Autowired
    private ProjectMapper projectDao;

    @Autowired
    private ModelMapper   modelDao;

    @Autowired
    private UseCaseMapper useCaseDao;

    /**
     * @Description：获得项目列表
     * @param projectListInDto
     * @return ProjectListOutDto: 返回值类型
     * @throws
     */
    public ProjectListOutDto getProjectList(ProjectListInDto projectListInDto) {
        //init
        ProjectListOutDto projectListOutDto = new ProjectListOutDto();
        List<ProjectListOutDetailDto> projectListOutDtoList = null;
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //search conditions
        queryMap.put("searchContent",
                StringUtils.isEmpty(projectListInDto.getSearchContent()) ? null : projectListInDto.getSearchContent());
        //        queryMap.put("projectMode", "0");全部可见
        queryMap.put("pageNow", projectListInDto.getPageDto().getLimitPageNow());
        queryMap.put("pageSize", projectListInDto.getPageDto().getPageSize());
        //search
        Integer count = projectDao.selectListCountBySelective(queryMap);
        projectListOutDtoList = projectDao.selectListBySelective(queryMap);
        //set return dto
        projectListOutDto.setSearchContent(projectListInDto.getSearchContent());
        projectListOutDto.setProjectDtoList(projectListOutDtoList);
        projectListOutDto.setPageDto(new PageDto(projectListInDto.getPageDto().getPageNow(),
                projectListInDto.getPageDto().getPageSize(), count));
        return projectListOutDto;
    }

    /**
     * @Description：获得项目详情
     * @param projectDetailInDto
     * @return: 返回结果描述
     * @throws
     */
    public ProjectDetailOutDto getProjectDetail(ProjectDetailInDto projectDetailInDto) {
        //init
        ProjectDetailOutDto projectDetailOutDto = new ProjectDetailOutDto();
        //search
        Project project = projectDao.selectByPrimaryKey(projectDetailInDto.getProjectId());
        //set return dto
        projectDetailOutDto.setProjectId(project.getProjectId());
        projectDetailOutDto.setProjectName(project.getProjectName());
        projectDetailOutDto.setProjectUrl(project.getProjectUrl());
        projectDetailOutDto.setProjectMode(project.getProjectMode());
        projectDetailOutDto.setCreateTime(project.getCreateTime());
        projectDetailOutDto.setUpdateTime(project.getUpdateTime());
        projectDetailOutDto.setCreateUserId(project.getCreateUserId());
        projectDetailOutDto.setUpdateUserId(project.getUpdateUserId());
        return projectDetailOutDto;
    }

    /**
     * @throws Exception 
     * @Description：储存项目
     * @param projectSaveInDto
     * @return: 返回结果描述
     * @throws
     */
    @Transactional
    public Integer saveProject(ProjectSaveInDto projectSaveDto) throws Exception {
        //init
        Project project = null;
        User loginUser = (User) getSession().getAttribute("loginUser");
        Integer projectId;
        //set
        project = (Project) ObjectUtils.exchangeObj(projectSaveDto.getProjectDto(), Project.class);
        project.setProjectMode(0);
        project.setCreateTime(new Date());
        project.setCreateUserId(loginUser.getUserId());
        project.setUpdateTime(new Date());
        project.setUpdateUserId(loginUser.getUserId());
        //insert
        projectId = projectDao.insert(project);
        //set return dto
        return projectId;
    }

    /**
     * @throws Exception 
     * @Description：修改项目
     * @param projectUpdateInDto
     * @return: 返回结果描述
     * @throws
     */
    @Transactional
    public Integer updateProject(ProjectUpdateInDto projectUpdateDto) throws Exception {
        //init
        Project project = null;
        User loginUser = (User) getSession().getAttribute("loginUser");
        Integer projectId;
        //set
        project = (Project) ObjectUtils.exchangeObj(projectUpdateDto.getProjectDto(), Project.class);
        project.setUpdateTime(new Date());
        project.setUpdateUserId(loginUser.getUserId());
        //insert
        projectId = projectDao.updateByPrimaryKeySelective(project);
        //set return dto
        return projectId;
    }

    /**
     * @Description：删除项目（逻辑）含有未删除模块的项目不能删除
     * @param ProjectDeleteInDto
     * @return: String 未删除成功的项目id序列
     * @throws
     */
    @Transactional
    public String deleteProject(ProjectDeleteInDto projectDeleteInDto) {
        //init
        Project project = null;
        User loginUser = (User) getSession().getAttribute("loginUser");
        Integer count = null;
        Map<String, Object> queryMap = null;
        StringBuffer unDelProjects = new StringBuffer("");
        //set 含有未删除模块的项目不能删除
        for (Integer projectId : projectDeleteInDto.getProjectIdList()) {
            queryMap = new HashMap<String, Object>();
            queryMap.put("projectId", projectId);
            queryMap.put("modelStatus", 0);
            count = modelDao.selectListCountBySelective(queryMap);
            if (count > 0) {
                //此项目下有未删除模块
                unDelProjects.append(projectId + ",");
                continue;
            }
            project = new Project();
            project.setProjectId(projectId);
            project.setProjectMode(-1);
            project.setUpdateTime(new Date());
            project.setUpdateUserId(loginUser.getUserId());
            //update
            projectId = projectDao.updateByPrimaryKeySelective(project);
        }
        return unDelProjects.toString();
    }

    /**
     * @Description：获得模块列表
     * @param modelListInDto
     * @return ProjectListOutDto: 返回值类型
     * @throws
     */
    public ModelListOutDto getModelList(ModelListInDto modelListInDto) {
        //init
        ModelListOutDto modelListOutDto = new ModelListOutDto();
        List<ModelListOutDetailDto> modelListOutDtoList = null;
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //search conditions
        queryMap.put("searchContent",
                StringUtils.isEmpty(modelListInDto.getSearchContent()) ? null : modelListInDto.getSearchContent());
        //        queryMap.put("projectMode", "0");全部可见
        queryMap.put("projectId", modelListInDto.getProjectId());
        queryMap.put("pageNow", modelListInDto.getPageDto().getLimitPageNow());
        queryMap.put("pageSize", modelListInDto.getPageDto().getPageSize());
        //search
        Integer count = modelDao.selectListCountBySelective(queryMap);
        modelListOutDtoList = modelDao.selectListBySelective(queryMap);
        List<ProjectIdNameDto> projectList = projectDao.selectAllIdAndName();
        //set return dto
        modelListOutDto.setProjectId(modelListInDto.getProjectId());
        modelListOutDto.setProjectIdNameList(projectList);
        modelListOutDto.setSearchContent(modelListInDto.getSearchContent());
        modelListOutDto.setModelListOutDtoList(modelListOutDtoList);
        modelListOutDto.setPageDto(new PageDto(modelListInDto.getPageDto().getPageNow(),
                modelListInDto.getPageDto().getPageSize(), count));
        return modelListOutDto;
    }

    /**
     * @throws Exception 
     * @Description：获得模块详情
     * @param modelDetailInDto
     * @return: 返回结果描述
     * @throws
     */
    public ModelDetailOutDto getModelDetail(ModelDetailInDto modelDetailInDto) throws CustomException, Exception {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("modelId", modelDetailInDto.getModelId());
        queryMap.put("pageNow", 0);
        queryMap.put("pageSize", 1);
        ModelDetailOutDto modelDetailOutDto;
        try {
            ModelListOutDetailDto mlodDto = modelDao.selectListBySelective(queryMap).get(0);
            modelDetailOutDto = (ModelDetailOutDto) ObjectUtils.exchangeObj(mlodDto, ModelDetailOutDto.class);
        } catch (IndexOutOfBoundsException e1) {
            //modelId不存在
            throw new CustomException("模块不存在！");
        } catch (Exception e) {
            throw e;
        }
        return modelDetailOutDto;
    }

    /**
     * @throws Exception 
     * @Description：储存项目
     * @param projectSaveOrUpdateInDto
     * @return: 返回结果描述
     * @throws
     */
    @Transactional
    public Integer saveModel(ModelSaveInDto modelSaveDto) throws Exception {
        //init
        Model model = (Model) ObjectUtils.exchangeObj(modelSaveDto.getModelDto(), Model.class);
        User loginUser = (User) getSession().getAttribute("loginUser");
        //set
        model.setModelStatus(0);
        model.setCreateTime(new Date());
        model.setCreateUserId(loginUser.getUserId());
        model.setUpdateTime(new Date());
        model.setUpdateUserId(loginUser.getUserId());
        //insert
        Integer modelId = modelDao.insert(model);
        //set return dto
        return modelId;
    }

    /**
     * @throws Exception 
     * @Description：修改项目
     * @param projectSaveOrUpdateInDto
     * @return: 返回结果描述
     * @throws
     */
    @Transactional
    public Integer updateModel(ModelUpdateInDto modelUpdateDto) throws Exception {
        //init
        Model model = (Model) ObjectUtils.exchangeObj(modelUpdateDto.getModelDto(), Model.class);
        User loginUser = (User) getSession().getAttribute("loginUser");
        //set
        model.setUpdateTime(new Date());
        model.setUpdateUserId(loginUser.getUserId());
        //insert
        Integer modelId = modelDao.updateByPrimaryKeySelective(model);
        //set return dto
        return modelId;
    }

    /**
     * @Description：删除项目（逻辑）
     * @param ProjectDetailInDto
     * @return: 返回结果描述
     * @throws
     */
    @Transactional
    public String deleteModel(ModelDeleteInDto modelDeleteInDto) {
        //init
        User loginUser = (User) getSession().getAttribute("loginUser");
        List<Integer> delList = modelDeleteInDto.getModelIdList();
        Model model = null;
        Map<String, Object> queryMap = null;
        StringBuffer unDelUseCase = new StringBuffer("");
        //update
        for (Integer modelId : delList) {
            queryMap = new HashMap<String, Object>();
            queryMap.put("useCaseStatus", 0);
            queryMap.put("modelId", modelId);
            if (useCaseDao.selectUseCaseListCount(queryMap) > 0) {
                //模块下有未删除的用例
                unDelUseCase.append(modelId + ",");
                continue;
            }
            model = new Model();
            model.setModelId(modelId);
            model.setModelStatus(-1);
            model.setUpdateTime(new Date());
            model.setUpdateUserId(loginUser.getUserId());
            modelDao.updateByPrimaryKeySelective(model);
        }
        //set return dto
        return unDelUseCase.toString();
    }

    public List<ModelIdNameDto> findModelIdName(Integer projectId) {
        return modelDao.selectModelIdNameList(projectId);
    }
}
