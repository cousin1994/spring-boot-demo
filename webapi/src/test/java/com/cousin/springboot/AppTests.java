package com.cousin.springboot;

import com.cousin.springboot.model.pojo.User;
import com.cousin.springboot.service.TaskService;
import com.cousin.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class AppTests {

    private Logger logger = LoggerFactory.getLogger(AppTests.class);

    @Resource
    private UserService userService;

    @Resource
    private TaskService taskService;


    @Test
    public void test_redis(){
        User user = new User();
        user.setName("cousin");
        user.setCreateTime(new Date());
        user.setPassword("123456");
        user.setUser("user");
        user.setSalt(UUID.randomUUID().toString());
        userService.save(user);
    }

    @Test
    public void test_redis_find(){
        logger.info("开始查找");
        logger.info(userService.findById(4L).toString());
//        System.out.println(userService.findById(3L));
    }


    @Test
    public void test_redis_del(){
        logger.info("开始删除");
        userService.del(4L);
        logger.info("删除完成");
    }

    @Test
    public void test_task() throws Exception {
        taskService.doTaskOne();
        taskService.doTaskTwo();
        taskService.doTaskThree();
    }
}
