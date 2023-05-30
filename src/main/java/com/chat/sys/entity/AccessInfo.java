package com.chat.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
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
     * 用户类型(主，次账户）
     */
    private String accessType;

    /**
     * 聊天可用次数
     */
    private Integer availableNum;

    /**
     * 启用状态
     */
    private String status;

    /**
     * 启用状态
     */
    private String language;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
