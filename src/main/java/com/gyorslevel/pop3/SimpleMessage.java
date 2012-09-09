package com.gyorslevel.pop3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dave00
 */
public class SimpleMessage {

    // Filename:Path
    private Map<String, String> attachedFiles = new HashMap<String, String>();

    private String message;
    private String subject;
    private String from;
    private String sentDate;
    private String id;

    public SimpleMessage(String id, String message, String subject, String from, String sentDate) {
        this.id = id;
        this.message = message;
        this.subject = subject;
        this.from = from;
        this.sentDate = sentDate;
    }
    
    public SimpleMessage(String id, String message, String subject, String from, String sentDate, Map<String, String> attachedFiles) {
        this(id, message, subject, from, sentDate);
        this.attachedFiles = attachedFiles;
    }

    public String getFrom() {
        return from;
    }

    public String getSentDate() {
        return sentDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getAttachedFiles() {
        return attachedFiles;
    }
    
    @Override
    public String toString() {
        return "SimpleMessage{" + "message=" + message + ", subject=" + subject + ", from=" + from + ", sentDate=" + sentDate + ", id=" + id + '}';
    }

}