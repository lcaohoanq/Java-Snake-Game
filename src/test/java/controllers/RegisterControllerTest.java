package controllers;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Before;
import org.junit.Test;
import views.RegisterView;

import static org.junit.Assert.*;

public class RegisterControllerTest {
    String username;
    private RegisterView registerView;
    private RegisterController registerController;

    @Before
    public void setUp() throws Exception {
        Dotenv dotenv = Dotenv.load();
        registerView = new RegisterView();
        registerController = new RegisterController(registerView);
        this.username = dotenv.get("TEST_USERNAME");
    }

    @Test
    public void isDuplicateUsername() {
        boolean actualResult = registerController.isDuplicateUsername(username);
        assertTrue(actualResult);
    }
}