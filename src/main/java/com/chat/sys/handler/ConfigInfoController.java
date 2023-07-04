package com.chat.sys.handler;

import com.chat.common.TokenUtils;
import com.chat.sys.entity.ConfigInfo;
import com.chat.sys.entity.Enum.RespEnum;
import com.chat.sys.entity.ResponseResult;
import com.chat.sys.entity.UserInfo;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.service.ConfigInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/config")
public class ConfigInfoController {


    private static final String HEIGHT = "H";
    private static final String MEDIUM = "M";
    private static final String LOW = "L";
    private static final String CHECK = "ifCheck";
    private static final String LANGUAGE = "language";
    private static final String SWITCHT = "true";
    private static final String SWITCHF = "false";
    private static final String CHINESE = "CN";
    private static final String ENGLISH = "EN";

    private static final String HEIGHTBYCN = "高";
    private static final String MEDIUMBYCY = "中";
    private static final String LOWBYCN = "低";
    private static final String CHECKBYCN = "是否校验";
    private static final String LANGUAGEBYCN = "语种";
    private static final String SWITCHTBYCN = "开启";
    private static final String SWITCHFBYCN = "关闭";
    private static final String CHINESEBYCN = "中文";
    private static final String ENGLISHBYCN = "英文";


    @Value("${switch.ifCheck}")
    private String ifCheck;


    @Autowired
    ConfigInfoService configInfoService;

    @Autowired
    TokenUtils tokenUtils;


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

//            List<ConfigInfo> resultList = new ArrayList<>();
//            for (Object config:pageResponse.getListInfo()){
//                ObjectMapper objectMapper = new ObjectMapper();
//                ConfigInfo configInfo = objectMapper.convertValue(config, ConfigInfo.class);
//
//                if (StringUtils.equals(CHECK,configInfo.getCodeKey())){
//                    configInfo.setCodeKey(CHECKBYCN);
//                }else if (StringUtils.equals(LANGUAGE,configInfo.getCodeKey())){
//                    configInfo.setCodeKey(LANGUAGEBYCN);
//                }else if (StringUtils.equals(HEIGHT,configInfo.getCodeKey())){
//                    configInfo.setCodeKey(HEIGHTBYCN);
//                }else if (StringUtils.equals(MEDIUM,configInfo.getCodeKey())){
//                    configInfo.setCodeKey(MEDIUMBYCY);
//                }else if (StringUtils.equals(LOW,configInfo.getCodeKey())){
//                    configInfo.setCodeKey(LOWBYCN);
//                }
//
//                if (StringUtils.equals(SWITCHF,configInfo.getCodeValue())){
//                    configInfo.setCodeValue(SWITCHFBYCN);
//                }else if (StringUtils.equals(SWITCHT,configInfo.getCodeValue())){
//                    configInfo.setCodeValue(SWITCHTBYCN);
//                }else if (StringUtils.equals(CHINESE,configInfo.getCodeValue())){
//                    configInfo.setCodeValue(CHINESEBYCN);
//                }else if (StringUtils.equals(ENGLISH,configInfo.getCodeValue())){
//                    configInfo.setCodeValue(ENGLISHBYCN);
//                }
//
//                resultList.add(configInfo);
//            }
//            pageResponse.setListInfo(resultList);

            return new ResponseResult(RespEnum.SUCCESS,pageResponse);
        }
        return new ResponseResult(RespEnum.FAIL,"未查询到用户信息");
    }

    @PostMapping(value = "/deleteConfig")
    @ResponseBody
    public ResponseResult deleteConfig(@RequestBody ConfigInfo configInfo)  {

//        String token = request.getHeader("authToken");
//        String name = tokenUtils.verify(token);
//        if (!"admin".equals(name)){
//            return new ResponseResult(RespEnum.NOAUTH,"无管理员权限");
//        }
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
