package com.cousin.springboot.service;

import com.cousin.springboot.dao.GetHelloDao;
import com.cousin.springboot.model.pojo.Hello;

/**
 * @author cousin
 * @Created 2016/11/27 18:10
 */
public class HelloService {

    public Hello getHelloService(){
        return new GetHelloDao().getHelloPojo();
    }

}
