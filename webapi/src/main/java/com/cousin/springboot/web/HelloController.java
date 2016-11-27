package com.cousin.springboot.web;

import com.cousin.springboot.model.pojo.Hello;
import com.cousin.springboot.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cousin
 * @Created 2016/11/27 18:11
 */
@RestController
public class HelloController {

    @RequestMapping("/test")
    public String test(){
        Hello hello = new HelloService().getHelloService();
            return hello.getName();
    }



}
