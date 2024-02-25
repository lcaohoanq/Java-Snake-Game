package controllers;

import org.junit.Before;
import org.junit.Test;
import views.RegisterView;

import static org.junit.Assert.*;

public class RegisterControllerTest {
    private RegisterView registerView;
    private RegisterController registerController;

    @Before
    public void setUp() throws Exception {
        registerView = new RegisterView();
        registerController = new RegisterController(registerView);
    }

    @Test
    public void isDuplicateUsername() {
        boolean actualResult = registerController.isDuplicateUsername("hoang");
        assertTrue(actualResult);
    }
}