package com.gyorslevel.controller;

import com.gyorslevel.jmx.JMXBean;
import com.gyorslevel.pop3.Pop3EmailFetcher;
import com.gyorslevel.pop3.SimpleMessage;
import com.gyorslevel.timer.UserExpireController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController extends MyAbstractController {

    private UserExpireController expireController;

    @Autowired
    public MainController(UserExpireController expireController) {
        this.expireController = expireController;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public String process(ModelMap model,  HttpServletRequest request) {
        addTitleToModel(model);
        createUserIfMissing(request.getSession());
        addUserEmailToModel(model, request.getSession());
        addFetchedMails(model, request.getSession());
        return getPageName();

    }

    @Override
    public String getPageName() {
        return "main";
    }

    private void addUserEmailToModel(ModelMap model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
    }

    private void createUserIfMissing(HttpSession session) {

        String attribute = (String) session.getAttribute("email");

        if (attribute == null) {
            String email = expireController.createUser();
            session.setAttribute("email", email);
        }

    }

    private void addFetchedMails(ModelMap model, HttpSession session) {
        try {
            SimpleMessage[] messages = Pop3EmailFetcher.fetchMessages("localhost", (String) session.getAttribute("email"), "pass");
            model.addAttribute("messages", messages);
            session.setAttribute("messages", messages);
        } catch (MessagingException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}