package com.gyorslevel.timer;

import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.expiration.UserExpirationCreatedTimeFactory;
import com.gyorslevel.jmx.JMXBean;
import java.util.List;
import java.util.Timer;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import org.powermock.reflect.Whitebox;

/**
 * @author dave00
 */
@RunWith(PowerMockRunner.class)
public class UserExpireControllerTest {

    // Mock JMXBean
    JMXBean jmxBean = mock(JMXBean.class);
    UserExpirationCreatedTimeFactory factory;
    UserExpireController controller = new UserExpireController(jmxBean);
    UserExpiration expiration;

    @Before
    public void setUp() {

        factory = mock(UserExpirationCreatedTimeFactory.class);
        doReturn(System.currentTimeMillis()).when(factory).getCreatedTime();

        Whitebox.setInternalState(controller, "expirationCreatedTimeFactory", factory);

        // TODO: rename createUserExpiration()
        expiration = controller.createUser();
    }

    @Test
    public void testCreatedUserIsRecorded() {

        Assert.assertTrue(controller.userExists(expiration.getUserEmail()));

    }

    @Test
    public void testCreatedUserExpiryIsExactlyNowAndExpiryTime() {

        doReturn(1234L).when(factory).getCreatedTime();

        UserExpiration expiration = controller.createUser();

        Assert.assertEquals(expiration.getCreatedTime(), 1234L);

        controller.deleteUser(expiration);
    }

    @Test
    public void testListUserExpirationsAreInOrder() {

        doReturn(3L).when(factory).getCreatedTime();
        UserExpiration expiration1 = controller.createUser();

        doReturn(1L).when(factory).getCreatedTime();
        UserExpiration expiration2 = controller.createUser();

        doReturn(2L).when(factory).getCreatedTime();
        UserExpiration expiration3 = controller.createUser();

        List<UserExpiration> expirations = controller.getExpirations();
        UserExpiration[] expirationsArray = expirations.toArray(new UserExpiration[]{});

        Assert.assertEquals(1L, expirationsArray[0].getCreatedTime());
        Assert.assertEquals(2L, expirationsArray[1].getCreatedTime());
        Assert.assertEquals(3L, expirationsArray[2].getCreatedTime());

        controller.deleteUser(expiration1);
        controller.deleteUser(expiration2);
        controller.deleteUser(expiration3);

    }

    @Test
    public void testDeletedUserDoesNotExist() {

        controller.deleteUser(expiration);
        Assert.assertFalse(controller.userExists(expiration.getUserEmail()));

    }

    // TODO
    @Ignore
    @Test
    public void testTimer() {

        Timer myTimer = new Timer();
        myTimer.schedule(controller, 0);

        // TODO: create user with 2 second expiry
        // TODO: check after 1 second: user should still exist
        // TODO: check after 2 second: user should be gone

        verify(controller, times(1)).listAllUsers();

    }
}