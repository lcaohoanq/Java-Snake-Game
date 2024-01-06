package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Message;
import constants.Path;
import models.Account;
import services.DBServices;
import utils.DataHandler;
import utils.PasswordHandler;
import views.RegisterFormView;

public class RegisterFormController implements ActionListener {

    private DataHandler dataHandler;
    private PasswordHandler passwordHandler;

    public RegisterFormController(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        this.passwordHandler = new PasswordHandler();
    }

    // Su dung ArrayList<Account> de luu tru account
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = RegisterFormView.jTextField_Username.getText();
        // char[] passwordChar = RegisterFormView.jPasswordField_Password.getPassword();
        // char[] confirmPassword =
        // RegisterFormView.jPasswordField_ConfirmPassword.getPassword();
        String password = String.valueOf(RegisterFormView.jPasswordField_Password.getPassword());
        String confirmPassword = String.valueOf(RegisterFormView.jPasswordField_ConfirmPassword.getPassword());

        if ((isEmpty(username, password, confirmPassword))) {
            Message.IS_EMPTY_FIELD();
        } else if (!isMatchPasswordAndConfirmPassword(password, confirmPassword)) {
            Message.IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD();
        } else {
            if (isDuplicateUsername(username)) {
                Message.IS_EXISTED_USERNAME();
            } else {
                password = passwordHandler.hash(password); //replace password with the hashed 
                DBServices.insert(username, password, 0);
                DataHandler.accountList.add(new Account(username, password));
                dataHandler.writeFile(Path.url);
                Message.IS_REGISTER_SUCCESS();
            }

        }

    }

    private boolean isMatchPasswordAndConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmpty(String username, String password, String confirmPassword) {
        return username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty();
    }

    private boolean isDuplicateUsername(String username) {
        for (Account item : DataHandler.accountList) {
            if (item.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
