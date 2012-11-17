package com.gyorslevel.jmx;

import com.gyorslevel.configuration.ConfigurationBean;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.apache.james.domainlist.api.DomainListManagementMBean;
import org.apache.james.user.api.UsersRepositoryManagementMBean;

/**
 * TODO: add log4j
 *
 * @author dave00
 */
public class JMXBean {

    UsersRepositoryManagementMBean userRepositoryManagementMBeanProxy;
    DomainListManagementMBean domainListManagementMBean;

    public String generateUserEmail() {
        UUID uuid = UUID.randomUUID();
        String email = uuid.toString().replaceAll("-", "");
        String domain = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.Domain, String.class);
        return String.format("%s@%s", email, domain);
    }

    public void createUser(String email) {
        try {
            userRepositoryManagementMBeanProxy.addUser(email, "pass");
        } catch (Exception exception) {
            System.err.println(exception);
            throw new RuntimeException(exception);
        }
    }

    public void deleteUser(String email) {
        try {
            userRepositoryManagementMBeanProxy.deleteUser(email);
        } catch (Exception exception) {
            System.err.println(exception);
            throw new RuntimeException(exception);
        }
    }

    public List<String> listAllUsers() {
        try {
            return Arrays.asList(userRepositoryManagementMBeanProxy.listAllUsers());
        } catch (Exception exception) {
            System.err.println(exception);
            throw new RuntimeException(exception);
        }
    }
}