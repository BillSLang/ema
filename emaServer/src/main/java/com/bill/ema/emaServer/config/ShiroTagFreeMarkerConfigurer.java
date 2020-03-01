package com.bill.ema.emaServer.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.TemplateModelException;

@Component
public class ShiroTagFreeMarkerConfigurer {
 
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
    	 ShiroTags tags = new ShiroTags();
    	 tags.put("hasAnyPermissions", new HasAnyPermissionsTag());
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", tags);
    }
}