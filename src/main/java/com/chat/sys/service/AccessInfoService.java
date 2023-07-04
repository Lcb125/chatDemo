package com.chat.sys.service;

import com.chat.common.PageUtils;
import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.mapper.AccessInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    public List<AccessInfo> queryAccess(AccessInfo accessInfo) {
        return accessInfoMapper.queryAccess(accessInfo);
    }

    public PageResponse findPage(PageRequest pageRequest){
        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }


    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<AccessInfo> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        AccessInfo accessInfo = new AccessInfo();
        if (pageRequest.getCode() != 0){
            accessInfo.setAccessCode(pageRequest.getCode());
        }
        log.info("accessInfo=============="+accessInfo);
        List<AccessInfo> sysMenus = accessInfoMapper.queryAccess(accessInfo);
        return new PageInfo<AccessInfo>(sysMenus);
    }

    public boolean deleteAccessInfo(AccessInfo accessInfo) {
        return accessInfoMapper.deleteAccessInfo(accessInfo);
    }

}
