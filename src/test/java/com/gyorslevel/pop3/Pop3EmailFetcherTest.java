package com.gyorslevel.pop3;

import com.gyorslevel.pop3.Pop3EmailFetcher.BodyPartDOM;
import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * @author dave00
 */
@PrepareForTest(Pop3EmailFetcher.class)
@RunWith(PowerMockRunner.class)
public class Pop3EmailFetcherTest {
    
    Pop3EmailFetcher emailFetcher;
    
    @Before() 
    public void setUp() {
        
        emailFetcher = new Pop3EmailFetcher();
        emailFetcher.userName = "user";
        
    }

    /**
     * TODO: This test no longer makes sense, will be altered together with 
     * plain + attachments
     */
    @Ignore
    @Test
    public void testTransformTextMessage() throws MessagingException, IOException {

        Message message = mock(Message.class);
        doReturn(true).when(message).isMimeType("text/plain");
        doReturn("My sample text").when(message).getContent();

        BodyPartDOM dom = emailFetcher.getContent(message);
        Assert.assertEquals("My sample text", dom.processBodyParts(true));

    }

    @Test
    public void testMultiMailWithInlineImage() throws MessagingException, IOException {

        suppress(method(Pop3EmailFetcher.class, "saveFile"));

        String fileName = "cb.jpg";
        String contentId = "cid:part1.09070609.08000706@localhost";

        String text = ""
                + "<html>"
                + "<head>"
                + "<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\">"
                + "</head>"
                + "<body bgcolor=\"#FFFFFF\" text=\"#000000\">"
                + "<img alt=\"\" src=\"" + contentId + "\" height=\"417\""
                + "width=\"625\">"
                + "</body>"
                + "</html>";

        Part message = MailMock.newInstance().addStringContent(text).addInputStreamContent(fileName, "<" + contentId + ">").build();

        BodyPartDOM collectBodyParts = emailFetcher.collectBodyParts(message);

        Assert.assertEquals(1, collectBodyParts.mappedFiles.size());

        Assert.assertEquals(String.format("static/%s/%s", "user", fileName), collectBodyParts.mappedFiles.get("cid:"+contentId));
        
    }
    
    @Test
    public void testMultiMailWithAttachedImage() throws MessagingException, IOException {
        
        suppress(method(Pop3EmailFetcher.class, "saveFile"));

        String fileName = "cb.jpg";

        String text = ""
                + "<html>"
                + "<head>"
                + "<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\">"
                + "</head>"
                + "<body bgcolor=\"#FFFFFF\" text=\"#000000\">"
                + "Sample content"
                + "</body>"
                + "</html>";

        Part message = MailMock.newInstance().addStringContent(text).addInputStreamAttachment(fileName).build();

        BodyPartDOM collectBodyParts = emailFetcher.collectBodyParts(message);

        Assert.assertEquals(1, collectBodyParts.attachedFiles.size());

        Assert.assertEquals(String.format("static/%s/%s", "user", fileName), collectBodyParts.attachedFiles.get(fileName));
        
    }
    
}