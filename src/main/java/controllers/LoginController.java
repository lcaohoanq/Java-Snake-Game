package controllers;

import lombok.extern.slf4j.Slf4j;
import views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Slf4j
public final class LoginController implements ActionListener, MouseListener {

    public static String email = "";
    private final LoginView loginView;
    public String password = "";
    private static final Logger logger = LoggerFactory.getLogger(LogsUtils.class);

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
            loginView.handleEmpty();
            log.error("Empty field when login, please try again");
        } else {
            if (!loginView.isMatching()) {
                loginView.handleNotMatchingPasswordAndConfirmPassword();
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
                loginView.setHoverEmail(true, "light");
            } else {
                loginView.setHoverEmail(true, "dark");
            }
        }
        if (e.getSource() == loginView.jTextField_Right_Middle_FirstName) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverFirstName(true, "light");
            } else {
                loginView.setHoverFirstName(true, "dark");
            }
        }
        if (e.getSource() == loginView.jTextField_Right_Middle_LastName) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverLastName(true, "light");
            } else {
                loginView.setHoverLastName(true, "dark");
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
        if(e.getSource() == loginView.jButton_Right_Bottom_Forgot_Password){
            loginView.setHoverForgotPassword(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginView.jTextField_Right_Middle_Email) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverEmail(false, "light");
            } else {
                loginView.setHoverEmail(false, "dark");
            }
        }
        if (e.getSource() == loginView.jTextField_Right_Middle_FirstName) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverFirstName(false, "light");
            } else {
                loginView.setHoverFirstName(false, "dark");
            }
        }
        if (e.getSource() == loginView.jTextField_Right_Middle_LastName) {
            if (!loginView.getStatusToggle()) {
                loginView.setHoverLastName(false, "light");
            } else {
                loginView.setHoverLastName(false, "dark");
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
        if(e.getSource() == loginView.jButton_Right_Bottom_Forgot_Password){
            loginView.setHoverForgotPassword(false);
        }
    }
}
