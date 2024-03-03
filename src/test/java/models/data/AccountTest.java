package models.data;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    String username;
    String password;

    @Before
    public void setUp() throws Exception {
        Dotenv dotenv = Dotenv.load();
        this.username = dotenv.get("TEST_USERNAME");
        this.password = dotenv.get("TEST_PASSWORD");
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