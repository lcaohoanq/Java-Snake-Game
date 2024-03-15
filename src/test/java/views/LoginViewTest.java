package views;

import models.data.Account;
import org.junit.Before;
import org.junit.Test;
import utils.EnvUtils;

import static org.junit.Assert.*;

public class LoginViewTest {

    String username;
    String password;
    private LoginView loginView;

    @Before
    public void setUp() throws Exception {
        loginView = new LoginView();
        this.username = EnvUtils.get("TEST_USERNAME");
        this.password = EnvUtils.get("TEST_PASSWORD");
    }


    @Test
    public void getLogin() {
        Account expectedResult = new Account(username, password);
        loginView.setLogin(username, password);
        Account actualResult = loginView.getLogin();
        assertEquals(expectedResult, actualResult);
    }

}