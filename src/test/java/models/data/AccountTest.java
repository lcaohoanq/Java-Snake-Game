package models.data;

import org.junit.Before;
import org.junit.Test;
import utils.EnvUtils;

import static org.junit.Assert.*;

public class AccountTest {

    String username;
    String password;

    @Before
    public void setUp() throws Exception {
        this.username = EnvUtils.get("TEST_USERNAME");
        this.password = EnvUtils.get("TEST_PASSWORD");
    }

    @Test
    public void username() {
        String expectedResult = username;
        Account account = new Account(expectedResult, "");
        String actualResult = account.username();
        assertEquals(expectedResult, actualResult); //passed
    }

    @Test
    public void password() {
        String expectedResult = password;
        Account account = new Account(username, expectedResult);
        String actualResult = account.password();
        assertEquals(expectedResult, actualResult);
    }
}