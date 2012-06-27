package com.gyorslevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/index", "/"})
public class IndexController extends MyAbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String process(ModelMap model) {
        addTitleToModel(model);
        addBodyPageNameToModel(model, "indexbody");
        return "template";

    }

}