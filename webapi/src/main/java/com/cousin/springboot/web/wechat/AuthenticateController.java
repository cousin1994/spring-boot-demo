package com.cousin.springboot.web.wechat;

import com.cousin.springboot.service.WechatService;
import com.cousin.springboot.wechat.CheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信服务器认证
 * Created by cousin
 * CreatedTime 2017/1/7 0:07
 */
@Controller
@RequestMapping("/wechat/portal")
public class AuthenticateController {

    private static Logger logger = LoggerFactory.getLogger(AuthenticateController.class);

    @Autowired
    private WechatService wechatService;

    @ResponseBody
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authen(String signature, String timestamp, String nonce, String echostr) {

        logger.info("其中signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);
        if (wechatService.checkSignature(signature, timestamp, nonce)) {
            logger.info("微信认证成功！其中signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);
            if (StringUtils.isNotBlank(echostr)) {
                return echostr;
            }
        } else {
            logger.error("微信认证失败！其中signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);
        }
        return "非法请求";
    }





}
