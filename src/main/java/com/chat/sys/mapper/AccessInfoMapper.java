package com.chat.sys.mapper;

import com.chat.sys.entity.AccessInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccessInfoMapper {

    /**
     * 插入用户信息
     * @param accessInfo
     * @return
     */
    boolean insertAccessInfo(AccessInfo accessInfo);

    List<AccessInfo> queryByAccessCode(Integer userNo);

    Boolean updateAccessInfo(AccessInfo accessInfo);

    List<AccessInfo> queryAccess(AccessInfo accessInfo);

    boolean deleteAccessInfo(AccessInfo accessInfo);

}
