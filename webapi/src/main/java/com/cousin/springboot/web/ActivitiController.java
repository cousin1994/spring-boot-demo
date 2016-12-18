package com.cousin.springboot.web;

import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * activitiController的应用
 *
 * @author cousin
 * @Created 2016/12/15 11:10
 */
@Controller
@RequestMapping("/activiti")
public class ActivitiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        ModelAndView mav = new ModelAndView("activiti/processes");
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        mav.addObject("processes", list);
        mav.addObject("test", "你好");
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
        ModelAndView mav = new ModelAndView("activiti/tasks");
        List<Task> list = taskService.createTaskQuery().list();
        mav.addObject("tasks", list);
        return mav;
    }

    @RequestMapping("/task/complete/{taskId}")
    public String completeTask(@PathVariable("taskId") String taskId) {
        taskService.complete(taskId);
        return "redirect:/activiti/tasks";
    }

    /**
     * 删除部署的流程，级联删除流程实例
     *
     * @param deploymentId 流程部署ID
     */
    @RequestMapping(value = "/processes/delete/{deploymentId}")
    public String deleteProcessDefinition(@PathVariable("deploymentId") String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
        return "redirect:/activiti/processes";
    }


    /**
     * 读取流程资源
     *
     * @param processDefinitionId 流程定义ID
     * @param resourceName        资源名称
     */
    @RequestMapping(value = "/read-resource")
    public void readResource(@RequestParam("pdid") String processDefinitionId, @RequestParam("resourceName") String resourceName, HttpServletResponse response)
            throws Exception {


        ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition pd = pdq.processDefinitionId(processDefinitionId).singleResult();

        // 通过接口读取
        InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);



        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }


    /**
     * 部署流程资源
     */
    @RequestMapping(value = "/deploy")
    public String deploy(@RequestParam(value = "file", required = true) MultipartFile file) {

        // 获取上传的文件名
        String fileName = file.getOriginalFilename();

        try {
            // 得到输入流（字节流）对象
            InputStream fileInputStream = file.getInputStream();

            // 文件的扩展名
            String extension = FilenameUtils.getExtension(fileName);

            // zip或者bar类型的文件用ZipInputStream方式部署
            DeploymentBuilder deployment = repositoryService.createDeployment();
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment.addZipInputStream(zip);
            } else {
                // 其他类型的文件直接部署
                deployment.addInputStream(fileName, fileInputStream);
            }
            deployment.deploy();
        } catch (Exception e) {
            logger.error("error on deploy process, because of file input stream");
        }

        return "redirect:/activiti/processes";
    }

    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @RequestMapping(value = "/list/running")
    public ModelAndView runningList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("activiti/running");
        List<Map<String, Object>> params = new ArrayList<>();
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().active().orderByProcessInstanceId().desc().list();
        List<String> nameList = new ArrayList<>();
        for (ProcessInstance processInstance : list) {
            Map<String, Object> get = new HashMap<>();
//            String businessKey = processInstance.getBusinessKey();
//            if (StringUtils.isBlank(businessKey)){
//                continue;
//            }
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().singleResult();
//            nameList.add(task.getName());
            get.put("pi", processInstance);
            get.put("task", task);
            params.add(get);
        }
        mav.addObject("list", list);
        mav.addObject("taskList", nameList);
        mav.addObject("map", params);
        return mav;
    }

}
