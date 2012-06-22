package com.gyorslevel.it.gui;

import com.gyorslevel.it.BaseITTest;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
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

    protected static WebDriver driver;
    protected static String baseUrl;
    protected static StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setUpClass() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
            return false;
        }
    }

    protected void openUrl(String pageName) {
        driver.get(baseUrl + "/" + pageName);
    }
}