package com.autotestplatform.service.index;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.autotestplatform.contants.Contants;
import com.autotestplatform.dao.UserMapper;
import com.autotestplatform.dto.createuser.UserInDto;
import com.autotestplatform.dto.createuser.UserOutDto;
import com.autotestplatform.dto.login.LoginOutInDto;
import com.autotestplatform.dto.login.LoginInDto;
import com.autotestplatform.dto.login.LoginOutDto;
import com.autotestplatform.dto.register.RegisterInDto;
import com.autotestplatform.dto.register.RegisterOutDto;
import com.autotestplatform.entity.User;
import com.autotestplatform.service.base.BaseService;
import com.autotestplatform.utils.CustomException;
import com.autotestplatform.utils.MD5Utils;

/**
 * 用户业务
 * @author : 孔德华
 * @date : 2018年5月7日 下午7:17:32  
 * @version : 2018年5月7日 孔德华 创建类
 */
@Service
public class UserService extends BaseService {

    @Autowired
    private UserMapper userDao;

    /**
     * @throws  
     * @Description：登录操作
     * @param loginInDto
     * @return: 返回结果描述
     * @return loginOutDto: 返回值类型
     * @throws
     */
    public LoginOutDto login(LoginInDto loginInDto) throws CustomException {
        LoginOutDto loginOutDto = new LoginOutDto();
        User user = userDao.selectByUserName(loginInDto.getUserName());
        loginOutDto.setUserName(loginInDto.getUserName());
        if (user == null) {
            loginOutDto.setMessage("账号不存在");
            return loginOutDto;
        }
        loginOutDto.setUserId(user.getUserId());
        String realPassword;
        try {
            realPassword = MD5Utils.getMD5(loginInDto.getPassWord(), user.getSalt());
        } catch (NoSuchAlgorithmException e) {
            logger.info("用户 " + loginOutDto.getUserName() + " 登录时校验异常");
            throw new CustomException("登录失败", e);
        }
        if (realPassword.equals(user.getPassword())) {
            loginOutDto.setHaveSuccess(1);
            loginOutDto.setMessage("登录成功");
            //存入session
            getSession().setAttribute("loginUser", user);
            logger.info("用户: " + loginOutDto.getUserName() + " 已登录");
        } else {
            loginOutDto.setMessage("账号或密码错误");
        }
        return loginOutDto;
    }

    /**
     * @Description：创建用户
     * @param User
     * @return: 返回结果描述
     * @return User
     * @throws
     */
    @Transactional
    public UserOutDto createUser(UserInDto userInDto) throws CustomException {
        UserOutDto userOutDto = new UserOutDto();
        User user = userInDto.getUser();
        try {
            if (userDao.selectUserCountByUserName(user.getUserName()) > 0) {
                //用户名重复
                userOutDto.setUser(user);
                userOutDto.setHaveSuccess(0);
                userOutDto.setMessage("用户名已存在！");
                return userOutDto;
            }
            StringBuffer salt = new StringBuffer("");
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                salt.append(Contants.CHARARRAY[random.nextInt(62)]);
            }
            String realPassword = MD5Utils.getMD5(user.getPassword(), salt.toString());
            user.setSalt(salt.toString());
            user.setPassword(realPassword);
            //非空字段
            user.setUserStatus(0);
            userDao.insertSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("创建用户失败");
            throw new CustomException("创建用户失败", e);
        }
        userOutDto.setUser(user);
        userOutDto.setHaveSuccess(1);
        userOutDto.setMessage("创建用户成功");
        return userOutDto;
    }

    /**
     * @Description：注册-暂留
     * @param loginInDto
     * @return: 返回结果描述
     * @return RegisterOutDto: 返回值类型
     * @throws
     */
    @Transactional
    public RegisterOutDto register(RegisterInDto loginInDto) throws CustomException {
        RegisterOutDto registerOutDto = new RegisterOutDto();
        return registerOutDto;
    }

    /**
     * @Description：注销
     * @param loginOutInDto
     * @throws
     */
    public void loginOut(LoginOutInDto loginOutInDto) {
        //清除session
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        request.getSession().invalidate();
        logger.info("用户: " + loginOutInDto.getUserName() + " 已退出");
    }
}
