package com.cousin.springboot.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 微信公众号接口
 *
 * @author cousin
 * @Created 2017/2/28 15:40
 */
@Service
public class WechatService {

    private static Logger logger = LoggerFactory.getLogger(WechatService.class);


    @Value("${wx_token}")
    private String token ;


    public boolean checkSignature(String timestamp, String nonce, String signature) {
        String[] arr = new String[]{token, timestamp, nonce};
        //排序
        Arrays.sort(arr);

        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        //sha1加密
        String temp = DigestUtils.sha1Hex(content.toString());

        return temp.equals(signature);
    }

}
