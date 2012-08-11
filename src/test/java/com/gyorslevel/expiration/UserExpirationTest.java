package com.gyorslevel.expiration;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author dave00
 */
public class UserExpirationTest {

    @Test
    public void testUserNameEquals() {

        UserExpiration exp1 = new UserExpiration("user1");
        UserExpiration exp2 = new UserExpiration("user1");

        Assert.assertEquals(exp1, exp2);

    }

    @Test
    public void testUserNameNotEquals() {

        UserExpiration exp1 = new UserExpiration("user1");
        UserExpiration exp2 = new UserExpiration("user2");

        Assert.assertFalse(exp1.equals(exp2));

    }

    @Test
    public void testGetUserName() {
        
        UserExpiration expiration = new UserExpiration("test@gyorslevel.hu");
        Assert.assertEquals("test", expiration.getUserName());
        
    }
}