package controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Messages;
import models.Account;
import models.LoginData;
import utils.DataHandler;
import views.LoginView;

public class LoginController extends FrameController implements ActionListener, LoginData {

  public static String username = "";
  public static String password = "";

  private LoginView loginView;

  public LoginController(LoginView loginView) {
    super();
    this.loginView = loginView;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
     username = loginView.getLogin().getUsername();
     password = loginView.getLogin().getPassword();

    System.out.println("Data: " + username + " " + password);

    if (isEmpty(username, password)) {
      handleEmpty();
    } else {
      if (!isMatching(username, password)) {
        handleWrong();
      } else {
        handleSuccess();
      }
    }

    }

  @Override
  public void handleEmpty() {
    Messages.IS_EMPTY_USERNAME_OR_PASSWORD();
    System.out.println("Login failed: " + "username:" + username + " password:" + password);
  }

  @Override
  public void handleWrong() {
    Messages.IS_WRONG_USERNAME_OR_PASSWORD();
    System.out.println("Login failed: " + "username:" + username + " password:" + password);
  }

  @Override
  public void handleSuccess() {
    Messages.IS_LOGIN_SUCCESS();
    // Switch to the play button card using static methods
    System.out.println("Login success: " + "username:" + username + " password:" + password);
    CardLayout cardLayout = LoginView.cardLayout;
    cardLayout.next(LoginView.jPanel_Right_Bottom_Button);
  }

  @Override
  public boolean isEmpty(String username, String password) {
    return username.isEmpty() || password.isEmpty();
  }

  @Override
  public boolean isMatching(String username, String password) {
    for (Account item : DataHandler.accountList) {
      if (item.getUsername().equals(username) && item.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }
}
