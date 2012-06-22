/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.it.gui;

import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.it.ResourceType;
import java.util.List;
import junit.framework.Assert;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author dave00
 */
public class AdminPageTestIT extends AbstractGUITestIT {

    private static String userEmail;

    @BeforeClass
    public static void setUpClass() throws Exception {
        AbstractGUITestIT.setUpClass();
    }
    
    @Before
    public void setUp()
    {
        injectResources(new ResourceType[]{ResourceType.UserExpireController});
        
        UserExpiration expiration = expireController.createUser();
        userEmail = expiration.getUserEmail();
    }

    @AfterClass
    public static void tearDown() throws Exception {

        AbstractGUITestIT.tearDownClass();
        expireController.deleteUser(new UserExpiration(userEmail));

    }

    @Test
    public void testOpenAdminPage() {
        openUrl("admin");
        Assert.assertEquals("Admin", driver.findElement(By.cssSelector(".pageName")).getText());
    }

    @Test
    public void testListUsers() {

        List<WebElement> jamesUsers = getTableElement("useremailstable");
        Assert.assertNotNull(jamesUsers);
    }

    private List<WebElement> getTableElement(String tableName) {
        WebElement table_element = driver.findElement(By.id(tableName));
        List<WebElement> tr_collection = table_element.findElements(By.xpath("id('" + tableName + "')/tbody/tr"));
        return tr_collection;
    }
}