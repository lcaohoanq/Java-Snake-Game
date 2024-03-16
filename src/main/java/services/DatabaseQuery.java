package services;

import constants.Database;
import constants.Errors;
import errors.DBException;
import models.data.Account;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQuery {
    private static DatabaseQuery instance;
    private DatabaseConnection dbConnection = new DatabaseConnection();

    private DatabaseQuery() {
    }

    public static DatabaseQuery getInstance() {
        if (instance == null) {
            instance = new DatabaseQuery();
        }
        return instance;
    }

    public List<String> selectUsernameAndScore() {
        List<String> resultList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             CallableStatement cStatement = connection.prepareCall("{CALL proc_select_username_score()}");
             ResultSet resultSet = cStatement.executeQuery()) {

            while (resultSet.next()) {
                resultList.add(resultSet.getString(Database.COLUMN_USERNAME) + " " + resultSet.getInt(Database.COLUMN_SCORE));
            }
        } catch (SQLException e) {
            System.out.println(Errors.ERROR_SELECT_USERNAME_AND_PASSWORD + e.getMessage());
        }
        return resultList;
    }

    public Account selectUsernameAndPasswordByUsername(String username) {
        try {
            Connection connection = dbConnection.getConnection();
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

    public Account selectUsernameAndScoreByUsername(String username) {
        try {
            Connection connection = dbConnection.getConnection();

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

    public boolean insert(String username, String password, int score, String regDate) {
        try {
            Connection connection = dbConnection.getConnection();

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
    public boolean setSafeUpdate() {
        try {
            Connection connection = dbConnection.getConnection();

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

    public boolean updateUsernameScore(String username, String score) {
        try {
            Connection connection = dbConnection.getConnection();

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
