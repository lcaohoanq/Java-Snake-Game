package controllers;

import lombok.extern.slf4j.Slf4j;
import views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import views.UIPrompts;

@Slf4j
public final class LoginController implements ActionListener, MouseListener {

    public static String email = "";
    private final LoginView loginView;
    public String password = "";

    public LoginController(LoginView loginView) {
        super();
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        email = loginView.getLogin().getEmail();
        password = loginView.getLogin().getPassword();

        if (loginView.isAdmin()) {
            loginView.handleSuccess();
            log.info("Admin login successful");
            return;
        }
        //prevent empty field when click submit button, but not when click on the menu
        if (loginView.isEmpty() && e.getSource() instanceof JButton) {
            UIPrompts.IS_EMPTY_USERNAME_OR_PASSWORD();
            log.error("Empty field when login, please try again");
        } else {
            if (!loginView.isMatching()) {
                UIPrompts.IS_WRONG_USERNAME_OR_PASSWORD();
                log.error("Password do not match, please try again");
            } else {
                loginView.handleSuccess();
                log.info("User {} login successful", email);
            }
        }

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
        if (e.getSource() == loginView.jTextField_Right_Middle_Email) {
            if (!loginView.getStatusToggle()) {
                loginView.setHover("email",true, "light");
            } else {
                loginView.setHover("email",true, "dark");
            }
        }
        if (e.getSource() == loginView.jPasswordField_Right_Middle_Password) {
            if (!loginView.getStatusToggle()) {
                loginView.setHover("password",true, "light");
            } else {
                loginView.setHover("password",true, "dark");
            }
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Submit) {
            if (!loginView.getStatusToggle()) {
                loginView.setHover("button",true, "light");
            } else {
                loginView.setHover("button",true, "dark");
            }
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Others) {
            loginView.setHover("others",true);
        }
        if(e.getSource() == loginView.jButton_Right_Bottom_Forgot_Password){
            loginView.setHoverForgotPassword(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginView.jTextField_Right_Middle_Email) {
            if (!loginView.getStatusToggle()) {
                loginView.setHover("email",false, "light");
            } else {
                loginView.setHover("email",false, "dark");
            }
        }
        if (e.getSource() == loginView.jPasswordField_Right_Middle_Password) {
            if (!loginView.getStatusToggle()) {
                loginView.setHover("password",false, "light");
            } else {
                loginView.setHover("password",false, "dark");
            }
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Submit) {
            if (!loginView.getStatusToggle()) {
                loginView.setHover("button",false, "light");
            } else {
                loginView.setHover("button",false, "dark");
            }
        }
        if (e.getSource() == loginView.jButton_Right_Bottom_Others) {
            loginView.setHoverOther(false);
        }
        if(e.getSource() == loginView.jButton_Right_Bottom_Forgot_Password){
            loginView.setHoverForgotPassword(false);
        }
    }
}
