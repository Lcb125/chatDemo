package com.chat.sys.handler;

import com.chat.common.TokenUtils;
import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.Enum.RespEnum;
import com.chat.sys.entity.ResponseResult;
import com.chat.sys.entity.UserInfo;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.mapper.UserInfoMapper;
import com.chat.sys.service.UserInfoService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserInfoController {

    private final static String ADMIN = "admin";

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UserInfoMapper userInfoMapper;


    @PostMapping(value = "/insertUser")
    @ResponseBody
    public ResponseResult insertUser(@RequestBody UserInfo userInfo)  {
        UserInfo user = new UserInfo();
        user.setUserName(userInfo.getUserName());
        user.setUserStatus("Y");
        List<UserInfo> userInfos = userInfoMapper.queryUsers(user);
        if (userInfos.size()>0){
            return new ResponseResult(RespEnum.EXIST,"当前用户已存在");
        }

        userInfo.setUserStatus("Y");
        boolean result = userInfoService.insertUserInfo(userInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息插入成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息插入失败");
        }
    }


    @PostMapping(value = "/queryUser")
    @ResponseBody
    public ResponseResult queryUser(@RequestBody UserInfo userInfo)  {
        log.info("userinfo"+userInfo);

        List<UserInfo> resultUser = userInfoService.queryUserInfo(userInfo);
        if (resultUser.size()>0){
            String token = tokenUtils.getToken(userInfo.getUserName(), userInfo.getPassword());
//            boolean equals = StringUtils.equals(ADMIN, resultUser.getUserType());
            return new ResponseResult(RespEnum.SUCCESS,token);
        }
        return new ResponseResult(RespEnum.FAIL,"用户名或密码错误");
    }

    @PostMapping(value = "/updateUser")
    @ResponseBody
    public ResponseResult updateUser(@RequestBody UserInfo userInfo)  {

        userInfo.setUpdateTime(new Date());
        boolean result = userInfoService.updateUserInfo(userInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息插入成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息插入失败");
        }
    }

    @PostMapping(value = "/deleteUser")
    @ResponseBody
    public ResponseResult deleteUser(@RequestBody UserInfo userInfo)  {
//        String token = request.getHeader("authToken");
//        String name = tokenUtils.verify(token);
//        if (!"admin".equals(name)){
//            return new ResponseResult(RespEnum.NOAUTH,"无管理员权限");
//        }

        userInfo.setUserStatus("N");
        boolean result = userInfoService.deleteUserInfo(userInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息删除成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息删除失败");
        }
    }


    @PostMapping(value = "/queryUsers")
    @ResponseBody
    public ResponseResult queryUsers(@RequestBody PageRequest pageRequest,HttpServletRequest request)  {

        String token = request.getHeader("authToken");
        String result = tokenUtils.verify(token);
        String[] split = result.split("-");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(split[0]);
        userInfo.setPassword(split[1]);
        PageResponse pageResponse = userInfoService.queryUsersInfo(pageRequest);
        List<UserInfo> userInfos = userInfoService.queryUserInfo(userInfo);
        if (userInfos.size()>0){
            UserInfo user = userInfos.get(0);
            pageResponse.setUserType(user.getUserType());
        }


        if (pageResponse.getListInfo().size()>0){

            return new ResponseResult(RespEnum.SUCCESS,pageResponse);
        }
        return new ResponseResult(RespEnum.FAIL,"未查询到用户信息");
    }


}
