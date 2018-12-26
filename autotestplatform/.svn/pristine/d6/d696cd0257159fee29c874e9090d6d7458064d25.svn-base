package com.autotestplatform.controller.pymanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.autotestplatform.controller.base.BaseController;
import com.autotestplatform.dto.pymanage.FileListInDto;
import com.autotestplatform.dto.pymanage.UploadFileDto;
import com.autotestplatform.service.pymanage.PyManageService;
import com.autotestplatform.utils.CustomException;

@Controller
@RequestMapping(value = "/pymanage")
public class PyManageController extends BaseController {

    @Autowired
    private PyManageService pyManageService;

    /**
     * @Description：文件列表
     * @param fileListInDto
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/findList")
    public ModelAndView findFileList(FileListInDto fileListInDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("fileListOutDto", pyManageService.findFileList(fileListInDto));
        modelAndView.setViewName("ftl/index/fileList");
        return modelAndView;
    }

    /**
     * @Description：创建文件夹
     * @param 
     * @return: 返回结果描述
     * @return 
     * @throws
     */
    @RequestMapping(value = "/createFile")
    @ResponseBody
    public String createFile(String path) {
        Integer res = pyManageService.createFile(path);
        if (res == 1) {
            return "success";
        }
        return "exsist";
    }

    /**
     * @Description：删除文件
     * @param 
     * @return: 返回结果描述
     * @return 
     * @throws
     */
    @RequestMapping(value = "/deleteFile")
    @ResponseBody
    public String deleteFile(String path) {
        pyManageService.deleteFile(path);
        return "success";
    }

    /**
     * @Description：上传文件
     * @param 
     * @return: 返回结果描述
     * @return
     * @throws
     */
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public String uploadFile(UploadFileDto uploadFileDto) {
        try {
            pyManageService.uploadFile(uploadFileDto);
        } catch (CustomException e) {
            e.printStackTrace();
            return "faild";
        }
        return "success";
    }
}
