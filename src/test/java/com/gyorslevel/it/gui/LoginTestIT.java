/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.it.gui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 *
 * @author dave00
 */
public class LoginTestIT extends AbstractGUITestIT {

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
        openUrl("index");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    }
          
}