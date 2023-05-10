package com.java.sys.handler;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.java.common.ChatUtil;
import com.java.sys.entity.ChatGPTReq;
import com.java.sys.service.ISysuserService;
import com.theokanning.openai.completion.CompletionChoice;
import io.github.asleepyfish.util.OpenAiUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("user")
public class ChatDemoController {

    @Resource
    private ISysuserService us;

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
        if (StringUtils.isEmpty(req.getCode()) || !"123456".equals(req.getCode())){
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
