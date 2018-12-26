package com.autotestplatform.service.useCase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.autotestplatform.contants.Contants;
import com.autotestplatform.dao.ExampleMapper;
import com.autotestplatform.dao.ModelMapper;
import com.autotestplatform.dao.ProjectMapper;
import com.autotestplatform.dao.ScriptMapper;
import com.autotestplatform.dao.UseCaseMapper;
import com.autotestplatform.dto.common.ExampleDto;
import com.autotestplatform.dto.common.ModelIdNameDto;
import com.autotestplatform.dto.common.PageDto;
import com.autotestplatform.dto.common.ScriptDto;
import com.autotestplatform.dto.common.UseCaseIdNameDto;
import com.autotestplatform.dto.useCase.delete.UseCaseDeleteInDto;
import com.autotestplatform.dto.useCase.detail.UseCaseDetailInDto;
import com.autotestplatform.dto.useCase.detail.UseCaseDetailOutDto;
import com.autotestplatform.dto.useCase.example.ExampleListInDto;
import com.autotestplatform.dto.useCase.example.ExampleListOutDto;
import com.autotestplatform.dto.useCase.insert.UseCaseInsertInDto;
import com.autotestplatform.dto.useCase.list.UseCaseListDetailDto;
import com.autotestplatform.dto.useCase.list.UseCaseListInDto;
import com.autotestplatform.dto.useCase.list.UseCaseListOutDto;
import com.autotestplatform.dto.useCase.script.ScriptListInDto;
import com.autotestplatform.dto.useCase.script.ScriptListOutDto;
import com.autotestplatform.dto.useCase.update.UseCaseUpdateInDto;
import com.autotestplatform.dto.useCase.uploadexample.UploadExampleInDto;
import com.autotestplatform.dto.useCase.uploadscript.UploadScriptInDto;
import com.autotestplatform.entity.Example;
import com.autotestplatform.entity.Script;
import com.autotestplatform.entity.UseCase;
import com.autotestplatform.entity.User;
import com.autotestplatform.service.base.BaseService;
import com.autotestplatform.utils.CustomException;
import com.autotestplatform.utils.FileUtils;
import com.autotestplatform.vo.ExampleNameAndUrl;
import com.autotestplatform.vo.ScriptNameAndUrl;

/**
 * 
 * 简要描述本类的主要模块、函数及功能的说明
 * @author : 张文博
 * @date : 2018年5月9日 下午1:58:40  
 * @version : 2018年5月9日  张文博 TODO简要描述修改内容，修改原因
 */
@Service
public class UseCaseService extends BaseService {

    @Autowired
    private ProjectMapper projectDao;

    @Autowired
    private ModelMapper   modelDao;

    @Autowired
    private UseCaseMapper useCaseDao;

    @Autowired
    private ScriptMapper  scriptDao;

    @Autowired
    private ExampleMapper exampleDao;

    /**
     * 
     * @Description：获取用例列表
     * @param testCaseListInDto
     * @return: 返回结果描述
     * @return TestCaseListOutDto: 返回值类型
     * @throws
     */
    public UseCaseListOutDto getUserCaseList(UseCaseListInDto testCaseListInDto) {
        UseCaseListOutDto testCaseListOutDto = new UseCaseListOutDto();
        List<UseCaseListDetailDto> useCaseList = null;
        //		查询条件拼接
        Map<String, Object> selectMap = new HashMap<String, Object>();
        selectMap.put("projectId", testCaseListInDto.getProjectId());
        selectMap.put("modelId", testCaseListInDto.getModelId());
        selectMap.put("useCaseName", testCaseListInDto.getUseCaseName());
        selectMap.put("pageNow", testCaseListInDto.getPageDto().getLimitPageNow());
        selectMap.put("pageSize", testCaseListInDto.getPageDto().getPageSize());
        //		查询
        useCaseList = useCaseDao.selectUseCaseList(selectMap);
        Integer count = useCaseDao.selectUseCaseListCount(selectMap);
        List<ModelIdNameDto> modelIdNameList = modelDao.selectAllIdNameList();
        //		封装返回内容
        testCaseListOutDto.setModelId(testCaseListInDto.getModelId());
        testCaseListOutDto.setProjectId(testCaseListInDto.getProjectId());
        testCaseListOutDto.setModelIdNameList(modelIdNameList);
        testCaseListOutDto.setTestCaseInfoList(useCaseList);
        testCaseListOutDto.setPageDto(new PageDto(testCaseListInDto.getPageDto().getPageNow(),
                testCaseListInDto.getPageDto().getPageSize(), count));
        return testCaseListOutDto;
    }

