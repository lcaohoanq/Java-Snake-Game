package views;

import io.github.cdimascio.dotenv.Dotenv;
import models.data.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginViewTest {

    String username;
    String password;
    private LoginView loginView;

    @Before
    public void setUp() throws Exception {
        Dotenv dotenv = Dotenv.load();
        loginView = new LoginView();
        this.username = dotenv.get("TEST_USERNAME");
        this.password = dotenv.get("TEST_PASSWORD");
    }


    @Test
    public void getLogin() {
        Account expectedResult = new Account(username, password);
        loginView.setLogin(username, password);
        Account actualResult = loginView.getLogin();
        assertEquals(expectedResult, actualResult);
    }

}