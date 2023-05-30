package com.chat.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户类型
     */
    private String userType;

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
