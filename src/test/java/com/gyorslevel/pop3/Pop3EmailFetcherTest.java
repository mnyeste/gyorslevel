package com.gyorslevel.pop3;

import java.io.IOException;
import java.io.InputStream;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

/**
 * @author dave00
 */
@RunWith(PowerMockRunner.class)
public class Pop3EmailFetcherTest {

    @Test
    public void testTransformTextMessage() throws MessagingException, IOException {

        Message message = mock(Message.class);
        doReturn(true).when(message).isMimeType("text/plain");
        doReturn("My sample text").when(message).getContent();
        
        String content = Pop3EmailFetcher.getContent(message);
        Assert.assertEquals("My sample text", content);
        
        
    }
    
    /**
     * !!!This is ignored because this is already tested in {@link BodyPartDomTest}
     * @throws MessagingException
     * @throws IOException 
     */
    @Ignore
    @Test
    public void testNestedContentFetch() throws MessagingException, IOException
    {
        
        
        String nestedtextPart = "<html>" +
            "<head><meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\"></head>"
                + "<body text=\"#000000\" bgcolor=\"#FFFFFF\">"
                + "Izeke<br>"
                + "<img src=\"cid:part1.04030201.02040206@localhost\" alt=\"\"><br>"
                + "</body>" + 
            "</html>";
        
        BodyPart nestedTextBodyPart = mock(BodyPart.class);
        doReturn(nestedtextPart).when(nestedTextBodyPart).getContent();
        
        InputStream is = mock(InputStream.class);
        BodyPart nestedInputStreamBodyPart = mock(BodyPart.class);
        doReturn(is).when(nestedInputStreamBodyPart).getContent();
        doReturn("proba.png").when(nestedInputStreamBodyPart).getFileName();
        
        // Nested multipart
        Multipart nestedMultipart = mock(Multipart.class);
        doReturn(2).when(nestedMultipart).getCount();        
        doReturn(nestedTextBodyPart).when(nestedMultipart).getBodyPart(0);
        doReturn(nestedInputStreamBodyPart).when(nestedMultipart).getBodyPart(1);
        
        BodyPart textBodyPart = mock(BodyPart.class);
        doReturn("Izeke").when(textBodyPart).getContent();
        
        BodyPart nestedMultiPartBodyPart = mock(BodyPart.class);
        doReturn(nestedMultipart).when(nestedMultiPartBodyPart).getContent();
        
        // Main multipart
        Multipart multipart = mock(Multipart.class);
        doReturn(2).when(multipart).getCount();
        doReturn(textBodyPart).when(multipart).getBodyPart(0);
        doReturn(nestedMultiPartBodyPart).when(multipart).getBodyPart(1);
        
        // MimeMessage
        Message message = mock(Message.class);
        doReturn(true).when(message).isMimeType("multipart/related");
        doReturn(multipart).when(message).getContent();
        
        Assert.assertEquals("Izeke"+nestedtextPart, Pop3EmailFetcher.getContent(message));
        
        
    }
}
