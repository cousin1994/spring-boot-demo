package com.cousin.springboot.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录controller
 *
 * @author cousin
 * @Created 2016/12/2 14:10
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {"/", "/index"})
    public String index(){
        return "/index";
    }

//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public String login(){
//        String user = (String) SecurityUtils.getSubject().getPrincipal();
//        if (StringUtils.isNotBlank(user)) {
//            SecurityUtils.getSubject().getSession().setTimeout(-1000L);
//            return "index";
//        }
//        return "login";
//    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Model model){
        logger.info("开始接受登录页面操作");
//        UserInfo user = (UserInfo) SecurityUtils.getSubject().getPrincipal();
//        if (user!=null) {
//            SecurityUtils.getSubject().getSession().setTimeout(-1000L);
//            return "/index";
//        }

        String exception = (String)request.getAttribute("shiroLoginFailure");

        logger.info("接受到的登录异常为={}",exception);

        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                logger.error("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                logger.error("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                logger.error("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                logger.error("else -- >" + exception);
            }
        }
        if(StringUtils.isNotBlank(msg)) {
            model.addAttribute("msg", msg);
        }
        // 此方法不处理登录成功,由shiro进行处理.
        return "/login";

    }

}
