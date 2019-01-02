package com.autotestplatform.entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 6268263749740490480L;

    private Integer           messageId;

    private String            messageName;

    private String            messageContent;

    private Integer           messageType;

    private Integer           foreignId;

    private Integer           messageStatus;

    private Date              createTime;

    private Date              updateTime;

    private Integer           createUserId;

    private Integer           updateUserId;

    public Message(Integer messageId, String messageName, String messageContent, Integer messageType, Integer foreignId,
                   Integer messageStatus, Date createTime, Date updateTime, Integer createUserId,
                   Integer updateUserId) {
        super();
        this.messageId = messageId;
        this.messageName = messageName;
        this.messageContent = messageContent;
        this.messageType = messageType;
        this.foreignId = foreignId;
        this.messageStatus = messageStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName == null ? null : messageName.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getForeignId() {
        return foreignId;
    }

    public void setForeignId(Integer foreignId) {
        this.foreignId = foreignId;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
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
