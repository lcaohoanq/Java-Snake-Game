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

public class UserDAO {
    private static UserDAO instance;
    private DatabaseConnection dbConnection = new DatabaseConnection();

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public List<String> selectFirstNameAndScore() {
        List<String> resultList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             CallableStatement cStatement = connection.prepareCall("{CALL proc_select_first_name_score()}");
             ResultSet resultSet = cStatement.executeQuery()) {

            while (resultSet.next()) {
                resultList.add(resultSet.getString("first_name") + " " + resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println(ErrorMessages.ERROR_SELECT_FIRST_NAME_AND_SCORE + e.getMessage());
        }
        return resultList;
    }

    public Account selectEmailAndPasswordByEmail(String email) {
        try {
            Connection connection = dbConnection.getConnection();
            CallableStatement cStatement = connection.prepareCall("{CALL proc_select_email_password(?)}");
            cStatement.setString(1, email);
            ResultSet resultSet = cStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(resultSet.getString("email"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(ErrorMessages.ERROR_SELECT_EMAIL_AND_PASSWORD + e.getMessage());
        }
        return null;
    }

    public Account selectEmailAndScoreByEmail(String email) {
        try {
            Connection connection = dbConnection.getConnection();

            CallableStatement cStatement = connection.prepareCall(
                "{CALL proc_select_email_score_by_email(?)}");
            cStatement.setString(1, email);

            ResultSet resultSet = cStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(resultSet.getString("email"), resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println(ErrorMessages.ERROR_SELECT_USERNAME_AND_SCORE + e.getMessage());
        }
        return null;
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

    public boolean updateEmailScore(String email, String score) {
        try {
            Connection connection = dbConnection.getConnection();

            CallableStatement cStatement = connection.prepareCall("{CALL proc_update_score_by_email(?,?)}");
            cStatement.setString(1, email);
            cStatement.setString(2, score);

            int rowsAffected = cStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update success for " + email);
                return true;
            }
            throw new DBException("Update failed for " + email);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insertMail(String email, String phone_number, String first_name, String last_name, String password) {
        try {
            Connection connection = dbConnection.getConnection();

            CallableStatement cStatement = connection.prepareCall(
                "{CALL proc_insert_user_new(?,?,?,?,?)}");

            cStatement.setString(1, email);
            cStatement.setString(2, phone_number);
            cStatement.setString(3, first_name);
            cStatement.setString(4, last_name);
            cStatement.setString(5, password);

            int rowsAffected = cStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert success for " + email);
                return true;
            }
            throw new DBException("Insert failed for " + email);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        UserDAO.getInstance().insertMail("hoangclw@gmail.com", "", "Hoang", "Luu", "123456");
    }
}
