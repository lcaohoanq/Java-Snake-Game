package services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseConnectionTest {

    private DatabaseConnection databaseConnection;

    @Before
    public void setUp() throws Exception {
        databaseConnection = new DatabaseConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getConnection() {
        assertNotNull(databaseConnection);
    }
}