package controllers;

public interface OTPVerificationListener {

    void onOtpVerified();

    void onResendOtp();

    void onBlockUser();
}
