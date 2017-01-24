package com.cousin.springboot.service.impl;

import com.cousin.springboot.dao.UserMapper;
import com.cousin.springboot.dao.UserRepository;
import com.cousin.springboot.model.pojo.User;
import com.cousin.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cousin
 * @Created 2016/11/30 15:39
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Cacheable(value = "userinfo", key = "getTargetClass().name.replace('.',':')+#id.toString()")//缓存，没有指定key
    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @CacheEvict(value = "userinfo", key = "getTargetClass().name.replace('.',':')+#id.toString()")
    @Override
    public void del(Long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> selectAllList(Map<String,Object> params){
        return userMapper.selectByExample(params);
    }
}
