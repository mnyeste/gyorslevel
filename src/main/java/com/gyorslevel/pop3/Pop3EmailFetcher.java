/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.pop3;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;

/**
 *
 * @author dave00
 */
public class Pop3EmailFetcher {

    public static SimpleMessage[] fetchMessages(String host, String username, String password) throws MessagingException {

        Properties props = new Properties();

        String provider = "pop3";

        try {

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore(provider);
            store.connect(host, username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();

            SimpleMessage[] subjects = new SimpleMessage[messages.length];

            for (int i = 0; i < messages.length; i++) {
                final String subject = messages[i].getSubject();
                // TODO: handle multiple 'from'
                final String from = messages[i].getFrom()[0].toString();
                final Date sentDate = messages[i].getSentDate();
                final String sentDateStr = sentDate == null ? "" : sentDate.toString();
                final String content = getContent(messages[i]);
                subjects[i] = new SimpleMessage(Integer.toString(i + 1), content, subject, from, sentDateStr);
            }

            inbox.close(false);
            store.close();

            return subjects;

        } catch (MessagingException exception) {
            System.err.println(exception);
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            System.err.println(exception);
            throw new RuntimeException(exception);
        }

    }

    static String getContent(Message message) throws MessagingException, IOException {

        if (message.isMimeType("text/plain") || message.isMimeType("text/html")) {
            return (String) message.getContent();
        } else {
            throw new UnsupportedOperationException("Not yet implemented");
        }

    }
}
