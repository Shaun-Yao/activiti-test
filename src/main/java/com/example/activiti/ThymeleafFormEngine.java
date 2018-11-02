package com.example.activiti;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.form.FormEngine;
import org.activiti.engine.impl.persistence.entity.ResourceEntity;

import java.io.UnsupportedEncodingException;

public class ThymeleafFormEngine implements FormEngine {
    @Override
    public String getName() {
        return "thymeleaf";
    }

    @Override
    public Object renderStartForm(StartFormData startForm) {
        return null;
    }

    @Override
    public Object renderTaskForm(TaskFormData taskForm) {
        if (taskForm.getFormKey() == null) {
            return null;
        }
        String formTemplateString = getFormTemplateString(taskForm, taskForm.getFormKey());

        return  formTemplateString;
    }

    private String getFormTemplateString(FormData formInstance, String formKey) {
        String deploymentId = formInstance.getDeploymentId();

        ResourceEntity resourceStream = Context.getCommandContext().getResourceEntityManager().findResourceByDeploymentIdAndResourceName(deploymentId, formKey);

        if (resourceStream == null) {
            throw new ActivitiObjectNotFoundException("Form with formKey '" + formKey + "' does not exist", String.class);
        }

        byte[] resourceBytes = resourceStream.getBytes();
        String encoding = "UTF-8";
        String formTemplateString = "";
        try {
            formTemplateString = new String(resourceBytes, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new ActivitiException("Unsupported encoding of :" + encoding, e);
        }
        return formTemplateString;
    }
}
