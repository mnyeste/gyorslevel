/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.controller;

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
    @Override
    public String process(ModelMap model, HttpServletRequest request) {
        addTitleToModel(model);
        invalidateSession(request.getSession());
        return getPageName();

    }

    @Override
    public String getPageName() {
        return "index";
    }

    private void invalidateSession(HttpSession session) {

        String email = (String) session.getAttribute("email");
        if (email != null) {
            expireController.deleteUser(email);
        }

        session.invalidate();
    }
}
