package com.gyorslevel.controller.userhandler;

import com.gyorslevel.pop3.SimpleMessage;
import com.gyorslevel.timer.UserExpireController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mail")
public class MailController extends UserHandlerController {

    @Autowired
    public MailController(UserExpireController expireController) {
        this.expireController = expireController;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String process(ModelMap model, HttpServletRequest request, HttpSession session) {
        
        if (userSessionExpired(session))
        {
            return "expired";
        }
        
        addTitleToModel(model);
        addUserExpirationToModel(model, session);
        addEmailMessageToModel(model, session, request.getParameter("id"));
        return getPageName();
    }

    @Override
    public String getPageName() {
        return "mail";
    }

    private void addEmailMessageToModel(ModelMap model, HttpSession session, String id) {
        SimpleMessage[] messages = (SimpleMessage[]) session.getAttribute("messages");
        for (SimpleMessage simpleMessage : messages) {
            if (simpleMessage.getId().equals(id)) {
                model.addAttribute("openedMessage", simpleMessage);
                break;
            }
        }
    }
}