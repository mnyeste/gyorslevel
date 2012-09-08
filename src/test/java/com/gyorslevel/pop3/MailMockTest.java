/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.pop3;

import java.io.IOException;
import java.io.InputStream;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author dave00
 */
@RunWith(PowerMockRunner.class)
public class MailMockTest {
    
    String contentId = "<part1.09070609.08000706@localhost>";
 
    @Test
    public void testMockMailStringContent() throws IOException, MessagingException {
        
        Part message = MailMock.newInstance().addStringContent("testStringContent").build();
        Multipart content = (Multipart) message.getContent();
        BodyPart bodyPart = content.getBodyPart(0);
        String text = (String) bodyPart.getContent();
        Assert.assertEquals("testStringContent", text);
        
    }
    
    @Test
    public void testMockMailInputStreamContent() throws IOException, MessagingException {
        
        Part message = MailMock.newInstance().addInputStreamContent("cb.jpg", contentId).build();
        Multipart content = (Multipart) message.getContent();
        BodyPart bodyPart = content.getBodyPart(0);
        Object is = bodyPart.getContent();
        Assert.assertTrue(is instanceof InputStream);
        
    }
    
    @Test
    public void testMockMailInputStreamAttachment() throws IOException, MessagingException {
        
        Part message = MailMock.newInstance().addInputStreamAttachment("cb.jpg").build();
        Multipart content = (Multipart) message.getContent();
        BodyPart bodyPart = content.getBodyPart(0);
        Object is = bodyPart.getContent();
        Assert.assertTrue(is instanceof InputStream);
        
    }
    
    @Test
    public void testMockMailMixedContent() throws IOException, MessagingException {
        
        Part message = MailMock.newInstance().addStringContent("testStringContent").addInputStreamContent("cb.jpg", contentId).build();
        Multipart content = (Multipart) message.getContent();
        
        BodyPart textBodyPart = content.getBodyPart(0);
        String text = (String) textBodyPart.getContent();
        Assert.assertEquals("testStringContent", text);
        
        BodyPart isBodyPart = content.getBodyPart(1);
        Object is = isBodyPart.getContent();
        Assert.assertTrue(is instanceof InputStream);
        
    }
    
}