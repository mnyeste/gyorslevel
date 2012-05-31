/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel.pop3;

import java.util.Properties;
import javax.mail.*;

/**
 *
 * @author dave00
 */
public class Pop3EmailFetcher {

    public static String[] fetchMessages(String host, String username, String password) throws MessagingException {

        Properties props = new Properties();

        String provider = "pop3";

        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore(provider);
        store.connect(host, username, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();

        String[] subjects = new String[messages.length];

        for (int i = 0; i < messages.length; i++) {
            subjects[i] = messages[i].getSubject();
        }

        inbox.close(false);
        store.close();

        return subjects;

    }
}
