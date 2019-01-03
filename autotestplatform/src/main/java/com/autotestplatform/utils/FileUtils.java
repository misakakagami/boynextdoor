package com.autotestplatform.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.autotestplatform.vo.FileMsg;
import com.mysql.jdbc.log.Log;

public class FileUtils {

    /**
     * @Description：上传文件
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * @throws IOException 
     * @throws FileNotFoundException 
     * @Description：修改脚本中的案例文件名【将制定字符串"excelName"替换】
     * @param path: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    public static boolean updateExcelNameInScript(String path, String changeName) throws IOException {
        RandomAccessFile rf = new RandomAccessFile(path, "rw");
        String line = null;
        long lastPoint = 0;
        boolean isChange = false;
        try {
            while ((line = rf.readLine()) != null) {
                final long point = rf.getFilePointer();
                if (line.contains("excelName")) {
                    line = line.replace(line.substring(line.indexOf("excelName"), line.length() - 2),
                            changeName + "\"#");
                    rf.seek(lastPoint);
                    rf.writeBytes(line);
                    isChange = true;
                    break;
                }
                lastPoint = point;
            }
        } catch (IOException e) {
            throw e;
        } finally {
            rf.close();
        }
        return isChange;
    }

    /**
     * @Description：获得稳健目录
     * @param path
     * @return: 返回结果描述
     * @return List<FileMsg>: 返回值类型
     * @throws
     */
    public static List<FileMsg> getFileInPath(String path) {
        File f = new File(path);
        File[] tempList = f.listFiles();
        List<FileMsg> fileMsgList = new ArrayList<FileMsg>();
        for (File tempF : tempList) {
            if (tempF.isFile()) {
                fileMsgList.add(new FileMsg(
                        tempF.getPath().substring(tempF.getPath().indexOf("scriptUpload")).replaceAll("\\\\", "/"),
                        tempF.getName(), "file"));
            } else if (tempF.isDirectory()) {
                fileMsgList.add(new FileMsg(
                        tempF.getPath().substring(tempF.getPath().indexOf("scriptUpload")).replaceAll("\\\\", "/"),
                        tempF.getName(), "directory"));
            }
        }
        return fileMsgList;
    }

    /**
     * @Description：创建文件夹
     * @return : 1成功 2文件夹已存在
     * @throws
     */
    public static Integer createFile(String path) {
        File f = new File(path);
        if (f.exists()) {
            return 2;
        }
        f.mkdirs();
        return 1;
    }

    /**
     * @Description：删除文件
     * @return
     * @throws
     */
    public static void deleteFile(String path) {
        File f = new File(path);
        f.deleteOnExit();
    }

    /**
     * 读取txt
     * @param url
     * @return 失败数-总数-失败案例的用例号+++测试点
     */
    public static String readTxt(String url) {
        File f = new File(url);
        StringBuffer sb = new StringBuffer("");
        StringBuffer fai = new StringBuffer("");
        try {
            if (f.isFile() && f.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(f), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String tempStr = null;
                String pointStr = null;
                //errorstatus与casesatue为处理日志输出多次统计结果特殊处理                
                Boolean errorstatus = false;
                Boolean casesatue = false;
                while ((tempStr = br.readLine()) != null) {
                    try {
                        if (tempStr.contains("测试点:")) {
                            pointStr = tempStr.substring(tempStr.indexOf("测试点:") + 4);
                        }
                        if (tempStr.contains("error message: ") && errorstatus == false) {
                            tempStr = br.readLine();
                            if (tempStr.contains(" faild*****")) {
                                fai.append("+++").append(
                                        tempStr.substring(tempStr.indexOf("***case") + 7, tempStr.indexOf("faild***")))
                                        .append(":").append(pointStr);
                                errorstatus = true;
                            }
                        }
                        if (tempStr.contains("case count:") && casesatue == false) {
                            sb.append(tempStr.substring(tempStr.indexOf("faild count:") + 12)).append("-")
                                    .append(tempStr.substring(tempStr.indexOf("case count: ") + 11,
                                            tempStr.indexOf(", success count:")));
                            casesatue = true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                br.close();
                isr.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sb.length() < 2) {
            sb.append("0-0-执行出错");
            fai.append("+++");
        }
        if (fai.length() < 2) {
            fai.append("无");
        }
        return sb.append("-").append(fai).toString();
    }

}
