package com.gyorslevel.controller.userhandler;

import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.pop3.Pop3EmailFetcher;
import com.gyorslevel.pop3.SimpleMessage;
import com.gyorslevel.timer.UserExpireController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
    public String process(ModelMap model, HttpSession session) {
        
        if (userSessionExpired(session))
        {
            return "redirect:expired";
        }
        
        addTitleToModel(model);
        createUserIfMissing(session);
        addUserExpirationToModel(model, session);
        addFetchedMails(model, session);
        return getPageName();

    }

    @Override
    public String getPageName() {
        return "main";
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