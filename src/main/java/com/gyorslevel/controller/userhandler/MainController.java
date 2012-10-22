package com.gyorslevel.controller.userhandler;

import com.gyorslevel.configuration.ConfigurationBean;
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

@Controller
@RequestMapping("/main")
public class MainController extends UserHandlerController {

    @Autowired
    public MainController(UserExpireController expireController) {
        this.expireController = expireController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String process(ModelMap model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (!userSessionMissing(session)) {
            if (userSessionExpired(session)) {
                return "redirect:expired";
            }

        }

        createUserIfMissing(session);
        addUserExpirationToModel(model, session);
        addFetchedMails(model, session);
        addBodyPageNameToModel(model, "mainbody");
        addVersionToSession(request);
        return "template";

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
            String jamesEmailHost = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.JamesHost, String.class);
            SimpleMessage[] messages = new Pop3EmailFetcher().fetchMessages(jamesEmailHost, expiration.getUserName(), "pass");
            model.addAttribute("messages", messages);
            session.setAttribute("messages", messages);
        } catch (MessagingException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}