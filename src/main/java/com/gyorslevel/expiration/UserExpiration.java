package com.gyorslevel.expiration;

/**
 * Representing a User Expiration made up of an e-mail address and the time
 * was created.
 * @author dave00
 */
public class UserExpiration implements Comparable<UserExpiration> {

    private String userEmail;
    private long createdTime;

    /*
     * Use this to create a new instance
     */
    public UserExpiration(String userEmail, long createdTime) {
        this.userEmail = userEmail;
        this.createdTime = createdTime;
    }
    
    /*
     * Used for equality checks
     */
    public UserExpiration(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public long getCreatedTime() {
        return createdTime;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserExpiration other = (UserExpiration) obj;
        if ((this.userEmail == null) ? (other.userEmail != null) : !this.userEmail.equals(other.userEmail)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.userEmail != null ? this.userEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(UserExpiration o) {
        return Long.compare(createdTime, o.createdTime);
    }
    
}