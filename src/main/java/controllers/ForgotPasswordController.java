package controllers;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import lombok.extern.slf4j.Slf4j;
import models.RegisterModel;
import modules.email.EmailUtils;
import modules.otp.OTPUtils;
import views.ChangePasswordView;
import views.LoginView;
import views.OTPVerificationView;

@Slf4j
public class ForgotPasswordController implements ActionListener {
    private final LoginView loginView;
    private OTPVerificationView otpVerificationView;
    public ForgotPasswordController(LoginView loginView, OTPVerificationView otpVerificationView) {
        this.loginView = loginView;
        this.otpVerificationView =  otpVerificationView;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (loginView.getJButton_Right_Bottom_Forgot_Password().getText().equals("Forgot password?")) {
            String data = loginView.getJTextField_Right_Middle_Email().getText();
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your email", "Error",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                // need to check if the user is in the database or not
                if (!new RegisterModel().isDuplicateEmail(data)) {
                    JOptionPane.showMessageDialog(null, "Account not existed", "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please check your email for OTP",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                    String otp = OTPUtils.generateOTP();
                    EmailUtils handleEmail = new EmailUtils();
                    //find the first name of this email
                    handleEmail.sendEmail(handleEmail.subjectGreeting(data),
                        handleEmail.emailSendOtp(data, otp), data);
                    otpVerificationView = new OTPVerificationView(otp,
                        new OTPVerificationListener() {

                            @Override
                            public void onOtpVerified() {
                                new ChangePasswordView(data).setVisible(true);
                                loginView.setEnabled(true);
                            }

                            @Override
                            public void onResendOtp() {
                                String newOtp = OTPUtils.generateOTP();
                                handleEmail.sendEmail(handleEmail.subjectGreeting(data),
                                    handleEmail.emailSendOtp(data, newOtp), data);
                                otpVerificationView.setGeneratedOtp(newOtp);
                                log.info("Resend OTP to email {}", data);
                            }

                            @Override
                            public void onBlockUser() {
                                handleEmail.sendEmail(handleEmail.subjectGreeting(data),
                                    handleEmail.emailSendBlockAccount(data, "Too many request, maybe abuse action, we added you to application blacklist"), data);
                                log.warn("Blocked user with email {}, too many request change password in time", data);
                            }
                        });
                    otpVerificationView.setVisible(true);
                    loginView.setEnabled(false);
                }
            }
        }
    }
}
