package com.autotestplatform.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.autotestplatform.contants.Contants;

/**
 * 过滤器
 * @author : 孔德华
 * @date : 2018年5月9日 下午2:47:27  
 * @version : 2018年5月9日  孔德华 创建类
 */
@Component
public class UserInterceptor implements HandlerInterceptor {

    /**
     * 未登录、注销用户需重新登录
     * 不需改变url的请求：
     * "/,/login,/needLogin"
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getSession().getAttribute("loginUser") != null) {
            return true;
        }
        response.sendRedirect(Contants.NEEDLOGINURL);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
