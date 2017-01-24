package com.cousin.springboot.dao;

import com.cousin.springboot.model.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends MyMapper<Role> {
}