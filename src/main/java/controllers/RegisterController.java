package controllers;

import views.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterController implements ActionListener, MouseListener {
    private RegisterView registerView;

    public RegisterController(RegisterView registerView) {
        super();
        this.registerView = registerView;
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
        if (e.getSource() == registerView.jTextField_Right_Middle_Username) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverUsername(true, "light");
            } else {
                registerView.setHoverUsername(true, "dark");
            }
        }
        if (e.getSource() == registerView.jPasswordField_Right_Middle_Password) {
            if (!registerView.getStatusToggle()) {
                registerView.setHoverPassword(true, "light");
            } else {
                registerView.setHoverPassword(true, "dark");
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
                registerView.setHoverButton(true, "light");
            } else {
                registerView.setHoverButton(true, "dark");
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
                registerView.setHoverPassword(false, "light");
            } else {
                registerView.setHoverPassword(false, "dark");
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
                registerView.setHoverButton(false, "light");
            } else {
                registerView.setHoverButton(false, "dark");
            }
        }
        if (e.getSource() == registerView.jButton_Right_Bottom_Others) {
            registerView.setHoverOther(false);
        }
    }
}
