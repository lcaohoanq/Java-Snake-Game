package controllers;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.http.HttpResponse;
import javax.swing.JOptionPane;
import lombok.extern.slf4j.Slf4j;
import utils.ApiUtils;
import views.ChangePasswordView;
import views.LoginView;
import views.OTPVerificationView;

@Slf4j
public class ForgotPasswordController implements ActionListener {

    private final LoginView loginView;
    private OTPVerificationView otpVerificationView;

    public ForgotPasswordController(LoginView loginView, OTPVerificationView otpVerificationView) {
        this.loginView = loginView;
        this.otpVerificationView = otpVerificationView;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (loginView.getJButton_Right_Bottom_Forgot_Password().getText()
            .equals("Forgot password?")) {
            String data = loginView.getJTextField_Right_Middle_Email().getText();
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your email", "Error",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                // need to check if the user is in the database or not
                try {
                    HttpResponse<String> response = ApiUtils.getRequest(
                        "http://localhost:8081/otp/send?type=mail&recipient=" + data);

                    if (response.statusCode() == 400) {
                        JOptionPane.showMessageDialog(null, "Account not register", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    } else if (response.statusCode() == 200) {
                        JOptionPane.showMessageDialog(null, "Please check your email for OTP",
                            null,
                            JOptionPane.INFORMATION_MESSAGE);

                        String otp = response.body().substring(43, 49);

                        otpVerificationView = new OTPVerificationView(otp,
                            new OTPVerificationListener() {

                                @Override
                                public void onOtpVerified() {
                                    new ChangePasswordView(data).setVisible(true);
                                    loginView.setEnabled(true);
                                }

                                @Override
                                public void onResendOtp() throws IOException, InterruptedException {

                                    final HttpResponse<String> resendOtp = ApiUtils.getRequest(
                                        "http://localhost:8081/otp/send?type=mail&recipient="
                                            + data);

                                    if (resendOtp.statusCode() == 200) {
                                        String newOtp = resendOtp.body().substring(43, 49);
                                        otpVerificationView.setGeneratedOtp(newOtp);
                                        log.info("Resend OTP to email {}", data);
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                            "Internal server error, please try again later!");
                                    }
                                }

                                @Override
                                public void onBlockUser() throws IOException, InterruptedException {

                                    final HttpResponse<String> blockResponse = ApiUtils.getRequest(
                                        "http://localhost:8081//mail/block?toEmail" + data);

                                    if (blockResponse.statusCode() == 200) {
                                        JOptionPane.showMessageDialog(null,
                                            "Blocked user with email " + data
                                                + ", too many request change password in time");
                                        log.warn(
                                            "Blocked user with email {}, too many request change password in time",
                                            data);
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                            "Internal server error, please try again later!");
                                    }
                                }
                            });
                        otpVerificationView.setVisible(true);
                        loginView.setEnabled(false);
                    }

                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + error.getMessage());
                }
            }
        }
    }
}
