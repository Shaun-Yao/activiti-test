package com.example.activiti.controller;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ActivitiController {


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FormService formService;

    @GetMapping("/repairForm/{processDefinitionId}")
    public String repairForm(@PathVariable("processDefinitionId") String processDefinitionId, Model model) {

        Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId);
        model.addAttribute("renderedStartForm", renderedStartForm);
        model.addAttribute("processDefinitionId", processDefinitionId);

        return "repairForm";
    }


}