    /**
     * 
     * @Description：获取用例详情
     * @param useCaseDetailInDto
     * @return: 返回结果描述
     * @return UseCaseDetailOutDto: 返回值类型
     * @throws
     */
    public UseCaseDetailOutDto getUseCaseDetail(UseCaseDetailInDto useCaseDetailInDto) {
        UseCaseDetailOutDto useCaseDetailOutDto = useCaseDao
                .selectUseCaseDetailOutDto(useCaseDetailInDto.getUseCaseId());
        List<Integer> fileId = scriptDao.selectOldScriptId(useCaseDetailInDto.getUseCaseId());
        if (fileId.size() > 0) {
            //当前使用的脚本
            useCaseDetailOutDto.setScriptId(fileId.get(0));
        }
        fileId = exampleDao.selectOldExampleId(useCaseDetailInDto.getUseCaseId());
        if (fileId.size() > 0) {
            //当前使用的案例
            useCaseDetailOutDto.setExampleId(fileId.get(0));
        }
        return useCaseDetailOutDto;
    }

    /**
     * 
     * @Description：追加测试用例
     * @param useCaseInsertInDto
     * @return: 返回结果描述
     * @return UseCaseDetailOutDto: 返回值类型
     * @throws
     */
    public UseCaseDetailOutDto insertUseCase(UseCaseInsertInDto useCaseInsertInDto) {
        //		init
        Integer useCaseId = null;
        UseCase useCase = new UseCase();
        UseCaseDetailInDto useCaseDetailInDto = new UseCaseDetailInDto();
        UseCaseDetailOutDto useCaseDetailOutDto = new UseCaseDetailOutDto();
        //		build insertdata
        User loginUser = (User) getSession().getAttribute("loginUser");
        useCase.setProjectId(modelDao.selectProjectIdByModelId(useCaseInsertInDto.getUseCaseDto().getModelId()));
        useCase.setModelId(useCaseInsertInDto.getUseCaseDto().getModelId());
        useCase.setConfigId(useCaseInsertInDto.getUseCaseDto().getConfigId());
        useCase.setUseCaseName(useCaseInsertInDto.getUseCaseDto().getUseCaseName());
        useCase.setUseCaseScriptUrl(useCaseInsertInDto.getUseCaseDto().getUseCaseScriptUrl());
        useCase.setUseCaseExampleUrl(useCaseInsertInDto.getUseCaseDto().getUseCaseExampleUrl());
        useCase.setUseCaseType(useCaseInsertInDto.getUseCaseDto().getUseCaseType());
        useCase.setUseCaseStatus(0);
        useCase.setUseCaseContent(useCaseInsertInDto.getUseCaseDto().getUseCaseContent());
        useCase.setUseCaseNum(0);
        useCase.setCreateTime(new Date());
        useCase.setCreateUserId(loginUser.getUserId());
        useCase.setUpdateTime(new Date());
        useCase.setUpdateUserId(loginUser.getUserId());
        //		insert
        useCaseId = useCaseDao.insert(useCase);
        //		set returndto
        useCaseDetailInDto.setUseCaseId(useCaseId);
        useCaseDetailOutDto = getUseCaseDetail(useCaseDetailInDto);
        return useCaseDetailOutDto;
    }

