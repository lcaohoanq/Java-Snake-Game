package controllers;

import enums.Hover;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import styles.UIHovers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import views.LoginView;
import views.RegisterView;
import views.UIPrompts;

@Slf4j
public class RegisterController implements ActionListener, MouseListener {

    private final RegisterView registerView;
    private final List<JTextField> inputFieldList;
    private final List<JButton> buttonList;
    private UIHovers<RegisterView> uiHovers;

    public RegisterController(RegisterView registerView) {
        super();
        this.registerView = registerView;
        this.inputFieldList = Arrays.asList(
            registerView.getJTextField_Right_Middle_Email(),
            registerView.getJTextField_Right_Middle_UserName(),
            registerView.getJPasswordField_Right_Middle_Password(),
            registerView.getJPasswordField_Right_Middle_Confirm_Password());
        this.buttonList = Arrays.asList(
            registerView.getJButton_Right_Bottom_Submit(),
            registerView.getJButton_Right_Bottom_Others());
        this.uiHovers = new UIHovers<>(registerView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle registration button click
        if (e.getSource() == registerView.getJButton_Right_Bottom_Submit()) {
            handleRegistration();
        }
        // Handle "Sign In Here" button click
        else if (e.getSource() == registerView.getJButton_Right_Bottom_Others()) {
            // Navigate to login view
            registerView.dispose();
            new LoginView().setVisible(true);
        }
    }

    private void handleRegistration() {
        log.info("Starting registration process...");
        
        // Check for empty fields
        if (!registerView.isEmpty()) {
            log.info("Fields are not empty, checking password match...");
            
            // Check if passwords match
            if (registerView.isMatchingPasswordAndConfirmPassword()) {
                log.info("Passwords match, checking for duplicate email...");
                
                // Check if email already exists
                if (!registerView.isDuplicateEmail()) {
                    log.info("Email is not duplicate, attempting to register user...");
                    
                    // Register the user
                    UserScore newUser = registerView.registerUser();
                    log.info("Register result: {}", newUser);

                    if (newUser != null) {
                        // Registration successful
                        UIPrompts.IS_REGISTER_SUCCESS();
                        log.info("User {} registered successfully", newUser.getEmail());

                        // Navigate to login view
                        registerView.dispose();
                        new LoginView().setVisible(true);
                    } else {
                        // Registration failed
                        UIPrompts.IS_REGISTER_FAILED();
                        log.error("Registration failed for unknown reason");
                    }
                } else {
                    UIPrompts.IS_EXISTED_EMAIL();
                    log.error("Email already exists, please try again");
                }
            } else {
                registerView.handleNotMatchingPasswordAndConfirmPassword();
                log.error("Password and confirm password do not match, please try again");
            }
        } else {
            UIPrompts.IS_EMPTY_FIELD();
            log.error("Empty field when register, please try again");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle "Sign In Here" button click
        if (e.getSource() == registerView.getJButton_Right_Bottom_Others()) {
            registerView.dispose();
            new LoginView().setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not needed
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not needed
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle hover effects - keeping your existing code
        inputFieldList.stream()
            .filter(inputField -> e.getSource() == inputField)
            .forEach(inputField -> {
                if (!registerView.getStatusToggle()) {
                    if (inputField == registerView.getJTextField_Right_Middle_Email()) {
                        uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "light");
                    }
                    if (inputField == registerView.getJPasswordField_Right_Middle_Password()) {
                        uiHovers.setHoverPassword(Hover.ENABLE.isStatus(), "light");
                    }
                    if (inputField
                        == registerView.getJPasswordField_Right_Middle_Confirm_Password()) {
                        uiHovers.setHoverConfirmPassword(Hover.ENABLE.isStatus(), "light");
                    }
                } else {
                    if (inputField == registerView.getJTextField_Right_Middle_Email()) {
                        uiHovers.setHoverEmail(Hover.ENABLE.isStatus(), "dark");
                    }
                    if (inputField == registerView.getJPasswordField_Right_Middle_Password()) {
                        uiHovers.setHoverPassword(Hover.ENABLE.isStatus(), "dark");
                    }
                    if (inputField
                        == registerView.getJPasswordField_Right_Middle_Confirm_Password()) {
                        uiHovers.setHoverConfirmPassword(Hover.ENABLE.isStatus(), "dark");
                    }
                }
            });

        buttonList.stream()
            .filter(button -> e.getSource() == button)
            .forEach(button -> {
                if (button.getText().equals("Submit")) {
                    if (!registerView.getStatusToggle()) {
                        uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "light");
                    } else {
                        uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "dark");
                    }
                } else {
                    uiHovers.setHoverOther(Hover.ENABLE.isStatus());
                }
            });
    }

    @Override
    public void mouseExited(MouseEvent e) {
        inputFieldList.stream()
            .filter(inputField -> e.getSource() == inputField)
            .forEach(inputField -> {
                if (!registerView.getStatusToggle()) {
                    uiHovers.setHoverEmail(Hover.DISABLE.isStatus(), "light");
                    uiHovers.setHoverFirstName(Hover.DISABLE.isStatus(), "light");
                    uiHovers.setHoverPassword(Hover.DISABLE.isStatus(), "light");
                    uiHovers.setHoverConfirmPassword(Hover.DISABLE.isStatus(), "light");
                } else {
                    uiHovers.setHoverEmail(Hover.DISABLE.isStatus(), "dark");
                    uiHovers.setHoverFirstName(Hover.DISABLE.isStatus(), "dark");
                    uiHovers.setHoverPassword(Hover.DISABLE.isStatus(), "dark");
                    uiHovers.setHoverConfirmPassword(Hover.DISABLE.isStatus(), "dark");

                }
            });

        buttonList.stream()
            .filter(button -> e.getSource() == button)
            .forEach(button -> {
                if (button.getText().equals("Submit")) {
                    if (!registerView.getStatusToggle()) {
                        uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light");
                    } else {
                        uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark");
                    }
                } else {
                    uiHovers.setHoverOther(Hover.DISABLE.isStatus());
                }
            });
    }
}
