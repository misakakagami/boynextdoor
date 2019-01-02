package com.autotestplatform.dto.createuser;

import java.io.Serializable;

import com.autotestplatform.entity.User;

/**
 * 创建用户UserOutDto
 * @author : 孔德华
 * @date : 2018年5月8日 下午4:19:14  
 * @version : 2018年5月8日  孔德华 创建类
 */
public class UserOutDto implements Serializable{

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */  
    private static final long serialVersionUID = 1695216118924629095L;
    
    private User user;
    
    private String message;
    
    /**
     * 是否成功 0否1是
     */
    private int haveSuccess = 0;
    
    public UserOutDto() {
        super();
    }

    public UserOutDto(User user, String message, int haveSuccess) {
        super();
        this.user = user;
        this.message = message;
        this.haveSuccess = haveSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHaveSuccess() {
        return haveSuccess;
    }

    public void setHaveSuccess(int haveSuccess) {
        this.haveSuccess = haveSuccess;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
