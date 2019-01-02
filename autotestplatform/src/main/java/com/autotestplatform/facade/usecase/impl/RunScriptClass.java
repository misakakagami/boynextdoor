package com.autotestplatform.facade.usecase.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autotestplatform.vo.CaseResult;

public abstract class RunScriptClass {

    protected Logger logger;

    public RunScriptClass() {
        this.logger = LoggerFactory.getLogger(super.getClass());
    }

    /**
     * @Description：执行脚本
     * 超时时间 1小时
     * @return: 返回结果描述
     * @return String: 返回值类型
     * @throws
     */
    public CaseResult runScript(String scriptURL) {
        CaseResult caseResult = new CaseResult();
        try {
            final Process process = Runtime.getRuntime().exec("python " + "src/main/webapp/" + scriptURL);
            //            final Process process = Runtime.getRuntime().exec("python test1.py");
            //            System.out.println("1 " + System.getProperty("user.dir"));
            //            System.out.println("2 " + (((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
            //                    .getRequest().getSession()).getServletContext().getRealPath("scriptUpload/"));

            //            System.out.println("python " + scriptURL);
            final StringBuffer resultStr = new StringBuffer("");
            long startTime = System.currentTimeMillis() / 1000;
            new Thread() {
                @Override
                public void run() {
                    BufferedReader in = null;
                    try {
                        in = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    String str = null;
                    try {
                        while ((str = in.readLine()) != null) {
                            resultStr.append(str);
                        }
                        String logName = resultStr.toString();
                        System.out.println("aaaa " + logName);
                        caseResult.setLogUrl(
                                logName.substring(logName.indexOf("InterfaceDemoTest"), logName.indexOf(".log") + 4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    BufferedReader err = null;
                    try {
                        err = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        while (err.readLine() != null) {
                            //                            System.out.println("err: " + line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            err.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            int exitVal = process.waitFor();
            caseResult.setExitVal(exitVal);
        } catch (Exception e) {
            logger.info("script: " + scriptURL + " " + new Date() + "运行出错");
            e.printStackTrace();
            caseResult.setExitVal(-5);
        }
        return caseResult;
    }

}
