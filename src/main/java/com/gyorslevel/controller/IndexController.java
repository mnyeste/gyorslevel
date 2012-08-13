package com.gyorslevel.controller;

import com.gyorslevel.configuration.ConfigurationBean;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/index", "/"})
public class IndexController extends MyAbstractController {
    
    private static Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String process(ModelMap model, HttpServletRequest request) {
        addTitleToModel(model);
        addBodyPageNameToModel(model, "indexbody");
        addContextFolderToConfiguration(request);
        addVersionToSession(request.getSession());
        return "template";
    }

    private void addContextFolderToConfiguration(HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        ConfigurationBean.setValue(ConfigurationBean.ConfigurationBeanKey.ContextFolder.getKey(), realPath);
    }

}