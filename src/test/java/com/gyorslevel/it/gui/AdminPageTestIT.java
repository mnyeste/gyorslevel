/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.it.gui;

import com.gyorslevel.jmx.JMXBean;
import com.gyorslevel.timer.UserExpireController;
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
    private static UserExpireController expireController;

    @BeforeClass
    public static void setUpClass() throws Exception {

        AbstractGUITestIT.setUpClass();


        JMXBean jMXBean = new JMXBean();
        expireController = new UserExpireController(jMXBean);
        userEmail = expireController.createUser();

    }

    @AfterClass
    public static void tearDown() throws Exception {

        AbstractGUITestIT.tearDownClass();
        expireController.deleteUser(userEmail);

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
        
        // System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());

        /*
         * int row_num, col_num; row_num = 1; for (WebElement trElement :
         * tr_collection) { List<WebElement> td_collection =
         * trElement.findElements(By.xpath("td")); System.out.println("NUMBER OF
         * COLUMNS=" + td_collection.size()); col_num = 1; for (WebElement
         * tdElement : td_collection) { System.out.println("row # " + row_num +
         * ", col # " + col_num + "text=" + tdElement.getText()); col_num++; }
         * row_num++; }
         */

    }

    private List<WebElement> getTableElement(String tableName) {
        WebElement table_element = driver.findElement(By.id(tableName));
        List<WebElement> tr_collection = table_element.findElements(By.xpath("id('" + tableName + "')/tbody/tr"));
        return tr_collection;
    }
}