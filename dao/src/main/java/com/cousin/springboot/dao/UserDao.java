package com.cousin.springboot.dao;

import com.cousin.springboot.model.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author cousin
 * @Created 2016/11/27 19:08
 */
@Repository
public class UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;


    public User getList(Long id){
        String sql = "select * from user WHERE id=?";
        RowMapper<User> userRowMapper  = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql,userRowMapper,id);
    }

}
