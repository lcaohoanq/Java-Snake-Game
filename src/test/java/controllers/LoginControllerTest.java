package controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.DBServices;
import utils.PasswordHandler;
import views.LoginView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LoginControllerTest {

    LoginView mockView;
    LoginController mockController;

    @Before
    public void setUp() throws Exception {
//        mockView = mock(LoginView.class);
//        mockController = new LoginController(mockView);
    }

    @After
    public void tearDown() throws Exception {
    }

    //Test with empty and non-empty fields.
    @Test
    public void isEmpty() {
//        boolean actualResult = mockController.isEmpty("", "");
//        assertTrue(actualResult);
    }

    //Test with valid and invalid credentials, database interactions, password matching.
    @Test
    public void isMatching() {
//        boolean actualResult = mockController.isMatching("hoang", "Luucaohoang1604^^");
//        assertTrue(actualResult);
    }
}