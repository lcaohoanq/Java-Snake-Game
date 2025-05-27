package services;

import modules.user.UserScore;

public interface UserService {

    UserScore findById(Integer userId);
    UserScore login(String email, String password);

}
