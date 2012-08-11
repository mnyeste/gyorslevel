package com.gyorslevel.timer;

import com.gyorslevel.configuration.ConfigurationBean;
import com.gyorslevel.configuration.ConfigurationBean.ConfigurationBeanKey;
import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.expiration.UserExpirationCreatedTimeFactory;
import com.gyorslevel.jmx.JMXBean;
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

/**
 * @author dave00
 */
@PrepareForTest(ConfigurationBean.class)
@RunWith(PowerMockRunner.class)
public class UserExpireControllerTest {

    // Mock JMXBean
    JMXBean jmxBean;
    UserExpirationCreatedTimeFactory factory;
    UserExpireController controller;
    UserExpiration expiration;
    long timeOut;
    String tempdir = System.getProperty("java.io.tmpdir");

    @Before
    public void setUp() throws Exception {

        jmxBean = mock(JMXBean.class);
        controller = new UserExpireController(jmxBean);

        factory = mock(UserExpirationCreatedTimeFactory.class);
        doReturn(System.currentTimeMillis()).when(factory).getCreatedTime();

        Whitebox.setInternalState(controller, "expirationCreatedTimeFactory", factory);

        PowerMockito.when(jmxBean.generateUserEmail()).thenReturn(getNextEmail(), getPossibleUserEmails());

        PowerMockito.mockStatic(ConfigurationBean.class);
        PowerMockito.when(ConfigurationBean.getValue(ConfigurationBeanKey.TimeOut, Long.class)).thenReturn(10000L);
        PowerMockito.when(ConfigurationBean.getValue(ConfigurationBeanKey.ContextFolder, String.class)).thenReturn(tempdir);

        expiration = controller.createUser();

        timeOut = ConfigurationBean.getValue(ConfigurationBean.ConfigurationBeanKey.TimeOut, Long.class);

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
    public void testCreatedUserExpiryCreatesUserFolder() {

        UserExpiration expiration = controller.createUser();
        String contextFile = ConfigurationBean.getValue(ConfigurationBeanKey.ContextFolder, String.class);

        File tempFile = new File(contextFile + "/" + expiration.getUserName());

        Assert.assertTrue(tempFile.exists());

        controller.deleteUser(expiration);

        Assert.assertFalse(tempFile.exists());
    }

    @Test
    public void testDeletedUserDoesNotExist() {

        controller.deleteUser(expiration);
        Assert.assertFalse(controller.userExists(expiration.getUserEmail()));

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
    public void testUserShouldBeDeletedBecauseExpired() {

        doReturn((System.currentTimeMillis() - 1) - timeOut).when(factory).getCreatedTime();
        UserExpiration expiration = controller.createUser();

        Assert.assertTrue(controller.userExpired(expiration));

    }

    @Test
    public void testUserShouldNotBeDeleted() {

        doReturn((System.currentTimeMillis() + 100) - timeOut).when(factory).getCreatedTime();
        UserExpiration expiration = controller.createUser();

        Assert.assertFalse(controller.userExpired(expiration));

    }

    @Test
    public void testTimer() throws InterruptedException {

        Timer controllerTimer = new Timer();

        // Create user
        doReturn(System.currentTimeMillis()).when(factory).getCreatedTime();
        UserExpiration expiration = controller.createUser();

        // Expect it exists
        Assert.assertTrue(controller.userExists(expiration.getUserEmail()));

        // Start timer
        controllerTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                controller.expireUsers();
            }
        }, 0, 5);

        // Wait
        Thread.sleep(TimeUnit.MILLISECONDS.convert((timeOut / 1000) + 5, TimeUnit.SECONDS));

        // Expect deleted
        Assert.assertFalse(controller.userExists(expiration.getUserEmail()));

    }

    private String[] getPossibleUserEmails() {
        String[] userEmails = new String[100];
        for (int i = 0; i < 100; i++) {
            userEmails[i] = getNextEmail();
        }
        return userEmails;
    }

    private String getNextEmail() {
        return Long.toString(System.nanoTime()) + "@gyorslevel.hu";
    }
}
