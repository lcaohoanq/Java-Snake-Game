package services;

import constants.Database;
import constants.Errors;
import errors.DBException;
import models.data.Account;
import utils.EnvUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBServices {

    public static Connection getConnection() throws SQLException {
        try {
            String dbUrl = EnvUtils.get(Database.DB_URL);
            String dbUsername = EnvUtils.get(Database.DB_USERNAME);
            String dbPassword = EnvUtils.get(Database.DB_PASSWORD);
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            throw new DBException(Errors.ERROR_READ_FILE_ENV + e.getMessage());
        }
    }

    // !my query
    public static List<String> selectUsernameAndScore() {
        List<String> resultList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            CallableStatement cStatement = connection.prepareCall("{CALL proc_select_username_score()}");
            ResultSet resultSet = cStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(resultSet.getString(Database.COLUMN_USERNAME) + " " + resultSet.getInt(Database.COLUMN_SCORE));
            }
        } catch (SQLException e) {
            System.out.println(Errors.ERROR_SELECT_USERNAME_AND_PASSWORD + e.getMessage());
        }
        return resultList;
    }

    public static Account selectUsernameAndPasswordByUsername(String username) {
        try {
            Connection connection = getConnection();
            CallableStatement cStatement = connection.prepareCall("{CALL proc_select_username_password(?)}");
            cStatement.setString(1, username);
            ResultSet resultSet = cStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(resultSet.getString(Database.COLUMN_USERNAME), resultSet.getString(Database.COLUMN_PASSWORD));
            }
        } catch (SQLException e) {
            System.out.println(Errors.ERROR_SELECT_USERNAME_AND_PASSWORD + e.getMessage());
        }
        return null;
    }

    public static Account selectUsernameAndScoreByUsername(String username) {
        try {
            Connection connection = getConnection();

            CallableStatement cStatement = connection.prepareCall("{CALL proc_select_username_score_by_username(?)}");
            cStatement.setString(1, username);

            ResultSet resultSet = cStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(resultSet.getString(Database.COLUMN_USERNAME), resultSet.getInt(Database.COLUMN_SCORE));
            }
        } catch (SQLException e) {
            System.out.println(Errors.ERROR_SELECT_USERNAME_AND_SCORE + e.getMessage());
        }
        return null;
    }

    public static boolean insert(String username, String password, int score, String regDate) {
        try {
            Connection connection = getConnection();

            CallableStatement cStatement = connection.prepareCall("{CALL proc_insert_user(?,?,?,?)}");

            cStatement.setString(1, username);
            cStatement.setString(2, password);
            cStatement.setInt(3, score);
            cStatement.setString(4, regDate);

            int rowsAffected = cStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(Database.INSERT_SUCCESS + " for " + username);
                return true;
            }
            throw new DBException(Database.INSERT_FAILED + " for " + username);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    //set safe updates
    public static boolean setSafeUpdate() {
        try {
            Connection connection = getConnection();

            CallableStatement cStatement = connection.prepareCall("{CALL proc_set_safe_update()}");

            int rowsAffected = cStatement.executeUpdate();
            if (rowsAffected == 0) {
                return true;
            }
            throw new DBException(Database.ERROR_EXECUTE_SQL_SAFE_UPDATES);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean updateUsernameScore(String username, String score) {
        try {
            Connection connection = getConnection();

            CallableStatement cStatement = connection.prepareCall("{CALL proc_update_username_score(?,?)}");
            cStatement.setString(1, username);
            cStatement.setString(2, score);

            int rowsAffected = cStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(Database.UPDATE_SUCCESS + " for " + username);
                return true;
            }
            throw new DBException(Database.UPDATE_FAILED + " for " + username);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
