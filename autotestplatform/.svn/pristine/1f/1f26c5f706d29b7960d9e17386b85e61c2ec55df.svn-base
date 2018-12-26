package com.autotestplatform.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = -240631158373801486L;

    private Integer           userId;

    private String            nickName;

    private String            userName;

    private String            password;

    private Integer           userIdentityId;

    private Integer           userStatus;

    private Date              createTime;

    private Date              updateTime;

    private String            salt;

    public User() {
        super();
    }

    public User(Integer userId, String nickName, String userName, String password, Integer userIdentityId,
                Integer userStatus, Date createTime, Date updateTime, String salt) {
        super();
        this.userId = userId;
        this.nickName = nickName;
        this.userName = userName;
        this.password = password;
        this.userIdentityId = userIdentityId;
        this.userStatus = userStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.salt = salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUserIdentityId() {
        return userIdentityId;
    }

    public void setUserIdentityId(Integer userIdentityId) {
        this.userIdentityId = userIdentityId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
