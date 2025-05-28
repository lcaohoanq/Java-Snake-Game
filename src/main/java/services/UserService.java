package services;

import java.util.List;
import models.UserScore;

public interface UserService {

    List<UserScore> getAll();
    UserScore findById(Integer userId);
    UserScore login(String email, String password);
    UserScore register(String username, String email, String password);
    void updateScore(UserScore user, int score);
}
