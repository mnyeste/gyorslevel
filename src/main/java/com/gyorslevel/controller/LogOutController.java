/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.controller;

import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.timer.UserExpireController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogOutController extends MyAbstractController {

    private UserExpireController expireController;

    @Autowired
    public LogOutController(UserExpireController expireController) {
        this.expireController = expireController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String process(ModelMap model, HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        
        invalidateSession(session);
        addBodyPageNameToModel(model, "indexbody");
        addVersionToSession(request);
        
        return "template";

    }

    private void invalidateSession(HttpSession session) {

        UserExpiration expiration = (UserExpiration) session.getAttribute("expiration");
        if (expiration != null) {
            expireController.deleteUser(expiration);
        }

        session.invalidate();
    }
}
