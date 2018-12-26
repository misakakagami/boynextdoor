package com.autotestplatform.facade.base;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BaseFacade {

    protected Logger logger;

    public BaseFacade() {
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
