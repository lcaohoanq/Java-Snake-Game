package views;

import javax.swing.JOptionPane;

public class UIPrompts {
    public static void IS_NOT_SUPPORT() {
        JOptionPane.showMessageDialog(null, "This function is not supported", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void IS_LOGIN_SUCCESS() {
        JOptionPane.showMessageDialog(null, "Login success", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void IS_REGISTER_SUCCESS() {
        JOptionPane.showMessageDialog(null, "Register success", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void IS_CHANGE_PASSWORD_SUCCESS(){
        JOptionPane.showMessageDialog(null, "Change password success", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void IS_WRONG_FORMAT_EMAIL(){
        JOptionPane.showMessageDialog(null, "Email must be in the correct format", "Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_WRONG_USERNAME_OR_PASSWORD() {
        JOptionPane.showMessageDialog(null, "Wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void IS_EMPTY_FIELD() {
        JOptionPane.showMessageDialog(null, "Please fill in all field", "Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD() {
        JOptionPane.showMessageDialog(null, "Password and confirm password are not match", "Error",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_EXISTED_EMAIL() {
        JOptionPane.showMessageDialog(null, "Email already existed, please try with another email", "Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void IS_WRONG_FORMAT_NAME() {
        JOptionPane.showMessageDialog(null, "Name must be 1-20 characters, no special character, no number", "Error", JOptionPane.WARNING_MESSAGE);
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
        JOptionPane.showMessageDialog(null, "This is a Java Swing project by lcaohoanq", "About me", JOptionPane.INFORMATION_MESSAGE);
    }
}
