package com.gyorslevel.controller;

import com.gyorslevel.timer.UserExpireController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/admin"})
public class AdminController extends MyAbstractController {

    private UserExpireController expireController;

    @Autowired
    public AdminController(UserExpireController expireController) {
        this.expireController = expireController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String process(ModelMap model) {
        addTitleToModel(model);
        addUserEmailsToModel(model);
        addBodyPageNameToModel(model, "adminbody");
        return "template";

    }

    private void addUserEmailsToModel(ModelMap model) {
        model.addAttribute("userEmails", expireController.listAllUsers());
    }
}