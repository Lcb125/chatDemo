package com.chat.sys.service;

import com.chat.sys.mapper.QueryCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryCodeService {

    @Autowired
    QueryCodeMapper queryCodeMapper;

    public String queryByCodeKey(String ifCheck) {
        return queryCodeMapper.queryByCodeKey(ifCheck);
    }
}
