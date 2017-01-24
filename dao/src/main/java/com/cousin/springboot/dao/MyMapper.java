package com.cousin.springboot.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * mybatis基础的dao接口封装
 *
 * @author cousin
 * @Created 2017/1/24 12:08
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
