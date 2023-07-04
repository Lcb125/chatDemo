package com.chat.sys.handler;

import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.ConfigInfo;
import com.chat.sys.entity.Enum.RespEnum;
import com.chat.sys.entity.MsgInfo;
import com.chat.sys.entity.ResponseResult;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.service.ConfigInfoService;
import com.chat.sys.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/msg")
public class MsgController {



    @Autowired
    MsgService msgService;



    @PostMapping(value = "/queryMsg")
    @ResponseBody
    public ResponseResult queryMsg(@RequestBody AccessInfo accessInfo)  {

        try {
            MsgInfo msgInfo = new MsgInfo();
            log.info("MsgType====="+accessInfo);
            msgInfo.setMsgType(accessInfo.getLanguage());
            List<MsgInfo> msgInfos = msgService.queryMsgInfo(msgInfo);
            Map<String,String> resultMap = new HashMap<>();
            for (MsgInfo msg : msgInfos){
                resultMap.put(msg.getMsgKey(),msg.getMsgValue());
            }
            resultMap.put("language",accessInfo.getLanguage());
            return new ResponseResult(RespEnum.SUCCESS,resultMap);
        }catch (Exception e){
            log.info("queryMsg异常"+e.getMessage());
            return new ResponseResult(RespEnum.FAIL,"获取配置信息失败");
        }
    }

}
