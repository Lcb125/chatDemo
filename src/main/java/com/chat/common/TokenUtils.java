package com.chat.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @desc   使用token验证用户是否登录
 * @author zm
 **/
@Component
public class TokenUtils {
    //设置过期时间
    private static final long EXPIRE_DATE=24*60*60*1000;
    //token秘钥
    private static final String TOKEN_SECRET = "ChuanBinLiasdfghjklmn2023";

    public String getToken (String username,String password){

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username",username)
                    .withClaim("password",password).withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return token;
    }

    public String verify(String token){
        /**
         * @desc   验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
//            System.out.println(jwt.getClaims().get("password").asString());
            String username = jwt.getClaims().get("username").asString();
            String password = jwt.getClaims().get("password").asString();
            String result = username+"-"+password;
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
//    public static void main(String[] args) {
//        String username ="admin";
//        String password = "admin123";
//        String token = getToken(username,password);
//        System.out.println(token);
//        System.out.println(1);
//        boolean b = verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6ImFkbWluMTIzIiwiZXhwIjoxNjg1Mjc4MTA1LCJ1c2VybmFtZSI6ImFkbWluIn0.ePjbXPJhq5E_2B4xOXVo1ZdecqDaeBUVvr9YvTXZNPM");
//        System.out.println(b);
//    }
}
