package com.chat.sys.handler;

import com.chat.common.TokenUtils;
import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.Enum.RespEnum;
import com.chat.sys.entity.ResponseResult;
import com.chat.sys.entity.UserInfo;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    private final static String ADMIN = "admin";

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    TokenUtils tokenUtils;


    @PostMapping(value = "/insertUser")
    @ResponseBody
    public ResponseResult insertUser(@RequestBody UserInfo userInfo)  {

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
        System.out.println("userinfo"+userInfo);

        UserInfo resultUser = userInfoService.queryUserInfo(userInfo);

        System.out.println("resultUser"+resultUser);
        if (resultUser!=null){
            String token = tokenUtils.getToken(userInfo.getUserName(), userInfo.getPassword());
//            boolean equals = StringUtils.equals(ADMIN, resultUser.getUserType());
            return new ResponseResult(RespEnum.SUCCESS,token);
        }
        return new ResponseResult(RespEnum.FAIL,"用户名或密码错误");
    }

    @PostMapping(value = "/updateUser")
    @ResponseBody
    public ResponseResult updateUser(@RequestBody UserInfo userInfo)  {

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

        boolean result = userInfoService.deleteUserInfo(userInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息删除成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息删除失败");
        }
    }


    @PostMapping(value = "/queryUsers")
    @ResponseBody
    public ResponseResult queryUsers(@RequestBody PageRequest pageRequest)  {

        System.out.println("222222222222");

        PageResponse pageResponse = userInfoService.queryUsersInfo(pageRequest);

        if (pageResponse.getListInfo().size()>0){

            System.out.println("111111111111111");
            return new ResponseResult(RespEnum.SUCCESS,pageResponse);
        }
        return new ResponseResult(RespEnum.FAIL,"未查询到用户信息");
    }


}
