package com.gyorslevel.configuration;

import org.junit.Test;

public class ConfigurationBeanTest {

    @Test(expected=IllegalArgumentException.class)
    public void testNullKeyNotAccepted()
    {
        ConfigurationBean.getValue(null, Object.class);
    }
    
}
