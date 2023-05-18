package com.chat.sys.entity;

import lombok.Data;

import java.util.Date;


public class AccessInfo {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户编码
     */
    private Integer accessCode;

    /**
     * 设备唯一标识
     */
    private String deviceId;

    /**
     * 用户等级（用来区别用户每天可使用次数）
     */
    private String accessLevel;

    /**
     * 用户等级（用来区别用户每天可使用次数）
     */
    private String accessType;

    /**
     * 聊天可用次数
     */
    private Integer availableNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(Integer accessCode) {
        this.accessCode = accessCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(Integer availableNum) {
        this.availableNum = availableNum;
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

    @Override
    public String toString() {
        return "AccessInfo{" +
                "id=" + id +
                ", accessCode=" + accessCode +
                ", deviceId='" + deviceId + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", accessType='" + accessType + '\'' +
                ", availableNum=" + availableNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
