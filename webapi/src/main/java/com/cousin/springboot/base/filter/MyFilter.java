package com.cousin.springboot.base.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 使用注解表示过滤器
 * @WebFilter将一个实现javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称，可选
 * 属性urlPatterns指定要过滤的url模式，也可以使用属性value来声明（指定要过滤的url模式是必选的
 *
 * @author cousin
 * @created 2016/11/29 1:02
 */
@WebFilter(filterName = "myFilter",urlPatterns = "/")
public class MyFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("执行过滤器操作");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("销毁过滤器");
    }
}
