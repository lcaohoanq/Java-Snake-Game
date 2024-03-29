package services;

import constants.ErrorMessages;
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
                resultList.add(resultSet.getString("username") + " " + resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println(ErrorMessages.ERROR_SELECT_USERNAME_AND_PASSWORD + e.getMessage());
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
                return new Account(resultSet.getString("username"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(ErrorMessages.ERROR_SELECT_USERNAME_AND_PASSWORD + e.getMessage());
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
                return new Account(resultSet.getString("username"), resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println(ErrorMessages.ERROR_SELECT_USERNAME_AND_SCORE + e.getMessage());
        }
        return null;
    }

    public boolean insert(String username, String password) {
        try {
            Connection connection = dbConnection.getConnection();

            CallableStatement cStatement = connection.prepareCall("{CALL proc_insert_user(?,?)}");

            cStatement.setString(1, username);
            cStatement.setString(2, password);

            int rowsAffected = cStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert success for " + username);
                return true;
            }
            throw new DBException("Insert failed for " + username);
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
            throw new DBException("Error execute sql safe updates");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateUsernameScore(String username, String score) {
        try {
            Connection connection = dbConnection.getConnection();

            CallableStatement cStatement = connection.prepareCall("{CALL proc_update_score_by_username(?,?)}");
            cStatement.setString(1, username);
            cStatement.setString(2, score);

            int rowsAffected = cStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update success for " + username);
                return true;
            }
            throw new DBException("Update failed for " + username);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
