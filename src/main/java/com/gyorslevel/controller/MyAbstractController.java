package com.gyorslevel.controller;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

public abstract class MyAbstractController {

    private static Logger logger = Logger.getLogger(MyAbstractController.class);
    private static String applicationVersion;

    protected void addVersionToSession(HttpSession session) {

        if (applicationVersion == null) {
            try {
                Package pack = this.getClass().getPackage();
                applicationVersion = null;
                applicationVersion = pack.getImplementationVersion();
                if (applicationVersion == null) {
                    applicationVersion = pack.getSpecificationVersion();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        session.setAttribute("applicationVersion", applicationVersion);

    }

    protected void addTitleToModel(ModelMap model) {
        model.addAttribute("title", "Gyorslevél");
    }

    protected void addBodyPageNameToModel(ModelMap model, String bodyPageName) {
        model.addAttribute("bodyPage", bodyPageName);
    }
}