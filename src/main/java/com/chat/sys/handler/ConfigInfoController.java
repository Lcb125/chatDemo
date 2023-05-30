package com.chat.sys.handler;

import com.chat.sys.entity.ConfigInfo;
import com.chat.sys.entity.Enum.RespEnum;
import com.chat.sys.entity.ResponseResult;
import com.chat.sys.entity.UserInfo;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.service.ConfigInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/config")
public class ConfigInfoController {




    @Value("${switch.ifCheck}")
    private String ifCheck;


    @Autowired
    ConfigInfoService configInfoService;


    @GetMapping(value = "/querySwitch")
    @ResponseBody
    public ResponseResult querySwitch() {
        String switchValue = configInfoService.queryByCodeKey(ifCheck);
        if (StringUtils.equals("true",switchValue)){
            return new ResponseResult(RespEnum.SUCCESS,"打开校验");
        }else {
            return new ResponseResult(RespEnum.FAIL,"关闭校验");
        }
    }


    @PostMapping(value = "/queryConfig")
    @ResponseBody
    public ResponseResult queryConfig(@RequestBody PageRequest pageRequest)  {

        PageResponse pageResponse = configInfoService.queryConfigInfo(pageRequest);

        if (pageResponse.getListInfo().size()>0){
            return new ResponseResult(RespEnum.SUCCESS,pageResponse);
        }
        return new ResponseResult(RespEnum.FAIL,"未查询到用户信息");
    }

    @PostMapping(value = "/deleteConfig")
    @ResponseBody
    public ResponseResult deleteConfig(@RequestBody ConfigInfo configInfo)  {

        boolean result = configInfoService.deleteConfigInfo(configInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息删除成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息删除失败");
        }
    }

    @PostMapping(value = "/updateConfig")
    @ResponseBody
    public ResponseResult updateConfig(@RequestBody ConfigInfo configInfo)  {

        boolean result = configInfoService.updateConfigInfo(configInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息变更成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息变更失败");
        }
    }


}
