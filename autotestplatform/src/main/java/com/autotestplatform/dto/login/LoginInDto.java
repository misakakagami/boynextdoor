package com.autotestplatform.dto.login;

import java.io.Serializable;

/**
 * 登录dto
 * @author : 孔德华
 * @date : 2018年5月7日 下午7:01:55  
 * @version : 2018年5月7日  孔德华 创建dto
 */
public class LoginInDto implements Serializable {
    
    /** 
    * @Fields serialVersionUID
    */  
    private static final long serialVersionUID = 1L;
    
    /**
     * 账号
     */
    private String userName;
    
    /**
     * 密码
     */
    private String passWord;
    
    public LoginInDto() {
        super();
    }

    public LoginInDto(String userName, String passWord) {
        super();
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
