package com.cousin.springboot.wechat;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * 微信认证的校验
 * Created by cousin
 * CreatedTime 2017/1/7 0:18
 */
public class CheckUtil {

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String token = "cousin";
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
