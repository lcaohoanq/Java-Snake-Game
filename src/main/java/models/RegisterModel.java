package models;

import constants.Regex;
import lombok.extern.slf4j.Slf4j;
import modules.user.UserDAO;
import modules.user.UserEntity;
import utils.PBKDF2;

@Slf4j
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
        UserEntity db;
        UserDAO executeQuery = UserDAO.getInstance();
        try {
            db = executeQuery.selectEmailAndPasswordByEmail(username);
            return db == null ? false : true;
        } catch (Exception e) {
            log.error("Error while checking if email exists", e);
        }
        return true;
    }

    public void insertMail(String email, String firstName, String lastName, String password) {
        UserDAO executeQuery = UserDAO.getInstance();
        executeQuery.insertMail(email,null, firstName,lastName, new PBKDF2().hash(password.toCharArray()));
    }
}
