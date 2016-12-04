package com.cousin.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 *
 * @author cousin
 * @Created 2016/11/27 3:05
 */
@SpringBootApplication
@ServletComponentScan
@EnableAsync
public class App {

    /**
     * 注册Servlet.不需要添加注解：@ServletComponentScan
     * @return
     */
//    @Bean
//    public ServletRegistrationBean MyServlet1(){
//        return new ServletRegistrationBean(new MyServlet1(),"/myServlet/*");
//    }

    public static void main(String[] args) {

        SpringApplication.run(App.class,args);
    }

}
