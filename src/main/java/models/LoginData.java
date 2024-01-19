package models;

public interface LoginData {
    boolean isEmpty(String username, String password);
    boolean isMatching(String username, String password);
    void handleEmpty();
    void handleWrong();
    void handleSuccess();
}
