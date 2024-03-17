package services;

import models.data.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.EnvUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DBServicesTest {
    String passwordHashed;
    private DatabaseQuery dbQuery = DatabaseQuery.getInstance();

    @Before
    public void setUp() throws Exception {
        this.passwordHashed = EnvUtils.get("TEST_PASSWORD_DB");
    }

    @Test
    public void selectUsernameAndScore() {
        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("hoang 42");
        expectedResult.add("toilaluu 0");
        expectedResult.add("duong 0");
        expectedResult.add("huy 0");
        expectedResult.add("bao 0");
        expectedResult.add("minhnhu 0");
        expectedResult.add("test 0");

        List<String> actualResult = dbQuery.selectUsernameAndScore();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void selectUsernameAndPasswordByUsername() {
        Account expectedResult = new Account("minhnhu", passwordHashed);
        Account actualResult = dbQuery.selectUsernameAndPasswordByUsername("minhnhu");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void selectUsernameAndScoreByUsername() {
        Account expectedResult = new Account("minhnhu", 0);
        Account actualResult = dbQuery.selectUsernameAndScoreByUsername("minhnhu");
        assertEquals(expectedResult, actualResult);
    }
}