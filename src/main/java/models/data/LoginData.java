package models.data;

sealed public interface LoginData permits LoginController, RegisterData {
    boolean isEmpty(String username, String password);

    boolean isMatching(String username, String password);

    void handleEmpty();

    void handleWrong();

    void handleSuccess();
}
