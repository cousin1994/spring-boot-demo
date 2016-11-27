package com.cousin.springboot.service;

import com.cousin.springboot.dao.UserDao;
import com.cousin.springboot.dao.UserRepository;
import com.cousin.springboot.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cousin
 * @Created 2016/11/27 18:51
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDao userDao;

    public void save(User user){
        userRepository.save(user);
    }

    public User fingById(Long id){
        return userDao.getList(id);
    }



}
