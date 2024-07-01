package utils;

import constants.Regex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordHandlerTest {

    String password;
    PasswordHandler pwdHandler;

    @Before
    public void setUp() throws Exception {
        this.password = EnvUtils.get("TEST_PASSWORD");
        this.pwdHandler = new PasswordHandler();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void hash() {
        String actualResult = pwdHandler.hash(password.toCharArray());
        //test if the hash method return the hashed password in right format
        assert (actualResult.matches(Regex.HASHED_PASSWORD));
    }

    @Test
    public void authenticate() {
        boolean expectedResult = true;
        String hashedPassword = pwdHandler.hash(password.toCharArray());
        boolean actualResult = pwdHandler.authenticate(password.toCharArray(), hashedPassword);
        //test if the authenticate method return true when the password is correct
        assertEquals(expectedResult, actualResult);
    }
}