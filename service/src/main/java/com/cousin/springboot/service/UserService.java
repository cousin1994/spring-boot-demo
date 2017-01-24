package com.cousin.springboot.service;

import com.cousin.springboot.model.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cousin
 * @created 2016/11/30 0:24
 */
@Service
public interface UserService {

    void save(User user);

    User findById(Long id);

    void del(Long id);

    List<User> selectAllList(Map<String,Object> params);


}
