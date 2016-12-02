package com.cousin.springboot.service;

import com.cousin.springboot.model.pojo.UserInfo;

/**
 * @author cousin
 * @Created 2016/12/2 11:10
 */
public interface UserInfoService {
    public UserInfo findByUsername(String username);

}
