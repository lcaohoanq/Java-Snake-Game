package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Message;
import constants.Path;
import model.Account;
import utils.DataHandler;
import view.RegisterFormView;

public class RegisterFormController implements ActionListener {

    private DataHandler dataHandler;

    public RegisterFormController(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    // Su dung ArrayList<Account> de luu tru account
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = RegisterFormView.jTextField_Username.getText();
        String password = RegisterFormView.jTextField_Password.getText();
        String confirmPassword = RegisterFormView.jTextField_ConfirmPassword.getText();

        if ((isEmpty(username, password, confirmPassword))) {
            Message.IS_EMPTY_FIELD();
        } else if (!isMatchPasswordAndConfirmPassword(password, confirmPassword)) {
            Message.IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD();
        } else {
            if (isDuplicateUsername(username)) {
                Message.IS_EXISTED_USERNAME();
            } else {
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
