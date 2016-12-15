package com.cousin.springboot.web;

import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * activitiController的应用
 *
 * @author cousin
 * @Created 2016/12/15 11:10
 */
@Controller
@RequestMapping("/activiti")
public class ActivitiController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagementService managementService;

    private final RepositoryService repositoryService;

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    @Autowired
    public ActivitiController(ManagementService managementService, RepositoryService repositoryService, RuntimeService runtimeService, TaskService taskService) {
        this.managementService = managementService;
        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    @RequestMapping("/")
    public ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView("activiti");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping("/engine/info")
    public Map<String, String> engineProperties() {
        return managementService.getProperties();
    }

    @RequestMapping("/processes")
    public ModelAndView processes() {
        ModelAndView mav = new ModelAndView("processes");
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        mav.addObject("processes", list);
        return mav;
    }

    @RequestMapping("/process/start/{processDefinitionId}")
    public String startProcess(@PathVariable("processDefinitionId") String processDefinitionId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
        logger.info("成功启动了流程：" + processInstance.getId());
        return "redirect:/activiti/tasks";
    }

    @RequestMapping("/tasks")
    public ModelAndView tasks() {
        ModelAndView mav = new ModelAndView("tasks");
        List<Task> list = taskService.createTaskQuery().list();
        mav.addObject("tasks", list);
        return mav;
    }

    @RequestMapping("/task/complete/{taskId}")
    public String completeTask(@PathVariable("taskId") String taskId) {
        taskService.complete(taskId);
        return "redirect:/activiti/tasks";
    }


}
