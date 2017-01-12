package com.cousin.springboot.web.wechat;

import com.cousin.springboot.wechat.CheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信服务器认证
 * Created by cousin
 * CreatedTime 2017/1/7 0:07
 */
@Controller
@RequestMapping("/wechat")
public class AuthenticateController {

    private static Logger logger = LoggerFactory.getLogger(AuthenticateController.class);

    @RequestMapping("/authen")
    public String authen(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response) {

        logger.info("其中signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);
        try {

            ServletOutputStream out = response.getOutputStream();
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                logger.info("微信认证成功！其中signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);
                if (StringUtils.isNotBlank(echostr)) {
                    out.print(echostr);
                }
            } else {
                logger.error("微信认证失败！其中signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);
            }
        } catch (Exception e) {
            logger.error("传输IO异常！原因是{}", e);
        }
        return null;
    }

}
