package com.gyorslevel.jmx;

import com.gyorslevel.configuration.ConfigurationBean;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.management.MalformedObjectNameException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest( ConfigurationBean.class )
@RunWith(PowerMockRunner.class)
public class JMXBeanTest {

    @Test
    public void testGenerateUserEmail() throws MalformedURLException, IOException, MalformedObjectNameException {

        PowerMockito.mockStatic(ConfigurationBean.class);
        PowerMockito.when(ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.Domain, String.class)).thenReturn("gyorslevel.info");
        
        String newEmail = new JMXBean().generateUserEmail();
        String domain = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.Domain, String.class);
        Assert.assertTrue(newEmail.contains("@" + domain));

    }
}
