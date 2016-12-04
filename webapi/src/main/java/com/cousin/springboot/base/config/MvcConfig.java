package com.cousin.springboot.base.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * springMvc的配置
 * Created by cousin
 * CreatedTime 2016/12/422:49
 */
@SpringBootConfiguration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 替换json返回的组件为fastjson
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        fastJsonHttpMessageConverter.setFeatures(SerializerFeature.PrettyFormat);

        converters.add(fastJsonHttpMessageConverter);

    }
}
