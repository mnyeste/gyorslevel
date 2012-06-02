package com.gyorslevel.controller;

import com.gyorslevel.pop3.SimpleMessage;
import javax.servlet.http.HttpServletRequest;
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
    public String process(ModelMap model, HttpServletRequest request) {
        addTitleToModel(model);
        addUserEmailToModel(model, request.getSession());
        addEmailMessageToModel(model, request.getSession(), request.getParameter("id"));
        return getPageName();
    }

    @Override
    public String getPageName() {
        return "mail";
    }

    private void addUserEmailToModel(ModelMap model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
    }

    private void addEmailMessageToModel(ModelMap model, HttpSession session, String id) {
        System.out.println("--id:" + id);
        SimpleMessage[] messages = (SimpleMessage[]) session.getAttribute("messages");
        for (SimpleMessage simpleMessage : messages) {
            if (simpleMessage.getId().equals(id)) {
                model.addAttribute("openedMessage", simpleMessage);
                break;
            }
        }
    }
}