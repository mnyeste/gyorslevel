package com.gyorslevel.timer;

import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.expiration.UserExpirationCreatedTimeFactory;
import com.gyorslevel.jmx.JMXBean;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO: use List instead of list TODO: create class to represent user expiry
 *
 * @author dave00
 */
public class UserExpireController extends TimerTask {

    private final JMXBean jmxBean;
    private List<UserExpiration> expirations = new ArrayList<UserExpiration>();
    private UserExpirationCreatedTimeFactory expirationCreatedTimeFactory = new UserExpirationCreatedTimeFactory();

    @Autowired
    public UserExpireController(JMXBean jmxBean) {
        this.jmxBean = jmxBean;
    }

    @Override
    public void run() {
        jmxBean.listAllUsers();
    }

    public List<String> listAllUsers() {
        return jmxBean.listAllUsers();
    }

    public void deleteUser(UserExpiration expiration) {
        jmxBean.deleteUser(expiration.getUserEmail());
        expirations.remove(expiration);
    }

    public UserExpiration createUser() {
        String email = JMXBean.generateUserEmail();
        jmxBean.createUser(email);
        UserExpiration expiration = new UserExpiration(email, expirationCreatedTimeFactory.getCreatedTime());
        expirations.add(expiration);
        return expiration;
    }

    boolean userExists(String email) {
        return expirations.contains(new UserExpiration(email));
    }

    /**
     * @post: n.getCreatedTime() <= n.getCreatedTime()
     *
     * @return
     */
    public List<UserExpiration> getExpirations() {
        Collections.sort(expirations);
        return expirations;
    }
}
