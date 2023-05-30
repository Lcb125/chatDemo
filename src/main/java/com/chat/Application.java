package com.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.chat.sys.mapper")
@SpringBootConfiguration
//@EnableAutoConfiguration
//@EnableChatGPT
//@PropertySource("classpath:jdbc.properties")//加载自定义properties配置文件
//@ImportResource("classpath:spring.xml")//加载自定义的xml配置文件
@Import(MyWebMvcConfigurer.class)//加载自定义的配置类
//@ComponentScan(basePackageClasses={com.chat.MyWebMvcConfigurer.class})
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
