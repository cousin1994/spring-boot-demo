package com.cousin.springboot.dao;

import com.cousin.springboot.model.pojo.User;

/**
 * @author cousin
 * @Created 2016/11/27 19:08
 */
public interface UserDao {
    User findUserById(Long id);
}
