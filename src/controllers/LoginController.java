package controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Messages;
import models.Account;
import utils.DataHandler;
import views.LoginView;
import views.MyFrame;

public class LoginController implements ActionListener {

  public static String username = "";
  public static String password = "";
  private DataHandler dataHandler;

  public LoginController(DataHandler dataHandler) {
    this.dataHandler = dataHandler;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    username = MyFrame.jTextField_Right_Middle_Username.getText();
    password = MyFrame.jPasswordField_Right_Middle_Password.getText();
    if (isEmpty(username, password)) {
      Messages.IS_EMPTY_USERNAME_OR_PASSWORD();
    } else {
      if (!isMatching(username, password)) {
        Messages.IS_WRONG_USERNAME_OR_PASSWORD();
      } else {
        Messages.IS_LOGIN_SUCCESS();
        Messages.IS_WELCOME(username);
        // Switch to the play button card using static methods
        CardLayout cardLayout = (CardLayout) LoginView.cardLayout;
        cardLayout.next(LoginView.jPanel_Right_Bottom_Button);
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
