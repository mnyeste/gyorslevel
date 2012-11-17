package com.gyorslevel.filter;

import com.gyorslevel.configuration.ConfigurationBean;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Responsible to check if the language mode was already set. If not reads the
 * corresponding value from the request header.
 *
 * @author dave00
 */
public class LangFilter implements Filter {
    
    private static Logger logger = Logger.getLogger(LangFilter.class);
    public static final String DEFAULT_LANG = "en";
    private FilterConfig filterConfig = null;
    
    public void init(FilterConfig filterConfig)
            throws ServletException {
        this.filterConfig = filterConfig;
    }
    
    public void destroy() {
        this.filterConfig = null;
    }
    
    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        try {
            
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession();
            String lang = (String) session.getAttribute("lang");
            
            if (lang == null || lang.isEmpty()) {
                
                String mode = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.Mode, String.class);
                
                if (mode.equals("dev")) {
                    logger.debug("doFilter() - dev mode detected");
                    lang = DEFAULT_LANG;
                } else {
                    Enumeration headerNames = httpRequest.getHeaderNames();
                    while (headerNames.hasMoreElements()) {
                        String headerName = (String) headerNames.nextElement();
                        if (headerName.equals("X-App-Lang")) {                            
                            lang = httpRequest.getHeader(headerName).toLowerCase();
                        }
                    }
                }
                
                session.setAttribute("lang", lang);
                
            }
            
            logger.debug("doFilter() - lang: [" + lang + "]");
            
            chain.doFilter(request, response);
            
        } catch (Exception exception) {
            logger.error(exception);
        }
        
    }
}
