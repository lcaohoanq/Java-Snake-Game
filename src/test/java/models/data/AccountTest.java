package models.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    @Test
    public void username() {
        String expectedResult = "hoang";
        Account account = new Account(expectedResult, "123");
        String actualResult = account.username();
        assertEquals(expectedResult, actualResult); //passed
    }

    @Test
    public void password() {
        String expectedResult = "Luucaohoang1604^^";
        Account account = new Account("hoang", expectedResult);
        String actualResult = account.password();
        assertEquals(expectedResult, actualResult);
    }
}