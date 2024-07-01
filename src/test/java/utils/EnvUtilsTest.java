package utils;

import static org.junit.Assert.*;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Before;
import org.junit.Test;

public class EnvUtilsTest {
    private Dotenv dotenv;
    private String DB_URL = "jdbc:mysql://localhost:3306/snake_game_app";

    @Before
    public void setUp() throws Exception {
        dotenv = Dotenv.configure().load();
    }

    @Test
    public void get() {
        assertEquals(DB_URL, dotenv.get("DB_URL"));
    }
}