package com.autotestplatform.dto.createuser;

import java.io.Serializable;

import com.autotestplatform.entity.User;

/**
 * 创建用户UserInDto
 * @author : 孔德华
 * @date : 2018年5月8日 下午4:18:45  
 * @version : 2018年5月8日  孔德华 创建类
 */
public class UserInDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */  
    private static final long serialVersionUID = -3451330691289890692L;
    
    private User user;
    
    public UserInDto() {
        super();
    }

    public UserInDto(User user) {
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
