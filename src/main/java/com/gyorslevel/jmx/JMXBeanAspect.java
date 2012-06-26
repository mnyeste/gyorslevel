package com.gyorslevel.jmx;

import com.gyorslevel.configuration.ConfigurationBean;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
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

    @Before("execution(* com.gyorslevel.jmx.JMXBean.createUser(..)) || execution(* com.gyorslevel.jmx.JMXBean.deleteUser(..)) || execution(* com.gyorslevel.jmx.JMXBean.listAllUsers(..))")
    public void visitBefore(JoinPoint joinPoint) {

        try {

            String domain = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.Domain, String.class);

            JMXServiceURL url = new JMXServiceURL(
                    "service:jmx:rmi:///jndi/rmi://" + domain + ":9999/jmxrmi");

            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

            ObjectName mbeanName = new ObjectName("org.apache.james:type=component,name=usersrepository");

            JMXBean jmxBean = (JMXBean) joinPoint.getTarget();
            jmxBean.mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, org.apache.james.user.api.UsersRepositoryManagementMBean.class, true);

            // TODO: we are not 'really' dropping the connection
            // jmxc.close();

        } catch (Exception exception) {
            System.err.println(exception);
            throw new RuntimeException(exception);
        }
    }
}