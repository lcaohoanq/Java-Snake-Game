package models.ui;

import controllers.LoginController;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.LoginView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LoginModelTest {
    String username;
    String password;
    private LoginModel loginModel;

    @Before
    public void setUp() throws Exception {
        Dotenv dotenv = Dotenv.load();
        this.loginModel = new LoginModel();
        this.username = dotenv.get("TEST_USERNAME");
        this.password = dotenv.get("TEST_PASSWORD");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isAdmin() {
    }

    @Test
    public void isMatching() {
        boolean actualResult = loginModel.isMatching(username, password);
        assertTrue(actualResult);
    }

    @Test
    public void isEmpty() {
        boolean actualResult = loginModel.isEmpty("", "");
        assertTrue(actualResult);
    }

    @Test
    public void username() {
    }

    @Test
    public void password() {
    }
}