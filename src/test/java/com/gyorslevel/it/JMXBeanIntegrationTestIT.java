package com.gyorslevel.it;

import com.gyorslevel.jmx.JMXBean;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

public class JMXBeanIntegrationTestIT extends BaseITTest {

    @TestResource(resourceTypes=ResourceType.JMXBean)
    @Test
    public void testCreate() throws Exception {

        final String userEmail = new JMXBean().generateUserEmail();
        jmxbean.createUser(userEmail);

        List<String> asList = jmxbean.listAllUsers();
        Assert.assertTrue(asList.contains(userEmail));

        jmxbean.deleteUser(userEmail);

    }
}