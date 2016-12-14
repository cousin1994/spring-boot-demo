package com.cousin.springboot.guava;

import com.cousin.springboot.model.pojo.UserInfo;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * guava的测试类
 * Created by cousin
 * CreatedTime 2016/12/5 23:51
 */
public class GuavaTest {

    private static ThreadLocal<UserInfo> userInfoThreadLocal = ThreadLocal.withInitial(() -> {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("cousin");
        return userInfo;
    });

    @Test
    public void test1() {
        Optional<Integer> possible = Optional.of(1);
        if (possible.isPresent()) {
            System.out.println("true");
            System.out.println(possible.get());
        }
        Map<String, List<String>> map = Maps.newHashMap();
    }

    @Test
    public void thread() {
        UserInfo userInfo = userInfoThreadLocal.get();
        System.out.println(userInfo.getName());
    }

}
