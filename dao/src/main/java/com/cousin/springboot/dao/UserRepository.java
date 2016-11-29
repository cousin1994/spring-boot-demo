package com.cousin.springboot.dao;

import com.cousin.springboot.model.pojo.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author cousin
 * @created 2016/11/30 0:21
 */
public interface UserRepository extends CrudRepository<User,Long> {
}
