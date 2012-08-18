package com.gyorslevel.controller.userhandler;

import com.gyorslevel.expiration.UserExpiration;
import com.gyorslevel.timer.UserExpireController;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import org.powermock.reflect.Whitebox;

/**
 * @author dave00
 */
@RunWith(PowerMockRunner.class)
public class UserHandlerControllerTest {

    private UserExpireController expirationController;
    private UserHandlerController controller;
    private HttpSession session;
    
    @Before
    public void setUp()
    {
        
        session = mock(HttpSession.class);
        
        expirationController = mock(UserExpireController.class);
        
        controller = new UserHandlerController() {
        };
        
        Whitebox.setInternalState(controller, "expireController", expirationController);
        
    }
    
    @Test
    public void testActiveUserInSessionButAlreadyExpired()
    {
        UserExpiration expiration = new UserExpiration("test@localhost");
        
        doReturn(expiration).when(session).getAttribute("expiration");
        doReturn(false).when(expirationController).userExists("test@localhost");
        
        Assert.assertTrue(controller.userSessionExpired(session));
    }
    
    @Test
    public void testActiveUserInSessionActiveExpiration()
    {
        UserExpiration expiration = new UserExpiration("test@localhost");
        
        doReturn(expiration).when(session).getAttribute("expiration");
        doReturn(true).when(expirationController).userExists("test@localhost");
        
        Assert.assertFalse(controller.userSessionExpired(session));
    }
    
}