package controllers;

import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import views.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterController implements ActionListener, MouseListener {

    private RegisterView registerView;
    private List<JTextField> inputFieldList;
    private List<JButton> buttonList;

    public RegisterController(RegisterView registerView) {
        super();
        this.registerView = registerView;
        this.inputFieldList = Arrays.asList(
            registerView.jTextField_Right_Middle_Username,
            registerView.jPasswordField_Right_Middle_Password,
            registerView.jPasswordField_Right_Middle_Confirm_Password);
        this.buttonList = Arrays.asList(
            registerView.jButton_Right_Bottom_Submit,
            registerView.jButton_Right_Bottom_Others);
    }

    // Su dung ArrayList<Account> de luu tru account
    @Override
    public void actionPerformed(ActionEvent e) {

        registerView.isMatchingPattern();

        if (registerView.isEmpty()) {
            registerView.handleEmpty();
        } else if (!registerView.isMatching()) {
            registerView.handleWrong();
        } else {
            if (registerView.isDuplicateUsername()) {
                registerView.handleDuplicateUsername();
            } else {
                registerView.insert();
                registerView.handleSuccess();
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
        inputFieldList.stream()
            .filter(inputField -> e.getSource() == inputField)
            .forEach(inputField -> {
                if (!registerView.getStatusToggle()) {
                    registerView.setHoverUsername(true, "light");
                    registerView.setHoverPassword(true, "light");
                    registerView.setHoverConfirmPassword(true, "light");
                } else {
                    registerView.setHoverUsername(true, "dark");
                    registerView.setHoverPassword(true, "dark");
                    registerView.setHoverConfirmPassword(true, "dark");
                }
            });

        buttonList.stream()
            .filter(button -> e.getSource() == button)
            .forEach(button -> {
                if (button.getText().equals("Submit")) {
                    if (!registerView.getStatusToggle()) {
                        registerView.setHoverButton(true, "light");
                    } else {
                        registerView.setHoverButton(true, "dark");
                    }
                } else {
                    registerView.setHoverOther(true);
                }
            });
    }

    @Override
    public void mouseExited(MouseEvent e) {
        inputFieldList.stream()
            .filter(inputField -> e.getSource() == inputField)
            .forEach(inputField -> {
                if (!registerView.getStatusToggle()) {
                    registerView.setHoverUsername(false, "light");
                    registerView.setHoverPassword(false, "light");
                    registerView.setHoverConfirmPassword(false, "light");
                } else {
                    registerView.setHoverUsername(false, "dark");
                    registerView.setHoverPassword(false, "dark");

                }
            });

        buttonList.stream()
            .filter(button -> e.getSource() == button)
            .forEach(button -> {
                if (button.getText().equals("Submit")) {
                    if (!registerView.getStatusToggle()) {
                        registerView.setHoverButton(false, "light");
                    } else {
                        registerView.setHoverButton(false, "dark");
                    }
                } else {
                    registerView.setHoverOther(false);
                }
            });
    }
}
