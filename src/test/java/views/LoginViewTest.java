package views;

import modules.user.UserDTO;
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

    private void assertUserEntityEqualsExceptCreated(UserEntity expected, UserEntity actual) {
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getConfirmPassword(), actual.getConfirmPassword());
        assertEquals(expected.getScore(), actual.getScore());
    }


    @Test
    public void getLogin() {
        UserEntity expectedResult = new UserEntity(email_phone, password);
        loginView.setLogin(email_phone, password);
        UserEntity actualResult = loginView.getLogin();
        assertUserEntityEqualsExceptCreated(expectedResult, actualResult);
    }

}