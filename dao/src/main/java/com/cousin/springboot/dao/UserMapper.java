package com.cousin.springboot.dao;

import com.cousin.springboot.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MyMapper<User> {
}