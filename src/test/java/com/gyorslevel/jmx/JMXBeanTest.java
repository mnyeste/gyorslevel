package com.gyorslevel.jmx;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.management.MalformedObjectNameException;
import org.junit.Assert;
import org.junit.Test;

public class JMXBeanTest {

    @Test
    public void testGenerateUserEmail() throws MalformedURLException, IOException, MalformedObjectNameException {

        String newEmail = JMXBean.generateUserEmail();
        Assert.assertTrue(newEmail.contains("@" + JMXBean.getDomain()));

    }
}
