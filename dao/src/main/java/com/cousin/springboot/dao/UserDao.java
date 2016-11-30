package com.cousin.springboot.dao;

import com.cousin.springboot.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cousin
 * @Created 2016/11/27 19:08
 */
@Mapper
public interface UserDao {
    User findUserById(Long id);
}
