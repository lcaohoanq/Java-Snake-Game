package modules.email;

import lombok.Getter;

@Getter
public enum EmailCategories {
    OTP("sendOtp"),
    BLOCK_ACCOUNT("blockAccount"),
    FORGOT_PASSWORD("forgotPassword");

    private final String type;

    EmailCategories(String type) {
        this.type = type;
    }

}
