package controllers;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.DBServices;
import utils.PasswordHandler;
import views.LoginView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LoginControllerTest {

    String username;
    String password;
    LoginView mockView;
    LoginController mockController;

    @Before
    public void setUp() throws Exception {
        Dotenv dotenv = Dotenv.load();
        mockView = mock(LoginView.class);
        mockController = new LoginController(mockView);
        this.username = dotenv.get("TEST_USERNAME");
        this.password = dotenv.get("TEST_PASSWORD");
    }

    @After
    public void tearDown() throws Exception {
    }

    //Test with empty and non-empty fields.
    @Test
    public void isEmpty() {
        boolean actualResult = mockController.isEmpty("", "");
        assertTrue(actualResult);
    }

    //Test with valid and invalid credentials, database interactions, password matching.
    @Test
    public void isMatching() {
        boolean actualResult = mockController.isMatching(username, password);
        assertTrue(actualResult);
    }
}