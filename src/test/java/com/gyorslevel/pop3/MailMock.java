package com.gyorslevel.pop3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

public class MailMock {
    
    List<BodyPart> parts = new ArrayList<BodyPart>();
    Part message;
    
    private MailMock() {
        message = mock(Part.class);
    }
    
    public static MailMock newInstance() {
        return new MailMock();
    }

    MailMock addStringContent(String testStringContent) throws IOException, MessagingException {
        
        BodyPart textBodyPart = mock(BodyPart.class);
        doReturn(testStringContent).when(textBodyPart).getContent();
        
        parts.add(textBodyPart);
        
        return this;
    }
    
    MailMock addInputStreamContent(String fileName, String contentId) throws IOException, MessagingException {
        
        InputStream is = mock(InputStream.class);
        
        BodyPart isBodyPart = mock(BodyPart.class);
        doReturn(is).when(isBodyPart).getContent();
        doReturn(fileName).when(isBodyPart).getFileName();
        
        String[] header = new String[]{contentId};
        doReturn(header).when(isBodyPart).getHeader("Content-ID");
        
        parts.add(isBodyPart);
        
        return this;
        
    }
    
    MailMock addInputStreamAttachment(String fileName) throws IOException, MessagingException {
        
        InputStream is = mock(InputStream.class);
        
        BodyPart isBodyPart = mock(BodyPart.class);
        doReturn(is).when(isBodyPart).getContent();
        doReturn(fileName).when(isBodyPart).getFileName();
        
        parts.add(isBodyPart);
        
        return this;
        
    }
    
    Part build() throws MessagingException, IOException {
        
        Multipart multipart = mock(Multipart.class);
        doReturn(parts.size()).when(multipart).getCount();
        
        for (int i = 0; i < parts.size(); i++) {
            BodyPart bodyPart = parts.get(i);
            doReturn(bodyPart).when(multipart).getBodyPart(i);
        }
        
        doReturn(multipart).when(message).getContent();
        
        return message;
    }

}
