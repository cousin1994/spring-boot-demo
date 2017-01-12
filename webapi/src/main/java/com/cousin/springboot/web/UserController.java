package com.cousin.springboot.web;

import com.cousin.springboot.model.pojo.User;
import com.cousin.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @author cousin
 * @created 2016/11/30 0:33
 */
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/usersave")
    public String save() {
        User user = new User();
        user.setCreateTime(new Date());
        user.setEmail("773807943@qq.com");
        user.setName("cousin");
        user.setPhone("155210147099");
        user.setSalt(UUID.randomUUID().toString());
        userService.save(user);
        return "true";
    }

    @RequestMapping("/fingOne/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @RequestMapping("/del/{id}")
    public String deluser(@PathVariable("id") Long id) {
        userService.del(id);
        return "true";
    }
}
