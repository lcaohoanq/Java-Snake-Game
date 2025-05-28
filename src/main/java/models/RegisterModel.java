package models;

import constants.Regex;
import lombok.extern.slf4j.Slf4j;
import services.UserService;
import services.UserServiceImpl;

@Slf4j
public class RegisterModel {
    private final UserService userService;

    public RegisterModel() {
        this.userService = new UserServiceImpl();
    }

    // Constructor for testing with dependency injection
    public RegisterModel(UserService userService) {
        this.userService = userService;
    }

    public boolean isNameFormat(String name) {
        return name.matches(Regex.NAME);
    }

    public boolean isEmailFormat(String email) {
        return email.matches(Regex.EMAIL);
    }

    public boolean isPhoneNumberFormat(String phoneNumber) {
        return phoneNumber.matches(Regex.PHONE_NUMBER);
    }

    public boolean isPasswordFormat(String password) {
        return password.matches(Regex.PASSWORD);
    }

    public boolean isEmpty(String email, String password, String confirmPassword) {
        return email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty();
    }

    public boolean isMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public boolean isDuplicateEmail(String email) {
        // Check if any user with this email exists
        return userService.getAll().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    public UserScore registerUser(String fullName, String email, String password) {
        log.info("Registering user: {}", email);
        return userService.register(fullName, email, password);
    }
}