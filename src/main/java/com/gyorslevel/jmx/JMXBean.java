package com.gyorslevel.jmx;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.apache.james.user.api.UsersRepositoryManagementMBean;

/**
 * TODO: add log4j
 *
 * @author dave00
 */
public class JMXBean {

    private UsersRepositoryManagementMBean mbeanProxy;
    private JMXConnector jmxc;

    public JMXBean() throws MalformedURLException, IOException, MalformedObjectNameException {
        JMXServiceURL url = new JMXServiceURL(
                "service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
        jmxc = JMXConnectorFactory.connect(url, null);

        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        ObjectName mbeanName = new ObjectName("org.apache.james:type=component,name=usersrepository");

        mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, org.apache.james.user.api.UsersRepositoryManagementMBean.class, true);
    }

    static String getDomain() {
        return "localhost";
    }

    public static String generateUserEmail() {
        UUID uuid = UUID.randomUUID();
        String email = uuid.toString().replaceAll("-", "");
        return String.format("%s@%s", email, getDomain());
    }

    public void close() throws IOException {
        jmxc.close();
    }

    public void createUser(String email) {
        try {
            mbeanProxy.addUser(email, "pass");
        } catch (Exception exception) {
            System.err.println(exception);
            throw new RuntimeException(exception);
        }
    }

    public void deleteUser(String email) {
        try {
            mbeanProxy.deleteUser(email);
        } catch (Exception exception) {
            System.err.println(exception);
            throw new RuntimeException(exception);
        }
    }

    public UsersRepositoryManagementMBean getMbeanProxy() {
        return mbeanProxy;
    }
}
