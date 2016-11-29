package com.cousin.springboot.base.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 上传文件的限制配置
 *
 * @author cousin
 * @created 2016/11/29 21:45
 */
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize("1KB"); //单个文件指定放KB 或MB
        factory.setMaxRequestSize("256KB");//上传文件总大小

        return factory.createMultipartConfig();

    }

}
