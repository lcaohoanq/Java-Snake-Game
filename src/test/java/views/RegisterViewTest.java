package views;

import models.data.Account;
import org.junit.Before;
import org.junit.Test;
import utils.EnvUtils;

import static org.junit.Assert.*;

public class RegisterViewTest {

    String username;
    String password;
    RegisterView registerView;

    @Before
    public void setUp() throws Exception {
        registerView = new RegisterView();
        this.username = EnvUtils.get("TEST_USERNAME");
        this.password = EnvUtils.get("TEST_PASSWORD");
    }

    @Test
    public void getRegister() {
        Account expectedResult = new Account(username, password, password);

        //reg_date above and below is different because the constructor of Account class has a reg_date parameter
        //depend on the current time, so we can't compare the whole object
        registerView.setRegister(username, password, password);
        Account actualAccount = registerView.getRegister();

        boolean actualResult = expectedResult.username().equals(actualAccount.username()) && expectedResult.password().equals(actualAccount.password());
        assertTrue(actualResult);
    }
}