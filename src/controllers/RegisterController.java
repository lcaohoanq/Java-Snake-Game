package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Messages;
import constants.Paths;
import models.Account;
import services.DBServices;
import utils.DataHandler;
import utils.PasswordHandler;
import views.RegisterView;

public class RegisterController implements ActionListener {

    private DataHandler dataHandler;
    private PasswordHandler passwordHandler;

    public RegisterController(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        this.passwordHandler = new PasswordHandler();
    }

    // Su dung ArrayList<Account> de luu tru account
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = RegisterView.jTextField_Right_Middle_Username.getText();
        // char[] passwordChar = RegisterFormView.jPasswordField_Password.getPassword();
        // char[] confirmPassword =
        // RegisterFormView.jPasswordField_ConfirmPassword.getPassword();
        String password = String.valueOf(RegisterView.jPasswordField_Right_Middle_Password.getPassword());
        String confirmPassword = String
                .valueOf(RegisterView.jPasswordField_Right_Middle_Confirm_Password.getPassword());

        if ((isEmpty(username, password, confirmPassword))) {
            Messages.IS_EMPTY_FIELD();
        } else if (!isMatchPasswordAndConfirmPassword(password, confirmPassword)) {
            Messages.IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD();
        } else {
            if (isDuplicateUsername(username)) {
                Messages.IS_EXISTED_USERNAME();
            } else {
                password = passwordHandler.hash(password); // replace password with the hashed
                DBServices.insert(username, password, 0);
                DataHandler.accountList.add(new Account(username, password));
                dataHandler.writeFile(Paths.URL_ACCOUNT);
                Messages.IS_REGISTER_SUCCESS();
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
