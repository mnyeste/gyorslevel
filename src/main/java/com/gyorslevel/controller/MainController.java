package com.gyorslevel.controller;

import com.gyorslevel.jmx.JMXBean;
import com.gyorslevel.pop3.Pop3EmailFetcher;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
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
        addUserEmailToModel(model, session);
        addFetchedMails(model, session);
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
            String email = JMXBean.generateUserEmail();
            jMXBean.createUser(email);
            session.setAttribute("email", email);
        }

    }

    private void addFetchedMails(ModelMap model, HttpSession session) {
        try {

            System.out.println((String) session.getAttribute("email"));

            String[] messages = Pop3EmailFetcher.fetchMessages("localhost", (String) session.getAttribute("email"), "pass");

            System.out.println("Messages: " + messages.length);

            model.addAttribute("messages", messages);
        } catch (MessagingException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}