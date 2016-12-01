package com.cousin.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

/**
 * 代码中获取国际化的资源
 *
 * @author cousin
 * @Created 2016/12/1 17:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class I18nTest {

    private Logger logger = LoggerFactory.getLogger(I18nTest.class);

    @Autowired
    private MessageSource messageSource;

    @Test
    public void getResource(){
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage("file",null,locale);
        logger.info("国际化字段,{}",msg);
    }

}
