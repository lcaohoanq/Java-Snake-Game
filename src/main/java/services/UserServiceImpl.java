package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import utils.PBKDF2;

@Slf4j
public class UserServiceImpl implements UserService {

    private List<UserScore> users;
    private final String JSON_FILE_PATH = "src/main/resources/users.json";
    private final Gson gson;
    private final PBKDF2 passwordHasher;

    public UserServiceImpl() {
        this.gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                (json, type, context) -> LocalDateTime.parse(json.getAsString()))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
            .setPrettyPrinting()
            .create();
        this.passwordHasher = new PBKDF2();
        loadUsersFromJson();
    }

    private void loadUsersFromJson() {
        Path jsonPath = Paths.get(JSON_FILE_PATH);
        
        // First try to read from file system
        if (Files.exists(jsonPath)) {
            try (FileReader reader = new FileReader(jsonPath.toFile())) {
                Type listType = new TypeToken<List<UserScore>>() {}.getType();
                this.users = gson.fromJson(reader, listType);
                if (this.users == null) {
                    this.users = new ArrayList<>();
                }
                log.info("Loaded {} users from file: {}", users.size(), JSON_FILE_PATH);
                return;
            } catch (IOException e) {
                log.error("Error reading file from disk: {}", e.getMessage());
                // Continue to try classpath resources
            }
        }
        
        // If file doesn't exist or can't be read, try classpath resources
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("users.json")) {
            if (inputStream == null) {
                log.info("Resource users.json not found in classpath, creating default users");
                this.users = new ArrayList<>();
                createDefaultUsersFile();
                return;
            }
            
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type listType = new TypeToken<List<UserScore>>() {}.getType();
            this.users = gson.fromJson(reader, listType);
            if (this.users == null) {
                this.users = new ArrayList<>();
            }
            
            // Copy resource to file for future use
            saveUsersToFile();
            log.info("Loaded {} users from classpath resource", users.size());
            
        } catch (Exception e) {
            log.error("Error loading users from JSON: {}", e.getMessage());
            this.users = new ArrayList<>();
            createDefaultUsersFile();
        }
    }

    private void createDefaultUsersFile() {
        try {
            // Create directory if it doesn't exist
            Path parentDir = Paths.get(JSON_FILE_PATH).getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            // Create default users
            this.users = createDefaultUsers();
            saveUsersToFile();

        } catch (Exception e) {
            log.error("Error creating default users file: {}", e.getMessage());
            this.users = new ArrayList<>();
        }
    }

    private List<UserScore> createDefaultUsers() {
        List<UserScore> defaultUsers = new ArrayList<>();

        // Store hashed passwords for default users
        String hashedPassword1 = passwordHasher.hash("password123".toCharArray());
        String hashedPassword2 = passwordHasher.hash("password456".toCharArray());
        String hashedPassword3 = passwordHasher.hash("password789".toCharArray());

        defaultUsers.add(new UserScore(1, "John Doe", "john@example.com", hashedPassword1, 100, LocalDateTime.now()));
        defaultUsers.add(new UserScore(2, "Jane Smith", "jane@example.com", hashedPassword2, 85, LocalDateTime.now()));
        defaultUsers.add(new UserScore(3, "Bob Johnson", "bob@example.com", hashedPassword3, 92, LocalDateTime.now()));

        return defaultUsers;
    }

    @Override
    public List<UserScore> getAll() {
        if (users == null || users.isEmpty()) {
            log.info("No users found.");
            return new ArrayList<>();
        }
        return new ArrayList<>(users); // Return a copy to prevent external modification
    }

    @Override
    public UserScore findById(Integer userId) {
        if (userId == null) {
            return null;
        }

        return users.stream()
            .filter(user -> user.getUserId().equals(userId))
            .findFirst()
            .orElse(null);
    }

    @Override
    public UserScore login(String email, String password) {
        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            return null;
        }

        return users.stream()
            .filter(user -> user.getEmail().equals(email) && 
                           verifyPassword(password, user.getPassword()))
            .findFirst()
            .orElse(null);
    }

    private boolean verifyPassword(String plainPassword, String storedHash) {
        try {
            return passwordHasher.authenticate(plainPassword.toCharArray(), storedHash);
        } catch (Exception e) {
            // If there's an error (e.g., invalid hash format), authentication fails
            log.error("Password verification error: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public UserScore register(String username, String email, String password) {
        if (username == null || email == null || password == null ||
            username.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
            return null;
        }

        // Check if user already exists
        if (users.stream().anyMatch(user -> user.getEmail().equals(email))) {
            log.info("User with email {} already exists.", email);
            return null;
        }

        // Hash the password before storing
        String hashedPassword = passwordHasher.hash(password.toCharArray());
        
        // Create new user with hashed password
        UserScore newUser = new UserScore(users.size() + 1, username, email, hashedPassword, 0, LocalDateTime.now());
        users.add(newUser);
        saveUsersToFile();
        log.info("New user registered: {}", username);
        return newUser;
    }

    @Override
    public void updateScore(UserScore user, int score) {
        if (user == null) {
            log.info("Cannot update score: user is null");
            return;
        }

        UserScore existingUser = findById(user.getUserId());
        if (existingUser != null) {
            int oldScore = existingUser.getScore();
            existingUser.setScore(oldScore + score);
            existingUser.setTimestamp(LocalDateTime.now());

            saveUsersToFile();
            log.info("Score updated for user {}: {} -> {}", 
                  existingUser.getUsername(), oldScore, existingUser.getScore());
        } else {
            log.info("User not found for updating score: ID {}", user.getUserId());
        }
    }

    private void saveUsersToFile() {
        Path jsonPath = Paths.get(JSON_FILE_PATH);
        
        try {
            // Create parent directories if they don't exist
            Path parent = jsonPath.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            
            // Write to file
            try (FileWriter writer = new FileWriter(jsonPath.toFile())) {
                gson.toJson(users, writer);
                log.info("Users successfully saved to {}", jsonPath);
            }
        } catch (IOException e) {
            log.error("Error saving users to JSON: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean removeUser(Integer userId) {
        UserScore user = findById(userId);
        if (user != null) {
            users.remove(user);
            saveUsersToFile();
            return true;
        }
        return false;
    }

    public List<UserScore> getTopScorers(int limit) {
        return users.stream()
            .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
            .limit(limit)
            .toList();
    }

    public static void main(String[] args) {
        log.info("=== UserService Implementation Test ===");

        UserServiceImpl service = new UserServiceImpl();

        // Test 1: Get all users
        log.info("1. Testing getAll():");
        List<UserScore> allUsers = service.getAll();
        log.info("Total users: {}", allUsers.size());
        allUsers.forEach(user -> log.info("  - {}", user));

        // Test 2: Find user by ID
        log.info("2. Testing findById():");
        UserScore foundUser = service.findById(1);
        log.info("User with ID 1: {}", foundUser);

        UserScore notFoundUser = service.findById(999);
        log.info("User with ID 999: {}", notFoundUser);

        // Test 3: Login functionality
        log.info("3. Testing login():");
        UserScore loginUser = service.login("john@example.com", "password123");
        log.info("Login with correct credentials: {}", 
              (loginUser != null ? loginUser.getUsername() : "Failed"));

        UserScore failedLogin = service.login("john@example.com", "wrongpassword");
        log.info("Login with wrong password: {}", 
              (failedLogin != null ? failedLogin.getUsername() : "Failed"));

        UserScore nullLogin = service.login("", "");
        log.info("Login with empty credentials: {}", 
              (nullLogin != null ? nullLogin.getUsername() : "Failed"));

        // Test 4: Register new user
        log.info("4. Testing register():");
        UserScore newUser = service.register("Alice Johnson", "alice@gmail.com", "alicepass");
        log.info("New user registered: {}", (newUser != null ? newUser.getUsername() : "Failed"));
        log.info("Total users after registration: {}", service.getAll().size());
        UserScore duplicateUser = service.register("John Doe", "alice@gmail.com", "alicepass");
        log.info("Attempt to register duplicate user: {}", 
              (duplicateUser != null ? duplicateUser.getUsername() : "Failed"));

        // Test 5: Update score
        log.info("5. Testing updateScore():");
        if (foundUser != null) {
            int originalScore = foundUser.getScore();
            log.info("Original score for {}: {}", foundUser.getUsername(), originalScore);

            service.updateScore(foundUser, 25);

            // Reload user to see updated score
            UserScore updatedUser = service.findById(foundUser.getUserId());
            log.info("Updated score: {}", updatedUser.getScore());
            log.info("Score difference: +{}", (updatedUser.getScore() - originalScore));
        }

        // Test 6: Update score with null user
        log.info("6. Testing updateScore() with null user:");
        service.updateScore(null, 10);

        // Test 7: Top scorers
        log.info("7. Testing getTopScorers():");
        List<UserScore> topScorers = service.getTopScorers(3);
        log.info("Top 3 scorers:");
        for (int i = 0; i < topScorers.size(); i++) {
            UserScore user = topScorers.get(i);
            log.info("  {}. {} - Score: {}", (i + 1), user.getUsername(), user.getScore());
        }

        // Test 8: Remove user
        log.info("8. Testing removeUser():");
        boolean removed = service.removeUser(4);
        log.info("User with ID 4 removed: {}", removed);
        if (removed) {
            log.info("Total users now: {}", service.getAll().size());
        }

        // Test 9: Save to JSON
        log.info("9. Testing saveUsersToJson():");
        try {
            service.saveUsersToFile();
            log.info("Users saved successfully to {}", service.JSON_FILE_PATH);
        } catch (Exception e) {
            log.error("Failed to save users: {}", e.getMessage());
        }

        // Final state
        log.info("=== Final Test Results ===");
        log.info("Final user count: {}", service.getAll().size());
        log.info("All tests completed successfully!");

        log.info("=== Resetting User Database ===");

        // Uncomment if you need to reset user database
        /*
        // Clear existing users
        service.users.clear();
        log.info("Cleared all existing users.");

        // Create new users with properly hashed passwords
        String hoangPassword = service.passwordHasher.hash("123456".toCharArray());
        String johnPassword = service.passwordHasher.hash("abcdef".toCharArray());
        String alicePassword = service.passwordHasher.hash("alicepass".toCharArray());

        // Add users with same IDs and data, but with hashed passwords
        service.users.add(new UserScore(1, "hoang", "hoang@example.com", hoangPassword, 175, LocalDateTime.now()));
        service.users.add(new UserScore(2, "john", "john@example.com", johnPassword, 200, LocalDateTime.now()));
        service.users.add(new UserScore(3, "Alice Johnson", "alice@gmail.com", alicePassword, 0, LocalDateTime.now()));

        // Save updated users to file
        service.saveUsersToFile();

        log.info("Created new users with hashed passwords:");
        service.users.forEach(user -> log.info("  - {} ({})", user.getUsername(), user.getEmail()));

        log.info("You can now log in with:");
        log.info("  Email: hoang@example.com");
        log.info("  Password: 123456");

        log.info("All users saved to: {}", service.JSON_FILE_PATH);
        */
    }
}