package com.chat.sys.handler;

import com.chat.common.AddressUtil;
import com.chat.common.ChatUtil;
import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.ChatGPTReq;
import com.chat.sys.entity.Enum.RespEnum;
import com.chat.sys.entity.ResponseResult;
import com.chat.sys.entity.UserInfo;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.service.ConfigInfoService;
import com.chat.sys.service.AccessInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatDemoController {


    //上传文件夹路径
    @Value("${oss.file.folder}")
    private String FOLDER;
    @Value("${oss.file.endpoint}")
    private String END_POINT;
    @Value("${oss.file.accessKeyId}")
    private String ACCESS_KEY_ID;
    @Value("${oss.file.accessKeySecret}")
    private String ACCESS_KEY_SECRET;
    @Value("${oss.file.bucketName}")
    private String BUCKET_NAME;

    @Value("${chatgpt.retries}")
    private String retries;

    @Value("${chatgpt.model}")
    private String model;
    @Value("${chatgpt.endpoint}")
    private String endpoint;
    @Value("${chatgpt.token}")
    private String apiKey;


    private static final  String STATUS = "A";


    @Value("${switch.ifCheck}")
    private String ifCheck;

    @Autowired
    ChatUtil chatUtil;

    @Autowired
    AddressUtil addressUtil;

    @Autowired
    AccessInfoService accessInfoService;

    @Autowired
    ConfigInfoService configInfoService;


    @PostMapping(value = "/testChat")
    @ResponseBody
    public ResponseResult testChat(@RequestBody ChatGPTReq req)  {
//        如果关闭校验则直接使用
        String check = configInfoService.queryByCodeKey(ifCheck);
        if (StringUtils.equals("true",check)){
            //        根据前端传输的userNo查询用户信息
            List<AccessInfo> accessInfos = accessInfoService.queryByAccessCode(req.getUserNo());
            AccessInfo validUser = new AccessInfo();
            if (accessInfos.size()==0){
                return new ResponseResult(RespEnum.FAIL,"未查询到用户信息");
            }else {
                boolean checkResult = false;
                for (AccessInfo accessInfo : accessInfos){
//                匹配到库里的设备标识，则正常调用，当天使用次数减一
                    if(StringUtils.equals(accessInfo.getDeviceId(),req.getUniqueIdentification())){
                        checkResult = true;
                    }
                    if (accessInfo.getAvailableNum()>=1){
                        validUser.setId(accessInfo.getId());
                        validUser.setAvailableNum(accessInfo.getAvailableNum()-1);

                    }
                }
                if(validUser.getId() == null || !checkResult){
                    System.out.println("次数用尽");
                    return new ResponseResult(RespEnum.SUCCESS,"设备已满");
                }
                validUser.setUpdateTime(new Date());
                accessInfoService.updateAccessInfo(validUser);
            }
        }

        String result = "";
        try {
            result = chatUtil.chat(req.getContent());
        }catch (Exception e){
            System.out.println("chat接口报错："+e.getMessage());
            return new ResponseResult(RespEnum.FAIL,"接口异常");
        }

        return new ResponseResult(RespEnum.SUCCESS,result);
    }



    @PostMapping(value = "/requestAccess")
    @ResponseBody
    public ResponseResult requestAccess(@RequestBody AccessInfo accessInfo)  {

        List<AccessInfo> users = accessInfoService.queryByAccessCode(accessInfo.getAccessCode());

        if (users.size()==1){
            AccessInfo getUser = users.get(0);
            String value = configInfoService.queryByCodeKey(getUser.getAccessLevel());
            Integer times = Integer.valueOf(value);
            if (StringUtils.isEmpty(getUser.getDeviceId())){
//                第一次进来给更新唯一标识，塞满当天使用次数
                getUser.setAvailableNum(times);
                getUser.setAccessType("P");//P为首次进入的记录,为主账号，用来存放使用次数
                getUser.setUpdateTime(new Date());
                getUser.setDeviceId(accessInfo.getDeviceId());
                getUser.setStatus("A");
                Boolean updateResult = accessInfoService.updateAccessInfo(getUser);
                if (updateResult){
                    return new ResponseResult(RespEnum.FIRST,"首次进入，正常使用");
                }else {
                    return new ResponseResult(RespEnum.FAIL,"首次进入更新信息失败，请稍后重试");
                }
            }else if (StringUtils.equals(getUser.getDeviceId(), accessInfo.getDeviceId())){
//                库里已有登记记录，正常使用
                if (StringUtils.equals(STATUS,getUser.getStatus())){
                    log.info("已有记录，正常使用："+getUser.getAccessCode());
                    return new ResponseResult(RespEnum.SUCCESS,getUser);
                }else {
                    return new ResponseResult(RespEnum.FAIL,"已有记录，但被禁用");
                }

            }else {
//                与库里记录不匹配，可再加一条记录
                accessInfo.setAccessLevel("c");
                accessInfo.setAvailableNum(0);
                accessInfo.setAccessType("S");//S类型为二次进入的记录，为次账号，不用来存放使用次数
                accessInfo.setStatus("A");
                Boolean updateResult = accessInfoService.insertAccessInfo(accessInfo);
                if (updateResult){
                    log.info("二次进入，正常使用："+getUser.getAccessCode());
                    return new ResponseResult(RespEnum.SUCCESS,getUser);
                }else {
                    return new ResponseResult(RespEnum.FAIL,"二次进入插入信息失败，请稍后重试");
                }
            }

        }else if (users.size()==2){
            AccessInfo access = new AccessInfo();
            for (AccessInfo user:users){
                if (StringUtils.equals("P",user.getAccessType())){
                    access.setLanguage(user.getLanguage());
                }
                if (StringUtils.equals(accessInfo.getDeviceId(),user.getDeviceId()) && StringUtils.equals(STATUS,user.getStatus())){

//                    如设备标识和库里能对应的上，则正常使用
                    log.info("记录已满，正常使用："+user.getAccessCode());
                    return new ResponseResult(RespEnum.SUCCESS,user);
                }
            }
            return new ResponseResult(RespEnum.FAIL,"记录已满且唯一标识不匹配或被禁用，当前设备不可用");
        }else {
            return new ResponseResult(RespEnum.FAIL,"查不到用户信息");
        }

    }

    @PostMapping(value = "/getAddr")
    @ResponseBody
    public ResponseResult getAddr(@RequestBody AccessInfo accessInfo, HttpServletRequest request) throws Exception {
        System.out.println("userInfo=="+ accessInfo);
        String clientIP = addressUtil.getClientIP(request);
        System.out.println("clientIP=="+clientIP);
        String localMac = addressUtil.getMac(clientIP);
        System.out.println("localMac=="+localMac);
        return null;
    }





}
