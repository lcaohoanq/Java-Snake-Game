package controllers;

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import views.LoginView;
import views.OTPVerificationView;

@Slf4j
public class ForgotPasswordController implements ActionListener {

    private final LoginView loginView;

    @Deprecated
    private OTPVerificationView otpVerificationView;

    public ForgotPasswordController(LoginView loginView, OTPVerificationView otpVerificationView) {
        this.loginView = loginView;
        this.otpVerificationView = otpVerificationView;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String api = "http://localhost:3000/forgot-password";
        try {
            // Specify the URL of the website
            URI uri = new URI("http://localhost:3000/forgot-password");
            // Open the website in the default browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
