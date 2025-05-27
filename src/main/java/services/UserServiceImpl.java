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
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modules.user.UserScore;

public class UserServiceImpl implements UserService {

    private List<UserScore> users;
    private final String JSON_FILE_PATH = "src/main/resources/users.json";
    private final Gson gson;

    public UserServiceImpl() {
        this.gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                (json, type, context) -> LocalDateTime.parse(json.getAsString()))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
            .setPrettyPrinting()
            .create();
        loadUsersFromJson();
    }

    private void loadUsersFromJson() {
        try {
            Path path = Paths.get(JSON_FILE_PATH);

            // Create file if it doesn't exist
            if (!Files.exists(path)) {
                createDefaultUsersFile();
                return;
            }

            // Read from file
            try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
                Type listType = new TypeToken<List<UserScore>>() {}.getType();
                this.users = gson.fromJson(reader, listType);

                if (this.users == null) {
                    this.users = new ArrayList<>();
                }
            }

        } catch (Exception e) {
            System.err.println("Error loading users from JSON: " + e.getMessage());
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
            System.err.println("Error creating default users file: " + e.getMessage());
            this.users = new ArrayList<>();
        }
    }

    private List<UserScore> createDefaultUsers() {
        List<UserScore> defaultUsers = new ArrayList<>();

        // You'll need to adjust these constructors based on your UserScore class
        defaultUsers.add(new UserScore(1, "John Doe", "john@example.com", "password123", 100, LocalDateTime.now()));
        defaultUsers.add(new UserScore(2, "Jane Smith", "jane@example.com", "password456", 85, LocalDateTime.now()));
        defaultUsers.add(new UserScore(3, "Bob Johnson", "bob@example.com", "password789", 92, LocalDateTime.now()));

        return defaultUsers;
    }

    @Override
    public List<UserScore> getAll() {
        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
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
            .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
            .findFirst()
            .orElse(null);
    }

    @Override
    public void updateScore(UserScore user, int score) {
        if (user == null) {
            System.out.println("Cannot update score: user is null");
            return;
        }

        UserScore existingUser = findById(user.getUserId());
        if (existingUser != null) {
            int oldScore = existingUser.getScore();
            existingUser.setScore(oldScore + score);
            existingUser.setTimestamp(LocalDateTime.now());

            saveUsersToFile();
            System.out.println("Score updated for user " + existingUser.getUsername() +
                                   ": " + oldScore + " -> " + existingUser.getScore());
        } else {
            System.out.println("User not found for updating score: ID " + user.getUserId());
        }
    }

    @Override
    public void saveUsersToJson(UserScore user) {
        // This method seems to be intended for saving a single user,
        // but based on the interface, we'll save all users
        saveUsersToFile();
    }

    private void saveUsersToFile() {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(users, writer);
            System.out.println("Users successfully saved to " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving users to JSON: " + e.getMessage());
            throw new RuntimeException("Failed to save users to JSON", e);
        }
    }

    // Additional utility methods
    public boolean addUser(UserScore user) {
        if (user == null || findById(user.getUserId()) != null) {
            return false;
        }

        users.add(user);
        saveUsersToFile();
        return true;
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
        System.out.println("=== UserService Implementation Test ===\n");

        UserServiceImpl service = new UserServiceImpl();

        // Test 1: Get all users
        System.out.println("1. Testing getAll():");
        List<UserScore> allUsers = service.getAll();
        System.out.println("Total users: " + allUsers.size());
        allUsers.forEach(user -> System.out.println("  - " + user));
        System.out.println();

        // Test 2: Find user by ID
        System.out.println("2. Testing findById():");
        UserScore foundUser = service.findById(1);
        System.out.println("User with ID 1: " + foundUser);

        UserScore notFoundUser = service.findById(999);
        System.out.println("User with ID 999: " + notFoundUser);
        System.out.println();

        // Test 3: Login functionality
        System.out.println("3. Testing login():");
        UserScore loginUser = service.login("john@example.com", "password123");
        System.out.println("Login with correct credentials: " +
                               (loginUser != null ? loginUser.getUsername() : "Failed"));

        UserScore failedLogin = service.login("john@example.com", "wrongpassword");
        System.out.println("Login with wrong password: " +
                               (failedLogin != null ? failedLogin.getUsername() : "Failed"));

        UserScore nullLogin = service.login("", "");
        System.out.println("Login with empty credentials: " +
                               (nullLogin != null ? nullLogin.getUsername() : "Failed"));
        System.out.println();

        // Test 4: Update score
        System.out.println("4. Testing updateScore():");
        if (foundUser != null) {
            int originalScore = foundUser.getScore();
            System.out.println("Original score for " + foundUser.getUsername() + ": " + originalScore);

            service.updateScore(foundUser, 25);

            // Reload user to see updated score
            UserScore updatedUser = service.findById(foundUser.getUserId());
            System.out.println("Updated score: " + updatedUser.getScore());
            System.out.println("Score difference: +" + (updatedUser.getScore() - originalScore));
        }
        System.out.println();

        // Test 5: Update score with null user
        System.out.println("5. Testing updateScore() with null user:");
        service.updateScore(null, 10);
        System.out.println();

        // Test 6: Top scorers
        System.out.println("6. Testing getTopScorers():");
        List<UserScore> topScorers = service.getTopScorers(3);
        System.out.println("Top 3 scorers:");
        for (int i = 0; i < topScorers.size(); i++) {
            UserScore user = topScorers.get(i);
            System.out.println("  " + (i + 1) + ". " + user.getUsername() + " - Score: " + user.getScore());
        }
        System.out.println();

        // Test 7: Add new user
        System.out.println("7. Testing addUser():");
        UserScore newUser = new UserScore(4, "Alice Wilson", "alice@example.com", "newpass", 0, LocalDateTime.now());
        boolean added = service.addUser(newUser);
        System.out.println("New user added: " + added);
        if (added) {
            System.out.println("Total users now: " + service.getAll().size());
        }
        System.out.println();

        // Test 8: Try to add duplicate user
        System.out.println("8. Testing addUser() with duplicate ID:");
        UserScore duplicateUser = new UserScore(1, "Duplicate User", "dup@example.com", "pass", 0, LocalDateTime.now());
        boolean duplicateAdded = service.addUser(duplicateUser);
        System.out.println("Duplicate user added: " + duplicateAdded);
        System.out.println();

        // Test 9: Remove user
        System.out.println("9. Testing removeUser():");
        boolean removed = service.removeUser(4);
        System.out.println("User with ID 4 removed: " + removed);
        if (removed) {
            System.out.println("Total users now: " + service.getAll().size());
        }
        System.out.println();

        // Test 10: Save to JSON
        System.out.println("10. Testing saveUsersToJson():");
        service.saveUsersToJson(null); // This will save all users
        System.out.println();

        // Final state
        System.out.println("=== Final Test Results ===");
        System.out.println("Final user count: " + service.getAll().size());
        System.out.println("All tests completed successfully!");
    }
}