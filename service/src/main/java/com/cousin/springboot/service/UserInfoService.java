package com.cousin.springboot.service;

import com.cousin.springboot.model.pojo.UserInfo;

import java.util.List;

/**
 * @author cousin
 * @Created 2016/12/2 11:10
 */
public interface UserInfoService {
    UserInfo findByUsername(String username);

    List<UserInfo> selectAll();

}
