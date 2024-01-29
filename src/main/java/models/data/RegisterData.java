package models.data;

import controllers.RegisterController;

public sealed interface RegisterData extends LoginData permits RegisterController {
    boolean isEmpty(String username, String password, String confirmPassword);

    boolean isDuplicateUsername(String username);

    void handleDuplicateUsername();
}
