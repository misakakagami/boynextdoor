package com.autotestplatform.service.base;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 业务层基础类
 * @author : 孔德华
 * @date : 2018年5月8日 下午3:48:40  
 * @version : 2018年5月8日  孔德华 创建类
 */
public abstract class BaseService {

    protected Logger logger;

    public BaseService() {
        this.logger = LoggerFactory.getLogger(super.getClass());
    }

    /**
     * @Description：获得session
     * @return: 返回结果描述
     * @return HttpSession: 返回值类型
     * @throws
     */
    public HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

}
