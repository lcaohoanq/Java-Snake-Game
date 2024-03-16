package controllers;

import views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class LoginController implements ActionListener, MouseListener {

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
            if (!loginView.isMatching()) {
                loginView.handleWrong();
            } else {
                loginView.handleSuccess();
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
