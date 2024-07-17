package views;

import modules.user.UserDTO;
import modules.user.UserEntity;
import org.junit.Before;
import org.junit.Test;
import utils.EnvUtils;

import static org.junit.Assert.*;

public class RegisterViewTest {

    String email;
    String firstName;
    String lastName;
    String password;
    RegisterView registerView;

    @Before
    public void setUp() throws Exception {
        registerView = new RegisterView();
        this.email = EnvUtils.get("TEST_EMAIL");
        this.firstName = EnvUtils.get("TEST_FIRST_NAME");
        this.lastName = EnvUtils.get("TEST_LAST_NAME");
        this.password = EnvUtils.get("TEST_PASSWORD");
    }

    @Test
    public void getRegister() {
        UserEntity expectedResult = new UserEntity(email, firstName, lastName,password, password);

        //reg_date above and below is different because the constructor of Account class has a reg_date parameter
        //depend on the current time, so we can't compare the whole object
        registerView.setRegister(email, firstName, lastName, password, password);
        UserEntity actualUserDTO = registerView.getRegister();

        boolean actualResult = expectedResult.getEmail().equals(actualUserDTO.getEmail()) && expectedResult.getPassword().equals(
            actualUserDTO.getPassword());
        assertTrue(actualResult);
    }
}