package com.gyorslevel.controller;

import com.gyorslevel.jmx.JMXBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/admin", "/"})
public class AdminController extends MyAbstractController {

    private JMXBean jMXBean;

    @Autowired
    public AdminController(JMXBean jMXBean) {
        this.jMXBean = jMXBean;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public String process(ModelMap model, HttpServletRequest request) {
        addTitleToModel(model);
        addUserEmailsToModel(model);
        return getPageName();

    }

    @Override
    public String getPageName() {
        return "admin";
    }

    private void addUserEmailsToModel(ModelMap model) {
        model.addAttribute("userEmails", jMXBean.listAllUsers());
    }
}