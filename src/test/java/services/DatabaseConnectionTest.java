package services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseConnectionTest {

    private DatabaseService databaseService;

    @Before
    public void setUp() throws Exception {
        databaseService = new DatabaseService();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getConnection() {
        assertNotNull(databaseService);
    }
}