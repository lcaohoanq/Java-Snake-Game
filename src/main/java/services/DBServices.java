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

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username, score FROM USERS");
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

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT username, password FROM users WHERE username = '" + username + "'");
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

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT username, score FROM users WHERE username = '" + username + "'");
            while (resultSet.next()) {
                return new Account(resultSet.getString(Database.COLUMN_USERNAME), resultSet.getInt(Database.COLUMN_SCORE));
            }
        } catch (SQLException e) {
            System.out.println(Errors.ERROR_SELECT_USERNAME_AND_SCORE + e.getMessage());
        }
        return null;
    }

    public static void insert(String username, String password, int score) {
        try {
            Connection connection = getConnection();

            Statement statement = connection.createStatement();
            String sql = "INSERT INTO `users_schema`.`users` (`username`,`password`,`score`) VALUES ('" + username
                    + "', '" + password + "', " + score + ")";
            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println(Database.INSERT_SUCCESS + " for " + username);
            } else {
                throw new DBException(Database.INSERT_FAILED + " for " + username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(String username, String password, int score, String regDate) {
        try {
            Connection connection = getConnection();

            String sql = "INSERT INTO `users_schema`.`users` " + "(`username`,`password`,`score`, `reg_date`) " + "VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, score);
            statement.setString(4, regDate);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(Database.INSERT_SUCCESS + " for " + username);
            } else {
                throw new DBException(Database.INSERT_FAILED + " for " + username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //set safe updates
    public static void executeOther() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate("SET SQL_SAFE_UPDATES = 0");
            if (rowsAffected != 0) {
                throw new DBException(Database.ERROR_EXECUTE_SQL_SAFE_UPDATES);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateUsernameScore(String username, String score) {
        try {
            Connection connection = getConnection();

            Statement statement = connection.createStatement();
            String sql = "UPDATE `users_schema`.`users` SET `score` = '" + score + "' WHERE (`username` = '" + username + "')";
            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println(Database.UPDATE_SUCCESS + " for " + username);
            } else {
                throw new DBException(Database.UPDATE_FAILED + " for " + username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
