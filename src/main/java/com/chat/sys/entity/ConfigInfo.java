package com.chat.sys.entity;

import java.util.Date;

public class ConfigInfo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 配置类型
     */
    private String codeType;

    /**
     * 配置key
     */
    private String codeKey;

    /**
     * 配置value
     */
    private String codeValue;

    /**
     * 配置描述
     */
    private String codeDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private Integer updateBy;

}
