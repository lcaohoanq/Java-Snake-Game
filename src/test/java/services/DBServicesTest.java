package services;

import models.data.Account;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBServicesTest {

    @Test
    public void selectUsernameAndScoreByUsername() {
        Account expectedResult = new Account("hoang", 18);
        Account actualResult = DBServices.selectUsernameAndScoreByUsername("hoang");
        assertEquals(expectedResult, actualResult);
    }
}