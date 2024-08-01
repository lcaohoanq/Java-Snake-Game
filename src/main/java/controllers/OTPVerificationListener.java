package controllers;

import java.io.IOException;

public interface OTPVerificationListener  {

    void onOtpVerified();

    void onResendOtp() throws IOException, InterruptedException;

    void onBlockUser() throws IOException, InterruptedException;
}
