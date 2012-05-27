package com.gyorslevel.controller;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController extends MyAbstractController {

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public String process(ModelMap model, HttpSession session) {
        addTitleToModel(model);
        addEmailToModel(model, session);
        addEmailToSessionIfMissing(session);
        return getPageName();

    }

    @Override
    public String getPageName() {
        return "main";
    }

    private void addEmailToModel(ModelMap model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
    }

    private void addEmailToSessionIfMissing(HttpSession session) {
        
        String attribute = (String) session.getAttribute("email");
        
        if (attribute == null)
        {
            UUID uuid = UUID.randomUUID();
            session.setAttribute("email", uuid.toString()+"@gyorslevel.hu");
        }
        
    }

}