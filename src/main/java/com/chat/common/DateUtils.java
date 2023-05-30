package com.chat.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Slf4j
@Component
public class DateUtils {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";


    public String conversionDate(Date date){
        try {
            SimpleDateFormat simpleFormatter = new SimpleDateFormat(PATTERN);
            return simpleFormatter.format(date);
        }catch (Exception e){
            log.info("时间转换出错："+e.getMessage());
            return date.toString();
        }
    }
}
