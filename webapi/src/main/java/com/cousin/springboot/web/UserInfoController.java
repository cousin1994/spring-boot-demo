package com.cousin.springboot.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息控制
 *
 * @author cousin
 * @Created 2016/12/2 15:06
 */
@Configuration
@RequestMapping("/userinfo")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);


    /**
     * 用户查询信息
     *
     * @return the string
     */
    @RequestMapping("/userList")
    public String userInfo(){
        return "userInfo";
    }


    /**
     * 新增用户信息
     *
     * @return the string
     */
    @RequiresPermissions("userInfo:add")
    @RequestMapping("/userAdd")
    public String userInfoadd(){
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     * @return the string
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }

}
