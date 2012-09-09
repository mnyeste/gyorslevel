package com.gyorslevel.pop3;

import com.gyorslevel.configuration.ConfigurationBean;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
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
    String userName;

    private void saveFile(InputStream is, final String contextFolder, final String fileName) throws FileNotFoundException, IOException {
        File f = new File(contextFolder + "/" + userName + "/" + fileName);
        OutputStream out = new FileOutputStream(f);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        is.close();
    }

    /**
     * Represents the elements a parsed MimeMessage. Items contained can be type
     * String or HTML.
     */
    static class MyBodyPart {

        String text;
        boolean html;

        public MyBodyPart(String text, boolean html) {
            this.text = text;
            this.html = html;
        }
    }

    /**
     * Represents the tree structure of a parsed MimeMessage. Contains a list of
     * bodyParts which can be processed when the image tags 'src' attribute gets
     * overwritten by the filename where the image was actually saved.
     */
    static class BodyPartDOM {

        List<MyBodyPart> bodyParts = new ArrayList<MyBodyPart>();
        // Content-ID:Filename
        Map<String, String> mappedFiles = new HashMap<String, String>();
        // Filename:Path
        Map<String, String> attachedFiles = new HashMap<String, String>();

        String processBodyParts(boolean html) {

            StringBuilder builder = new StringBuilder();

            for (MyBodyPart bodyPart : bodyParts) {

                if (html != bodyPart.html) {
                    continue;
                }

                Iterator<String> iterator = mappedFiles.keySet().iterator();

                String part = bodyPart.text;

                while (iterator.hasNext()) {

                    String contentId = iterator.next();
                    if (bodyPart.text.contains(contentId)) {
                        String fileName = mappedFiles.get(contentId);
                        part = bodyPart.text.replaceAll(contentId, fileName);
                    }

                }

                builder.append(part);

            }

            return builder.toString();

        }
    }

    public SimpleMessage[] fetchMessages(String host, String username, String password) throws MessagingException {

        this.userName = username;

        try {

            final String domain = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.Domain, String.class);

            final MailReceiver receiver = new Pop3MailReceiver(host, username + "@" + domain, password);

            final Message[] messages = receiver.receive();

            SimpleMessage[] simpleMessages = new SimpleMessage[messages.length];

            for (int i = 0; i < messages.length; i++) {

                final Message message = messages[i];

                final String subject = message.getSubject();
                final Address address = message.getFrom()[0];
                final InternetAddress internetAddress = (InternetAddress) address;

                // TODO: handle multiple 'from'
                final String from = internetAddress.getAddress();
                final Date sentDate = message.getSentDate();
                final String sentDateStr = sentDate == null ? "" : sentDate.toString();
                
                String content;
                
                // TODO: attachments for plain text mails...
                
                if (message.isMimeType("text/plain") || message.isMimeType("text/html")) {
                    
                    content = (String) message.getContent();
                    
                    simpleMessages[i] = new SimpleMessage(Integer.toString(i + 1), content, subject, from, sentDateStr);
                    
                } else {
                    
                    BodyPartDOM dom = getContent(message);
                    content = dom.processBodyParts(true);
                    
                    simpleMessages[i] = new SimpleMessage(Integer.toString(i + 1), content, subject, from, sentDateStr, dom.attachedFiles);
                    
                }

            }

            return simpleMessages;

        } catch (MessagingException exception) {
            logger.error(exception);
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            logger.error(exception);
            throw new RuntimeException(exception);
        }

    }

    BodyPartDOM getContent(Message message) throws MessagingException, IOException {

        if (message.isMimeType("multipart/related") || message.isMimeType("multipart/mixed") || message.isMimeType("multipart/alternative")) {
            return collectBodyParts(message);
        } else {
            throw new UnsupportedOperationException("Not yet implemented:" + message.getContentType());
        }

    }

    BodyPartDOM collectBodyParts(Part part) throws IOException, MessagingException {

        BodyPartDOM dom = new BodyPartDOM();

        Object content = part.getContent();

        logger.debug("handleBodyPart() - Datahandler: " + part.getDataHandler() + " FileName: " + part.getFileName() + " Description: " + part.getDescription() + " Headers: " + part.getAllHeaders());

        if (content instanceof String) {

            logger.debug("handleBodyPart() - Found String content");

            if (part.isMimeType("text/plain")) {

                dom.bodyParts.add(new MyBodyPart((String) content, false));

            } else if (part.isMimeType("text/html")) {

                dom.bodyParts.add(new MyBodyPart((String) content, true));

            }

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
                dom.attachedFiles.putAll(subDom.attachedFiles);

            }

        } else if (content instanceof InputStream) {

            final String contextFolder = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.ContextFolder, String.class);
            final String mailImagesFolder = "static";
            final String fileName = part.getFileName();

            if (fileName == null) {
                logger.error("handleBodyPart() - File name is null! Username: " + userName);
                throw new RuntimeException("");
            }

            String contentId = null;

            final String[] headers = part.getHeader("Content-ID");

            if (headers != null && headers.length >= 1 && headers[0] != null) {
                contentId = headers[0].replaceAll("<", "").replaceAll(">", "");
            }

            logger.debug("handleBodyPart() - Found nested InputStream content. FileName: " + fileName + " Context Folder: " + contextFolder + " Content-ID: " + contentId);
            saveFile((InputStream) content, contextFolder, fileName);

            if (contentId == null) {
                dom.attachedFiles.put(fileName, mailImagesFolder + "/" + userName + "/" + fileName);
            } else {
                dom.mappedFiles.put("cid:" + contentId, mailImagesFolder + "/" + userName + "/" + fileName);
            }

        }

        return dom;

    }
}
