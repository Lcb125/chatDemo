package com.java.sys.handler;

import com.java.common.ChatUtil;
import com.java.sys.entity.ChatGPTReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

    @Autowired
    ChatUtil chatUtil;



    @PostMapping(value = "/testChat")
    @ResponseBody
    public ResponseEntity testChat(@RequestBody ChatGPTReq req)  {
        if (StringUtils.isEmpty(req.getCode()) || !"135790".equals(req.getCode())){
            return ResponseEntity.status(500).body("401");
        }
        String s = "";
//        for (int i= 1;i<=Integer.valueOf(retries);i++){
//            if ()
//        }
        try {
            s = chatUtil.chat(req.getContent());
        }catch (Exception e){
            System.out.println("chat接口报错："+e.getMessage());
            return ResponseEntity.status(500).body("接口异常");
        }

        return ResponseEntity.ok(s);
    }


}
