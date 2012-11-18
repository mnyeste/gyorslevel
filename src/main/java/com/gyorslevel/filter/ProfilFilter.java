package com.gyorslevel.filter;

import com.gyorslevel.configuration.ConfigurationBean;
import com.gyorslevel.profil.Profile;
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
public class ProfilFilter implements Filter {
    
    private static Logger logger = Logger.getLogger(ProfilFilter.class);
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
            Profile profile = (Profile) session.getAttribute("profil");
            
            if (profile == null) {
                
                String lang = null;
                
                Enumeration headerNames = httpRequest.getHeaderNames();
                while (headerNames.hasMoreElements()) {
                    String headerName = (String) headerNames.nextElement();
                    if (headerName.equals("X-App-Lang")) {                            
                        lang = httpRequest.getHeader(headerName).toLowerCase();
                    }
                }
                
                String mode = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.Mode, String.class);
                
                if (lang == null) {
                    if (mode.equals("dev")) {
                        logger.trace("doFilter() - dev mode detected");
                        lang = DEFAULT_LANG;
                    } else {
                        throw new RuntimeException("'lang' cannot be detected");
                    }
                }
                
                String domain = request.getServerName();
                
                profile = new Profile(lang, domain);
                
                session.setAttribute("profile", profile);
                
            }
            
            logger.trace("doFilter() - profile: [" + profile + "]");
            
            chain.doFilter(request, response);
            
        } catch (Exception exception) {
            logger.error(exception);
        }
        
    }
}
