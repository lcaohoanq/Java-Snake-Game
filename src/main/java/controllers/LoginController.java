package controllers;

import constants.Messages;
import models.Account;
import models.LoginData;
import services.DBServices;
import utils.PasswordHandler;
import views.LoginView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginController implements ActionListener, MouseListener, LoginData {

    public static String username = "";
    public static String password = "";
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        super();
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        username = loginView.getLogin().getUsername();
        password = loginView.getLogin().getPassword();

        System.out.println("Data: " + username + " " + password);
        //prevent empty field when click submit button, but not when click on the menu
        if (isEmpty(username, password) && e.getSource() == loginView.jButton_Right_Bottom_Submit) {
            handleEmpty();
        } else {
            if (!isMatching(username, password)) {
                handleWrong();
            } else {
                handleSuccess();
            }
        }

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
                return new PasswordHandler().authenticate(password.toCharArray(), db.getPassword());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Error authenticate: " + e.getMessage());
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
            loginView.setHoverUsername(true);
        }
        if (e.getSource() == loginView.jPasswordField_Right_Middle_Password) {
            loginView.setHoverPassword(true);
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Submit) {
            loginView.setHoverButton(true);
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Others) {
            loginView.setHoverOther(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginView.jTextField_Right_Middle_Username) {
            loginView.setHoverUsername(false);
        }
        if (e.getSource() == loginView.jPasswordField_Right_Middle_Password) {
            loginView.setHoverPassword(false);
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Submit) {
            loginView.setHoverButton(false);
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Others) {
            loginView.setHoverOther(false);
        }
    }
}
