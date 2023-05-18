package com.chat.sys.handler;

import com.chat.common.AddressUtil;
import com.chat.common.ChatUtil;
import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.ChatGPTReq;
import com.chat.sys.service.QueryCodeService;
import com.chat.sys.service.AccessInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Controller
@RequestMapping("user")
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


    @Value("${switch.ifCheck}")
    private String ifCheck;

    @Autowired
    ChatUtil chatUtil;

    @Autowired
    AddressUtil addressUtil;

    @Autowired
    AccessInfoService accessInfoService;

    @Autowired
    QueryCodeService queryCodeService;


    @PostMapping(value = "/testChat")
    @ResponseBody
    public ResponseEntity testChat(@RequestBody ChatGPTReq req)  {
//        如果关闭校验则直接使用
        String check = queryCodeService.queryByCodeKey(ifCheck);
        if (StringUtils.equals("true",check)){
            //        根据前端传输的userNo查询用户信息
            List<AccessInfo> accessInfos = accessInfoService.queryByAccessCode(req.getUserNo());
            AccessInfo validUser = new AccessInfo();
            if (accessInfos.size()==0){
                return ResponseEntity.status(500).body("未查询到用户信息");
            }else {
                for (AccessInfo accessInfo : accessInfos){
//                匹配到库里的设备标识，则正常调用，当天使用次数减一
                    if(StringUtils.equals(accessInfo.getDeviceId(),req.getUniqueIdentification())){
                        validUser.setAvailableNum(accessInfo.getAvailableNum()-1);
                    }
                    if (accessInfo.getAvailableNum()>=1){
                        validUser.setId(accessInfo.getId());
                    }
                }
                if(validUser.getId() == null){
                    System.out.println("次数用尽");
                    return ResponseEntity.ok("设备已满");
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
            return ResponseEntity.status(500).body("接口异常");
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/insertUser")
    @ResponseBody
    public ResponseEntity insertUser(@RequestBody AccessInfo accessInfo1)  {

        Random random = new Random();
        for (int i=0;i<11;i++){
            int code = random.nextInt(9000) + 1000;
            System.out.println(code);
            AccessInfo accessInfo = new AccessInfo();
            accessInfo.setAccessCode(code);
            accessInfo.setAccessLevel("c");
            accessInfoService.insertAccessInfo(accessInfo);

        }
//        boolean result = userInfoService.insertUser(userInfo);

//        if (result){
//            return ResponseEntity.ok("信息插入成功");
//        }else {
//            return ResponseEntity.status(500).body("信息插入失败");
//        }
        return ResponseEntity.ok("信息插入成功");

    }

    @PostMapping(value = "/requestAccess")
    @ResponseBody
    public ResponseEntity requestAccess(@RequestBody AccessInfo accessInfo)  {

        List<AccessInfo> users = accessInfoService.queryByAccessCode(accessInfo.getAccessCode());

        if (users.size()==1){
            AccessInfo getUser = users.get(0);
            String value = queryCodeService.queryByCodeKey(getUser.getAccessLevel());
            Integer times = Integer.valueOf(value);
            if (StringUtils.isEmpty(getUser.getDeviceId())){
//                第一次进来给更新唯一标识，塞满当天使用次数
                getUser.setAvailableNum(times);
                getUser.setAccessType("L");//L类型为首次进入的记录，用来存放使用次数
                getUser.setUpdateTime(new Date());
                getUser.setDeviceId(accessInfo.getDeviceId());
                Boolean updateResult = accessInfoService.updateAccessInfo(getUser);
                if (updateResult){
                    return ResponseEntity.ok("首次进入，正常使用");
                }else {
                    return ResponseEntity.status(500).body("首次进入更新信息失败，请稍后重试");
                }
            }else if (StringUtils.equals(getUser.getDeviceId(), accessInfo.getDeviceId())){
//                库里已有登记记录，正常使用
                return ResponseEntity.ok("已有记录，正常使用");
            }else {
//                与库里记录不匹配，可再加一条记录
                accessInfo.setAccessLevel("c");
                accessInfo.setAvailableNum(0);
                accessInfo.setAccessType("M");//M类型为二次进入的记录，不用来存放使用次数
                Boolean updateResult = accessInfoService.insertAccessInfo(accessInfo);
                if (updateResult){
                    return ResponseEntity.ok("二次进入，正常使用");
                }else {
                    return ResponseEntity.status(500).body("二进入插入信息失败，请稍后重试");
                }
            }

        }else if (users.size()==2){
            for (AccessInfo user:users){
                if (StringUtils.equals(accessInfo.getDeviceId(),user.getDeviceId())){

//                    如设备标识和库里能对应的上，则正常使用
                    return ResponseEntity.ok("记录已满，正常使用");
                }
            }
            return ResponseEntity.status(500).body("记录已满且唯一标识不匹配，当前设备不可用");
        }else {
            return ResponseEntity.status(500).body("查不到用户信息");
        }

    }

    @PostMapping(value = "/getAddr")
    @ResponseBody
    public ResponseEntity requestAccess(@RequestBody AccessInfo accessInfo, HttpServletRequest request) throws Exception {
        System.out.println("userInfo=="+ accessInfo);
        String clientIP = addressUtil.getClientIP(request);
        System.out.println("clientIP=="+clientIP);
        String localMac = addressUtil.getMac(clientIP);
        System.out.println("localMac=="+localMac);
        return null;
    }


    @GetMapping(value = "/querySwitch")
    @ResponseBody
    public ResponseEntity querySwitch() {
        String switchValue = queryCodeService.queryByCodeKey(ifCheck);
        if (StringUtils.equals("true",switchValue)){
            return ResponseEntity.ok("打开校验");
        }else {
            return ResponseEntity.ok("关闭校验");
        }
    }


}
