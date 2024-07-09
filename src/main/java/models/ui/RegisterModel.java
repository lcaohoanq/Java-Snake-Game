package models.ui;

import constants.Regex;
import errors.DataException;
import modules.user.UserDTO;
import modules.user.UserDAO;
import utils.PasswordHandler;

public record RegisterModel(String username, String password, String confirmPassword) {
    public RegisterModel() {
        this("", "", "");
    }

    public boolean isNameFormat(String name) {
        return name.matches(Regex.NAME);
    }

    public boolean isEmailFormat(String email) {
        return email.matches(Regex.EMAIL);
    }

    public boolean isPhoneNumberFormat(String phoneNumber) {
        return phoneNumber.matches(Regex.PHONE_NUMBER);
    }

    public boolean isPasswordFormat(String password) {
        return password.matches(Regex.PASSWORD);
    }

    public boolean isEmpty(String email_phoneNumber , String firstName, String lastName, String password, String confirmPassword) {
        return email_phoneNumber.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty();
    }

    public boolean isMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public boolean isDuplicateEmail(String username) {
        UserDTO db;
        UserDAO executeQuery = UserDAO.getInstance();
        try {
            db = executeQuery.selectEmailAndPasswordByEmail(username);
            if (db == null) {
                return false;
            } else {
                throw new DataException("Error, is duplicated username");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void insertMail(String email, String firstName, String lastName, String password) {
        UserDAO executeQuery = UserDAO.getInstance();
        password = new PasswordHandler().hash(password); // replace password with the hashed
        executeQuery.insertMail(email,null, firstName,lastName, password);
    }
}
