package com.gyorslevel.controller;

import com.gyorslevel.expiration.UserExpiration;
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

// TODO: add expiration to model instead of email
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
    public String process(ModelMap model, HttpServletRequest request) {
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

    // TODO: move to upper class
    private void addUserEmailToModel(ModelMap model, HttpSession session) {
        UserExpiration expiration = (UserExpiration) session.getAttribute("expiration");
        model.addAttribute("email", expiration.getUserEmail());
    }

    private void createUserIfMissing(HttpSession session) {

        UserExpiration expiration = (UserExpiration) session.getAttribute("expiration");

        if (expiration == null) {
            expiration = expireController.createUser();
            session.setAttribute("expiration", expiration);
        }

    }

    private void addFetchedMails(ModelMap model, HttpSession session) {
        try {
            UserExpiration expiration = (UserExpiration) session.getAttribute("expiration");
            SimpleMessage[] messages = Pop3EmailFetcher.fetchMessages("localhost", expiration.getUserEmail(), "pass");
            model.addAttribute("messages", messages);
            session.setAttribute("messages", messages);
        } catch (MessagingException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}