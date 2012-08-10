package com.gyorslevel.timer;

import com.gyorslevel.configuration.ConfigurationBean;
import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.expiration.UserExpirationCreatedTimeFactory;
import com.gyorslevel.jmx.JMXBean;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dave00
 */
public class UserExpireController {

    private JMXBean jmxBean;
    private List<UserExpiration> expirations = new ArrayList<UserExpiration>();
    private UserExpirationCreatedTimeFactory expirationCreatedTimeFactory = new UserExpirationCreatedTimeFactory();

    @Autowired
    public UserExpireController(JMXBean jmxBean) {
        this.jmxBean = jmxBean;
    }

    public void expireUsers() {

        List<UserExpiration> expirationsSnapShot = new ArrayList(getExpirations());
        Iterator<UserExpiration> iterator = expirationsSnapShot.iterator();

        while (iterator.hasNext()) {

            UserExpiration userExpiration = iterator.next();

            // We break the loop at the first user that did not expire
            // because the list is sorted
            if (!userExpired(userExpiration)) {
                break;
            }

            // We remove all expired users
            deleteUser(userExpiration);

        }

    }

    public List<String> listAllUsers() {

        List<String> activeUserEmails = new ArrayList<String>();

        for (UserExpiration expiration : expirations) {
            activeUserEmails.add(expiration.getUserEmail());
        }

        return activeUserEmails;

    }

    public void deleteUser(UserExpiration expiration) {
        jmxBean.deleteUser(expiration.getUserEmail());
        expirations.remove(expiration);
    }

    public UserExpiration createUser() {
        String email = jmxBean.generateUserEmail();
        jmxBean.createUser(email);
        UserExpiration expiration = new UserExpiration(email, expirationCreatedTimeFactory.getCreatedTime());
        expirations.add(expiration);
        return expiration;
    }

    public boolean userExists(String email) {
        return expirations.contains(new UserExpiration(email));
    }

    /**
     * @post: n.getCreatedTime() <= n.getCreatedTime()
     * @return
     */
    public List<UserExpiration> getExpirations() {
        Collections.sort(expirations);
        return expirations;
    }

    /**
     * @param expiration
     * @return true if expiration.getCreatedTime() < NOW - TIME_OUT
     */
    boolean userExpired(UserExpiration expiration) {
        long timeOut = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.TimeOut, Long.class);
        return expiration.getCreatedTime() < System.currentTimeMillis() - timeOut;
    }
}
