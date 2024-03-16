package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import constants.Database;
import constants.Errors;
import errors.DBException;
import io.github.cdimascio.dotenv.Dotenv;
import utils.EnvUtils;

public class DatabaseConnection {

    public Connection getConnection() throws SQLException {
        try {
            String dbUrl = EnvUtils.get(Database.DB_URL);
            String dbUsername = EnvUtils.get(Database.DB_USERNAME);
            String dbPassword = EnvUtils.get(Database.DB_PASSWORD);
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            throw new DBException(Errors.ERROR_READ_FILE_ENV + e.getMessage());
        }
    }

}
