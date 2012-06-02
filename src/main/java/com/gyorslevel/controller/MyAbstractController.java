package com.gyorslevel.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;

abstract class MyAbstractController {
    
    protected void addTitleToModel(ModelMap model) {
        model.addAttribute("title", "Gyorslevél");
    }

    protected abstract String getPageName();
    
    protected abstract String process(ModelMap model, HttpServletRequest request);
}