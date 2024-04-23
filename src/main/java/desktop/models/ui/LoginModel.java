package desktop.models.ui;

import desktop.errors.DataException;
import desktop.models.data.Account;
import desktop.services.DatabaseQuery;
import desktop.utils.PasswordHandler;

public record LoginModel(String username, String password) {
    public LoginModel() {
        this("", "");
    }

    public boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    public boolean isMatching(String username, String password) {
        Account db;
        DatabaseQuery executeQuery = DatabaseQuery.getInstance();
        try {
            db = executeQuery.selectUsernameAndPasswordByUsername(username);
            if (db != null) {
                return new PasswordHandler().authenticate(password.toCharArray(), db.password());
            } else {
                throw new DataException("Error authenticate, password do not match");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isEmpty(String username, String password) {
        return username.isEmpty() || password.isEmpty();
    }
}
