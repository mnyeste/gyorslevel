package com.gyorslevel.timer;

import com.gyorslevel.jmx.JMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dave00
 */
public class UserExpireController extends TimerTask {
    
    private final JMXBean jmxBean;
    private List<String> userEmails = new ArrayList<String>();

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

    public void deleteUser(String email) {
        jmxBean.deleteUser(email);
        userEmails.remove(email);
    }

    public String createUser() {
        String email = JMXBean.generateUserEmail();
        jmxBean.createUser(email);
        userEmails.add(email);
        return email;
    }

    boolean userExists(String userEmail) {
        return userEmails.contains(userEmail);
    }
    
}
