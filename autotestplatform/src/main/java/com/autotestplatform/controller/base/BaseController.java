package com.autotestplatform.controller.base;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.autotestplatform.dto.common.PageDto;
import com.autotestplatform.utils.CustomException;
import com.autotestplatform.utils.ObjectUtils;

public abstract class BaseController {

    protected Logger logger;

    public BaseController() {
        this.logger = LoggerFactory.getLogger(super.getClass());
    }

    /**
     * @Description：控制层捕获自定义异常后跳转错误页面
     * @param e
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @ExceptionHandler({ CustomException.class })
    public ModelAndView handleCustomException(CustomException e) {
        ModelAndView modelAndView = new ModelAndView();
        this.logger.info(e.getMessage());
        modelAndView.addObject("errorInfo", e.getErrorCode());
        modelAndView.setViewName("error/error");
        return modelAndView;
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

    /**
     * @param <T>
     * @Description：为空时初始化实例
     * @param object: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    public <T> Object setValueIfNull(Object object, Class<T> clazz, Integer pageSize) {
        Object obj = null;
        try {
            obj = ObjectUtils.setValueIfNull(object, clazz, pageSize);
        } catch (Exception e) {
            logger.info(clazz.getName() + " 为空时实例化失败");
        }
        return obj;
    }
}
