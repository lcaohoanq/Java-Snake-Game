package views;

import models.data.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterViewTest {

    RegisterView registerView;

    @Before
    public void setUp() throws Exception {
        registerView = new RegisterView();
    }

    @Test
    public void getRegister() {
        registerView.setRegister("hoang", "Luucaohoang1604^^", "Luucaohoang1604^^");
        Account expectedResult = new Account("hoang", "Luucaohoang1604^^", "Luucaohoang1604^^");
        Account actualResult = registerView.getRegister();
        assertEquals(expectedResult, actualResult);
    }
}