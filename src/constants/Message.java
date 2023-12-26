package constants;

import javax.swing.JOptionPane;

public class Message {
    public static void IS_LOGIN_SUCCESS() {
        JOptionPane.showMessageDialog(null, "Login success", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void IS_LOGIN_FAILED() {
        JOptionPane.showMessageDialog(null, "Login failed", "Failed", JOptionPane.ERROR_MESSAGE);
    }

    public static void IS_REGISTER_SUCCESS() {
        JOptionPane.showMessageDialog(null, "Register success", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void IS_REGISTER_FAILED() {
        JOptionPane.showMessageDialog(null, "Register failed", "Failed", JOptionPane.ERROR_MESSAGE);
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

    public static void IS_WELLCOME(String msg) {
        JOptionPane.showMessageDialog(null, "Hello " + msg, null, JOptionPane.INFORMATION_MESSAGE);
    }
    public static void IS_ABOUT_ME(){
        JOptionPane.showMessageDialog(null, "This is a project of Java Swing", "About me", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void IS_COPYRIGHT(){
        JOptionPane.showMessageDialog(null, "Core game made by Zet Code", "About me", JOptionPane.INFORMATION_MESSAGE);
    }
}
