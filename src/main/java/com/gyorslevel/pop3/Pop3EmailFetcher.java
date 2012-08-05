package com.gyorslevel.pop3;

import com.gyorslevel.configuration.ConfigurationBean;
import java.io.*;
import java.util.*;
import javax.mail.*;
import org.apache.log4j.Logger;
import org.springframework.integration.mail.MailReceiver;
import org.springframework.integration.mail.Pop3MailReceiver;

/**
 * Responsible to fetch and parse {@link Message}s
 *
 * @author dave00
 */
public class Pop3EmailFetcher {

    private static Logger logger = Logger.getLogger(Pop3EmailFetcher.class);

    /**
     * Represents the tree structure of a parsed MimeMessage. Contains a list of
     * bodyParts which can be processed when the image tags 'src' attribute gets
     * overwritten by the filename where the image was actually saved.
     */
    static class BodyPartDOM {

        List<String> bodyParts = new ArrayList<String>();
        // Content-ID:Filename
        Map<String, String> mappedFiles = new HashMap<String, String>();

        String processBodyParts() {

            StringBuilder builder = new StringBuilder();

            for (String bodyPart : bodyParts) {
                Iterator<String> iterator = mappedFiles.keySet().iterator();

                String part = bodyPart;

                while (iterator.hasNext()) {

                    String contentId = iterator.next();
                    if (bodyPart.contains(contentId)) {
                        String fileName = mappedFiles.get(contentId);
                        part = bodyPart.replaceAll(contentId, fileName);
                    }

                }

                builder.append(part);

            }

            return builder.toString();

        }
    }

    public static SimpleMessage[] fetchMessages(String host, String username, String password) throws MessagingException {

        try {

            MailReceiver receiver = new Pop3MailReceiver(host, username, password);

            Message[] messages = receiver.receive();

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

        logger.debug(message.getClass().getName());

        if (message.isMimeType("text/plain") || message.isMimeType("text/html")) {

            return (String) message.getContent();

        } else if (message.isMimeType("multipart/related") || message.isMimeType("multipart/mixed") || message.isMimeType("multipart/alternative")) {

            BodyPartDOM dom = collectBodyParts(message);
            return dom.processBodyParts();

        } else {

            throw new UnsupportedOperationException("Not yet implemented:" + message.getContentType());

        }

    }

    private static BodyPartDOM collectBodyParts(Part part) throws IOException, MessagingException {

        BodyPartDOM dom = new BodyPartDOM();

        Object content = part.getContent();

        logger.debug("handleBodyPart() - Datahandler: " + part.getDataHandler() + " FileName: " + part.getFileName() + " Description: " + part.getDescription() + " Headers: " + part.getAllHeaders());

        if (content instanceof String) {

            logger.debug("handleBodyPart() - Found String content");
            dom.bodyParts.add((String) content);

        } else if (content instanceof Multipart) {

            logger.debug("handleBodyPart() - Found nested Multipart content");

            Multipart innerMultiPart = (Multipart) content;
            int count = innerMultiPart.getCount();

            logger.debug("handleBodyPart() - Nested Multipart has [" + count + "] inner element");

            for (int i = 0; i < count; i++) {

                BodyPart innerBodyPart = innerMultiPart.getBodyPart(i);
                BodyPartDOM subDom = collectBodyParts(innerBodyPart);
                dom.bodyParts.addAll(subDom.bodyParts);
                dom.mappedFiles.putAll(subDom.mappedFiles);

            }

        } else if (content instanceof InputStream) {

            final String contextFolder = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.ContextFolder, String.class);
            final String mailImagesFolder = "images";
            final String fileName = part.getFileName();
            final String contentId = part.getHeader("Content-ID")[0].replaceAll("<", "").replaceAll(">", "");

            logger.debug("handleBodyPart() - Found nested InputStream content. FileName: " + fileName + " Content-ID: " + contentId + " Context Folder: " + contextFolder);

            InputStream is = (InputStream) content;
            File f = new File(contextFolder + "/" + fileName);
            OutputStream out = new FileOutputStream(f);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            is.close();

            dom.mappedFiles.put("cid:" + contentId, mailImagesFolder + "/" + fileName);

        }

        return dom;

    }
}