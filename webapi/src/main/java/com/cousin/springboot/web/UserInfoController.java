package com.cousin.springboot.web;

import com.cousin.springboot.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户信息控制
 *
 * @author cousin
 * @Created 2016/12/2 15:06
 */
@Controller
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    /**
     * 用户查询信息
     *
     * @return the string
     */
    @RequestMapping("")
    public String userInfo(){
        return "userinfo/userInfo";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object userInfoList(){
        return  userInfoService.selectAll();

    }


    /**
     * 新增用户信息
     *
     * @return the string
     */
    @RequiresPermissions("userInfo:add")
    @RequestMapping("/userAdd")
    public String userInfoadd(){
        return "userinfo/userInfoAdd";
    }

    /**
     * 用户删除;
     * @return the string
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理
    public String userDel(){
        return "userinfo/userInfoDel";
    }

}
