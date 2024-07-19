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
            String url = EnvUtils.get("DB_URL_DOCKER");
            String user = EnvUtils.get("MYSQL_USER");
            String pass = EnvUtils.get("MYSQL_PASSWORD");

            if(url == null || user == null || pass == null) {
                throw new DBException(ErrorMessages.ERROR_READ_FILE_ENV);
            }

            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
