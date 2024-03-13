package controllers;

import constants.Messages;
import errors.DataException;
import models.data.Account;
import models.data.LoginData;
import services.DBServices;
import utils.PasswordHandler;
import views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class LoginController implements ActionListener, MouseListener, LoginData {

    public static String username = "";
    private final LoginView loginView;
    public String password = "";

    public LoginController(LoginView loginView) {
        super();
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        username = loginView.getLogin().username();
        password = loginView.getLogin().password();

        System.out.println("Data: " + username + " " + password);
        if (loginView.isAdmin()) {
            loginView.handleSuccess();
            return;
        }
        //prevent empty field when click submit button, but not when click on the menu
        if (loginView.isEmpty() && e.getSource() instanceof JButton) {
            loginView.handleEmpty();
        } else {
            if (!isMatching(username, password)) {
                loginView.handleWrong();
            } else {
                loginView.handleSuccess();
            }
        }

    }

    private boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    @Override
    public void handleEmpty() {
        Messages.IS_EMPTY_USERNAME_OR_PASSWORD();
        System.out.println("Login failed: " + "username:" + username + " password:" + password);
    }

    @Override
    public void handleWrong() {
        Messages.IS_WRONG_USERNAME_OR_PASSWORD();
        System.out.println("Login failed: " + "username:" + username + " password:" + password);
    }

    @Override
    public void handleSuccess() {
        Messages.IS_LOGIN_SUCCESS();
        // Switch to the play button card using static methods
        System.out.println("Login success: " + "username:" + username + " password:" + password);
        CardLayout cardLayout = LoginView.cardLayout;
        cardLayout.next(LoginView.jPanel_Right_Bottom_Button);
        //hidden the username and password input field
        loginView.setStatusInputData(false);
    }

    @Override
    public boolean isEmpty(String username, String password) {
        return username.isEmpty() || password.isEmpty();
    }

    @Override
    public boolean isMatching(String username, String password) {
        Account db;
        try {
            db = DBServices.selectUsernameAndPasswordByUsername(username);
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
        if (e.getSource() == loginView.jTextField_Right_Middle_Username) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverUsername(true, "light");
            } else {
                loginView.setHoverUsername(true, "dark");
            }
        }
        if (e.getSource() == loginView.jPasswordField_Right_Middle_Password) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverPassword(true, "light");
            } else {
                loginView.setHoverPassword(true, "dark");
            }
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Submit) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverButton(true, "light");
            } else {
                loginView.setHoverButton(true, "dark");
            }
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Others) {
            loginView.setHoverOther(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginView.jTextField_Right_Middle_Username) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverUsername(false, "light");
            } else {
                loginView.setHoverUsername(false, "dark");
            }
        }
        if (e.getSource() == loginView.jPasswordField_Right_Middle_Password) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverPassword(false, "light");
            } else {
                loginView.setHoverPassword(false, "dark");
            }
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Submit) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverButton(false, "light");
            } else {
                loginView.setHoverButton(false, "dark");
            }
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Others) {
            loginView.setHoverOther(false);
        }
    }
}
