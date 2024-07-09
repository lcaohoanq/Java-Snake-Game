package views;

import modules.user.UserDTO;
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
    public void getLogin() {
        UserDTO expectedResult = new UserDTO(email_phone, password);
        loginView.setLogin(email_phone, password);
        UserDTO actualResult = loginView.getLogin();
        assertEquals(expectedResult, actualResult);
    }

}