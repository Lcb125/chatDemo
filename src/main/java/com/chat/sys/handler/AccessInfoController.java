package com.chat.sys.handler;


import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.Enum.RespEnum;
import com.chat.sys.entity.ResponseResult;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.service.AccessInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;


@Slf4j
@Controller
@RequestMapping("/access")
public class AccessInfoController {

    @Autowired
    AccessInfoService accessInfoService;

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
                accessInfo.setAccessLevel("c");
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
            return new ResponseResult(RespEnum.SUCCESS,pageInfo);
        }else {
            return new ResponseResult(RespEnum.FAIL,"未查询到相关信息");
        }
    }

    @PostMapping(value = "/deleteAccess")
    @ResponseBody
    public ResponseResult deleteAccess(@RequestBody AccessInfo accessInfo)  {

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
        boolean result = accessInfoService.updateAccessInfo(accessInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"修改信息成功");
        }
        return new ResponseResult(RespEnum.FAIL,"修改信息失败");
    }


}
