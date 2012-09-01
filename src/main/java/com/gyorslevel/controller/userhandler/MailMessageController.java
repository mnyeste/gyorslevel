package com.gyorslevel.controller.userhandler;

import com.gyorslevel.pop3.SimpleMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mailmessage")
public class MailMessageController extends UserHandlerController {
    
    private static Logger logger = Logger.getLogger(MailMessageController.class);
    
    @RequestMapping(method = RequestMethod.GET)
    public String process(ModelMap model, HttpServletRequest request) {
        
        final HttpSession session = request.getSession();
        final String messageId = request.getParameter("id");
        
        addEmailMessageToModel(model, session, messageId);
        
        return "mailmessagebody";
        
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
