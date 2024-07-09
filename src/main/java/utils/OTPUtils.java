package utils;

import javax.swing.JOptionPane;

public class OTPUtils {
    public static String generateOTP() {
        return String.format("%06d", (int) (Math.random() * 1000000));
    };

    public static void IS_NOTIFY_VERIFY_ACCOUNT() {
        JOptionPane.showMessageDialog(null, "We've send your otp back to your email, please type back to authenticate!", "Snake Game", JOptionPane.INFORMATION_MESSAGE);
    }
}
