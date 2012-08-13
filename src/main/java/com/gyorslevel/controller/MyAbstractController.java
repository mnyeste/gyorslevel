package com.gyorslevel.controller;

import java.io.InputStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

public abstract class MyAbstractController {

    private static Logger logger = Logger.getLogger(MyAbstractController.class);
    private static String applicationVersion;

    protected void addVersionToSession(HttpServletRequest request) {

        if (applicationVersion == null) {

            try {

                InputStream inputStream = request.getSession().getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF");

                if (inputStream == null) {
                    applicationVersion = "development version";
                } else {
                    Properties prop = new Properties();
                    prop.load(inputStream);
                    applicationVersion = (String) prop.get("Implementation-Version");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        request.getSession().setAttribute("applicationVersion", applicationVersion);

    }

    protected void addTitleToModel(ModelMap model) {
        model.addAttribute("title", "Gyorslevél");
    }

    protected void addBodyPageNameToModel(ModelMap model, String bodyPageName) {
        model.addAttribute("bodyPage", bodyPageName);
    }
}