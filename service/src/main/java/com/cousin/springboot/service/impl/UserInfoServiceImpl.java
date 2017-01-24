package com.cousin.springboot.service.impl;

import com.cousin.springboot.dao.UserInfoRepository;
import com.cousin.springboot.model.pojo.UserInfo;
import com.cousin.springboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cousin
 * @Created 2016/12/2 11:10
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }

    @Override
    public List<UserInfo> selectAll() {
        return (List<UserInfo>) userInfoRepository.findAll();
    }
}
