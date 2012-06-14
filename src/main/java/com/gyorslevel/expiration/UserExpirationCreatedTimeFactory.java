package com.gyorslevel.expiration;

/**
 * This is class has been extracted to help 'mock' the created time in the
 * respective classes.
 * @author dave00
 */
public class UserExpirationCreatedTimeFactory {
    
    public long getCreatedTime()
    {
        return System.currentTimeMillis();
    }
    
}