    /**
     * 
     * @Description：修改用例信息
     * @param useCaseUpdateInDto
     * @return: 返回结果描述
     * @return UseCaseUpdateOutDto: 返回值类型
     * @throws
     */
    public UseCaseDetailOutDto updateUseCase(UseCaseUpdateInDto useCaseUpdateInDto) {
        //		init
        Integer useCaseId = null;
        UseCase useCase = new UseCase();
        UseCaseDetailInDto useCaseDetailInDto = new UseCaseDetailInDto();
        UseCaseDetailOutDto useCaseDetailOutDto = new UseCaseDetailOutDto();
        //		build uodatetdata
        User loginUser = (User) getSession().getAttribute("loginUser");
        useCase.setUseCaseId(useCaseUpdateInDto.getUseCaseDto().getUseCaseId());
        useCase.setProjectId(useCaseUpdateInDto.getUseCaseDto().getProjectId());
        useCase.setModelId(useCaseUpdateInDto.getUseCaseDto().getModelId());
        useCase.setConfigId(useCaseUpdateInDto.getUseCaseDto().getConfigId());
        useCase.setUseCaseName(useCaseUpdateInDto.getUseCaseDto().getUseCaseName());
        useCase.setUseCaseScriptUrl(useCaseUpdateInDto.getUseCaseDto().getUseCaseScriptUrl());
        useCase.setUseCaseExampleUrl(useCaseUpdateInDto.getUseCaseDto().getUseCaseExampleUrl());
        useCase.setUseCaseType(useCaseUpdateInDto.getUseCaseDto().getUseCaseType());
        useCase.setUseCaseStatus(useCaseUpdateInDto.getUseCaseDto().getUseCaseStatus());
        useCase.setUseCaseContent(useCaseUpdateInDto.getUseCaseDto().getUseCaseContent());
        useCase.setUseCaseNum(useCaseUpdateInDto.getUseCaseDto().getUseCaseNum());
        useCase.setUpdateTime(new Date());
        useCase.setUpdateUserId(loginUser.getUserId());
        //		update
        useCaseId = useCaseDao.updateByPrimaryKeySelective(useCase);
        //		set returndto
        useCaseDetailInDto.setUseCaseId(useCaseId);
        useCaseDetailOutDto = getUseCaseDetail(useCaseDetailInDto);
        return useCaseDetailOutDto;

    }

