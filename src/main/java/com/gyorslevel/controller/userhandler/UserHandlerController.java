package com.gyorslevel.controller.userhandler;

import com.gyorslevel.controller.MyAbstractController;
import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.timer.UserExpireController;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;

/**
 * Represents those classes which are directly dealing with user expiration
 * and session information.
 * @author dave00
 */
public abstract class UserHandlerController extends MyAbstractController {
 
    protected UserExpireController expireController;
    
    protected void addUserExpirationToModel(ModelMap model, HttpSession session) {
        UserExpiration expiration = (UserExpiration) session.getAttribute("expiration");
        model.addAttribute("expiration", expiration);
    }

    /**
     * @param session
     * @return true if there is no active user session
     * expired
     */
    protected boolean userSessionMissing(HttpSession session) {
        UserExpiration expiration = (UserExpiration) session.getAttribute("expiration");
        return expiration == null;
    }
    
    /**
     * @param session
     * @return true if there is an active user session but the user was already
     * expired
     */
    protected boolean userSessionExpired(HttpSession session) {
        
        UserExpiration expiration = (UserExpiration) session.getAttribute("expiration");
        
        final String userEmail = expiration.getUserEmail();
        final boolean expired = !expireController.userExists(userEmail);
        
        if (expired)
        {
            session.setAttribute("expiration", null);
        }
        
        return expired;
    }
    
}
