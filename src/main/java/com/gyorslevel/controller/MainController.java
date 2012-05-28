package com.gyorslevel.controller;

import com.gyorslevel.jmx.JMXBean;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController extends MyAbstractController {

    private JMXBean jMXBean;

    @Autowired
    public MainController(JMXBean jMXBean) {
        this.jMXBean = jMXBean;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public String process(ModelMap model, HttpSession session) {
        addTitleToModel(model);
        createUserIfMissing(session);
        addEmailToModel(model, session);
        return getPageName();

    }

    @Override
    public String getPageName() {
        return "main";
    }

    private void addEmailToModel(ModelMap model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
    }

    private void createUserIfMissing(HttpSession session) {

        String attribute = (String) session.getAttribute("email");

        if (attribute == null) {
            String email = jMXBean.generateUserEmail();
            jMXBean.createUser(email);
            session.setAttribute("email", email);
        }

    }
}