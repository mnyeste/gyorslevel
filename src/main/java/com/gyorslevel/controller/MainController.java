package com.gyorslevel.controller;

import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController extends MyAbstractController {

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public String process(ModelMap model) {
        addTitleToModel(model);
        addRandomEmailToModel(model);
        return getPageName();

    }

    @Override
    public String getPageName() {
        return "main";
    }

    private void addRandomEmailToModel(ModelMap model) {
        model.addAttribute("email", Long.toString(System.currentTimeMillis())+"@gyorslevel.hu");
    }

}