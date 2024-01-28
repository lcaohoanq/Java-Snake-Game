package controllers;

import constants.Messages;
import constants.Regex;
import errors.DataException;
import models.Account;
import models.RegisterData;
import services.DBServices;
import utils.PasswordHandler;
import views.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterController implements ActionListener, MouseListener, RegisterData {

    private String username;
    private String password;
    private String confirmPassword;
    private PasswordHandler passwordHandler;
    private RegisterView registerView;

    public RegisterController(RegisterView registerView) {
        super();
        this.registerView = registerView;
        this.passwordHandler = new PasswordHandler();
    }

    // Su dung ArrayList<Account> de luu tru account
    @Override
    public void actionPerformed(ActionEvent e) {
        username = registerView.getRegister().username();
        password = registerView.getRegister().password();
        confirmPassword = registerView.getRegister().confirmPassword();
        if (!username.matches(Regex.USERNAME)) {
            Messages.IS_WRONG_FORMAT_USERNAME();
            return;
        } else if (!password.matches(Regex.PASSWORD)) {
            Messages.IS_WRONG_FORMAT_PASSWORD();
            return;
        } else if (!confirmPassword.matches(Regex.PASSWORD)) {
            Messages.IS_WRONG_FORMAT_PASSWORD();
            return;
        }

        if ((isEmpty(username, password, confirmPassword))) {
            handleEmpty();
        } else if (!isMatching(password, confirmPassword)) {
            handleWrong();
        } else {
            if (isDuplicateUsername(username)) {
                handleDuplicateUsername();
            } else {
                handleSuccess();
            }
        }

    }

    @Override
    public void handleEmpty() {
        Messages.IS_EMPTY_FIELD();
        System.out.println("Register failed: " + "username:" + username + " password:" + password);
    }

    @Override
    public void handleWrong() {
        Messages.IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD();
        System.out.println("Register failed: " + "username:" + username + " password:" + password);
    }

    @Override
    public void handleDuplicateUsername() {
        Messages.IS_EXISTED_USERNAME();
        System.out.println("Register failed: " + "username:" + username);
    }

    @Override
    public void handleSuccess() {
        password = passwordHandler.hash(password); // replace password with the hashed
        System.out.println("Register success: " + "username:" + username + " password:" + password);
        DBServices.insert(username, password, 0);
        Messages.IS_REGISTER_SUCCESS();
    }

    @Override
    public boolean isEmpty(String username, String password) {
        return false;
    }

    @Override
    public boolean isMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }


    @Override
    public boolean isEmpty(String username, String password, String confirmPassword) {
        return username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty();
    }

    @Override
    public boolean isDuplicateUsername(String username) {
        Account db;
        try {
            db = DBServices.selectUsernameAndPasswordByUsername(username);
            if (db == null || !db.username().equals(username)) {
                throw new DataException("Error, is duplicated username");
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == registerView.jTextField_Right_Middle_Username) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverUsername(true, "light");
            } else {
                registerView.setHoverUsername(true, "dark");
            }
        }
        if (e.getSource() == registerView.jPasswordField_Right_Middle_Password) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverUserPassword(true, "light");
            } else {
                registerView.setHoverUserPassword(true, "dark");
            }
        }
        if (e.getSource() == registerView.jPasswordField_Right_Middle_Confirm_Password) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverConfirmPassword(true, "light");
            } else {
                registerView.setHoverConfirmPassword(true, "dark");
            }
        }
        if (e.getSource() == registerView.jButton_Right_Bottom_Submit) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverUserButton(true, "light");
            } else {
                registerView.setHoverUserButton(true, "dark");
            }
        }
        if (e.getSource() == registerView.jButton_Right_Bottom_Others) {
            registerView.setHoverOther(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == registerView.jTextField_Right_Middle_Username) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverUsername(false, "light");
            } else {
                registerView.setHoverUsername(false, "dark");
            }
        }
        if (e.getSource() == registerView.jPasswordField_Right_Middle_Password) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverUserPassword(false, "light");
            } else {
                registerView.setHoverUserPassword(false, "dark");
            }
        }
        if (e.getSource() == registerView.jPasswordField_Right_Middle_Confirm_Password) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverConfirmPassword(false, "light");
            } else {
                registerView.setHoverConfirmPassword(false, "dark");
            }
        }
        if (e.getSource() == registerView.jButton_Right_Bottom_Submit) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverUserButton(false, "light");
            } else {
                registerView.setHoverUserButton(false, "dark");
            }
        }
        if (e.getSource() == registerView.jButton_Right_Bottom_Others) {
            registerView.setHoverOther(false);
        }
    }
}