    /**
     * 
     * @Description：删除用例信息
     * @param useCaseDeleteInDto: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    public String deleteUseCase(UseCaseDeleteInDto useCaseDeleteInDto) {
        //		init
        UseCase useCase = new UseCase();
        List<Integer> useCaseIdList = useCaseDeleteInDto.getUseCaseIdList();
        User loginUser = (User) getSession().getAttribute("loginUser");
        //        Map<String, Object> queryMap = null;
        //        StringBuffer unDelUseCase = new StringBuffer("");
        //		delete
        for (Integer delUseCaseId : useCaseIdList) {
            //            if (useCaseDao.selectUseCaseListCount(queryMap) > 0) {
            //                //
            //                queryMap = new HashMap<String, Object>();
            //                queryMap.put("useCaseStatus", 0);
            //                unDelUseCase.append(delUseCaseId + ",");
            //                continue;
            //            }
            useCase.setUseCaseId(delUseCaseId);
            useCase.setUseCaseStatus(-1);
            useCase.setUpdateUserId(loginUser.getUserId());
            useCase.setUpdateTime(new Date());
            useCaseDao.updateByPrimaryKeySelective(useCase);
        }
        //        return unDelUseCase.toString();
        return null;
    }

    /**
     * @Description：上传案例
     * 
     * ps:
     * 1 修改上一版案例文件信息（状态、url），并把原文件改名为"案例id.xxx"
     * 2 插入本版文件信息，上传本版本件。正在使用的文件名为"use用例id.xxx"
     * 3 补全更新本版文件信息、更新用例信息
     * 
     * @param uploadExampleInDto
     * @return
     * @throws CustomException: 返回结果描述
     * @return String: 返回值类型
     * @throws
     */
    @Transactional
    public String uploadExample(UploadExampleInDto uploadExampleInDto) throws CustomException {
        User loginUser = (User) getSession().getAttribute("loginUser");
        //change old example
        Example example = null;
        File oldFile = null;
        File renameOldFile = null;
        String renameUrl;
        List<Integer> oldExampleId = exampleDao.selectOldExampleId(uploadExampleInDto.getUseCaseId());
        for (Integer eid : oldExampleId) {
            example = exampleDao.selectByPrimaryKey(eid);
            oldFile = new File(getSession().getServletContext().getRealPath(example.getExampleUrl()));
            renameUrl = getSession().getServletContext().getRealPath(
                    "exampleUpload/" + eid + oldFile.getName().substring(oldFile.getName().lastIndexOf('.')));
            renameOldFile = new File(renameUrl);
            oldFile.renameTo(renameOldFile);
            example.setExampleStatus(-1);
            example.setExampleUrl(
                    "exampleUpload/" + eid + oldFile.getName().substring(oldFile.getName().lastIndexOf('.')));
            example.setUpdateTime(new Date());
            example.setUpdateUserId(loginUser.getUserId());
            exampleDao.updateByPrimaryKeySelective(example);
        }
        //new example message
        example = new Example();
        example.setUseCaseId(uploadExampleInDto.getUseCaseId());
        example.setExampleStatus(0);
        example.setCreateTime(new Date());
        example.setCreateUserId(loginUser.getUserId());
        example.setUpdateTime(new Date());
        example.setUpdateUserId(loginUser.getUserId());
        exampleDao.insert(example);
        //upload
        MultipartFile file = uploadExampleInDto.getExampleFile();
        String contentType = file.getContentType();
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String fileName = "use" + example.getUseCaseId() + fileType;
        //        fileName = file.getOriginalFilename();
        //        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);

        String filePath = getSession().getServletContext().getRealPath("exampleUpload/");
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            throw new CustomException("上传失败", e);
        }
        //example name(useCaseName-案例-timestamp.xxx)&url
        UseCase useCase = useCaseDao.selectByPrimaryKey(uploadExampleInDto.getUseCaseId());
        String useCaseName = useCase.getUseCaseName();
        String timeStr = new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());
        example.setExampleName(useCaseName + "-案例-" + timeStr + fileType);
        example.setExampleUrl("exampleUpload/" + fileName);
        exampleDao.updateByPrimaryKey(example);
        //useCase message
        useCase.setUseCaseExampleUrl(example.getExampleUrl());
        useCase.setUpdateTime(new Date());
        useCase.setUpdateUserId(loginUser.getUserId());
        useCaseDao.updateByPrimaryKeySelective(useCase);
        return example.getExampleId().toString();
    }

    /**
     * @Description：上传脚本
     * @param uploadScriptInDto
     * @return
     * @throws CustomException: 返回结果描述
     * @return String: 返回
     * @throws
     */
    @Transactional
    public String uploadScript(UploadScriptInDto uploadScriptInDto) throws CustomException {
        User loginUser = (User) getSession().getAttribute("loginUser");
        //change old script
        List<Integer> oldScriptId = scriptDao.selectOldScriptId(uploadScriptInDto.getUseCaseId());
        Script script = null;
        for (Integer sid : oldScriptId) {
            script = new Script();
            script.setScriptId(sid);
            script.setScriptStatus(-1);
            script.setUpdateTime(new Date());
            script.setUpdateUserId(loginUser.getUserId());
            scriptDao.updateByPrimaryKeySelective(script);
        }
        //script message
        script = new Script();
        script.setUseCaseId(uploadScriptInDto.getUseCaseId());
        script.setCreateTime(new Date());
        script.setCreateUserId(loginUser.getUserId());
        script.setUpdateTime(new Date());
        script.setUpdateUserId(loginUser.getUserId());
        script.setScriptStatus(0);
        scriptDao.insert(script);
        //upload
        MultipartFile file = uploadScriptInDto.getScriptFile();
        //        String contentType = file.getContentType();
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String fileName = script.getScriptId() + fileType;
        //        fileName = file.getOriginalFilename();
        //        System.out.println("fileName-->" + fileName);
        //        System.out.println("getContentType-->" + contentType);
        String filePath = getSession().getServletContext().getRealPath("scriptUpload/");
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            throw new CustomException("上传失败", e);
        }
        //changeExampleName
        try {
            if (!FileUtils.updateExcelNameInScript(filePath + fileName,
                    "exampleUpload/use" + uploadScriptInDto.getUseCaseId() + ".xlsx")) {
                //脚本中不包含excelName，删除并提示
                File f = new File(filePath);
                f.deleteOnExit();
                throw new CustomException("脚本中不包含'excelName'字符串，请修改后再上传");
            }
        } catch (IOException e) {
            logger.info("上传脚本时修改代码中案例文件名失败 用例id:" + uploadScriptInDto.getUseCaseId());
            throw new CustomException("修改脚本中的案例名失败", e);
        }
        //example url
        UseCase useCase = useCaseDao.selectByPrimaryKey(script.getUseCaseId());
        String useCaseName = useCase.getUseCaseName();
        String timeStr = new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());
        script.setScriptUrl("scriptUpload/" + fileName);
        script.setScriptName(useCaseName + "-脚本-" + timeStr + fileType);
        scriptDao.updateByPrimaryKey(script);
        //useCase
        useCase.setUseCaseScriptUrl(script.getScriptUrl());
        useCase.setUpdateTime(new Date());
        useCase.setUpdateUserId(loginUser.getUserId());
        useCaseDao.updateByPrimaryKeySelective(useCase);
        return script.getScriptId().toString();
    }

    /**
     * @Description：案例历史版本列表
     * @param exampleListInDto
     * @return: 返回结果描述
     * @return ExampleListOutDto: 返回值类型
     * @throws
     */
    public ExampleListOutDto getExampleList(ExampleListInDto exampleListInDto) {
        ExampleListOutDto exampleListOutDto = new ExampleListOutDto();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("useCaseId", exampleListInDto.getUseCaseId());
        queryMap.put("pageNow", exampleListInDto.getPageDto().getLimitPageNow());
        queryMap.put("pageSize", exampleListInDto.getPageDto().getPageSize());
        List<ExampleDto> exampleList = exampleDao.selectList(queryMap);
        Integer count = exampleDao.selectCount(exampleListInDto.getUseCaseId());
        exampleListOutDto.setExampleList(exampleList);
        exampleListOutDto.setPageDto(new PageDto(exampleListInDto.getPageDto().getPageNow(),
                exampleListInDto.getPageDto().getPageSize(), count));
        exampleListOutDto.setUseCaseId(exampleListInDto.getUseCaseId());
        return exampleListOutDto;
    }

    /**
     * @Description：脚本历史版本列表
     * @param scriptListInDto
     * @return: 返回结果描述
     * @return ScriptListOutDto: 返回值类型
     * @throws
     */
    public ScriptListOutDto getScriptList(ScriptListInDto scriptListInDto) {
        ScriptListOutDto scriptListOutDto = new ScriptListOutDto();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("useCaseId", scriptListInDto.getUseCaseId());
        queryMap.put("pageNow", scriptListInDto.getPageDto().getLimitPageNow());
        queryMap.put("pageSize", scriptListInDto.getPageDto().getPageSize());
        List<ScriptDto> scriptList = scriptDao.selectList(queryMap);
        Integer count = scriptDao.selectCount(scriptListInDto.getUseCaseId());
        scriptListOutDto.setScriptList(scriptList);
        scriptListOutDto.setPageDto(new PageDto(scriptListInDto.getPageDto().getPageNow(),
                scriptListInDto.getPageDto().getPageSize(), count));
        scriptListOutDto.setUseCaseId(scriptListInDto.getUseCaseId());
        return scriptListOutDto;
    }

    /**
     * @Description：用例联动列表
     * @param modelId
     * @return: 返回结果描述
     * @return List<UseCaseIdNameDto>: 返回值类型
     * @throws
     */
    public List<UseCaseIdNameDto> findUseCaseIdName(Integer modelId) {
        return useCaseDao.findUseCaseIdName(modelId);
    }

    /**
     * @Description：获得案例下载时显示的名字、案例路径
     * @param exampleId
     * @return: 返回结果描述
     * @return ExampleNameAndUrl: 返回值类型
     * @throws
     */
    public ExampleNameAndUrl getExampleFileNameAndUrl(Integer exampleId) {
        ExampleNameAndUrl exampleNameAndUrl = new ExampleNameAndUrl();
        Example example = exampleDao.selectByPrimaryKey(exampleId);
        exampleNameAndUrl.setExampleUrl(example.getExampleUrl());
        exampleNameAndUrl.setExampleName(example.getExampleName());
        return exampleNameAndUrl;
    }

    /**
     * @Description：获得脚本下载时显示的名字、脚本路径
     * @param scriptId
     * @return: 返回结果描述
     * @return ScriptNameAndUrl: 返回值类型
     * @throws
     */
    public ScriptNameAndUrl getScriptFileNameAndUrl(Integer scriptId) {
        ScriptNameAndUrl scriptNameAndUrl = new ScriptNameAndUrl();
        Script script = scriptDao.selectByPrimaryKey(scriptId);
        scriptNameAndUrl.setScriptUrl(script.getScriptUrl());
        scriptNameAndUrl.setScriptName(script.getScriptName());
        return scriptNameAndUrl;
    }
}
