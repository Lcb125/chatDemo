package com.chat.sys.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MsgInfo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 配置类型
     */
    private String msgType;

    /**
     * 配置key
     */
    private String msgKey;

    /**
     * 配置value
     */
    private String msgValue;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
