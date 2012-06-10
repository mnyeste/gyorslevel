package com.gyorslevel.timer;

import com.gyorslevel.jmx.JMXBean;
import java.util.Timer;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

/**
 * @author dave00
 */
@RunWith(PowerMockRunner.class)
public class UserExpireControllerTest {

    // Mock JMXBean
    JMXBean jmxBean = mock(JMXBean.class);
    UserExpireController controller = new UserExpireController(jmxBean);
    String userEmail;
    
    @Before
    public void setUp() {
        userEmail = controller.createUser();
    }
    
    @Test
    public void testCreatedUserIsRecorded() {
        
        Assert.assertTrue(controller.userExists(userEmail));
        
    }
    
    @Test
    public void testDeletedUserDoesNotExist() {
        
        controller.deleteUser(userEmail);
        Assert.assertFalse(controller.userExists(userEmail));
        
    }
    
    @Test
    public void testTimer() {
        
        Timer myTimer = new Timer();
        myTimer.schedule(controller, 0);
        
        verify(jmxBean, times(1)).listAllUsers();
        
    }
}
