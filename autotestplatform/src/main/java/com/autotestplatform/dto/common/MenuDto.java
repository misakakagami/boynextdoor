package com.autotestplatform.dto.common;

import java.io.Serializable;
import java.util.Date;

public class MenuDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 6504796283417242932L;

    private Integer           menuId;

    private String            menuName;

    private Integer           menuLevel;

    private Integer           menuStatus;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    public MenuDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MenuDto(Integer menuId, String menuName, Integer menuLevel, Integer menuStatus, Date createTime,
                   Date updateTime, Integer createUserId, Integer updateUserId) {
        super();
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuLevel = menuLevel;
        this.menuStatus = menuStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
}
