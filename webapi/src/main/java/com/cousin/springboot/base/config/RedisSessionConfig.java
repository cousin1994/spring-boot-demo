package com.cousin.springboot.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * redis配置session失效时间
 *
 * @author cousin
 * @Created 2016/12/1 10:08
 */
@Configuration
@EnableRedisHttpSession //启动redis保存session状态.
public class RedisSessionConfig {



}
