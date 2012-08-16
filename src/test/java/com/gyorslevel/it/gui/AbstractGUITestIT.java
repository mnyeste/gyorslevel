package com.gyorslevel.it.gui;

import com.gyorslevel.configuration.ConfigurationBean;
import com.gyorslevel.it.BaseITTest;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * TODO: create enum with page names
 *
 * @author dave00
 */
public class AbstractGUITestIT extends BaseITTest {

    private static Logger logger = Logger.getLogger(AbstractGUITestIT.class);
    
    protected static WebDriver driver;
    protected static String baseUrl;
    
    protected final static StringBuffer verificationErrors = new StringBuffer();
    protected final static String jamesHost = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.JamesHost, String.class);

    @BeforeClass
    public static void setUpClass() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://" + jamesHost + ":8080";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            logger.error(e);
            return false;
        }
    }

    protected void openUrl(String pageName) {
        driver.get(baseUrl + "/" + pageName);
    }
}