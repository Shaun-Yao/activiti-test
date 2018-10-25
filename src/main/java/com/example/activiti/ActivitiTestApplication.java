package com.example.activiti;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiTestApplication implements CommandLineRunner {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    public static void main(String[] args) {
        SpringApplication.run(ActivitiTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final String processId = "testRepair";
        System.out.println("Number of process definitions : "
                + repositoryService.createProcessDefinitionQuery().count());
        System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
        runtimeService.startProcessInstanceByKey(processId);

        System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
    }
}
