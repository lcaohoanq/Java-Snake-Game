package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import constants.ErrorMessages;
import errors.DBException;
import utils.EnvUtils;

public class DatabaseService {

    public Connection getConnection() throws SQLException {
        try {
            String dbUrl = EnvUtils.get("DB_URL_DOCKER");
            String dbUsername = EnvUtils.get("MYSQL_USER");
            String dbPassword = EnvUtils.get("MYSQL_PASSWORD");
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            throw new DBException(ErrorMessages.ERROR_READ_FILE_ENV + e.getMessage());
        }
    }

}
