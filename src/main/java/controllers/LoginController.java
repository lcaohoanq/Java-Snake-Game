package controllers;

import enums.Hover;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import styles.UIHovers;
import views.LoginView;
import views.MenuView;
import views.UIPrompts;

@Slf4j
public final class LoginController implements ActionListener, MouseListener {

    private final LoginView loginView;
    private final UIHovers<LoginView> uiHovers;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.uiHovers = new UIHovers<>(loginView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle admin login
        if (loginView.isAdmin()) {
            loginView.handleSuccess();
            log.info("Admin login successful");
            return;
        }
        
        // Check for empty fields
        if (loginView.isEmpty()) {
            UIPrompts.IS_EMPTY_FIELD();
            log.error("Empty field when login, please try again");
            return;
        }
        
        // Attempt to login
        UserScore user = loginView.login();
        if (user != null) {
            loginView.handleSuccess();
            log.info("User login successful: {}", user.getUsername());
            
            // Add the play button listener with user info
            loginView.getJButton_Right_Play().addActionListener(new PlayController(loginView, user));
        } else {
            UIPrompts.IS_INCORRECT_CREDENTIALS();
            log.error("Incorrect credentials, please try again");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Not needed for basic functionality
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not needed for basic functionality
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not needed for basic functionality
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Keep your existing hover handling code
        if (e.getSource() == loginView.getJTextField_Right_Middle_Email()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJPasswordField_Right_Middle_Password()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "light");

            } else {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Submit()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Others()) {
            uiHovers.setHoverOther(Hover.ENABLE.isStatus());
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Forgot_Password()) {
            uiHovers.setHoverForgotPassword(Hover.ENABLE.isStatus());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Keep your existing hover exit code
        if (e.getSource() == loginView.getJTextField_Right_Middle_Email()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverEmail(Hover.DISABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverEmail(Hover.DISABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJTextField_Right_Middle_UserName()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverFirstName(Hover.DISABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverFirstName(Hover.DISABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJPasswordField_Right_Middle_Password()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverPassword(Hover.DISABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverPassword(Hover.DISABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Submit()) {
            if (!loginView.getStatusToggle()) {
                uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light");
            } else {
                uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark");
            }
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Others()) {
            uiHovers.setHoverOther(Hover.DISABLE.isStatus());
        }
        if (e.getSource() == loginView.getJButton_Right_Bottom_Forgot_Password()) {
            uiHovers.setHoverForgotPassword(Hover.DISABLE.isStatus());
        }
    }
}
