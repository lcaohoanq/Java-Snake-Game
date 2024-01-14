package controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Message;
import models.Account;
import utils.DataHandler;
import views.LoginFormView;
import views.MyFrame;

public class LoginFormController implements ActionListener {

  public static String username = "";
  public static String password = "";
  private DataHandler dataHandler;

  public LoginFormController(DataHandler dataHandler) {
    this.dataHandler = dataHandler;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    username = MyFrame.jTextField_Right_Middle_Username.getText();
    password = MyFrame.jPasswordField_Right_Middle_Password.getText();
    System.out.println("username: " + username);
    System.out.println("password: " + password);
    if (isEmpty(username, password)) {
      Message.IS_EMPTY_USERNAME_OR_PASSWORD();
    } else {
      if (!isMatching(username, password)) {
        Message.IS_WRONG_USERNAME_OR_PASSWORD();
      } else {
        Message.IS_LOGIN_SUCCESS();
        Message.IS_WELLCOME(username);
        // Switch to the play button card using static methods
        CardLayout cardLayout = (CardLayout) LoginFormView.cardLayout;
        cardLayout.next(LoginFormView.jPanel_Button);
      }
    }
  }

  private boolean isEmpty(String username, String password) {
    return username.isEmpty() || password.isEmpty();
  }

  private boolean isMatching(String username, String password) {
    for (Account item : DataHandler.accountList) {
      if (item.getUsername().equals(username) && item.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }
}
