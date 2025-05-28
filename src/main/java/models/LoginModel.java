package models;

import lombok.extern.slf4j.Slf4j;
import services.UserService;
import services.UserServiceImpl;

@Slf4j
public class LoginModel {
    private final UserService userService;

    public LoginModel() {
        this.userService = new UserServiceImpl();
    }

    // Constructor for testing with dependency injection
    public LoginModel(UserService userService) {
        this.userService = userService;
    }
    
    public boolean isEmpty(String email, String password) {
        return email == null || password == null || 
               email.trim().isEmpty() || password.trim().isEmpty();
    }
    
    public boolean isAdmin(String email, String password) {
        // Simple admin check - you can customize this as needed
        return "admin@example.com".equals(email) && "admin123".equals(password);
    }
    
    public UserScore login(String email, String password) {
        log.info("Attempting login for: {}", email);
        try {
            return userService.login(email, password);
        } catch (Exception e) {
            log.error("Login error: {}", e.getMessage());
            return null;
        }
    }
}