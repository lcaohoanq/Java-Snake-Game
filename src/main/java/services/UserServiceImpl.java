package services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import modules.user.UserScore;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {

    private List<UserScore> users;

    public UserServiceImpl() {
        loadUsersFromJson();
    }

    private void loadUsersFromJson() {
        try (InputStreamReader reader = new InputStreamReader(
            getClass().getClassLoader().getResourceAsStream("users.json"))) {

            Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                    (json, type, context) -> LocalDateTime.parse(json.getAsString()))
                .create();

            Type listType = new TypeToken<List<UserScore>>() {}.getType();
            this.users = gson.fromJson(reader, listType);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load users.json");
        }
    }

    @Override
    public UserScore findById(Integer userId) {
        return users.stream()
            .filter(user -> user.getUserId().equals(userId))
            .findFirst()
            .orElse(null);
    }

    @Override
    public UserScore login(String email, String password) {
        return users.stream()
            .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
            .findFirst()
            .orElse(null);
    }

    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();

        System.out.println("Find user by ID:");
        System.out.println(service.findById(1));

        System.out.println("Login test:");
        System.out.println(service.login("john@example.com", "abcdef"));
    }
}
