package com.gyorslevel.controller;

import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mail")
public class MailController extends MyAbstractController {

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public String process(ModelMap model, HttpSession session) {
        addTitleToModel(model);
        addEmailToModel(model, session);
        return getPageName();

    }

    @Override
    public String getPageName() {
        return "mail";
    }

    private void addEmailToModel(ModelMap model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
    }

}