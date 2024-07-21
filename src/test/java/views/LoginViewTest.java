package views;

import modules.user.UserEntity;
import org.junit.Before;
import org.junit.Test;
import utils.EnvUtils;

import static org.junit.Assert.*;

public class LoginViewTest {

    String email_phone;
    String password;
    private LoginView loginView;

    @Before
    public void setUp() throws Exception {
        loginView = new LoginView();
        this.email_phone = EnvUtils.get("TEST_EMAIL");
        this.password = EnvUtils.get("TEST_PASSWORD");
    }


    @Test
    public void getDataWhenLogin() {
        UserEntity expectedResult = new UserEntity(email_phone, password);
        loginView.setLogin(email_phone, password);
        UserEntity actualResult = loginView.getDataWhenLogin();
        assertEquals(expectedResult, actualResult);
    }

}