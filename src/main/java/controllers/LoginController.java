package controllers;

import lombok.extern.slf4j.Slf4j;
import styles.UIHovers;
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
    private final UIHovers<LoginView> uiHovers;

    public LoginController(LoginView loginView) {
        super();
        this.loginView = loginView;
        this.uiHovers = new UIHovers<>(loginView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        email = loginView.getDataWhenLogin().getEmail();
        password = loginView.getDataWhenLogin().getPassword();

        if (loginView.isAdmin()) {
            loginView.handleSuccess();
            log.info("Admin login successful");
            return;
        }
        //prevent empty field when click submit button, but not when click on the menu
        if (loginView.isEmpty() && e.getSource() instanceof JButton) {
            UIPrompts.IS_EMPTY_FIELD();
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
        if (e.getSource() == loginView.getJTextField_Right_Middle_Email()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(true, "light");
            } else {
                uiHovers.setHoverEmail(true, "dark");
            }
        }
        if (e.getSource() == loginView.getJPasswordField_Right_Middle_Password()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverPassword(true, "light");
            } else {
                uiHovers.setHoverPassword(true, "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Submit()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverButton(true, "light");
            } else {
                uiHovers.setHoverButton(true, "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Others()) {
            uiHovers.setHoverOther(true);
        }
        if(e.getSource() == loginView.getJButton_Right_Bottom_Forgot_Password()){
            uiHovers.setHoverForgotPassword(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == loginView.getJTextField_Right_Middle_Email()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(false, "light");
            } else {
                uiHovers.setHoverEmail(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJTextField_Right_Middle_FirstName()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverFirstName(false, "light");
            } else {
                uiHovers.setHoverFirstName(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJTextField_Right_Middle_LastName()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverLastName(false, "light");
            } else {
                uiHovers.setHoverLastName(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJPasswordField_Right_Middle_Password()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverPassword(false, "light");
            } else {
                uiHovers.setHoverPassword(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Submit()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverButton(false, "light");
            } else {
                uiHovers.setHoverButton(false, "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Others()) {
            uiHovers.setHoverOther(false);
        }
        if(e.getSource() == loginView.getJButton_Right_Bottom_Forgot_Password()){
            uiHovers.setHoverForgotPassword(false);
        }
    }
}
