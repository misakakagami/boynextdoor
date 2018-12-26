package com.autotestplatform.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.autotestplatform.controller.base.BaseController;
import com.autotestplatform.dto.createuser.UserInDto;
import com.autotestplatform.dto.createuser.UserOutDto;
import com.autotestplatform.dto.login.LoginInDto;
import com.autotestplatform.dto.login.LoginOutDto;
import com.autotestplatform.dto.login.LoginOutInDto;
import com.autotestplatform.facade.user.UserFacade;
import com.autotestplatform.service.index.UserService;
import com.autotestplatform.utils.CustomException;

/**
 * index
 * @author : 孔德华
 * @date : 2018年5月8日 下午2:57:28  
 * @version : 2018年5月8日  孔德华 创建类
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFacade  baseFacade;

    /**
     * @Description：跳转至登录界面
     * @return: 登录页面视图
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView toLogin() {
        logger.info("打开登录页面");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ftl/page/login");
        return modelAndView;
    }

    /**
     * @Description：跳转至创建用户界面
     * @return: 创建用户页面视图
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/cu", method = RequestMethod.GET)
    public ModelAndView toCreateUser() {
        logger.info("打开创建用户页面");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ftl/index/createUser");
        return modelAndView;
    }

    /**
     * @Description：跳转至需要登录界面
     * @return: 创建用户页面视图
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/needLogin", method = RequestMethod.GET)
    public ModelAndView toNeedLoginPage() {
        logger.info("打开登录用户页面");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "用户超时，请重新登录");
        modelAndView.setViewName("ftl/page/login");
        return modelAndView;
    }

    /**
     * @Description：登录操作
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(LoginInDto loginInDto) throws CustomException {
        logger.info("用户:" + loginInDto.getUserName() + " 开始登录操作");
        ModelAndView modelAndView = new ModelAndView();
        LoginOutDto loginOutDto = baseFacade.login(loginInDto);
        modelAndView.addObject("loginOutDto", loginOutDto);
        if (loginOutDto.getHaveSuccess() == 1) {
            modelAndView.setViewName("ftl/page/index");
        } else {
            modelAndView.addObject("errorMessage", loginOutDto.getMessage());
            modelAndView.setViewName("ftl/page/login");
        }
        return modelAndView;
    }

    /**
     * @Description：创建用户
     * @return: 返回结果描述
     * @return ModelAndView: 返回值类型
     * @throws
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ModelAndView createUser(UserInDto userInDto) throws CustomException {
        logger.info("创建用户操作开始");
        ModelAndView modelAndView = new ModelAndView();
        UserOutDto userOutDto = baseFacade.createUser(userInDto);
        modelAndView.addObject("loginOutDto.userName", userOutDto.getUser().getUserName());
        modelAndView.setViewName("ftl/page/index");
        logger.info("创建用户操作完成");
        return modelAndView;
    }

    /**
     * @Description：退出
     * @return: 登录页面视图
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public ModelAndView loginOut(LoginOutInDto loginOutInDto) {
        logger.info("用户退出");
        ModelAndView modelAndView = new ModelAndView();
        userService.loginOut(loginOutInDto);
        modelAndView.setViewName("ftl/page/login");
        return modelAndView;
    }
}
