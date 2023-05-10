package com.java.common;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String str) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = df.parse(str);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
