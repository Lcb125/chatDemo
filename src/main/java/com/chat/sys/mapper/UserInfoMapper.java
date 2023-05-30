package com.chat.sys.mapper;

import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    boolean insertUserInfo(UserInfo userInfo);

    UserInfo queryUserInfo(UserInfo userInfo);

    boolean updateUserInfo(UserInfo userInfo);

    boolean deleteUserInfo(UserInfo userInfo);

    List<UserInfo> queryUsers();
}
