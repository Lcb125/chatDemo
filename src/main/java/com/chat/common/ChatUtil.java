package com.chat.common;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.http.*;
import com.alibaba.fastjson.JSONObject;
import com.chat.sys.service.ConfigInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


//import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.*;

@Slf4j
@Service
public class ChatUtil {

    @Value("${chatgpt.token}")
    private String apiKey;

    @Value("${chatgpt.endpoint}")
    private String endpoint;

    @Value("${chatgpt.model}")
    private String model;

    @Value("${chatgpt.retries}")
    private String retries;

    @Value("${chatgpt.maxTokens}")
    private String maxTokens;

    @Value("${chatgpt.temperature}")
    private String temperature;

    private static final String OLDAPIKEY = "apiKey";

    private static final String OLDENDPOINT = "endpoint";

    private static final String NEWAPIKEY = "new_apiKey";

    private static final String NEWENDPOINT = "new_endpoint";

    @Autowired
    ConfigInfoService configInfoService;


    public String chat(String txt) {

        String apiKey = configInfoService.queryByCodeKey(OLDAPIKEY);
        String endpoint = configInfoService.queryByCodeKey(OLDENDPOINT);

//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 10809));
        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("model", model);
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>(){{
            put("role", "user");
            put("content", txt);
        }});
        paramMap.put("messages", dataList);
        JSONObject message = null;
        try {
            String body = HttpRequest.post(endpoint)
//                    .header("Authorization", "Bearer "+apiKey)
                    .header("Content-Type", "application/json")
                    .header("api-key", apiKey)
                    .timeout(360000)
//                    .setProxy(proxy)
                    .body(JSONUtil.toJsonStr(paramMap))
                    .execute()
                    .body();
            cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(body);
            log.info("jsonObject======="+jsonObject);
            cn.hutool.json.JSONArray choices = jsonObject.getJSONArray("choices");
            log.info("choices======="+choices);
            JSONObject result = choices.get(0, JSONObject.class, Boolean.TRUE);
            message = result.getJSONObject("message");
        } catch (HttpException | ConvertException e) {
            return "出现了异常";
        }
        return message.getString("content");
    }


    public String chatByNewAPI(String txt) {

        String apiKey = configInfoService.queryByCodeKey(NEWAPIKEY);
        String endpoint = configInfoService.queryByCodeKey(NEWENDPOINT);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("prompt", txt);
        String message = null;
        try {
            String body = HttpRequest.post(endpoint)
//                    .header("Authorization", "Bearer "+apiKey)
                    .header("Content-Type", "application/json")
                    .header("api-key", apiKey)
                    .timeout(360000)
//                    .setProxy(proxy)
                    .body(JSONUtil.toJsonStr(paramMap))
                    .execute()
                    .body();
            cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(body);
            log.info("jsonObject======="+jsonObject);
            cn.hutool.json.JSONArray choices = jsonObject.getJSONArray("choices");
            log.info("choices======="+choices);
            JSONObject result = choices.get(0, JSONObject.class,Boolean.TRUE);
            message = result.getString("text");
        } catch (HttpException | ConvertException e) {
            return "出现了异常";
        }
        return message;
    }

}
