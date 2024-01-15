package controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Messages;
import models.Account;
import utils.DataHandler;
import views.LoginView;
import views.MyFrame;

public class LoginController extends FrameController implements ActionListener {

  public static String username = "";
  public static String password = "";

  public LoginController() {
    super();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    username = MyFrame.jTextField_Right_Middle_Username.getText();
    password = String.valueOf(MyFrame.jPasswordField_Right_Middle_Password.getPassword());
    if (isEmpty(username, password)) {
      Messages.IS_EMPTY_USERNAME_OR_PASSWORD();
      System.out.println("Login failed: " + "username:" + username + " password:" + password);
    } else {
      if (!isMatching(username, password)) {
        Messages.IS_WRONG_USERNAME_OR_PASSWORD();
        System.out.println("Login failed: " + "username:" + username + " password:" + password);
      } else {
        Messages.IS_LOGIN_SUCCESS();
        // Switch to the play button card using static methods
        System.out.println("Login success: " + "username:" + username + " password:" + password);
        CardLayout cardLayout = LoginView.cardLayout;
        cardLayout.next(LoginView.jPanel_Right_Bottom_Button);
      }
    }
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

  @Override
  public boolean isEmpty(String username, String password, String confirmPassword) {
    return false;
  }

  @Override
  public boolean isDuplicateUsername(String username) {
    return false;
  }
}
