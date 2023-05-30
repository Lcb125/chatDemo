package com.chat.sys.mapper;

import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.ConfigInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfigInfoMapper {
    String queryByCodeKey(String codeKey);

    List<ConfigInfo> queryConfig(ConfigInfo configInfo);

    boolean deleteConfigInfo(ConfigInfo configInfo);

    boolean updateConfigInfo(ConfigInfo configInfo);
}
