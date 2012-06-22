/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.it;

import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.pop3.Pop3EmailFetcher;
import com.gyorslevel.pop3.SimpleMessage;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;

/**
 * @author dave00
 */
public class Pop3EmailFetchTestIT extends BaseITTest {

    String userEmail;

    @TestResource(resourceTypes=ResourceType.UserExpireController)
    @Test
    public void testFetchMail() throws MessagingException, InterruptedException {
        
        UserExpiration expiration = expireController.createUser();
        userEmail = expiration.getUserEmail();
        
        sendTestMail();
        
        SimpleMessage[] messages = Pop3EmailFetcher.fetchMessages("localhost", userEmail, "pass");
        Assert.assertEquals(1, messages.length);
        Assert.assertEquals("1", messages[0].getId());
    }

    // create user, sendmail, readmail, delete user
    @After
    public void tearDown() throws IOException {
        expireController.deleteUser(new UserExpiration(userEmail));
    }

    void sendTestMail() throws InterruptedException {
        String user = "test";

        String password = "test";
        String fromAddress = "Pop3MailFetchTest@localhost";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.username", user);
        properties.put("mail.smtp.password", password);
        properties.put("mail.transport.protocol", "smtp");

        Session session = Session.getDefaultInstance(properties, null);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Email from our JAMEs");
            message.setText("hiiiiii!!");
            Transport.send(message);
            System.out.println("Email sent");
            Thread.sleep(5000);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
    }
}
