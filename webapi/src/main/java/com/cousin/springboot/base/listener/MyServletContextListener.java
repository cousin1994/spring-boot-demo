package com.cousin.springboot.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 使用@WebListener注解，实现ServletContextLisener接口
 *
 * @author cousin
 * @created 2016/11/29 1:07
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("ServletContext初始化");
        ServletContext servletContext = servletContextEvent.getServletContext();
        setServletContext(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("servletContext销毁");
    }


    private static void setServletContext(ServletContext context) {
        ApplicationContext ctx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        logger.debug("===================================");
        logger.info("Count of beans in spring = " + beanDefinitionNames.length);
        for(int i = 0 ; i < beanDefinitionNames.length;i++) {
            logger.debug("definitionBean"+i+"  = "  + beanDefinitionNames[i]);
        }
        logger.debug("===================================");
        /**
         //初始化其他数据

         */
        logger.debug("initialization of spring context finished!");
    }

}
