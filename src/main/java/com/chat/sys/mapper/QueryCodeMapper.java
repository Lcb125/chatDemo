package com.chat.sys.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QueryCodeMapper {
    String queryByCodeKey(String codeKey);
}
