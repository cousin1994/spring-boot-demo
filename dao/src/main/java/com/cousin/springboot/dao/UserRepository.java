package com.cousin.springboot.dao;

import com.cousin.springboot.model.pojo.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author cousin
 * @Created 2016/11/27 18:49
 */
public interface UserRepository extends CrudRepository<User,Long> {
}
