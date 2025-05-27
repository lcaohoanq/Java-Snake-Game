package views;

import static org.junit.Assert.assertEquals;

import modules.user.UserScore;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import utils.EnvUtils;

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
    @Ignore
    public void getDataWhenLogin() {
        UserScore expectedResult = new UserScore(email_phone, password);
        loginView.setLogin(email_phone, password);
        UserScore actualResult = loginView.getDataWhenLogin();
        assertEquals(expectedResult, actualResult);
    }

}