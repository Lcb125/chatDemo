package com.chat.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
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
     * 配置描述
     */
    private String status;

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

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private Integer updateBy;

}
