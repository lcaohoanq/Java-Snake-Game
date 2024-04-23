package desktop.models.ui;

import desktop.constants.Regex;
import desktop.errors.DataException;
import desktop.models.data.Account;
import desktop.services.DatabaseQuery;
import desktop.utils.PasswordHandler;

public record RegisterModel(String username, String password, String confirmPassword) {
    public RegisterModel() {
        this("", "", "");
    }

    public boolean isUsernameFormat(String username) {
        return username.matches(Regex.USERNAME);
    }

    public boolean isPasswordFormat(String password) {
        return password.matches(Regex.PASSWORD);
    }

    public boolean isConfirmPasswordFormat(String confirmPassword) {
        return confirmPassword.matches(Regex.PASSWORD);
    }

    public boolean isEmpty(String username, String password, String confirmPassword) {
        return username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty();
    }

    public boolean isMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public boolean isDuplicateUsername(String username) {
        Account db;
        DatabaseQuery executeQuery = DatabaseQuery.getInstance();
        try {
            db = executeQuery.selectUsernameAndPasswordByUsername(username);
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

    public void insert(String username, String password) {
        DatabaseQuery executeQuery = DatabaseQuery.getInstance();
        password = new PasswordHandler().hash(password); // replace password with the hashed
        executeQuery.insert(username, password);
    }
}
