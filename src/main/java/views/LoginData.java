package views;

sealed public interface LoginData permits RegisterData, LoginView {
    void handleEmpty();

    void handleNotMatchingPasswordAndConfirmPassword();

    void handleSuccess();
}
