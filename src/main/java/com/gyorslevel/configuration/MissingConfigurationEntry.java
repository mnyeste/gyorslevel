package com.gyorslevel.configuration;

/**
 * @author dave00
 */
public class MissingConfigurationEntry extends RuntimeException {

    MissingConfigurationEntry(String key) {
        super(key);
    }
    
}
