package com.autotestplatform.dto.login;

import java.io.Serializable;

public class LoginOutOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = -5218121696455994803L;

    /**
     * 注销用户名
     */
    private String            userName;

    public LoginOutOutDto(String userName) {
        super();
        this.userName = userName;
    }

    public LoginOutOutDto() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
