package services;

import java.util.List;
import modules.user.UserScore;

public interface UserService {

    List<UserScore> getAll();
    UserScore findById(Integer userId);
    UserScore login(String email, String password);
    void updateScore(UserScore user, int score);
    void saveUsersToJson(UserScore user);
}
