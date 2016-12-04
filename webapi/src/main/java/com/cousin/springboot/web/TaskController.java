package com.cousin.springboot.web;

import com.cousin.springboot.service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 异步调用任务测试
 * Created by cousin
 * CreatedTime 2016/12/422:18
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @RequestMapping("")
    public String task() throws Exception {
        taskService.doTaskOne();
        taskService.doTaskTwo();
        taskService.doTaskThree();
        return "success";
    }

}
