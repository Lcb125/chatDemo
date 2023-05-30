package com.chat.sys.entity.Enum;

import com.chat.sys.entity.ResultCode;

public enum RespEnum implements ResultCode {
    
    SUCCESS(200,"操作成功"),
    FIRST(300,"首次进入"),
    FAIL(500,"操作失败");


    int code;
    String message;

    RespEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RespEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
