package com.gyorslevel.controller;

import com.gyorslevel.configuration.ConfigurationBean;
import com.gyorslevel.controller.userhandler.UserHandlerController;
import com.gyorslevel.timer.UserExpireController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/index", "/"})
public class IndexController extends UserHandlerController {

    private static Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    public IndexController(UserExpireController expireController) {
        this.expireController = expireController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String process(ModelMap model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (!userSessionMissing(session)) {
            if (!userSessionExpired(session)) {
                return "redirect:main";
            }

        }

        addBodyPageNameToModel(model, "indexbody");
        addContextFolderToConfiguration(request);
        addVersionToSession(request);
        return "template";
    }

    private void addContextFolderToConfiguration(HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        ConfigurationBean.setValue(ConfigurationBean.ConfigurationBeanKey.ContextFolder.getKey(), realPath);
    }
}