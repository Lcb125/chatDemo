package com.chat.sys.entity;

import lombok.Data;

@Data
public class ResponseResult {

    private Integer code;

    private String message;

    private Object data;

    public ResponseResult(ResultCode resultCode, Object data){
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public ResponseResult(int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult() {
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
