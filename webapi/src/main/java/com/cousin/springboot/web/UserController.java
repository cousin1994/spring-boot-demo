package com.cousin.springboot.web;

import com.cousin.springboot.model.pojo.User;
import com.cousin.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cousin
 * @Created 2016/11/27 18:53
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    public String saveUser(){
        User user = new User();
        user.setName("zhangsan");
        userService.save(user);
        return "成功";
    }


    @RequestMapping("/findOne/{id}")
    public User findOne(@PathVariable("id") Long id){
        return userService.fingById(id);
    }

}
