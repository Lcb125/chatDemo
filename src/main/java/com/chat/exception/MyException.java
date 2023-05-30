package com.chat.exception;

import com.chat.sys.entity.Enum.RespEnum;
import lombok.Data;

@Data
public class MyException extends RuntimeException{
    private Integer code;

    private RespEnum respEnum;



    public MyException(String message, Integer code) {
        super(message);
        this.code = code;
    }


    public MyException(RespEnum respEnum) {
        this.respEnum = respEnum;
    }
}
