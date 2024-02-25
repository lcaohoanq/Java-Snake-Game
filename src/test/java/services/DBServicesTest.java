package services;

import models.data.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DBServicesTest {

    @Test
    public void selectUsernameAndScore() {
        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("hoang 117");
        expectedResult.add("fpt 9");
        expectedResult.add("admin 0");

        List<String> actualResult = DBServices.selectUsernameAndScore();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void selectUsernameAndPasswordByUsername() {
        Account expectedResult = new Account("fpt", "$31$16$zQ908D_srETQGbje1udFTQBLzkxYgq7iQv7aHg7F7F0");
        Account actualResult = DBServices.selectUsernameAndPasswordByUsername("fpt");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void selectUsernameAndScoreByUsername() {
        Account expectedResult = new Account("hoang", 117);
        Account actualResult = DBServices.selectUsernameAndScoreByUsername("hoang");
        assertEquals(expectedResult, actualResult);
    }
}