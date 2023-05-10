package com.java;

import io.github.asleepyfish.annotation.EnableChatGPT;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableChatGPT
//@PropertySource("classpath:jdbc.properties")//加载自定义properties配置文件
//@ImportResource("classpath:spring.xml")//加载自定义的xml配置文件
//@Import(MyAnnotationConfig.class)//加载自定义的配置类
public class Application extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args)
    {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
//        SpringApplication.run(Application.class, args);
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
}
