package com.gyorslevel.it.gui;

import junit.framework.Assert;
import org.junit.*;
import org.openqa.selenium.By;

/**
 * @author dave00
 */
public class ExpirationTestIT extends AbstractGUITestIT {

    @BeforeClass
    public static void setUpClass() throws Exception {
        AbstractGUITestIT.setUpClass();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        AbstractGUITestIT.tearDownClass();
    }

    @Test
    public void testLogin() throws Exception {
        
        // Open index page
        openUrl("index");
        
        // Login
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        
        // Check if main page is opened
        Assert.assertEquals("Levelek", driver.findElement(By.cssSelector(".pageName")).getText());

        Thread.sleep(15000);
        
        openUrl("main");
        
        // Check if now we on the expired page
        Assert.assertEquals("Időtúllépés", driver.findElement(By.cssSelector(".pageName")).getText());
        
        Thread.sleep(10000);
        
    }
}