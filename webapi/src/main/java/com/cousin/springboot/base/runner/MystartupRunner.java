package com.cousin.springboot.base.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 服务启动时执行的类
 *
 * @author cousin
 * @created 2016/11/29 20:56
 */

@Component
@Order(value = 2)//表示优先级，数值越低，优先级越高
public class MystartupRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(MystartupRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>服务启动执行，执行加载数据等等<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
