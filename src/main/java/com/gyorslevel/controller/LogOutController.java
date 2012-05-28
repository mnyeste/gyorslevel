/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.controller;

import com.gyorslevel.jmx.JMXBean;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogOutController extends MyAbstractController {

    private JMXBean jMXBean;
    
    @Autowired
    public LogOutController(JMXBean jMXBean) {
        this.jMXBean = jMXBean;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public String process(ModelMap model, HttpSession session) {
        addTitleToModel(model);
        invalidateSession(session);
        return getPageName();

    }

    @Override
    public String getPageName() {
        return "index";
    }

    private void invalidateSession(HttpSession session) {
        
        String email = (String)session.getAttribute("email");
        if (email != null)
        {
            jMXBean.deleteUser(email);
        }
        
        session.invalidate();
    }
}
