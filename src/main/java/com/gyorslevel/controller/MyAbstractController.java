package com.gyorslevel.controller;

import org.springframework.ui.ModelMap;

public abstract class MyAbstractController {
    
    protected void addTitleToModel(ModelMap model) {
        model.addAttribute("title", "Gyorslevél");
    }
    
    protected void addBodyPageNameToModel(ModelMap model, String bodyPageName) {
        model.addAttribute("bodyPage", bodyPageName);
    }
    
}