package com.gyorslevel.it;

import com.gyorslevel.jmx.JMXBean;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import javax.management.MalformedObjectNameException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JMXBeanIntegrationTestIT {

    JMXBean jMXBean;
    
    @Before
    public void setup() throws MalformedURLException, IOException, MalformedObjectNameException
    {
        jMXBean = new JMXBean();
    }
    
    @Test
    public void testCreate() throws Exception
    {
        final String userEmail = JMXBean.generateUserEmail();
        jMXBean.createUser(userEmail);
        
        List<String> asList = Arrays.asList(jMXBean.getMbeanProxy().listAllUsers());
        Assert.assertTrue(asList.contains(userEmail));
        
        jMXBean.deleteUser(userEmail);
        
    }
    
    @After
    public void tearDown() throws IOException
    {
        jMXBean.close();
    }
    
}