package com.autotestplatform.facade.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autotestplatform.contants.Contants;
import com.autotestplatform.dto.createuser.UserInDto;
import com.autotestplatform.dto.createuser.UserOutDto;
import com.autotestplatform.dto.login.LoginInDto;
import com.autotestplatform.dto.login.LoginOutDto;
import com.autotestplatform.entity.ConfigContent;
import com.autotestplatform.facade.base.BaseFacade;
import com.autotestplatform.service.config.ConfigService;
import com.autotestplatform.service.index.UserService;
import com.autotestplatform.utils.CustomException;

/**
 * 外观模式 外观类
 * @author : 孔德华
 * @date : 2018年5月12日 下午2:06:30  
 * @version : 2018年5月12日  孔德华 创建类，添加创建用户方法
 */
@Service
public class UserFacade extends BaseFacade {

    @Autowired
    private UserService   userService;

    @Autowired
    private ConfigService configService;

    /**
     * @throws CustomException 
     * @Description：创建用户
     * @param userInDto
     * @return: 返回结果描述
     * @return UserOutDto: 返回值类型
     * @throws
     */
    @Transactional
    public UserOutDto createUser(UserInDto userInDto) throws CustomException {
        UserOutDto userOutDto = userService.createUser(userInDto);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userOutDto.getUser().getUserId().toString());
        configService.saveUserSelfConfigToDao(map);
        return userOutDto;
    }

    /**
     * @Description：登录
     * @param loginInDto
     * @throws CustomException: 返回结果描述
     * @return loginOutDto: 返回值类型
     * @throws
     */
    @Transactional
    public LoginOutDto login(LoginInDto loginInDto) throws CustomException {
        //验证账号密码
        LoginOutDto loginOutDto = userService.login(loginInDto);
        if (loginOutDto.getHaveSuccess() == 1) {
            //加载用户配置
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("createUserId", loginOutDto.getUserId().toString());
            queryMap.put("configType", Contants.USERSELFCONFIG);
            List<ConfigContent> ccList = configService.getConfigContentByCondition(queryMap);
            HttpSession session = getSession();
            for (ConfigContent cc : ccList) {
                session.setAttribute(cc.getConfigName(), cc.getConfigContent());
            }
        }
        return loginOutDto;
    }
}
