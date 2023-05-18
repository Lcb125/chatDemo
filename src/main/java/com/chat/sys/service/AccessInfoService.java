package com.chat.sys.service;

import com.chat.sys.entity.AccessInfo;
import com.chat.sys.mapper.AccessInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessInfoService {

    @Autowired
    private AccessInfoMapper accessInfoMapper;

    /**
     * 插入用户信息
     * @param user
     * @return
     */
    public boolean insertAccessInfo(AccessInfo user) {

        return accessInfoMapper.insertAccessInfo(user);
    }

    public List<AccessInfo> queryByAccessCode(Integer userNo) {
        return accessInfoMapper.queryByAccessCode(userNo);
    }

    public Boolean updateAccessInfo(AccessInfo accessInfo) {
        return accessInfoMapper.updateAccessInfo(accessInfo);
    }

    public List<AccessInfo> queryAccess() {
        return accessInfoMapper.queryAccess();
    }
}
