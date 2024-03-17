package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import constants.ErrorMessages;
import errors.DBException;
import utils.EnvUtils;

public class DatabaseConnection {

    public Connection getConnection() throws SQLException {
        try {
            String dbUrl = EnvUtils.get("DB_URL");
            String dbUsername = EnvUtils.get("DB_USERNAME");
            String dbPassword = EnvUtils.get("DB_PASSWORD");
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            throw new DBException(ErrorMessages.ERROR_READ_FILE_ENV + e.getMessage());
        }
    }

}
