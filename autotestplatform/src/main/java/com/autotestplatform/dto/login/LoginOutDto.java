package com.autotestplatform.dto.login;

import java.io.Serializable;

/**
 * 登录dto
 * @author : 孔德华
 * @date : 2018年5月7日 下午7:01:55  
 * @version : 2018年5月7日  孔德华 创建dto
 */
public class LoginOutDto implements Serializable {

    /** 
    * @Fields serialVersionUID
    */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer           userId;

    /**
     * 账号
     */
    private String            userName;

    /**
     * 提示信息
     */
    private String            message;

    /**
     * 是否登录成功 0否1是
     */
    private int               haveSuccess      = 0;

    public LoginOutDto() {
        super();
    }

    public LoginOutDto(Integer userId, String userName, String message, int haveSuccess) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.message = message;
        this.haveSuccess = haveSuccess;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
