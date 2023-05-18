package com.chat.sys.entity;


/**
 *
 */

public class ChatGPTReq {
    private String content;

    private String code;

    @Override
    public String toString() {
        return "ChatGPTReq{" +
                "content='" + content + '\'' +
                ", code='" + code + '\'' +
                ", userNo=" + userNo +
                ", uniqueIdentification='" + uniqueIdentification + '\'' +
                '}';
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getUniqueIdentification() {
        return uniqueIdentification;
    }

    public void setUniqueIdentification(String uniqueIdentification) {
        this.uniqueIdentification = uniqueIdentification;
    }

    /**
     * 用户编码
     */
    private Integer userNo;

    /**
     * 设备唯一标识
     */
    private String uniqueIdentification;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
