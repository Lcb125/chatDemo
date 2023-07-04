package com.chat.sys.handler;


import com.chat.common.TokenUtils;
import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.Enum.RespEnum;
import com.chat.sys.entity.ResponseResult;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.service.AccessInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Slf4j
@Controller
@RequestMapping("/access")
public class AccessInfoController {

    private static final String HEIGHT = "H";
    private static final String MEDIUM = "M";
    private static final String LOW = "L";

    private static final String HEIGHTBYCN = "高";
    private static final String MEDIUMBYCY = "中";
    private static final String LOWBYCN = "低";

    private static final String PRIMARY = "P";
    private static final String SECONDARY = "S";

    private static final String PRIMARYBYCN = "主账号";
    private static final String SECONDARYBYCN = "子账号";

    private static final String CHINESE = "CN";
    private static final String ENGLISH = "EN";

    private static final String CHINESEBYCN = "中文";
    private static final String ENGLISHBYCN = "英文";

    @Autowired
    AccessInfoService accessInfoService;

    @Autowired
    TokenUtils tokenUtils;

    @GetMapping(value = "/insertAccess")
    @ResponseBody
    public ResponseResult insertAccess()  {

        try {
            Random random = new Random();
            for (int i=0;i<11;i++){
                int code = random.nextInt(9000) + 1000;
                log.info("code==="+code);
                AccessInfo accessInfo = new AccessInfo();
                accessInfo.setAccessCode(code);
                accessInfo.setAccessLevel("L");
                accessInfo.setStatus("A");
                accessInfoService.insertAccessInfo(accessInfo);

            }
            return new ResponseResult(RespEnum.SUCCESS,"信息插入成功");
        }catch (Exception e){
            log.error("插入访问码报错："+e.getMessage());
            return new ResponseResult(RespEnum.FAIL,"信息插入失败");
        }



    }

    @PostMapping(value = "/queryAccess")
    @ResponseBody
    public ResponseResult queryAccess(@RequestBody PageRequest pageRequest) {
        PageResponse pageInfo = accessInfoService.findPage(pageRequest);
        if (pageInfo.getListInfo().size()>0){
            List<AccessInfo> resultList = new ArrayList<>();
            for (Object accessInfo : pageInfo.getListInfo()){
                ObjectMapper objectMapper = new ObjectMapper();
                AccessInfo access = objectMapper.convertValue(accessInfo, AccessInfo.class);
                if (StringUtils.equals(HEIGHT,access.getAccessLevel())){
                    access.setAccessLevel(HEIGHTBYCN);
                }else if (StringUtils.equals(MEDIUM,access.getAccessLevel())){
                    access.setAccessLevel(MEDIUMBYCY);
                }else if (StringUtils.equals(LOW,access.getAccessLevel())){
                    access.setAccessLevel(LOWBYCN);
                }

                if (StringUtils.equals(PRIMARY,access.getAccessType())){
                    access.setAccessType(PRIMARYBYCN);
                }else if (StringUtils.equals(SECONDARY,access.getAccessType())){
                    access.setAccessType(SECONDARYBYCN);
                }

                if (StringUtils.equals(CHINESE,access.getLanguage())){
                    access.setLanguage(CHINESEBYCN);
                }else if (StringUtils.equals(ENGLISH,access.getLanguage())){
                    access.setLanguage(ENGLISHBYCN);
                }
                resultList.add(access);
            }
            pageInfo.setListInfo(resultList);
            return new ResponseResult(RespEnum.SUCCESS,pageInfo);
        }else {
            return new ResponseResult(RespEnum.FAIL,"未查询到相关信息");
        }
    }

    @PostMapping(value = "/deleteAccess")
    @ResponseBody
    public ResponseResult deleteAccess(@RequestBody AccessInfo accessInfo)  {

//        String token = request.getHeader("authToken");
//        String name = tokenUtils.verify(token);
//        if (!"admin".equals(name)){
//            return new ResponseResult(RespEnum.NOAUTH,"无管理员权限");
//        }
        boolean result = accessInfoService.updateAccessInfo(accessInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"删除信息成功");
        }
        return new ResponseResult(RespEnum.FAIL,"删除信息失败");
    }


    @PostMapping(value = "/updateAccess")
    @ResponseBody
    public ResponseResult updateAccess(@RequestBody AccessInfo accessInfo)  {

        accessInfo.setUpdateTime(new Date());
        if (StringUtils.equals(HEIGHTBYCN,accessInfo.getAccessLevel())){
            accessInfo.setAccessLevel(HEIGHT);
        }else if (StringUtils.equals(MEDIUMBYCY,accessInfo.getAccessLevel())){
            accessInfo.setAccessLevel(MEDIUM);
        }else if (StringUtils.equals(LOWBYCN,accessInfo.getAccessLevel())){
            accessInfo.setAccessLevel(LOW);
        }

        if (StringUtils.equals(PRIMARYBYCN,accessInfo.getAccessType())){
            accessInfo.setAccessType(PRIMARY);
        }else if (StringUtils.equals(SECONDARYBYCN,accessInfo.getAccessType())){
            accessInfo.setAccessType(SECONDARY);
        }

        if (StringUtils.equals(CHINESEBYCN,accessInfo.getLanguage())){
            accessInfo.setLanguage(CHINESE);
        }else if (StringUtils.equals(ENGLISHBYCN,accessInfo.getLanguage())){
            accessInfo.setLanguage(ENGLISH);
        }
        log.info("language="+accessInfo.getLanguage());

        boolean result = accessInfoService.updateAccessInfo(accessInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"修改信息成功");
        }
        return new ResponseResult(RespEnum.FAIL,"修改信息失败");
    }

    @PostMapping(value = "/addAccess")
    @ResponseBody
    public ResponseResult addAccess(@RequestBody AccessInfo accessInfo)  {
        List<AccessInfo> list = accessInfoService.queryAccess(accessInfo);
        if (list.size()>0){
            return new ResponseResult(RespEnum.EXIST,"当前访问码已存在");
        }
        accessInfo.setStatus("A");
        boolean result = accessInfoService.insertAccessInfo(accessInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"新增信息成功");
        }
        return new ResponseResult(RespEnum.FAIL,"新增信息失败");
    }


}
