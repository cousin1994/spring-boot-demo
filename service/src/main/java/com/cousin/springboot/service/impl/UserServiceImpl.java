package com.cousin.springboot.service.impl;

import com.cousin.springboot.dao.UserRepository;
import com.cousin.springboot.model.pojo.User;
import com.cousin.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author cousin
 * @Created 2016/11/30 15:39
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, StringRedisTemplate stringRedisTemplate) {
        this.userRepository = userRepository;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Cacheable(value = "userinfo")//缓存，没有指定key
    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @CacheEvict(value = "userinfo")
    @Override
    public void del(Long id) {
        userRepository.delete(id);
    }
}
