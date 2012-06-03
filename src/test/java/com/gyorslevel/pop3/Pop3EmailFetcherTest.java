package com.gyorslevel.pop3;

import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import junit.framework.Assert;
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
}
