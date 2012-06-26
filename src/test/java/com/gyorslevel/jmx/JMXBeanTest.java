package com.gyorslevel.jmx;

import com.gyorslevel.configuration.ConfigurationBean;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.management.MalformedObjectNameException;
import org.junit.Assert;
import org.junit.Test;

public class JMXBeanTest {

    @Test
    public void testGenerateUserEmail() throws MalformedURLException, IOException, MalformedObjectNameException {

        String newEmail = new JMXBean().generateUserEmail();
        String domain = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.Domain, String.class);
        Assert.assertTrue(newEmail.contains("@" + domain));

    }
}
