package com.autotestplatform.service.pymanage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.autotestplatform.dto.pymanage.FileListInDto;
import com.autotestplatform.dto.pymanage.FileListOutDto;
import com.autotestplatform.dto.pymanage.UploadFileDto;
import com.autotestplatform.service.base.BaseService;
import com.autotestplatform.utils.CustomException;
import com.autotestplatform.utils.FileUtils;
import com.autotestplatform.utils.StringUtils;
import com.autotestplatform.vo.FileMsg;

@Service
public class PyManageService extends BaseService {

    /**
     * 文件列表
     * @Description：方法功能描述
     * @param fileListInDto
     * @return: 返回结果描述
     * @return FileListOutDto: 返回值类型
     * @throws
     */
    public FileListOutDto findFileList(FileListInDto fileListInDto) {
        FileListOutDto fileListOutDto = new FileListOutDto();
        if (StringUtils.isBlank(fileListInDto.getPath())) {
            //默认根路径
            fileListInDto.setPath("scriptUpload/aeonlifebase/");
        }
        List<FileMsg> fileList = FileUtils
                .getFileInPath(getSession().getServletContext().getRealPath(fileListInDto.getPath()));
        fileListOutDto.setFileList(fileList);
        fileListOutDto.setNowPath(fileListInDto.getPath());
        fileListOutDto.setLastPath(fileListInDto.getPath().substring(26).length() > 0 ? fileListInDto.getPath()
                : "scriptUpload/aeonlifebase/");
        return fileListOutDto;
    }

    /**
     * @Description：创建文件夹
     * @return 1成功 2已存在
     * @throws
     */
    public Integer createFile(String path) {
        Integer res = FileUtils.createFile(getSession().getServletContext().getRealPath(path));
        return res;
    }

    /**
     * @Description：删除文件
     * @return 
     * @throws
     */
    public void deleteFile(String path) {
        FileUtils.deleteFile(getSession().getServletContext().getRealPath(path));
    }

    /**
     * @Description：上传
     * @param uploadFileDto: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    public void uploadFile(UploadFileDto uploadFileDto) throws CustomException {
        //upload
        MultipartFile file = uploadFileDto.getFile();
        //        String contentType = file.getContentType();
        //        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String fileName = file.getOriginalFilename();
        String filePath = getSession().getServletContext().getRealPath(uploadFileDto.getPath());
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            throw new CustomException("上传失败", e);
        }
    }
}
