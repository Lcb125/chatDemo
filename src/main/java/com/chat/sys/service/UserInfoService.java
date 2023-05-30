package com.chat.sys.service;

import com.chat.common.PageUtils;
import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.UserInfo;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.mapper.UserInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    public boolean insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    public UserInfo queryUserInfo(UserInfo userInfo) {
        return userInfoMapper.queryUserInfo(userInfo);
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    public boolean deleteUserInfo(UserInfo userInfo) {
        return userInfoMapper.deleteUserInfo(userInfo);
    }

    public PageResponse queryUsersInfo(PageRequest pageRequest) {

        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<UserInfo> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> sysMenus = userInfoMapper.queryUsers();
        return new PageInfo<UserInfo>(sysMenus);
    }
}
