package constants;

import javax.swing.JOptionPane;

public class Messages {
    public static void IS_NOT_SUPPORT() {
        JOptionPane.showMessageDialog(null, "This function is not supported", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void IS_LOGIN_SUCCESS() {
        JOptionPane.showMessageDialog(null, "Login success", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void IS_REGISTER_SUCCESS() {
        JOptionPane.showMessageDialog(null, "Register success", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void IS_WRONG_USERNAME_OR_PASSWORD() {
        JOptionPane.showMessageDialog(null, "Wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void IS_EMPTY_USERNAME_OR_PASSWORD() {
        JOptionPane.showMessageDialog(null, "Username or password cannot be empty", "Error",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_EMPTY_FIELD() {
        JOptionPane.showMessageDialog(null, "Field cannot be empty", "Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD() {
        JOptionPane.showMessageDialog(null, "Password and confirm password are not match", "Error",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_EXISTED_USERNAME() {
        JOptionPane.showMessageDialog(null, "Username is existed", "Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_WRONG_FORMAT_USERNAME() {
        JOptionPane.showMessageDialog(null, "Username must be 1-20 characters and no special characters", "Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_WRONG_FORMAT_PASSWORD() {
        JOptionPane.showMessageDialog(null, "Password must be 8 characters and at least 1 lowercase character, 1 uppercase character, 1 number and 1 special character", "Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_WELCOME(String msg) {
        JOptionPane.showMessageDialog(null, "Hello " + msg, null, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int IS_CONFIRM_EXIT() {
        return JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Confirmation",
                JOptionPane.YES_NO_OPTION);
    }

    public static void IS_ABOUT_ME() {
        JOptionPane.showMessageDialog(null, "This is a project of Java Swing", "About me", JOptionPane.INFORMATION_MESSAGE);
    }
}
