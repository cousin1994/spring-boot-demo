package com.cousin.springboot;

import com.cousin.springboot.model.pojo.User;
import com.cousin.springboot.service.TaskService;
import com.cousin.springboot.service.UserService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * 测试类启动
 *
 * @author cousin
 * @Created 2016/11/30 15:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTests {

    private Logger logger = LoggerFactory.getLogger(AppTests.class);

    @Resource
    private UserService userService;

    @Resource
    private TaskService taskService;

    private static User user = new User();

    @Test
    public void test_redis001(){
        user.setName("cousin");
        user.setCreateTime(new Date());
        user.setPassword("123456");
        user.setUsername("user");
        user.setSalt(UUID.randomUUID().toString());
        userService.save(user);
    }

    @Test
    public void test_redis002_find(){
        logger.info(user.getId().toString());
        User user1 = userService.findById(user.getId());
        Assert.assertEquals(user1,user);
    }


    @Test
    public void test_redis003_del(){
        userService.del(user.getId());
    }

    @Test
    public void test_task() throws Exception {
        taskService.doTaskOne();
        taskService.doTaskTwo();
        taskService.doTaskThree();
    }
}
