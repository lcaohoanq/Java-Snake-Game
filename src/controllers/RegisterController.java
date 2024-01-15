package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Messages;
import constants.Paths;
import models.Account;
import services.DBServices;
import utils.DataHandler;
import utils.PasswordHandler;
import views.MyFrame;
import views.RegisterView;

public class RegisterController extends FrameController implements ActionListener {

    private PasswordHandler passwordHandler;

    public RegisterController() {
        super();
        this.passwordHandler = new PasswordHandler();
    }

    // Su dung ArrayList<Account> de luu tru account
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = MyFrame.jTextField_Right_Middle_Username.getText();
        // char[] passwordChar = RegisterFormView.jPasswordField_Password.getPassword();
        // char[] confirmPassword =
        // RegisterFormView.jPasswordField_ConfirmPassword.getPassword();
        String password = String.valueOf(MyFrame.jPasswordField_Right_Middle_Password.getPassword());
        String confirmPassword = String
                .valueOf(MyFrame.jPasswordField_Right_Middle_Confirm_Password.getPassword());

        if ((isEmpty(username, password, confirmPassword))) {
            Messages.IS_EMPTY_FIELD();
            System.out.println("Register failed: " + "username:" + username + " password:" + password);
        } else if (!isMatching(password, confirmPassword)) {
            Messages.IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD();
            System.out.println("Register failed: " + "username:" + username + " password:" + password);
        } else {
            if (isDuplicateUsername(username)) {
                Messages.IS_EXISTED_USERNAME();
                System.out.println("Register failed: " + "username:" + username + " password:" + password);
            } else {
                password = passwordHandler.hash(password); // replace password with the hashed
                System.out.println("Register success: " + "username:" + username + " password:" + password);
                DBServices.insert(username, password, 0);
                DataHandler.accountList.add(new Account(username, password));
                dataHandler.writeFile(Paths.URL_ACCOUNT);
                Messages.IS_REGISTER_SUCCESS();
            }

        }

    }

    @Override
    public boolean isEmpty(String username, String password) {
        return false;
    }

    @Override
    public boolean isMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }


    @Override
    public boolean isEmpty(String username, String password, String confirmPassword) {
        return username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty();
    }

    @Override
    public boolean isDuplicateUsername(String username) {
        for (Account item : DataHandler.accountList) {
            if (item.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
