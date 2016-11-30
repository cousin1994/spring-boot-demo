package com.cousin.springboot.service;

import com.cousin.springboot.model.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author cousin
 * @created 2016/11/30 0:24
 */
@Service
public interface UserService {

    void save(User user);

    User findById(Long id);

    void del(Long id);


}
