package com.cousin.springboot.dao;

import com.cousin.springboot.model.pojo.UserInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * UserInfo 查找
 *
 * @author cousin
 * @Created 2016 /12/2 11:05
 */
public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo,Long>  {


    /**
     * 通过username查找用户信息.
     *
     * @param username the username
     * @return the user info
     */
    public UserInfo findByUsername(String username);
}
