package com.gyorslevel.jmx;

import com.gyorslevel.configuration.ConfigurationBean;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.annotation.PostConstruct;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Responsible for visiting the {@link JMXBean} methods where JMX connection is
 * a must. Creates then demolishes the connection
 *
 * @author dave00
 */
@Aspect
public class JMXBeanAspect {

    private static Logger logger = Logger.getLogger(JMXBeanAspect.class);

    @PostConstruct
    public void init() {

        try {

            JMXBean jmxBean = new JMXBean();
            connectToJMX(jmxBean);

            final String domain = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.Domain, String.class);
            final boolean domainAvailable = jmxBean.domainListManagementMBean.containsDomain(domain);
            
            if (domainAvailable) {
                logger.debug("init() - Domain (" + domain + ") available");    
            } else {
                logger.info("init() - Domain (" + domain + ") missing => creating");    
                jmxBean.domainListManagementMBean.addDomain(domain);
            }

        } catch (Exception exception) {
            logger.error(exception);
            throw new RuntimeException(exception);
        }

    }

    @Before("execution(* com.gyorslevel.jmx.JMXBean.createUser(..)) || execution(* com.gyorslevel.jmx.JMXBean.deleteUser(..)) || execution(* com.gyorslevel.jmx.JMXBean.listAllUsers(..))")
    public void visitBefore(JoinPoint joinPoint) {

        try {

            JMXBean jmxBean = (JMXBean) joinPoint.getTarget();
            connectToJMX(jmxBean);

        } catch (Exception exception) {
            logger.error(exception);
            throw new RuntimeException(exception);
        }
    }

    private void connectToJMX(JMXBean jmxBean) throws MalformedObjectNameException, IOException, MalformedURLException {

        String jamesHost = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.JamesHost, String.class);

        JMXServiceURL url = new JMXServiceURL(
                "service:jmx:rmi:///jndi/rmi://" + jamesHost + ":9999/jmxrmi");

        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        ObjectName userRepositoryMbeanName = new ObjectName("org.apache.james:type=component,name=usersrepository");
        jmxBean.userRepositoryManagementMBeanProxy = JMX.newMBeanProxy(mbsc, userRepositoryMbeanName, org.apache.james.user.api.UsersRepositoryManagementMBean.class, true);

        ObjectName domainListMbeanName = new ObjectName("org.apache.james:type=component,name=domainlist");
        jmxBean.domainListManagementMBean = JMX.newMBeanProxy(mbsc, domainListMbeanName, org.apache.james.domainlist.api.DomainListManagementMBean.class, true);

        // TODO: we are not 'really' dropping the connection
        // jmxc.close();
    }
}